package com.quantitymeasurementapp.model;

import com.quantitymeasurementapp.units.Measurable;

public class QuantityModel<U extends Measurable> {

	private double value;
	private U unit;

	public QuantityModel(double value, U unit) {
		this.value = value;
		this.unit = unit;
	}

	public double getValue() {
		return value;
	}

	public U getUnit() {
		return unit;
	}
}