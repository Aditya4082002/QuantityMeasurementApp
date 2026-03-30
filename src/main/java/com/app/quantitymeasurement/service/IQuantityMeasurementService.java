package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.dto.QuantityMeasurementDTO;

import java.util.List;

public interface IQuantityMeasurementService {
   
    QuantityMeasurementDTO compare(QuantityInputDTO input);

    QuantityMeasurementDTO add(QuantityInputDTO input);

    QuantityMeasurementDTO subtract(QuantityInputDTO input);

    QuantityMeasurementDTO divide(QuantityInputDTO input);

    QuantityMeasurementDTO convert(QuantityInputDTO input);

    List<QuantityMeasurementDTO> getHistoryByOperation(String operation);

    long countByOperation(String operation);
}