package QuantityMeasurementApp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MeasurementApplicationTests {

    // --- UC3 & UC4: Equality Tests ---

    @Test
    void testEquality_FeetToInch_EquivalentValue() {
        assertEquals(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(12.0, LengthUnit.INCHES));
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        assertEquals(new QuantityLength(1.0, LengthUnit.YARDS), new QuantityLength(3.0, LengthUnit.FEET));
    }

    @Test
    void testEquality_CentimeterToInches_EquivalentValue() {
        assertEquals(new QuantityLength(2.54, LengthUnit.CENTIMETERS), new QuantityLength(1.0, LengthUnit.INCHES));
    }

    // --- UC5: Conversion Tests ---

    @Test
    void testConversion_FeetToInches() {
        QuantityLength result = new QuantityLength(1.0, LengthUnit.FEET).convertTo(LengthUnit.INCHES);
        assertEquals(new QuantityLength(12.0, LengthUnit.INCHES), result);
    }

    @Test
    void testConversion_InchesToFeet() {
        QuantityLength result = new QuantityLength(24.0, LengthUnit.INCHES).convertTo(LengthUnit.FEET);
        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testConversion_YardsToInches() {
        QuantityLength result = new QuantityLength(1.0, LengthUnit.YARDS).convertTo(LengthUnit.INCHES);
        assertEquals(new QuantityLength(36.0, LengthUnit.INCHES), result);
    }

    @Test
    void testConversion_FeetToYards() {
        QuantityLength result = new QuantityLength(6.0, LengthUnit.FEET).convertTo(LengthUnit.YARDS);
        assertEquals(new QuantityLength(2.0, LengthUnit.YARDS), result);
    }

    @Test
    void testConversion_ZeroValue() {
        QuantityLength result = new QuantityLength(0.0, LengthUnit.FEET).convertTo(LengthUnit.INCHES);
        assertEquals(new QuantityLength(0.0, LengthUnit.INCHES), result);
    }

    @Test
    void testConversion_NegativeValue() {
        QuantityLength result = new QuantityLength(-1.0, LengthUnit.FEET).convertTo(LengthUnit.INCHES);
        assertEquals(new QuantityLength(-12.0, LengthUnit.INCHES), result);
    }

    @Test
    void testConversion_SameUnit() {
        QuantityLength result = new QuantityLength(5.0, LengthUnit.FEET).convertTo(LengthUnit.FEET);
        assertEquals(new QuantityLength(5.0, LengthUnit.FEET), result);
    }
}
