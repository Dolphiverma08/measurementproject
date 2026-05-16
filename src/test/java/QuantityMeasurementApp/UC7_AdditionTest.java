package QuantityMeasurementApp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class UC7_AdditionTest {
    @Test void testAdd2InchesAnd2InchesInInches() { assertEquals(new Quantity<>(4.0, LengthUnit.INCHES), new Quantity<>(2.0, LengthUnit.INCHES).add(new Quantity<>(2.0, LengthUnit.INCHES), LengthUnit.INCHES)); }
    @Test void testAdd1FootAnd2InchesInInches() { assertEquals(new Quantity<>(14.0, LengthUnit.INCHES), new Quantity<>(1.0, LengthUnit.FEET).add(new Quantity<>(2.0, LengthUnit.INCHES), LengthUnit.INCHES)); }
    @Test void testAdd1FootAnd1FootInFeet() { assertEquals(new Quantity<>(2.0, LengthUnit.FEET), new Quantity<>(1.0, LengthUnit.FEET).add(new Quantity<>(1.0, LengthUnit.FEET), LengthUnit.FEET)); }
    @Test void testAdd2InchesAnd2_5CentimetersInInches() { assertEquals(new Quantity<>(3.0, LengthUnit.INCHES), new Quantity<>(2.0, LengthUnit.INCHES).add(new Quantity<>(2.5, LengthUnit.CENTIMETERS), LengthUnit.INCHES)); }
    @Test void testAdd1YardAnd3FeetInYards() { assertEquals(new Quantity<>(2.0, LengthUnit.YARDS), new Quantity<>(1.0, LengthUnit.YARDS).add(new Quantity<>(3.0, LengthUnit.FEET), LengthUnit.YARDS)); }
    @Test void testAddInCentimeters() { assertEquals(new Quantity<>(30.0, LengthUnit.CENTIMETERS), new Quantity<>(10.0, LengthUnit.CENTIMETERS).add(new Quantity<>(20.0, LengthUnit.CENTIMETERS), LengthUnit.CENTIMETERS)); }
    @Test void testAddInDifferentTargetUnit() { assertEquals(new Quantity<>(5.0, LengthUnit.FEET), new Quantity<>(24.0, LengthUnit.INCHES).add(new Quantity<>(36.0, LengthUnit.INCHES), LengthUnit.FEET)); }
}
