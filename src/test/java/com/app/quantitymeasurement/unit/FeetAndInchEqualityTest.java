package com.app.quantitymeasurement.unit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FeetAndInchEqualityTest {

    // UC2 - Test Case 1
    @Test
    void testEquality_SameValue() {
        assertEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(1.0, LengthUnit.FEET));
        assertEquals(new Quantity<>(1.0, LengthUnit.INCHES), new Quantity<>(1.0, LengthUnit.INCHES));
    }

    // UC2 - Test Case 2
    @Test
    void testEquality_DifferentValue() {
        assertNotEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(2.0, LengthUnit.FEET));
        assertNotEquals(new Quantity<>(1.0, LengthUnit.INCHES), new Quantity<>(2.0, LengthUnit.INCHES));
    }

    // UC2 - Test Case 3
    @Test
    void testEquality_NullComparison() {
        assertNotEquals(new Quantity<>(1.0, LengthUnit.FEET), null);
        assertNotEquals(new Quantity<>(1.0, LengthUnit.INCHES), null);
    }

    // UC2 - Test Case 4
    @Test
    void testEquality_NonNumericInput() {
        // Passing a String (non-numeric object type) to the equals method
        assertFalse(new Quantity<>(1.0, LengthUnit.FEET).equals("1.0"));
        assertFalse(new Quantity<>(1.0, LengthUnit.INCHES).equals("1.0"));
    }

    // UC2 - Test Case 5
    @Test
    void testEquality_SameReference() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        assertEquals(feet, feet);
        
        Quantity<LengthUnit> inches = new Quantity<>(1.0, LengthUnit.INCHES);
        assertEquals(inches, inches);
    }
}
