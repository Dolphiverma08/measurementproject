package QuantityMeasurementApp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MeasurementApplicationTests {

    // --- UC3: Feet and Inches ---

    @Test
    void testEquality_FeetToFeet_SameValue() {
        assertEquals(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(1.0, LengthUnit.FEET));
    }

    @Test
    void testEquality_InchToInch_SameValue() {
        assertEquals(new QuantityLength(1.0, LengthUnit.INCHES), new QuantityLength(1.0, LengthUnit.INCHES));
    }

    @Test
    void testEquality_FeetToInch_EquivalentValue() {
        assertEquals(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(12.0, LengthUnit.INCHES));
    }

    @Test
    void testEquality_InchToFeet_EquivalentValue() {
        assertEquals(new QuantityLength(12.0, LengthUnit.INCHES), new QuantityLength(1.0, LengthUnit.FEET));
    }

    // --- UC4: Yards ---

    @Test
    void testEquality_YardToYard_SameValue() {
        assertEquals(new QuantityLength(1.0, LengthUnit.YARDS), new QuantityLength(1.0, LengthUnit.YARDS));
    }

    @Test
    void testEquality_YardToYard_DifferentValue() {
        assertNotEquals(new QuantityLength(1.0, LengthUnit.YARDS), new QuantityLength(2.0, LengthUnit.YARDS));
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        assertEquals(new QuantityLength(1.0, LengthUnit.YARDS), new QuantityLength(3.0, LengthUnit.FEET));
    }

    @Test
    void testEquality_FeetToYard_EquivalentValue() {
        assertEquals(new QuantityLength(3.0, LengthUnit.FEET), new QuantityLength(1.0, LengthUnit.YARDS));
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {
        assertEquals(new QuantityLength(1.0, LengthUnit.YARDS), new QuantityLength(36.0, LengthUnit.INCHES));
    }

    // --- UC4: Centimeters ---

    @Test
    void testEquality_CentimeterToCentimeter_SameValue() {
        assertEquals(new QuantityLength(2.0, LengthUnit.CENTIMETERS), new QuantityLength(2.0, LengthUnit.CENTIMETERS));
    }

    @Test
    void testEquality_CentimeterToInches_EquivalentValue() {
        assertEquals(new QuantityLength(2.54, LengthUnit.CENTIMETERS), new QuantityLength(1.0, LengthUnit.INCHES));
    }

    @Test
    void testEquality_NullComparison() {
        assertFalse(new QuantityLength(1.0, LengthUnit.FEET).equals(null));
    }

    @Test
    void testEquality_SameReference() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET);
        assertTrue(q.equals(q));
    }
}
