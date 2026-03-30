package com.app.quantitymeasurement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.quantitymeasurement.core.LengthUnit;
import com.app.quantitymeasurement.core.Quantity;
import com.app.quantitymeasurement.core.TemperatureUnit;
import com.app.quantitymeasurement.core.VolumeUnit;
import com.app.quantitymeasurement.core.WeightUnit;
import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementEntity;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

	@Autowired
	private QuantityMeasurementRepository repository;

	// helper: convert DTO → Quantity
	private Quantity<?> toQuantity(QuantityDTO dto) {

		switch (dto.getMeasurementType()) {

		case "LENGTH":
			return new Quantity<>(dto.getValue(), LengthUnit.valueOf(dto.getUnit()));

		case "WEIGHT":
			return new Quantity<>(dto.getValue(), WeightUnit.valueOf(dto.getUnit()));

		case "VOLUME":
			return new Quantity<>(dto.getValue(), VolumeUnit.valueOf(dto.getUnit()));

		case "TEMPERATURE":
			return new Quantity<>(dto.getValue(), TemperatureUnit.valueOf(dto.getUnit()));

		default:
			throw new IllegalArgumentException("Invalid measurement type");
		}
	}

	// helper: save result
	private QuantityMeasurementDTO save(String operation, String op1, String op2, String result) {

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity(operation, op1, op2, result);

		entity = repository.save(entity);

		return QuantityMeasurementDTO.fromEntity(entity);
	}

	// COMPARE
	@Override
	public QuantityMeasurementDTO compare(QuantityInputDTO input) {

		Quantity<?> q1 = toQuantity(input.getQuantity1());
		Quantity<?> q2 = toQuantity(input.getQuantity2());

		boolean res = q1.equals(q2);

		return save("COMPARE", q1.toString(), q2.toString(), String.valueOf(res));

	}

	// ADD
	@Override
	public QuantityMeasurementDTO add(QuantityInputDTO input) {

		Quantity q1 = toQuantity(input.getQuantity1());
		Quantity q2 = toQuantity(input.getQuantity2());

		Quantity result = q1.add(q2);

		return save("ADD", q1.toString(), q2.toString(), result.toString());

	}

	// SUBTRACT
	@Override
	public QuantityMeasurementDTO subtract(QuantityInputDTO input) {

		Quantity q1 = toQuantity(input.getQuantity1());
		Quantity q2 = toQuantity(input.getQuantity2());

		Quantity result = q1.subtract(q2);

		return save("SUBTRACT", q1.toString(), q2.toString(), result.toString());

	}

	// DIVIDE
	@Override
	public QuantityMeasurementDTO divide(QuantityInputDTO input) {

		Quantity q1 = toQuantity(input.getQuantity1());
		Quantity q2 = toQuantity(input.getQuantity2());

		Quantity result = q1.divide(q2);

		return save("DIVIDE", q1.toString(), q2.toString(), result.toString());

	}

	// CONVERT
	@Override
	public QuantityMeasurementDTO convert(QuantityInputDTO input) {

		Quantity q1 = toQuantity(input.getQuantity1());

//			String target = input.getTargetUnit();

		Quantity result = q1.convertTo(q1.getUnit().getClass().getEnumConstants()[0]);

		return save("CONVERT", q1.toString(), "-", result.toString());

	}

	// HISTORY
	@Override
	public List<QuantityMeasurementDTO> getHistoryByOperation(String operation) {

		return repository.findByOperation(operation).stream().map(QuantityMeasurementDTO::fromEntity)
				.collect(Collectors.toList());
	}

	// COUNT
	@Override
	public long countByOperation(String operation) {
		return repository.countByOperation(operation);
	}

	// ERROR HANDLING
//	private QuantityMeasurementDTO errorResponse(String operation, Exception e) {
//
//		QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
//		dto.setOperation(operation);
//		dto.setSuccess(false);
//		dto.setErrorMessage(e.getMessage());
//
//		return dto;
//	}
}
