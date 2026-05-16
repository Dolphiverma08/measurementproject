package QuantityMeasurementApp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class UC6_AdditionTest {
    @Test void testAdd2InchesAnd2Inches() { assertEquals(new QuantityLength(4.0, LengthUnit.INCHES), new QuantityLength(2.0, LengthUnit.INCHES).add(new QuantityLength(2.0, LengthUnit.INCHES))); }
    @Test void testAdd1FootAnd2Inches() { assertEquals(new QuantityLength(14.0, LengthUnit.INCHES), new QuantityLength(1.0, LengthUnit.FEET).add(new QuantityLength(2.0, LengthUnit.INCHES))); }
    @Test void testAdd1FootAnd1Foot() { assertEquals(new QuantityLength(24.0, LengthUnit.INCHES), new QuantityLength(1.0, LengthUnit.FEET).add(new QuantityLength(1.0, LengthUnit.FEET))); }
    @Test void testAdd2InchesAnd2_5Centimeters() { assertEquals(new QuantityLength(3.0, LengthUnit.INCHES), new QuantityLength(2.0, LengthUnit.INCHES).add(new QuantityLength(2.5, LengthUnit.CENTIMETERS))); }
    @Test void testAddWithZeroValue() { assertEquals(new QuantityLength(5.0, LengthUnit.INCHES), new QuantityLength(5.0, LengthUnit.INCHES).add(new QuantityLength(0.0, LengthUnit.FEET))); }
    @Test void testAddNullThrowsException() { assertThrows(IllegalArgumentException.class, () -> new QuantityLength(1.0, LengthUnit.FEET).add(null)); }
    @Test void testAddNegativeValues() { assertEquals(new QuantityLength(10.0, LengthUnit.INCHES), new QuantityLength(12.0, LengthUnit.INCHES).add(new QuantityLength(-2.0, LengthUnit.INCHES))); }
    @Test void testAddYardsAndInches() { assertEquals(new QuantityLength(37.0, LengthUnit.INCHES), new QuantityLength(1.0, LengthUnit.YARDS).add(new QuantityLength(1.0, LengthUnit.INCHES))); }
    @Test void testAddCentimetersAndFeet() { assertEquals(new QuantityLength(13.0, LengthUnit.INCHES), new QuantityLength(2.5, LengthUnit.CENTIMETERS).add(new QuantityLength(1.0, LengthUnit.FEET))); }
    @Test void testAddLargeValues() { assertEquals(new QuantityLength(7200.0, LengthUnit.INCHES), new QuantityLength(100.0, LengthUnit.YARDS).add(new QuantityLength(100.0, LengthUnit.YARDS))); }
    @Test void testAddAndConvertBack() { QuantityLength sum = new QuantityLength(1.0, LengthUnit.FEET).add(new QuantityLength(12.0, LengthUnit.INCHES)); assertEquals(new QuantityLength(2.0, LengthUnit.FEET), sum.convertTo(LengthUnit.FEET)); }
    @Test void testCommutativeAddition() { QuantityLength sum1 = new QuantityLength(1.0, LengthUnit.FEET).add(new QuantityLength(12.0, LengthUnit.INCHES)); QuantityLength sum2 = new QuantityLength(12.0, LengthUnit.INCHES).add(new QuantityLength(1.0, LengthUnit.FEET)); assertEquals(sum1, sum2); }
}
