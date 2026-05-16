package QuantityMeasurementApp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class UC2_FeetAndInchEqualityTest {

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
