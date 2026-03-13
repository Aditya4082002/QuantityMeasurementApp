package com.quantitymeasurementapp.service;

import com.quantitymeasurementapp.dto.QuantityDTO;
import com.quantitymeasurementapp.entity.QuantityMeasurementEntity;
import com.quantitymeasurementapp.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

	private IQuantityMeasurementRepository repository;

	public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {

		this.repository = repository;
	}

	@Override
	public boolean compare(QuantityDTO q1, QuantityDTO q2) {

		boolean result = q1.getValue() == q2.getValue();

		repository.save(new QuantityMeasurementEntity(q1.toString(), q2.toString(), "COMPARE", String.valueOf(result)));

		return result;
	}

	@Override
	public QuantityDTO convert(QuantityDTO source, String targetUnit) {

		// simplified example
		QuantityDTO result = new QuantityDTO(source.getValue(), targetUnit);

		repository.save(new QuantityMeasurementEntity(source.toString(), targetUnit, "CONVERT", result.toString()));

		return result;
	}

	@Override
	public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

		double sum = q1.getValue() + q2.getValue();

		QuantityDTO result = new QuantityDTO(sum, q1.getUnit());

		repository.save(new QuantityMeasurementEntity(q1.toString(), q2.toString(), "ADD", result.toString()));

		return result;
	}

	@Override
	public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {

		double res = q1.getValue() - q2.getValue();

		QuantityDTO result = new QuantityDTO(res, q1.getUnit());

		repository.save(new QuantityMeasurementEntity(q1.toString(), q2.toString(), "SUBTRACT", result.toString()));

		return result;
	}

	@Override
	public double divide(QuantityDTO q1, QuantityDTO q2) {

		double result = q1.getValue() / q2.getValue();

		repository.save(new QuantityMeasurementEntity(q1.toString(), q2.toString(), "DIVIDE", String.valueOf(result)));

		return result;
	}
}