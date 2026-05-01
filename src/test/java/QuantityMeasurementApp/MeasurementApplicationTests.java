package QuantityMeasurementApp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MeasurementApplicationTests {

    // --- UC1: Feet Tests ---

    @Test
    void testFeetEquality_SameValue() {
        MeasurementApplication.Feet f1 = new MeasurementApplication.Feet(89.6);
        MeasurementApplication.Feet f2 = new MeasurementApplication.Feet(89.6);
        assertEquals(f1, f2);
    }

    @Test
    void testFeetEquality_DifferentValue() {
        MeasurementApplication.Feet f1 = new MeasurementApplication.Feet(89.6);
        MeasurementApplication.Feet f2 = new MeasurementApplication.Feet(34.6);
        assertNotEquals(f1, f2);
    }

    @Test
    void testFeetNullable_NullValue() {
        MeasurementApplication.Feet f1 = new MeasurementApplication.Feet(55.0);
        assertFalse(f1.equals(null));
    }

    @Test
    void testFeetEquality_ClassComparison() {
        MeasurementApplication.Feet f1 = new MeasurementApplication.Feet(89.6);
        assertFalse(f1.equals("Some String"));
    }

    @Test
    void testFeetEquality_SameReference() {
        MeasurementApplication.Feet f1 = new MeasurementApplication.Feet(89.6);
        assertTrue(f1.equals(f1));
    }

    // --- UC2: Inches Tests ---

    @Test
    void testInchesEquality_SameValue() {
        MeasurementApplication.Inches i1 = new MeasurementApplication.Inches(1.0);
        MeasurementApplication.Inches i2 = new MeasurementApplication.Inches(1.0);
        assertEquals(i1, i2);
    }

    @Test
    void testInchesEquality_DifferentValue() {
        MeasurementApplication.Inches i1 = new MeasurementApplication.Inches(1.0);
        MeasurementApplication.Inches i2 = new MeasurementApplication.Inches(2.0);
        assertNotEquals(i1, i2);
    }

    @Test
    void testInchesNullable_NullValue() {
        MeasurementApplication.Inches i1 = new MeasurementApplication.Inches(5.0);
        assertFalse(i1.equals(null));
    }

    @Test
    void testInchesEquality_ClassComparison() {
        MeasurementApplication.Inches i1 = new MeasurementApplication.Inches(1.0);
        assertFalse(i1.equals("Some String"));
    }

    @Test
    void testInchesEquality_SameReference() {
        MeasurementApplication.Inches i1 = new MeasurementApplication.Inches(1.0);
        assertTrue(i1.equals(i1));
    }
}
