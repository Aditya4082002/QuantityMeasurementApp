package com.quantitymeasurementapp;

import java.util.Objects;

public class QuantityMeasurementApp {

	public enum LengthUnit {

		FEET(1.0), // Base unit
		INCH(1.0 / 12.0), // 1 inch = 1/12 feet
		YARD(3.0), // 1 yard = 3 feet
		CENTIMETER(0.0328084167);// 1 cm = 0.0328084167 feet

		private final double conversionFactorToFeet;

		LengthUnit(double conversionFactorToFeet) {
			this.conversionFactorToFeet = conversionFactorToFeet;
		}

		public double toFeet(double value) {
			return value * conversionFactorToFeet;
		}
	}

	public static class QuantityLength {

		private final double value;
		private final LengthUnit unit;

		public QuantityLength(double value, LengthUnit unit) {

			if (unit == null) {
				throw new IllegalArgumentException("Unit cannot be null");
			}

			this.value = value;
			this.unit = unit;
		}
		
		public QuantityLength add(QuantityLength other) {
			if(other == null) {
				throw new IllegalArgumentException("Second operand can not be null.");
			}
			
			double value1 = this.convertToBase();
			double value2 = other.convertToBase();
			
			double total = value1 + value2;
			
			double converted =  convert(total,LengthUnit.FEET,this.unit);
			double rounded = Math.round(converted*100)/100.0;
			return new QuantityLength(rounded, this.unit);
		}

		public double getValue() {
			return value;
		}

		public LengthUnit getUnit() {
			return unit;
		}

		private double convertToBase() {
			return unit.toFeet(value);
		}

		@Override
		public boolean equals(Object obj) {

			// Reflexive
			if (this == obj)
				return true;

			// Null check
			if (obj == null)
				return false;

			// Type check
			if (getClass() != obj.getClass())
				return false;

			QuantityLength other = (QuantityLength) obj;

			// Compare after converting to base unit (feet)
			return Double.compare(this.convertToBase(), other.convertToBase()) == 0;
		}

		@Override
		public int hashCode() {
			return Objects.hash(convertToBase());
		}

		@Override
		public String toString() {
			return "Quantity(" + value + ", " + unit + ")";
		}
	}
	
	public static QuantityLength add(QuantityLength value1,QuantityLength value2) {
		if(value1 == null) {
			throw new IllegalArgumentException("First operand can not be null.");
		}
		return value1.add(value2);
	}

	//unit to unit conversion
	public static double convert(double value, LengthUnit source, LengthUnit target) {
		//finite value check
		if (!Double.isFinite(value))
			throw new IllegalArgumentException("Value must be finite");

		//null value check
		if (source == null || target == null)
			throw new IllegalArgumentException("Units cannot be null");
		
		//conversion
		double base = source.toFeet(value);
		return base / target.conversionFactorToFeet;
	}

	public static void demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to) {
		double result = convert(value, from, to);
		System.out.println(value + " " + from + " = " + result + " " + to);
	}

	public static void main(String[] args) {

//		demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCH);
//		demonstrateLengthConversion(3.0, LengthUnit.YARD, LengthUnit.FEET);
//		demonstrateLengthConversion(36.0, LengthUnit.INCH, LengthUnit.YARD);
//		demonstrateLengthConversion(1.0, LengthUnit.CENTIMETER, LengthUnit.INCH);
//		demonstrateLengthConversion(0.0, LengthUnit.FEET, LengthUnit.INCH);
		
		
		QuantityLength l1 = new QuantityLength(1.0,LengthUnit.FEET);
		QuantityLength l2 = new QuantityLength(3.0,LengthUnit.YARD);
		QuantityLength l3 = new QuantityLength(36.0,LengthUnit.INCH);
		QuantityLength l4 = new QuantityLength(1.0,LengthUnit.CENTIMETER);
		
		System.out.println(add(l1,l2));
		System.out.println(l3.add(l4));

	}
}
