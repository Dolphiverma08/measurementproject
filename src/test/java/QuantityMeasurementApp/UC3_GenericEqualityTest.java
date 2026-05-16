package QuantityMeasurementApp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class UC3_GenericEqualityTest {
    @Test void testFeetToFeetEquality() { assertEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(1.0, LengthUnit.FEET)); }
    @Test void testInchesToInchesEquality() { assertEquals(new Quantity<>(1.0, LengthUnit.INCHES), new Quantity<>(1.0, LengthUnit.INCHES)); }
    @Test void testFeetToInchesEquality() { assertEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(12.0, LengthUnit.INCHES)); }
    @Test void testInchesToFeetEquality() { assertEquals(new Quantity<>(12.0, LengthUnit.INCHES), new Quantity<>(1.0, LengthUnit.FEET)); }
    @Test void testFeetToFeetInequality() { assertNotEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(2.0, LengthUnit.FEET)); }
    @Test void testInchesToInchesInequality() { assertNotEquals(new Quantity<>(1.0, LengthUnit.INCHES), new Quantity<>(2.0, LengthUnit.INCHES)); }
    @Test void testNullEquality() { assertNotEquals(new Quantity<>(1.0, LengthUnit.FEET), null); }
    @Test void testTypeEquality() { assertNotEquals(new Quantity<>(1.0, LengthUnit.FEET), new Object()); }
    @Test void testSameReferenceEquality() { Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET); assertEquals(q, q); }
    @Test void testValueInequality() { assertNotEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(11.0, LengthUnit.INCHES)); }
}
