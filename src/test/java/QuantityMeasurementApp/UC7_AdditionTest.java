package QuantityMeasurementApp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class UC7_AdditionTest {
    @Test void testAdd2InchesAnd2InchesInInches() { assertEquals(new QuantityLength(4.0, LengthUnit.INCHES), new QuantityLength(2.0, LengthUnit.INCHES).add(new QuantityLength(2.0, LengthUnit.INCHES), LengthUnit.INCHES)); }
    @Test void testAdd1FootAnd2InchesInInches() { assertEquals(new QuantityLength(14.0, LengthUnit.INCHES), new QuantityLength(1.0, LengthUnit.FEET).add(new QuantityLength(2.0, LengthUnit.INCHES), LengthUnit.INCHES)); }
    @Test void testAdd1FootAnd1FootInFeet() { assertEquals(new QuantityLength(2.0, LengthUnit.FEET), new QuantityLength(1.0, LengthUnit.FEET).add(new QuantityLength(1.0, LengthUnit.FEET), LengthUnit.FEET)); }
    @Test void testAdd2InchesAnd2_5CentimetersInInches() { assertEquals(new QuantityLength(3.0, LengthUnit.INCHES), new QuantityLength(2.0, LengthUnit.INCHES).add(new QuantityLength(2.5, LengthUnit.CENTIMETERS), LengthUnit.INCHES)); }
    @Test void testAdd1YardAnd3FeetInYards() { assertEquals(new QuantityLength(2.0, LengthUnit.YARDS), new QuantityLength(1.0, LengthUnit.YARDS).add(new QuantityLength(3.0, LengthUnit.FEET), LengthUnit.YARDS)); }
    @Test void testAddInCentimeters() { assertEquals(new QuantityLength(30.0, LengthUnit.CENTIMETERS), new QuantityLength(10.0, LengthUnit.CENTIMETERS).add(new QuantityLength(20.0, LengthUnit.CENTIMETERS), LengthUnit.CENTIMETERS)); }
    @Test void testAddInDifferentTargetUnit() { assertEquals(new QuantityLength(5.0, LengthUnit.FEET), new QuantityLength(24.0, LengthUnit.INCHES).add(new QuantityLength(36.0, LengthUnit.INCHES), LengthUnit.FEET)); }
    @Test void testAddNullTargetUnitThrowsException() { assertThrows(IllegalArgumentException.class, () -> new QuantityLength(1.0, LengthUnit.FEET).add(new QuantityLength(1.0, LengthUnit.FEET), null)); }
    @Test void testAddWithZeroValueInTargetUnit() { assertEquals(new QuantityLength(12.0, LengthUnit.INCHES), new QuantityLength(12.0, LengthUnit.INCHES).add(new QuantityLength(0.0, LengthUnit.FEET), LengthUnit.INCHES)); }
    @Test void testAddNegativeValuesInTargetUnit() { assertEquals(new QuantityLength(10.0, LengthUnit.INCHES), new QuantityLength(12.0, LengthUnit.INCHES).add(new QuantityLength(-2.0, LengthUnit.INCHES), LengthUnit.INCHES)); }
    @Test void testAddLargeValuesInTargetUnit() { assertEquals(new QuantityLength(200.0, LengthUnit.YARDS), new QuantityLength(100.0, LengthUnit.YARDS).add(new QuantityLength(100.0, LengthUnit.YARDS), LengthUnit.YARDS)); }
    @Test void testAddAndConvertResult() { QuantityLength sum = new QuantityLength(1.0, LengthUnit.FEET).add(new QuantityLength(12.0, LengthUnit.INCHES), LengthUnit.FEET); assertEquals(new QuantityLength(2.0, LengthUnit.FEET), sum); }
    @Test void testAddSmallValuesInCentimeters() { assertEquals(new QuantityLength(5.0, LengthUnit.CENTIMETERS), new QuantityLength(1.0, LengthUnit.INCHES).add(new QuantityLength(1.0, LengthUnit.INCHES), LengthUnit.CENTIMETERS)); }
    @Test void testAddMixedUnitsToYard() { assertEquals(new QuantityLength(1.0, LengthUnit.YARDS), new QuantityLength(18.0, LengthUnit.INCHES).add(new QuantityLength(1.5, LengthUnit.FEET), LengthUnit.YARDS)); }
}
