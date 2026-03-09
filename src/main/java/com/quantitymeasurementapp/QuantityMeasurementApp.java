package com.quantitymeasurementapp;

public class QuantityMeasurementApp {

	public static double convert(double value, LengthUnit source, LengthUnit target) {

		if (!Double.isFinite(value))
			throw new IllegalArgumentException("Value must be finite.");

		if (source == null || target == null)
			throw new IllegalArgumentException("Units cannot be null.");

		double baseValue = source.convertToBaseUnit(value);

		return target.convertFromBaseUnit(baseValue);
	}

	public static QuantityLength add(QuantityLength l1, QuantityLength l2) {

		if (l1 == null || l2 == null)
			throw new IllegalArgumentException("Operand cannot be null");

		return l1.add(l2);
	}

	public static QuantityLength add(QuantityLength l1, QuantityLength l2, LengthUnit targetUnit) {

		if (l1 == null || l2 == null)
			throw new IllegalArgumentException("Operands cannot be null.");

		return l1.add(l2, targetUnit);
	}

	public static void main(String[] args) {

		QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
		QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCH);

		System.out.println(l1.convertTo(LengthUnit.INCH));

		System.out.println(l1.add(l2, LengthUnit.FEET));

		QuantityLength l3 = new QuantityLength(36.0, LengthUnit.INCH);
		QuantityLength l4 = new QuantityLength(1.0, LengthUnit.YARD);

		System.out.println(l3.equals(l4));

		QuantityLength l5 = new QuantityLength(2.54, LengthUnit.CENTIMETER);

		System.out.println(l5.convertTo(LengthUnit.INCH));
		
		QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

		System.out.println(w1.equals(w2)); 

		System.out.println(w1.convertTo(WeightUnit.POUND));

		System.out.println(w1.add(w2));

		System.out.println(w1.add(w2, WeightUnit.GRAM));
	}
}