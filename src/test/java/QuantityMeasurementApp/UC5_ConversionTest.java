package QuantityMeasurementApp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class UC5_ConversionTest {
    @Test void test1FeetTo12InchesConversion() { assertEquals(new Quantity<>(12.0, LengthUnit.INCHES), new Quantity<>(1.0, LengthUnit.FEET).convertTo(LengthUnit.INCHES)); }
    @Test void test12InchesTo1FeetConversion() { assertEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(12.0, LengthUnit.INCHES).convertTo(LengthUnit.FEET)); }
    @Test void test1YardTo36InchesConversion() { assertEquals(new Quantity<>(36.0, LengthUnit.INCHES), new Quantity<>(1.0, LengthUnit.YARDS).convertTo(LengthUnit.INCHES)); }
    @Test void test36InchesTo1YardConversion() { assertEquals(new Quantity<>(1.0, LengthUnit.YARDS), new Quantity<>(36.0, LengthUnit.INCHES).convertTo(LengthUnit.YARDS)); }
    @Test void test1FootTo30CentimetersConversion() { assertEquals(new Quantity<>(30.0, LengthUnit.CENTIMETERS), new Quantity<>(1.0, LengthUnit.FEET).convertTo(LengthUnit.CENTIMETERS)); }
    @Test void test30CentimetersTo1FootConversion() { assertEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(30.0, LengthUnit.CENTIMETERS).convertTo(LengthUnit.FEET)); }
    @Test void test1InchTo2_5CentimetersConversion() { assertEquals(new Quantity<>(2.5, LengthUnit.CENTIMETERS), new Quantity<>(1.0, LengthUnit.INCHES).convertTo(LengthUnit.CENTIMETERS)); }
    @Test void test2_5CentimetersTo1InchConversion() { assertEquals(new Quantity<>(1.0, LengthUnit.INCHES), new Quantity<>(2.5, LengthUnit.CENTIMETERS).convertTo(LengthUnit.INCHES)); }
    @Test void testSameUnitConversion() { assertEquals(new Quantity<>(10.0, LengthUnit.FEET), new Quantity<>(10.0, LengthUnit.FEET).convertTo(LengthUnit.FEET)); }
    @Test void testZeroValueConversion() { assertEquals(new Quantity<>(0.0, LengthUnit.INCHES), new Quantity<>(0.0, LengthUnit.FEET).convertTo(LengthUnit.INCHES)); }
    @Test void testNullTargetUnit() { assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, LengthUnit.FEET).convertTo(null)); }
    @Test void testLargeValueConversion() { assertEquals(new Quantity<>(3600.0, LengthUnit.INCHES), new Quantity<>(100.0, LengthUnit.YARDS).convertTo(LengthUnit.INCHES)); }
}
