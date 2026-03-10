package com.quantitymeasurementapp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

	// LENGTH EQUALITY

	@Test
	public void testEquality_YardToYard_SameValue() {

		Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.YARD);
		Quantity<LengthUnit> q2 = new Quantity<>(1.0, LengthUnit.YARD);

		assertTrue(q1.equals(q2));
	}

	@Test
	public void testEquality_YardToFeet_EquivalentValue() {

		Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARD);
		Quantity<LengthUnit> feet = new Quantity<>(3.0, LengthUnit.FEET);

		assertTrue(yard.equals(feet));
	}

	@Test
	public void testEquality_YardToInches_EquivalentValue() {

		Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARD);
		Quantity<LengthUnit> inch = new Quantity<>(36.0, LengthUnit.INCH);

		assertTrue(yard.equals(inch));
	}

	// WEIGHT EQUALITY
	@Test
	public void testEquality_KilogramToGram() {

		Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> g = new Quantity<>(1000.0, WeightUnit.GRAM);

		assertTrue(kg.equals(g));
	}

	@Test
	public void testEquality_PoundToGram() {

		Quantity<WeightUnit> lb = new Quantity<>(1.0, WeightUnit.POUND);
		Quantity<WeightUnit> g = new Quantity<>(453.592, WeightUnit.GRAM);

		assertTrue(lb.equals(g));
	}

	// VOLUME EQUALITY

	@Test
	public void testEquality_GallonToLiter() {
		Quantity<VolumeUnit> gal = new Quantity<>(1.0, VolumeUnit.GALLON);
		Quantity<VolumeUnit> l = new Quantity<>(3.78541, VolumeUnit.LITER);

		assertTrue(gal.equals(l));
	}

	@Test
	public void testEquality_LiterToMilliliter() {
		Quantity<VolumeUnit> l = new Quantity<>(1.0, VolumeUnit.LITER);
		Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITER);

		assertTrue(l.equals(ml));
	}

	// LENGTH CONVERSION

	@Test
	public void testConversion_FeetToInches() {

		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<LengthUnit> result = feet.convertTo(LengthUnit.INCH);

		assertEquals(12.0, result.getValue());
	}

	@Test
	public void testConversion_InchesToFeet() {

		Quantity<LengthUnit> inch = new Quantity<>(24.0, LengthUnit.INCH);

		Quantity<LengthUnit> result = inch.convertTo(LengthUnit.FEET);

		assertEquals(2.0, result.getValue());
	}

	@Test
	public void testConversion_YardToInch() {

		Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARD);

		Quantity<LengthUnit> result = yard.convertTo(LengthUnit.INCH);

		assertEquals(36.0, result.getValue());
	}

	// WEIGHT CONVERSION
	@Test
	public void testConversion_KilogramToGram() {

		Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		Quantity<WeightUnit> result = kg.convertTo(WeightUnit.GRAM);

		assertEquals(1000.0, result.getValue());
	}

	@Test
	public void testConversion_KilogramToPound() {

		Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		Quantity<WeightUnit> result = kg.convertTo(WeightUnit.POUND);

		assertEquals(2.20462, result.getValue(), 0.01);
	}

	// VOLUME CONVERSION
	@Test
	public void testConversion_GallonToLiter() {
		Quantity<VolumeUnit> gal = new Quantity<>(1.0, VolumeUnit.GALLON);

		Quantity<VolumeUnit> result = gal.convertTo(VolumeUnit.LITER);

		assertEquals(3.78541, result.getValue(), 0.01);
	}

	@Test
	public void testConversion_LiterToMilliliter() {
		Quantity<VolumeUnit> l = new Quantity<>(1.0, VolumeUnit.LITER);

		Quantity<VolumeUnit> result = l.convertTo(VolumeUnit.MILLILITER);

		assertEquals(1000.0, result.getValue());
	}

	// LENGTH ADDITION

	@Test
	public void testAddition_FeetPlusFeet() {

		Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> l2 = new Quantity<>(2.0, LengthUnit.FEET);

		Quantity<LengthUnit> result = l1.add(l2);

		assertEquals(new Quantity<>(3.0, LengthUnit.FEET), result);
	}

	@Test
	public void testAddition_FeetPlusInches() {

		Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCH);

		Quantity<LengthUnit> result = l1.add(l2);

		assertEquals(new Quantity<>(2.0, LengthUnit.FEET), result);
	}

	@Test
	public void testAddition_TargetUnit() {

		Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> l2 = new Quantity<>(3.0, LengthUnit.YARD);

		Quantity<LengthUnit> result = l1.add(l2, LengthUnit.INCH);

		assertEquals(new Quantity<>(120.0, LengthUnit.INCH), result);
	}

	// WEIGHT ADDITION

	@Test
	public void testAddition_KilogramPlusKilogram() {

		Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> w2 = new Quantity<>(2.0, WeightUnit.KILOGRAM);

		Quantity<WeightUnit> result = w1.add(w2);

		assertEquals(new Quantity<>(3.0, WeightUnit.KILOGRAM), result);
	}

	@Test
	public void testAddition_KilogramPlusGram() {

		Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> g = new Quantity<>(1000.0, WeightUnit.GRAM);

		Quantity<WeightUnit> result = kg.add(g);

		assertEquals(new Quantity<>(2.0, WeightUnit.KILOGRAM), result);
	}

	// VOLUME ADDITION
	@Test
	public void testAddition_LiterPlusMilliliter() {
		Quantity<VolumeUnit> l = new Quantity<>(1.0, VolumeUnit.LITER);
		Quantity<VolumeUnit> ml = new Quantity<>(5000.0, VolumeUnit.MILLILITER);

		Quantity<VolumeUnit> result = l.add(ml);

		assertEquals(new Quantity<>(6.0, VolumeUnit.LITER), result);
	}

	@Test
	public void testAddition_GallonPlusLiter() {
		Quantity<VolumeUnit> gal = new Quantity<>(1.0, VolumeUnit.GALLON);

		Quantity<VolumeUnit> l = new Quantity<>(3.78541, VolumeUnit.LITER);

		Quantity<VolumeUnit> result = gal.add(l);

		assertEquals(new Quantity<>(2.0, VolumeUnit.GALLON), result);

	}

	// SUBTRACTION TEST

	@Test
	public void testSubtraction_GallonMinusLiter() {
		Quantity<VolumeUnit> gal = new Quantity<>(1.0, VolumeUnit.GALLON);

		Quantity<VolumeUnit> l = new Quantity<>(3.78541, VolumeUnit.LITER);

		Quantity<VolumeUnit> result = gal.subtract(l);

		assertEquals(new Quantity<>(0, VolumeUnit.GALLON), result);
	}
	
	@Test
	public void testSubtraction_KilogramMinusGram() {
		Quantity<WeightUnit> kg = new Quantity<>(2.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> g = new Quantity<>(1000.0, WeightUnit.GRAM);

		Quantity<WeightUnit> result = kg.subtract(g);

		assertEquals(new Quantity<>(1, WeightUnit.KILOGRAM), result);
	}
	
	//DIVISION TEST
	
	@Test
	public void testDivision_KilogramDivideGram() {
		Quantity<WeightUnit> kg = new Quantity<>(4.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> g = new Quantity<>(2000.0, WeightUnit.GRAM);

		Quantity<WeightUnit> result = kg.divide(g);

		assertEquals(new Quantity<>(2, WeightUnit.KILOGRAM), result);
	}
	

	// CATEGORY SAFETY

	@Test
	public void testWeightVsLength_NotEqual() {

		Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);

		assertFalse(weight.equals(length));
	}

	// CONSTRUCTOR VALIDATION

	@Test
	public void testConstructor_NullUnit() {

		assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, null));
	}

	@Test
	public void testConstructor_InvalidValue() {

		assertThrows(IllegalArgumentException.class, () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
	}
}