package com.quantitymeasurementapp;

public class QuantityMeasurementApp {

	public static void main(String[] args) {

		// Length Example
		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCH);

		System.out.println(feet.equals(inches));

		System.out.println(feet.convertTo(LengthUnit.INCH));

		System.out.println(feet.add(inches, LengthUnit.FEET));

		// Weight Example
		Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		Quantity<WeightUnit> gram = new Quantity<>(1000.0, WeightUnit.GRAM);

		System.out.println(kg.equals(gram));

		System.out.println(kg.convertTo(WeightUnit.GRAM));

		System.out.println(kg.add(gram, WeightUnit.KILOGRAM));
	}
}