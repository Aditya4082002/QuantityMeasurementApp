package com.quantitymeasurementapp;

import java.util.Objects;

public class QuantityWeight {

    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
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

        QuantityWeight other = (QuantityWeight) obj;

        return Double.compare(
                this.convertToBase(),
                other.convertToBase()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(convertToBase());
    }

    // Conversion
    public QuantityWeight convertTo(WeightUnit target) {

        if (target == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base = convertToBase();
        double converted = target.convertFromBaseUnit(base);

        return new QuantityWeight(converted, target);
    }

    // Addition (default unit)
    public QuantityWeight add(QuantityWeight other) {

        if (other == null)
            throw new IllegalArgumentException("Second operand cannot be null");

        double base1 = this.convertToBase();
        double base2 = other.convertToBase();

        double total = base1 + base2;

        double converted = unit.convertFromBaseUnit(total);
        double rounded = Math.round(converted * 1000) / 1000.0;

        return new QuantityWeight(rounded, unit);
    }

    // Addition (target unit)
    public QuantityWeight add(QuantityWeight other, WeightUnit target) {

        if (other == null)
            throw new IllegalArgumentException("Second operand cannot be null");

        if (target == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base1 = this.convertToBase();
        double base2 = other.convertToBase();

        double total = base1 + base2;

        double converted = target.convertFromBaseUnit(total);
        double rounded = Math.round(converted * 1000) / 1000.0;

        return new QuantityWeight(rounded, target);
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}