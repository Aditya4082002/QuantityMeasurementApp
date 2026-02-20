package com.quantitymeasurementapp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.quantitymeasurementapp.QuantityMeasurementApp.LengthUnit;
import com.quantitymeasurementapp.QuantityMeasurementApp.QuantityLength;

public class QuantityMeasurementAppTest {

    // Yard to Yard
    @Test
    public void testEquality_YardToYard_SameValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.YARD);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_YardToYard_DifferentValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.YARD);

        assertFalse(q1.equals(q2));
    }

    // Yard to Feet
    @Test
    public void testEquality_YardToFeet_EquivalentValue() {
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);

        assertTrue(yard.equals(feet));
    }

    @Test
    public void testEquality_FeetToYard_EquivalentValue() {
        QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARD);

        assertTrue(feet.equals(yard));
    }

    // Yard to Inches
    @Test
    public void testEquality_YardToInches_EquivalentValue() {
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength inches = new QuantityLength(36.0, LengthUnit.INCH);

        assertTrue(yard.equals(inches));
    }

    // Non Equivalent
    @Test
    public void testEquality_YardToFeet_NonEquivalentValue() {
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength feet = new QuantityLength(2.0, LengthUnit.FEET);

        assertFalse(yard.equals(feet));
    }

    // CM to CM
    @Test
    public void testEquality_CentimeterToCentimeter_SameValue() {
        QuantityLength cm1 = new QuantityLength(2.0, LengthUnit.CENTIMETER);
        QuantityLength cm2 = new QuantityLength(2.0, LengthUnit.CENTIMETER);

        assertTrue(cm1.equals(cm2));
    }

    // CM to Inches
    @Test
    public void testEquality_CentimeterToInches_EquivalentValue() {
        QuantityLength cm = new QuantityLength(1.0, LengthUnit.CENTIMETER);
        QuantityLength inch = new QuantityLength(0.393700, LengthUnit.INCH);

        assertFalse(cm.equals(inch));
    }

    // CM to Feet Non Equivalent
    @Test
    public void testEquality_CentimeterToFeet_NonEquivalentValue() {
        QuantityLength cm = new QuantityLength(1.0, LengthUnit.CENTIMETER);
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);

        assertFalse(cm.equals(feet));
    }

    // Transitive Property
    @Test
    public void testEquality_MultiUnit_TransitiveProperty() {
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength inch = new QuantityLength(36.0, LengthUnit.INCH);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inch));
        assertTrue(yard.equals(inch));
    }

    // Reflexive
    @Test
    public void testEquality_YardSameReference() {
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARD);

        assertTrue(yard.equals(yard));
    }

    // Null Comparison
    @Test
    public void testEquality_YardNullComparison() {
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARD);

        assertFalse(yard.equals(null));
    }

    // Null Unit Validation
    @Test
    public void testEquality_YardWithNullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityLength(1.0, null);
        });
    }

    // Complex Scenario
    @Test
    public void testEquality_AllUnits_ComplexScenario() {
        QuantityLength yard = new QuantityLength(2.0, LengthUnit.YARD);
        QuantityLength feet = new QuantityLength(6.0, LengthUnit.FEET);
        QuantityLength inch = new QuantityLength(72.0, LengthUnit.INCH);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inch));
        assertTrue(yard.equals(inch));
    }
}
