package com.app.quantitymeasurement.unit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UC4_YardAndCentimeterEqualityTest {

    @Test
    void testEquality_YardToYard_SameValue() {
        assertEquals(new Quantity<>(1.0, LengthUnit.YARDS), new Quantity<>(1.0, LengthUnit.YARDS));
    }

    @Test
    void testEquality_YardToYard_DifferentValue() {
        assertNotEquals(new Quantity<>(1.0, LengthUnit.YARDS), new Quantity<>(2.0, LengthUnit.YARDS));
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        assertEquals(new Quantity<>(1.0, LengthUnit.YARDS), new Quantity<>(3.0, LengthUnit.FEET));
    }

    @Test
    void testEquality_FeetToYard_EquivalentValue() {
        assertEquals(new Quantity<>(3.0, LengthUnit.FEET), new Quantity<>(1.0, LengthUnit.YARDS));
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {
        assertEquals(new Quantity<>(1.0, LengthUnit.YARDS), new Quantity<>(36.0, LengthUnit.INCHES));
    }

    @Test
    void testEquality_InchesToYard_EquivalentValue() {
        assertEquals(new Quantity<>(36.0, LengthUnit.INCHES), new Quantity<>(1.0, LengthUnit.YARDS));
    }

    @Test
    void testEquality_YardToFeet_NonEquivalentValue() {
        assertNotEquals(new Quantity<>(1.0, LengthUnit.YARDS), new Quantity<>(2.0, LengthUnit.FEET));
    }

    @Test
    void testEquality_centimetersToInches_EquivalentValue() {
        // 1 inch = 2.54 cm, so 1 cm = 1/2.54 inches.
        // (1/2.54) / 12 = 1 / 30.48, which matches 1 cm * (1/30.48) exactly.
        assertEquals(new Quantity<>(1.0, LengthUnit.CENTIMETERS), new Quantity<>(1.0/2.54, LengthUnit.INCHES));
    }

    @Test
    void testEquality_centimetersToFeet_NonEquivalentValue() {
        assertNotEquals(new Quantity<>(1.0, LengthUnit.CENTIMETERS), new Quantity<>(1.0, LengthUnit.FEET));
    }

    @Test
    void testEquality_MultiUnit_TransitiveProperty() {
        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> feet = new Quantity<>(3.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(36.0, LengthUnit.INCHES);
        
        assertEquals(yard, feet);
        assertEquals(feet, inches);
        assertEquals(yard, inches);
    }

    @Test
    void testEquality_YardWithNullUnit() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, null));
    }

    @Test
    void testEquality_YardSameReference() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.YARDS);
        assertEquals(q, q);
    }

    @Test
    void testEquality_YardNullComparison() {
        assertNotEquals(new Quantity<>(1.0, LengthUnit.YARDS), null);
    }

    @Test
    void testEquality_CentimetersWithNullUnit() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, null));
    }

    @Test
    void testEquality_CentimetersSameReference() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.CENTIMETERS);
        assertEquals(q, q);
    }

    @Test
    void testEquality_CentimetersNullComparison() {
        assertNotEquals(new Quantity<>(1.0, LengthUnit.CENTIMETERS), null);
    }

    @Test
    void testEquality_AllUnits_ComplexScenario() {
        Quantity<LengthUnit> yards = new Quantity<>(2.0, LengthUnit.YARDS);
        Quantity<LengthUnit> feet = new Quantity<>(6.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(72.0, LengthUnit.INCHES);
        
        assertEquals(yards, feet);
        assertEquals(feet, inches);
        assertEquals(yards, inches);
    }
}
