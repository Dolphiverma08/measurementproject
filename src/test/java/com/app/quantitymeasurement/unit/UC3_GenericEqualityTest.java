package com.app.quantitymeasurement.unit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UC3_GenericEqualityTest {

    // UC3 - Test Case 1
    @Test
    void testEquality_FeetToFeet_SameValue() {
        assertEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(1.0, LengthUnit.FEET));
    }

    // UC3 - Test Case 2
    @Test
    void testEquality_InchToInch_SameValue() {
        assertEquals(new Quantity<>(1.0, LengthUnit.INCHES), new Quantity<>(1.0, LengthUnit.INCHES));
    }

    // UC3 - Test Case 3
    @Test
    void testEquality_FeetToInch_EquivalentValue() {
        assertEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(12.0, LengthUnit.INCHES));
    }

    // UC3 - Test Case 4
    @Test
    void testEquality_InchToFeet_EquivalentValue() {
        assertEquals(new Quantity<>(12.0, LengthUnit.INCHES), new Quantity<>(1.0, LengthUnit.FEET));
    }

    // UC3 - Test Case 5
    @Test
    void testEquality_FeetToFeet_DifferentValue() {
        assertNotEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(2.0, LengthUnit.FEET));
    }

    // UC3 - Test Case 6
    @Test
    void testEquality_InchToInch_DifferentValue() {
        assertNotEquals(new Quantity<>(1.0, LengthUnit.INCHES), new Quantity<>(2.0, LengthUnit.INCHES));
    }

    // UC3 - Test Case 7
    @Test
    void testEquality_InvalidUnit() {
        // Conceptual test: Quantity with an invalid unit type should be handled by the generic class/enum
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, null));
    }

    // UC3 - Test Case 8
    @Test
    void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, null));
    }

    // UC3 - Test Case 9
    @Test
    void testEquality_SameReference() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        assertEquals(feet, feet);
    }

    // UC3 - Test Case 10
    @Test
    void testEquality_NullComparison() {
        assertNotEquals(new Quantity<>(1.0, LengthUnit.FEET), null);
    }
}
