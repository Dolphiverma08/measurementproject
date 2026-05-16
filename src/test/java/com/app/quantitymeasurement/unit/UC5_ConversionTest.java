package com.app.quantitymeasurement.unit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UC5_ConversionTest {

    @Test
    void testConversion_FeetToInches() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        assertEquals(new Quantity<>(12.0, LengthUnit.INCHES), q.convertTo(LengthUnit.INCHES));
    }

    @Test
    void testConversion_InchesToFeet() {
        Quantity<LengthUnit> q = new Quantity<>(24.0, LengthUnit.INCHES);
        assertEquals(new Quantity<>(2.0, LengthUnit.FEET), q.convertTo(LengthUnit.FEET));
    }

    @Test
    void testConversion_YardsToInches() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.YARDS);
        assertEquals(new Quantity<>(36.0, LengthUnit.INCHES), q.convertTo(LengthUnit.INCHES));
    }

    @Test
    void testConversion_InchesToYards() {
        Quantity<LengthUnit> q = new Quantity<>(72.0, LengthUnit.INCHES);
        assertEquals(new Quantity<>(2.0, LengthUnit.YARDS), q.convertTo(LengthUnit.YARDS));
    }

    @Test
    void testConversion_CentimetersToInches() {
        Quantity<LengthUnit> q = new Quantity<>(2.54, LengthUnit.CENTIMETERS);
        assertEquals(new Quantity<>(1.0, LengthUnit.INCHES), q.convertTo(LengthUnit.INCHES));
    }

    @Test
    void testConversion_FeetToYard() {
        Quantity<LengthUnit> q = new Quantity<>(6.0, LengthUnit.FEET);
        assertEquals(new Quantity<>(2.0, LengthUnit.YARDS), q.convertTo(LengthUnit.YARDS));
    }

    @Test
    void testConversion_ZeroValue() {
        Quantity<LengthUnit> q = new Quantity<>(0.0, LengthUnit.FEET);
        assertEquals(new Quantity<>(0.0, LengthUnit.INCHES), q.convertTo(LengthUnit.INCHES));
    }

    @Test
    void testConversion_NegativeValue() {
        Quantity<LengthUnit> q = new Quantity<>(-1.0, LengthUnit.FEET);
        assertEquals(new Quantity<>(-12.0, LengthUnit.INCHES), q.convertTo(LengthUnit.INCHES));
    }

    @Test
    void testConversion_RoundTrip() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = q1.convertTo(LengthUnit.INCHES).convertTo(LengthUnit.FEET);
        assertEquals(q1.getValue(), q2.getValue(), 0.0001);
    }

    @Test
    void testConversion_InvalidUnit_Throws() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q.convertTo(null));
    }

    @Test
    void testConversion_NaNOrInfinite_Throws() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(Double.POSITIVE_INFINITY, LengthUnit.FEET));
    }

    @Test
    void testConversion_PrecisionTolerance() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = q.convertTo(LengthUnit.CENTIMETERS);
        // 1ft = 30.48cm
        assertEquals(30.48, result.getValue(), 1e-6);
    }
}
