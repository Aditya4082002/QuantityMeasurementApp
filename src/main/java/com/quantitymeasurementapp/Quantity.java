package com.quantitymeasurementapp;

import java.util.Objects;

public class Quantity<U extends Measurable> {

	private final double value;
	private final U unit;

	public Quantity(double value, U unit) {

		if (unit == null) {
			throw new IllegalArgumentException("Unit cannot be null");
		}
		if (!Double.isFinite(value)) {
			throw new IllegalArgumentException("Value must be finite");
		}
		this.value = value;
		this.unit = unit;
	}

	public double getValue() {
		return value;
	}

	public U getUnit() {
		return unit;
	}

	private double convertToBase() {
		return unit.convertToBaseUnit(value);
	}

	// Equality

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null || getClass() != obj.getClass())
			return false;

		Quantity<?> other = (Quantity<?>) obj;

		// prevent cross-category comparison
		if (!this.unit.getClass().equals(other.unit.getClass()))
			return false;

		return Double.compare(this.convertToBase(), other.unit.convertToBaseUnit(other.value)) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(convertToBase());
	}

	// Conversion

	public Quantity<U> convertTo(U targetUnit) {

		if (targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}
		double baseValue = convertToBase();
		double converted = targetUnit.convertFromBaseUnit(baseValue);

		double rounded = Math.round(converted * 100.0) / 100.0;

		return new Quantity<>(rounded, targetUnit);
	}

	// Addition

	public Quantity<U> add(Quantity<U> other) {

		if (other == null) {
			throw new IllegalArgumentException("Other quantity cannot be null");
		}
		double totalBase = this.convertToBase() + other.unit.convertToBaseUnit(other.value);

		double converted = unit.convertFromBaseUnit(totalBase);

		double rounded = Math.round(converted * 100.0) / 100.0;

		return new Quantity<>(rounded, unit);
	}

	public Quantity<U> add(Quantity<U> other, U targetUnit) {

		if (other == null) {
			throw new IllegalArgumentException("Other quantity cannot be null");
		}
		if (targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}
		double totalBase = this.convertToBase() + other.unit.convertToBaseUnit(other.value);

		double converted = targetUnit.convertFromBaseUnit(totalBase);

		double rounded = Math.round(converted * 100.0) / 100.0;

		return new Quantity<>(rounded, targetUnit);
	}

	public Quantity<U> subtract(Quantity<U> other) {
		if (other == null) {
			throw new IllegalArgumentException("Other quantity cannot be null");
		}

		// prevent cross-category
		if (!this.unit.getClass().equals(other.unit.getClass())) {
			throw new IllegalArgumentException("Different measurement categories.");
		}

		double result = this.convertToBase() - other.unit.convertToBaseUnit(other.value);

		double converted = unit.convertFromBaseUnit(result);

		double rounded = Math.round(converted * 100.0) / 100.0;
		return new Quantity<>(rounded, unit);
	}

	public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
		if (other == null) {
			throw new IllegalArgumentException("Other quantity cannot be null");
		}
		if (targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}

		// prevent cross-category
		if (!this.unit.getClass().equals(other.unit.getClass())) {
			throw new IllegalArgumentException("Different measurement categories.");
		}

		double result = this.convertToBase() - other.unit.convertToBaseUnit(other.value);

		double converted = targetUnit.convertFromBaseUnit(result);

		double rounded = Math.round(converted * 100.0) / 100.0;
		return new Quantity<>(rounded, targetUnit);
	}
	
	public Quantity<U> divide(Quantity<U> other){
		if (other == null) {
			throw new IllegalArgumentException("Other quantity cannot be null");
		}

		// prevent cross-category
		if (!this.unit.getClass().equals(other.unit.getClass())) {
			throw new IllegalArgumentException("Different measurement categories.");
		}
		
		if(other.value==0) {
			throw new ArithmeticException("Divide by zero is not allowed.");
		}

		double result = this.convertToBase() / other.unit.convertToBaseUnit(other.value);

		double converted = unit.convertFromBaseUnit(result);

		double rounded = Math.round(converted * 100.0) / 100.0;
		return new Quantity<>(rounded, unit);
	}

	@Override
	public String toString() {
		return "Quantity(" + value + ", " + unit.getUnitName() + ")";
	}
}