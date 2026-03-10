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
		
		//Volume Example 
		Quantity<VolumeUnit> gal = new Quantity<>(1.0,VolumeUnit.GALLON);
		
		Quantity<VolumeUnit> l = new Quantity<>(5.6,VolumeUnit.LITER);
		
		System.out.println(gal.equals(l));
		
		System.out.println(gal.convertTo(VolumeUnit.MILLILITER));
		
		System.out.println(gal.add(l,VolumeUnit.MILLILITER));
	}
}