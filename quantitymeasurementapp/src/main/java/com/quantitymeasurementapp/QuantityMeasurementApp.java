package com.quantitymeasurementapp;

import java.util.Objects;

public class QuantityMeasurementApp {
	
	public enum LengthUnit {

	    FEET(1.0),           // Base unit
	    INCH(1.0 / 12.0),    // 1 inch = 1/12 feet
		YARD(3.0),                    // 1 yard = 3 feet
		CENTIMETER(0.0328084167);     // 1 cm = 0.0328084167 feet

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
	        return Double.compare(this.convertToBase(),
	                              other.convertToBase()) == 0;
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

	public static void main(String[] args) {

        // 1 Yard = 3 Feet
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength q2 = new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println(q1 + " and " + q2);
        System.out.println("Equal: " + q1.equals(q2));
        System.out.println("--------------------------------");

        // 1 Yard = 36 Inches
        QuantityLength q3 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength q4 = new QuantityLength(36.0, LengthUnit.INCH);

        System.out.println(q3 + " and " + q4);
        System.out.println("Equal: " + q3.equals(q4));
        System.out.println("--------------------------------");

        // 1 CM = 0.393701 Inches
        QuantityLength q5 = new QuantityLength(1.0, LengthUnit.CENTIMETER);
        QuantityLength q6 = new QuantityLength(0.393701, LengthUnit.INCH);

        System.out.println(q5 + " and " + q6);
        System.out.println("Equal: " + q5.equals(q6));
        System.out.println("--------------------------------");

        // Same unit equality
        QuantityLength q7 = new QuantityLength(2.0, LengthUnit.YARD);
        QuantityLength q8 = new QuantityLength(2.0, LengthUnit.YARD);

        System.out.println(q7 + " and " + q8);
        System.out.println("Equal: " + q7.equals(q8));
        System.out.println("--------------------------------");

        // Multi-unit complex scenario
        QuantityLength q9 = new QuantityLength(2.0, LengthUnit.YARD);
        QuantityLength q10 = new QuantityLength(6.0, LengthUnit.FEET);
        QuantityLength q11 = new QuantityLength(72.0, LengthUnit.INCH);

        System.out.println(q9 + ", " + q10 + ", " + q11);
        System.out.println("All Equal: " +
                (q9.equals(q10) && q10.equals(q11) && q9.equals(q11)));
        System.out.println("--------------------------------");

        // Non equal example
        QuantityLength q12 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength q13 = new QuantityLength(2.0, LengthUnit.FEET);

        System.out.println(q12 + " and " + q13);
        System.out.println("Equal: " + q12.equals(q13));
    }
}
