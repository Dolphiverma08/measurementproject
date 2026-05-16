package com.app.quantitymeasurement.unit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FeetEqualityTest {

    // UC1 - Test Case 1
    @Test
    void testEquality_SameValue() {
        assertEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(1.0, LengthUnit.FEET));
    }

    // UC1 - Test Case 2
    @Test
    void testEquality_DifferentValue() {
        assertNotEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(2.0, LengthUnit.FEET));
    }

    // UC1 - Test Case 3
    @Test
    void testEquality_NullComparison() {
        assertNotEquals(new Quantity<>(1.0, LengthUnit.FEET), null);
    }

    // UC1 - Test Case 4
    @Test
    void testEquality_NonNumericInput() {
        // Comparing a Feet Quantity with a non-numeric object (String)
        assertFalse(new Quantity<>(1.0, LengthUnit.FEET).equals("1.0"));
    }

    // UC1 - Test Case 5
    @Test
    void testEquality_SameReference() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        assertEquals(feet, feet);
    }
}
