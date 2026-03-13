package com.quantitymeasurementapp.controller;

import com.quantitymeasurementapp.dto.QuantityDTO;
import com.quantitymeasurementapp.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

	private IQuantityMeasurementService service;

	public QuantityMeasurementController(IQuantityMeasurementService service) {
		this.service = service;
	}

	public void performOperations() {

		QuantityDTO q1 = new QuantityDTO(2, "FEET");
		QuantityDTO q2 = new QuantityDTO(12, "INCH");

		System.out.println(service.compare(q1, q2));

		System.out.println(service.add(q1, q2));

		System.out.println(service.subtract(q1, q2));

		System.out.println(service.divide(q1, q2));
	}
}