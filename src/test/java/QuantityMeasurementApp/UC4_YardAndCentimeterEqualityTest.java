package QuantityMeasurementApp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class UC4_YardAndCentimeterEqualityTest {
    @Test void test3FeetTo1YardEquality() { assertEquals(new QuantityLength(3.0, LengthUnit.FEET), new QuantityLength(1.0, LengthUnit.YARDS)); }
    @Test void test1YardTo3FeetEquality() { assertEquals(new QuantityLength(1.0, LengthUnit.YARDS), new QuantityLength(3.0, LengthUnit.FEET)); }
    @Test void test1YardTo36InchesEquality() { assertEquals(new QuantityLength(1.0, LengthUnit.YARDS), new QuantityLength(36.0, LengthUnit.INCHES)); }
    @Test void test36InchesTo1YardEquality() { assertEquals(new QuantityLength(36.0, LengthUnit.INCHES), new QuantityLength(1.0, LengthUnit.YARDS)); }
    @Test void test1YardTo1YardEquality() { assertEquals(new QuantityLength(1.0, LengthUnit.YARDS), new QuantityLength(1.0, LengthUnit.YARDS)); }
    @Test void test1InchTo2_5CentimeterEquality() { assertEquals(new QuantityLength(1.0, LengthUnit.INCHES), new QuantityLength(2.5, LengthUnit.CENTIMETERS)); }
    @Test void test2_5CentimeterTo1InchEquality() { assertEquals(new QuantityLength(2.5, LengthUnit.CENTIMETERS), new QuantityLength(1.0, LengthUnit.INCHES)); }
    @Test void testCentimeterToCentimeterEquality() { assertEquals(new QuantityLength(10.0, LengthUnit.CENTIMETERS), new QuantityLength(10.0, LengthUnit.CENTIMETERS)); }
    @Test void test1FootTo30CentimeterEquality() { assertEquals(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(30.0, LengthUnit.CENTIMETERS)); }
    @Test void test30CentimeterTo1FootEquality() { assertEquals(new QuantityLength(30.0, LengthUnit.CENTIMETERS), new QuantityLength(1.0, LengthUnit.FEET)); }
    @Test void testYardToYardInequality() { assertNotEquals(new QuantityLength(1.0, LengthUnit.YARDS), new QuantityLength(2.0, LengthUnit.YARDS)); }
    @Test void testCentimeterToCentimeterInequality() { assertNotEquals(new QuantityLength(2.5, LengthUnit.CENTIMETERS), new QuantityLength(5.0, LengthUnit.CENTIMETERS)); }
    @Test void testYardNullEquality() { assertNotEquals(new QuantityLength(1.0, LengthUnit.YARDS), null); }
    @Test void testCentimeterNullEquality() { assertNotEquals(new QuantityLength(1.0, LengthUnit.CENTIMETERS), null); }
    @Test void testYardSameReferenceEquality() { QuantityLength q = new QuantityLength(1.0, LengthUnit.YARDS); assertEquals(q, q); }
    @Test void testCentimeterSameReferenceEquality() { QuantityLength q = new QuantityLength(1.0, LengthUnit.CENTIMETERS); assertEquals(q, q); }
    @Test void testCrossUnitInequality() { assertNotEquals(new QuantityLength(1.0, LengthUnit.YARDS), new QuantityLength(1.0, LengthUnit.CENTIMETERS)); }
}
