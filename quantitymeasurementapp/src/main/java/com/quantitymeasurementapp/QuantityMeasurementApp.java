package com.quantitymeasurementapp;

import java.util.Objects;

public class QuantityMeasurementApp {
	
	public enum LengthUnit {

	    FEET(1.0),           // Base unit
	    INCH(1.0 / 12.0);    // 1 inch = 1/12 feet

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

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println(q1 + " and " + q2);
        System.out.println("Equal: " + q1.equals(q2));

        QuantityLength q3 = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength q4 = new QuantityLength(1.0, LengthUnit.INCH);

        System.out.println(q3 + " and " + q4);
        System.out.println("Equal: " + q3.equals(q4));
    }
}
