package QuantityMeasurementApp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class UC5_ConversionTest {
    @Test void test1FeetTo12InchesConversion() { assertEquals(new QuantityLength(12.0, LengthUnit.INCHES), new QuantityLength(1.0, LengthUnit.FEET).convertTo(LengthUnit.INCHES)); }
    @Test void test12InchesTo1FeetConversion() { assertEquals(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(12.0, LengthUnit.INCHES).convertTo(LengthUnit.FEET)); }
    @Test void test1YardTo36InchesConversion() { assertEquals(new QuantityLength(36.0, LengthUnit.INCHES), new QuantityLength(1.0, LengthUnit.YARDS).convertTo(LengthUnit.INCHES)); }
    @Test void test36InchesTo1YardConversion() { assertEquals(new QuantityLength(1.0, LengthUnit.YARDS), new QuantityLength(36.0, LengthUnit.INCHES).convertTo(LengthUnit.YARDS)); }
    @Test void test1FootTo30CentimetersConversion() { assertEquals(new QuantityLength(30.0, LengthUnit.CENTIMETERS), new QuantityLength(1.0, LengthUnit.FEET).convertTo(LengthUnit.CENTIMETERS)); }
    @Test void test30CentimetersTo1FootConversion() { assertEquals(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(30.0, LengthUnit.CENTIMETERS).convertTo(LengthUnit.FEET)); }
    @Test void test1InchTo2_5CentimetersConversion() { assertEquals(new QuantityLength(2.5, LengthUnit.CENTIMETERS), new QuantityLength(1.0, LengthUnit.INCHES).convertTo(LengthUnit.CENTIMETERS)); }
    @Test void test2_5CentimetersTo1InchConversion() { assertEquals(new QuantityLength(1.0, LengthUnit.INCHES), new QuantityLength(2.5, LengthUnit.CENTIMETERS).convertTo(LengthUnit.INCHES)); }
    @Test void testSameUnitConversion() { assertEquals(new QuantityLength(10.0, LengthUnit.FEET), new QuantityLength(10.0, LengthUnit.FEET).convertTo(LengthUnit.FEET)); }
    @Test void testZeroValueConversion() { assertEquals(new QuantityLength(0.0, LengthUnit.INCHES), new QuantityLength(0.0, LengthUnit.FEET).convertTo(LengthUnit.INCHES)); }
    @Test void testNullTargetUnit() { assertThrows(IllegalArgumentException.class, () -> new QuantityLength(1.0, LengthUnit.FEET).convertTo(null)); }
    @Test void testLargeValueConversion() { assertEquals(new QuantityLength(3600.0, LengthUnit.INCHES), new QuantityLength(100.0, LengthUnit.YARDS).convertTo(LengthUnit.INCHES)); }
}
