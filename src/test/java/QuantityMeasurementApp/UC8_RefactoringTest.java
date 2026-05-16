package QuantityMeasurementApp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class UC8_RefactoringTest {
    @Test void testFeetToInchesEquality() { assertEquals(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(12.0, LengthUnit.INCHES)); }
    @Test void testYardToInchesEquality() { assertEquals(new QuantityLength(1.0, LengthUnit.YARDS), new QuantityLength(36.0, LengthUnit.INCHES)); }
    @Test void testInchToCentimeterEquality() { assertEquals(new QuantityLength(1.0, LengthUnit.INCHES), new QuantityLength(2.5, LengthUnit.CENTIMETERS)); }
    @Test void testFeetToYardEquality() { assertEquals(new QuantityLength(3.0, LengthUnit.FEET), new QuantityLength(1.0, LengthUnit.YARDS)); }
    @Test void testCentimeterToFeetEquality() { assertEquals(new QuantityLength(30.0, LengthUnit.CENTIMETERS), new QuantityLength(1.0, LengthUnit.FEET)); }
    @Test void testAdd2InchesAnd2Inches() { assertEquals(new QuantityLength(4.0, LengthUnit.INCHES), new QuantityLength(2.0, LengthUnit.INCHES).add(new QuantityLength(2.0, LengthUnit.INCHES), LengthUnit.INCHES)); }
    @Test void testAdd1FootAnd2Inches() { assertEquals(new QuantityLength(14.0, LengthUnit.INCHES), new QuantityLength(1.0, LengthUnit.FEET).add(new QuantityLength(2.0, LengthUnit.INCHES), LengthUnit.INCHES)); }
    @Test void testAdd1FootAnd1Foot() { assertEquals(new QuantityLength(24.0, LengthUnit.INCHES), new QuantityLength(1.0, LengthUnit.FEET).add(new QuantityLength(1.0, LengthUnit.FEET), LengthUnit.INCHES)); }
    @Test void testAdd2InchesAnd2_5Centimeters() { assertEquals(new QuantityLength(3.0, LengthUnit.INCHES), new QuantityLength(2.0, LengthUnit.INCHES).add(new QuantityLength(2.5, LengthUnit.CENTIMETERS), LengthUnit.INCHES)); }
    @Test void testQuantityEquality_Null() { assertNotEquals(new QuantityLength(1.0, LengthUnit.FEET), null); }
    @Test void testQuantityEquality_SameRef() { QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET); assertEquals(q, q); }
    @Test void testQuantityEquality_DifferentType() { assertNotEquals(new QuantityLength(1.0, LengthUnit.FEET), new Object()); }
    @Test void testQuantityEquality_DifferentValue() { assertNotEquals(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(2.0, LengthUnit.FEET)); }
    @Test void testQuantityEquality_DifferentUnit() { assertNotEquals(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(1.0, LengthUnit.INCHES)); }
    @Test void testYardToFeetConversion() { assertEquals(new QuantityLength(3.0, LengthUnit.FEET), new QuantityLength(1.0, LengthUnit.YARDS).convertTo(LengthUnit.FEET)); }
    @Test void testInchesToYardConversion() { assertEquals(new QuantityLength(1.0, LengthUnit.YARDS), new QuantityLength(36.0, LengthUnit.INCHES).convertTo(LengthUnit.YARDS)); }
    @Test void testCentimetersToInchesConversion() { assertEquals(new QuantityLength(1.0, LengthUnit.INCHES), new QuantityLength(2.5, LengthUnit.CENTIMETERS).convertTo(LengthUnit.INCHES)); }
    @Test void testAddMixedUnits_FeetAndYard() { assertEquals(new QuantityLength(6.0, LengthUnit.FEET), new QuantityLength(3.0, LengthUnit.FEET).add(new QuantityLength(1.0, LengthUnit.YARDS), LengthUnit.FEET)); }
    @Test void testAddMixedUnits_InchAndCentimeter() { assertEquals(new QuantityLength(2.0, LengthUnit.INCHES), new QuantityLength(1.0, LengthUnit.INCHES).add(new QuantityLength(2.5, LengthUnit.CENTIMETERS), LengthUnit.INCHES)); }
    @Test void testEnumRefactoring_ValueCheck() { assertEquals(12.0, LengthUnit.FEET.conversionFactor); }
    @Test void testEnumRefactoring_InchValueCheck() { assertEquals(1.0, LengthUnit.INCHES.conversionFactor); }
    @Test void testEnumRefactoring_YardValueCheck() { assertEquals(36.0, LengthUnit.YARDS.conversionFactor); }
    @Test void testEnumRefactoring_CentimeterValueCheck() { assertEquals(0.4, LengthUnit.CENTIMETERS.conversionFactor); }
    @Test void testAddWithZero() { assertEquals(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(1.0, LengthUnit.FEET).add(new QuantityLength(0.0, LengthUnit.INCHES), LengthUnit.FEET)); }
    @Test void testAddLargeValues() { assertEquals(new QuantityLength(200.0, LengthUnit.YARDS), new QuantityLength(100.0, LengthUnit.YARDS).add(new QuantityLength(100.0, LengthUnit.YARDS), LengthUnit.YARDS)); }
}
