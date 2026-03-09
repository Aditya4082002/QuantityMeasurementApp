package com.quantitymeasurementapp;

public enum LengthUnit {
	FEET(1.0), // base unit
	INCH(1.0 / 12.0), // 1 inch = 1/12 feet
	YARD(3.0), // 1 yard = 3 feet
	CENTIMETER(0.0328084167); // 1 cm = 0.0328084167 feet

	private final double conversionFactorToFeet;

	LengthUnit(double conversionFactorToFeet) {
		this.conversionFactorToFeet = conversionFactorToFeet;
	}

	// Convert this unit to base unit (feet)
	public double convertToBaseUnit(double value) {
		return value * conversionFactorToFeet;
	}

	// Convert base unit (feet) to this unit
	public double convertFromBaseUnit(double baseValue) {
		return baseValue / conversionFactorToFeet;
	}

	public double getConversionFactor() {
		return conversionFactorToFeet;
	}
}
