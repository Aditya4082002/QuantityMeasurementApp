package com.quantitymeasurementapp;

import java.util.Objects;

public class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {

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

    public LengthUnit getUnit() {
        return unit;
    }

    private double convertToBase() {
        return unit.convertToBaseUnit(value);
    }

    public QuantityLength convertTo(LengthUnit target) {

        if (target == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = unit.convertToBaseUnit(value);

        double converted = target.convertFromBaseUnit(baseValue);

        return new QuantityLength(converted, target);
    }

    public QuantityLength add(QuantityLength other) {

        if (other == null)
            throw new IllegalArgumentException("Second operand cannot be null");

        double base1 = this.convertToBase();
        double base2 = other.convertToBase();

        double totalBase = base1 + base2;

        double converted = unit.convertFromBaseUnit(totalBase);

        double rounded = Math.round(converted * 1000) / 1000.0;

        return new QuantityLength(rounded, unit);
    }

    public QuantityLength add(QuantityLength other, LengthUnit target) {

        if (other == null)
            throw new IllegalArgumentException("Second operand cannot be null");

        if (target == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base1 = this.convertToBase();
        double base2 = other.convertToBase();

        double totalBase = base1 + base2;

        double converted = target.convertFromBaseUnit(totalBase);

        double rounded = Math.round(converted * 1000) / 1000.0;

        return new QuantityLength(rounded, target);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        QuantityLength other = (QuantityLength) obj;

        return Double.compare(
                this.convertToBase(),
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