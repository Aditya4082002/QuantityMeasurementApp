package com.quantitymeasurementapp;

public enum WeightUnit {

    KILOGRAM(1.0),       // base unit
    GRAM(0.001),         // 1 g = 0.001 kg
    POUND(0.453592);     // 1 lb = 0.453592 kg

    private final double conversionFactorToKilogram;

    WeightUnit(double conversionFactorToKilogram) {
        this.conversionFactorToKilogram = conversionFactorToKilogram;
    }

    public double getConversionFactor() {
        return conversionFactorToKilogram;
    }

    // convert value to base unit (kg)
    public double convertToBaseUnit(double value) {
        return value * conversionFactorToKilogram;
    }

    // convert from base unit (kg) to this unit
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactorToKilogram;
    }
}