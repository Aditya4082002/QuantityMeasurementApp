package com.quantitymeasurementapp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.quantitymeasurementapp.LengthUnit;
import com.quantitymeasurementapp.QuantityLength;

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

	@Test
	public void testConversion_FeetToInches() {
		double result = QuantityMeasurementApp.convert(1.0, LengthUnit.FEET, LengthUnit.INCH);

		assertEquals(12.0, result);
	}

	@Test
	public void testConversion_InchesToFeet() {
		double result = QuantityMeasurementApp.convert(24.0, LengthUnit.INCH, LengthUnit.FEET);

		assertEquals(2.0, result);
	}

	@Test
	public void testConversion_YardsToFeet() {
		double result = QuantityMeasurementApp.convert(3.0, LengthUnit.YARD, LengthUnit.FEET);

		assertEquals(9.0, result);
	}

	@Test
	public void testConversion_YardsToInches() {
		double result = QuantityMeasurementApp.convert(1.0, LengthUnit.YARD, LengthUnit.INCH);

		assertEquals(36.0, result);
	}

	@Test
	public void testConversion_InchesToYards() {
		double result = QuantityMeasurementApp.convert(72.0, LengthUnit.INCH, LengthUnit.YARD);

		assertEquals(2.0, result);
	}

	// Same Unit

	@Test
	public void testConversion_SameUnit() {
		double result = QuantityMeasurementApp.convert(5.0, LengthUnit.FEET, LengthUnit.FEET);

		assertEquals(5.0, result);
	}

	// Zero & Negative

	@Test
	public void testConversion_ZeroValue() {
		double result = QuantityMeasurementApp.convert(0.0, LengthUnit.FEET, LengthUnit.INCH);

		assertEquals(0.0, result);
	}

	@Test
	public void testConversion_NegativeValue() {
		double result = QuantityMeasurementApp.convert(-1.0, LengthUnit.FEET, LengthUnit.INCH);

		assertEquals(-12.0, result);
	}

	// Round Trip

	@Test
	public void testConversion_RoundTrip_PreservesValue() {

		double original = 10.0;

		double converted = QuantityMeasurementApp.convert(original, LengthUnit.FEET, LengthUnit.INCH);

		double back = QuantityMeasurementApp.convert(converted, LengthUnit.INCH, LengthUnit.FEET);

		assertEquals(original, back);
	}

	// Invalid Unit Handling

	@Test
	public void testConversion_NullSourceUnit_Throws() {
		assertThrows(IllegalArgumentException.class, () -> QuantityMeasurementApp.convert(1.0, null, LengthUnit.FEET));
	}

	@Test
	public void testConversion_NullTargetUnit_Throws() {
		assertThrows(IllegalArgumentException.class, () -> QuantityMeasurementApp.convert(1.0, LengthUnit.FEET, null));
	}

	// Invalid Value Handling

	@Test
	public void testConversion_NaN_Throws() {
		assertThrows(IllegalArgumentException.class,
				() -> QuantityMeasurementApp.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCH));
	}

	@Test
	public void testConversion_Infinite_Throws() {
		assertThrows(IllegalArgumentException.class,
				() -> QuantityMeasurementApp.convert(Double.POSITIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCH));
	}

	// Large & Small Values

	@Test
	public void testConversion_LargeValue() {
		double result = QuantityMeasurementApp.convert(1000000.0, LengthUnit.FEET, LengthUnit.INCH);

		assertEquals(12000000.0, result);
	}

	@Test
	public void testConversion_SmallValue() {
		double result = QuantityMeasurementApp.convert(0.5, LengthUnit.FEET, LengthUnit.INCH);

		assertEquals(6.0, result);
	}

	@Test
	public void testAddition_SameUnit_FeetPlusFeet() {
		QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
		QuantityLength l2 = new QuantityLength(2.0, LengthUnit.FEET);

		QuantityLength result = l1.add(l2);
		assertEquals(new QuantityLength(3.0, LengthUnit.FEET), result);
	}

	// addition static method
	@Test
	public void testAddition_CrossUnit_FeetPlusInches() {
		QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
		QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCH);

		QuantityLength result = l1.add(l2);

		assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
	}

	@Test
	public void testAddition_with_target() {
		QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
		QuantityLength l2 = new QuantityLength(3.0, LengthUnit.YARD);

		QuantityLength result = l1.add(l2, LengthUnit.INCH);

		assertEquals(new QuantityLength(120.0, LengthUnit.INCH), result);
	}
	// weight tests

	@Test
	public void testEquality_KilogramToKilogram_SameValue() {
		QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight w2 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

		assertTrue(w1.equals(w2));
	}

	@Test
	public void testEquality_KilogramToKilogram_DifferentValue() {
		QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight w2 = new QuantityWeight(2.0, WeightUnit.KILOGRAM);

		assertFalse(w1.equals(w2));
	}

	@Test
	public void testEquality_KilogramToGram_EquivalentValue() {
		QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight g = new QuantityWeight(1000.0, WeightUnit.GRAM);

		assertTrue(kg.equals(g));
	}

	@Test
	public void testEquality_GramToKilogram_EquivalentValue() {
		QuantityWeight g = new QuantityWeight(1000.0, WeightUnit.GRAM);
		QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

		assertTrue(g.equals(kg));
	}

	@Test
	public void testEquality_KilogramToPound_EquivalentValue() {
		QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight lb = new QuantityWeight(2.2046244201837775, WeightUnit.POUND);

		assertTrue(kg.equals(lb));
	}

	@Test
	public void testEquality_PoundToGram_EquivalentValue() {
		QuantityWeight lb = new QuantityWeight(1.0, WeightUnit.POUND);
		QuantityWeight g = new QuantityWeight(453.592, WeightUnit.GRAM);

		assertTrue(lb.equals(g));
	}

	@Test
	public void testEquality_WeightVsLength_Incompatible() {
		QuantityWeight weight = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityLength length = new QuantityLength(1.0, LengthUnit.FEET);

		assertFalse(weight.equals(length));
	}

	@Test
	public void testEquality_NullComparison() {
		QuantityWeight w = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		assertFalse(w.equals(null));
	}

	@Test
	public void testEquality_SameReference() {
		QuantityWeight w = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		assertTrue(w.equals(w));
	}

	@Test
	public void testEquality_ZeroValue() {
		QuantityWeight kg = new QuantityWeight(0.0, WeightUnit.KILOGRAM);
		QuantityWeight g = new QuantityWeight(0.0, WeightUnit.GRAM);

		assertTrue(kg.equals(g));
	}

	@Test
	public void testEquality_NegativeWeight() {
		QuantityWeight kg = new QuantityWeight(-1.0, WeightUnit.KILOGRAM);
		QuantityWeight g = new QuantityWeight(-1000.0, WeightUnit.GRAM);

		assertTrue(kg.equals(g));
	}

	@Test
	public void testEquality_LargeWeightValue() {
		QuantityWeight g = new QuantityWeight(1000000.0, WeightUnit.GRAM);
		QuantityWeight kg = new QuantityWeight(1000.0, WeightUnit.KILOGRAM);

		assertTrue(g.equals(kg));
	}

	@Test
	public void testEquality_SmallWeightValue() {
		QuantityWeight kg = new QuantityWeight(0.001, WeightUnit.KILOGRAM);
		QuantityWeight g = new QuantityWeight(1.0, WeightUnit.GRAM);

		assertTrue(kg.equals(g));
	}

	@Test
	public void testConversion_KilogramToGram() {
		QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight result = kg.convertTo(WeightUnit.GRAM);

		assertEquals(1000.0, result.getValue());
	}

	@Test
	public void testConversion_PoundToKilogram() {
		QuantityWeight lb = new QuantityWeight(2.20462, WeightUnit.POUND);
		QuantityWeight result = lb.convertTo(WeightUnit.KILOGRAM);

		assertEquals(1.0, result.getValue(), 0.01);
	}

	@Test
	public void testConversion_KilogramToPound() {
		QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight result = kg.convertTo(WeightUnit.POUND);

		assertEquals(2.20462, result.getValue(), 0.01);
	}

	@Test
	public void testConversion_SameUnitWeight() {
		QuantityWeight kg = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
		QuantityWeight result = kg.convertTo(WeightUnit.KILOGRAM);

		assertEquals(5.0, result.getValue());
	}

	@Test
	public void testConversion_ZeroValueWeight() {
		QuantityWeight kg = new QuantityWeight(0.0, WeightUnit.KILOGRAM);
		QuantityWeight result = kg.convertTo(WeightUnit.GRAM);

		assertEquals(0.0, result.getValue());
	}

	@Test
	public void testConversion_NegativeValueWeight() {
		QuantityWeight kg = new QuantityWeight(-1.0, WeightUnit.KILOGRAM);
		QuantityWeight result = kg.convertTo(WeightUnit.GRAM);

		assertEquals(-1000.0, result.getValue());
	}

	@Test
	public void testConversion_RoundTrip() {
		QuantityWeight original = new QuantityWeight(1.5, WeightUnit.KILOGRAM);

		QuantityWeight converted = original.convertTo(WeightUnit.GRAM);
		QuantityWeight back = converted.convertTo(WeightUnit.KILOGRAM);

		assertEquals(original.getValue(), back.getValue(), 0.01);
	}

	@Test
	public void testAddition_SameUnit_KilogramPlusKilogram() {
		QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight w2 = new QuantityWeight(2.0, WeightUnit.KILOGRAM);

		QuantityWeight result = w1.add(w2);

		assertEquals(new QuantityWeight(3.0, WeightUnit.KILOGRAM), result);
	}

	@Test
	public void testAddition_CrossUnit_KilogramPlusGram() {
		QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight g = new QuantityWeight(1000.0, WeightUnit.GRAM);

		QuantityWeight result = kg.add(g);

		assertEquals(new QuantityWeight(2.0, WeightUnit.KILOGRAM), result);
	}

	@Test
	public void testAddition_CrossUnit_PoundPlusKilogram() {
		QuantityWeight lb = new QuantityWeight(2.20462, WeightUnit.POUND);
		QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

		QuantityWeight result = lb.add(kg);

		assertEquals(4.40924, result.getValue(), 0.01);
	}

	@Test
	public void testAddition_ExplicitTargetUnit_Kilogram() {
		QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight g = new QuantityWeight(1000.0, WeightUnit.GRAM);

		QuantityWeight result = kg.add(g, WeightUnit.GRAM);

		assertEquals(2000.0, result.getValue());
	}

	@Test
	public void testAddition_Commutativity() {
		QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight b = new QuantityWeight(1000.0, WeightUnit.GRAM);

		QuantityWeight result1 = a.add(b);
		QuantityWeight result2 = b.add(a, WeightUnit.GRAM);

		assertTrue(result1.equals(result2));
	}

	@Test
	public void testAddition_WithZero() {
		QuantityWeight kg = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
		QuantityWeight zero = new QuantityWeight(0.0, WeightUnit.GRAM);

		QuantityWeight result = kg.add(zero);

		assertEquals(new QuantityWeight(5.0, WeightUnit.KILOGRAM), result);
	}

	@Test
	public void testAddition_NegativeValues() {
		QuantityWeight kg = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
		QuantityWeight negative = new QuantityWeight(-2000.0, WeightUnit.GRAM);

		QuantityWeight result = kg.add(negative);

		assertEquals(new QuantityWeight(3.0, WeightUnit.KILOGRAM), result);
	}

	@Test
	public void testAddition_LargeValues() {
		QuantityWeight w1 = new QuantityWeight(1e6, WeightUnit.KILOGRAM);
		QuantityWeight w2 = new QuantityWeight(1e6, WeightUnit.KILOGRAM);

		QuantityWeight result = w1.add(w2);

		assertEquals(new QuantityWeight(2e6, WeightUnit.KILOGRAM), result);
	}

	@Test
	public void testConstructor_NullUnit() {
		assertThrows(IllegalArgumentException.class, () -> {
			new QuantityWeight(1.0, null);
		});
	}

	@Test
	public void testConstructor_InvalidValue() {
		assertThrows(IllegalArgumentException.class, () -> {
			new QuantityWeight(Double.NaN, WeightUnit.KILOGRAM);
		});
	}



}
