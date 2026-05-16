package QuantityMeasurementApp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class UC6_AdditionTest {
    @Test void testAdd2InchesAnd2Inches() { assertEquals(new Quantity<>(4.0, LengthUnit.INCHES), new Quantity<>(2.0, LengthUnit.INCHES).add(new Quantity<>(2.0, LengthUnit.INCHES), LengthUnit.INCHES)); }
    @Test void testAdd1FootAnd2Inches() { assertEquals(new Quantity<>(14.0, LengthUnit.INCHES), new Quantity<>(1.0, LengthUnit.FEET).add(new Quantity<>(2.0, LengthUnit.INCHES), LengthUnit.INCHES)); }
    @Test void testAdd1FootAnd1Foot() { assertEquals(new Quantity<>(24.0, LengthUnit.INCHES), new Quantity<>(1.0, LengthUnit.FEET).add(new Quantity<>(1.0, LengthUnit.FEET), LengthUnit.INCHES)); }
    @Test void testAdd2InchesAnd2_5Centimeters() { assertEquals(new Quantity<>(3.0, LengthUnit.INCHES), new Quantity<>(2.0, LengthUnit.INCHES).add(new Quantity<>(2.5, LengthUnit.CENTIMETERS), LengthUnit.INCHES)); }
    @Test void testAddWithZeroValue() { assertEquals(new Quantity<>(5.0, LengthUnit.INCHES), new Quantity<>(5.0, LengthUnit.INCHES).add(new Quantity<>(0.0, LengthUnit.FEET), LengthUnit.INCHES)); }
    @Test void testAddNullThrowsException() { assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, LengthUnit.FEET).add(null, LengthUnit.FEET)); }
    @Test void testAddNegativeValues() { assertEquals(new Quantity<>(10.0, LengthUnit.INCHES), new Quantity<>(12.0, LengthUnit.INCHES).add(new Quantity<>(-2.0, LengthUnit.INCHES), LengthUnit.INCHES)); }
    @Test void testAddYardsAndInches() { assertEquals(new Quantity<>(37.0, LengthUnit.INCHES), new Quantity<>(1.0, LengthUnit.YARDS).add(new Quantity<>(1.0, LengthUnit.INCHES), LengthUnit.INCHES)); }
    @Test void testAddCentimetersAndFeet() { assertEquals(new Quantity<>(13.0, LengthUnit.INCHES), new Quantity<>(2.5, LengthUnit.CENTIMETERS).add(new Quantity<>(1.0, LengthUnit.FEET), LengthUnit.INCHES)); }
    @Test void testAddLargeValues() { assertEquals(new Quantity<>(7200.0, LengthUnit.INCHES), new Quantity<>(100.0, LengthUnit.YARDS).add(new Quantity<>(100.0, LengthUnit.YARDS), LengthUnit.INCHES)); }
}
