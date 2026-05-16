package com.app.quantitymeasurement.unit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UC8_RefactoringTest {

    // --- ENUM TESTS ---
    @Test
    void testLengthUnitEnum_FeetConstant() {
        assertEquals(1.0, LengthUnit.FEET.getConversionFactor());
    }

    @Test
    void testLengthUnitEnum_InchesConstant() {
        assertEquals(1.0 / 12.0, LengthUnit.INCHES.getConversionFactor(), 0.0001);
    }

    @Test
    void testLengthUnitEnum_YardsConstant() {
        assertEquals(3.0, LengthUnit.YARDS.getConversionFactor());
    }

    @Test
    void testLengthUnitEnum_CentimetersConstant() {
        assertEquals(1.0 / 30.48, LengthUnit.CENTIMETERS.getConversionFactor(), 0.0001);
    }

    // --- BASE UNIT CONVERSION TESTS ---
    @Test
    void testConvertToBaseUnit_FeetToFeet() {
        assertEquals(5.0, LengthUnit.FEET.convertToBaseUnit(5.0));
    }

    @Test
    void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0, LengthUnit.INCHES.convertToBaseUnit(12.0));
    }

    @Test
    void testConvertToBaseUnit_YardsToFeet() {
        assertEquals(3.0, LengthUnit.YARDS.convertToBaseUnit(1.0));
    }

    @Test
    void testConvertToBaseUnit_CentimetersToFeet() {
        assertEquals(1.0, LengthUnit.CENTIMETERS.convertToBaseUnit(30.48), 0.0001);
    }

    @Test
    void testConvertFromBaseUnit_FeetToFeet() {
        assertEquals(2.0, LengthUnit.FEET.convertFromBaseUnit(2.0));
    }

    @Test
    void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(1.0));
    }

    @Test
    void testConvertFromBaseUnit_FeetToYards() {
        assertEquals(1.0, LengthUnit.YARDS.convertFromBaseUnit(3.0));
    }

    @Test
    void testConvertFromBaseUnit_FeetToCentimeters() {
        assertEquals(30.48, LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0), 0.0001);
    }

    // --- QUANTITY REFACTORED TESTS ---
    @Test
    void testQuantityLengthRefactored_Equality() {
        assertEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(12.0, LengthUnit.INCHES));
    }

    @Test
    void testQuantityLengthRefactored_ConvertTo() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        assertEquals(new Quantity<>(12.0, LengthUnit.INCHES), q.convertTo(LengthUnit.INCHES));
    }

    @Test
    void testQuantityLengthRefactored_Add() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertEquals(new Quantity<>(2.0, LengthUnit.FEET), q1.add(q2));
    }

    @Test
    void testQuantityLengthRefactored_AddWithTargetUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        // 2ft = 0.67 yards
        assertEquals(new Quantity<>(0.67, LengthUnit.YARDS), q1.add(q2, LengthUnit.YARDS));
    }

    @Test
    void testQuantityLengthRefactored_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, null));
    }

    @Test
    void testQuantityLengthRefactored_InvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }

    // --- BACKWARD COMPATIBILITY ---
    @Test
    void testBackwardCompatibility_UC1EqualityTests() {
        assertEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(1.0, LengthUnit.FEET));
    }

    @Test
    void testBackwardCompatibility_UC5ConversionTests() {
        Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.GALLON);
        assertEquals(new Quantity<>(3.79, VolumeUnit.LITRE), q.convertTo(VolumeUnit.LITRE));
    }

    @Test
    void testBackwardCompatibility_UC6AdditionTests() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertEquals(new Quantity<>(2.0, LengthUnit.FEET), q1.add(q2));
    }

    @Test
    void testBackwardCompatibility_UC7AdditionWithTargetUnitTests() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertEquals(new Quantity<>(24.0, LengthUnit.INCHES), q1.add(q2, LengthUnit.INCHES));
    }

    // --- ARCHITECTURAL TESTS ---
    @Test
    void testArchitecturalScalability_MultipleCategories() {
        assertNotNull(WeightUnit.KILOGRAM);
        assertNotNull(VolumeUnit.LITRE);
    }

    @Test
    void testRoundTripConversion_RefactoredDesign() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = q1.convertTo(LengthUnit.INCHES).convertTo(LengthUnit.FEET);
        assertEquals(q1.getValue(), q2.getValue());
    }

    @Test
    void testUnitImmutability() {
        // Enums are final and immutable by nature
        assertTrue(LengthUnit.class.isEnum());
    }
}
