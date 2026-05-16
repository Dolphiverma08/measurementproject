package QuantityMeasurementApp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class UC3_GenericEqualityTest {
    @Test void testFeetToFeetEquality() { assertEquals(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(1.0, LengthUnit.FEET)); }
    @Test void testInchesToInchesEquality() { assertEquals(new QuantityLength(1.0, LengthUnit.INCHES), new QuantityLength(1.0, LengthUnit.INCHES)); }
    @Test void testFeetToInchesEquality() { assertEquals(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(12.0, LengthUnit.INCHES)); }
    @Test void testInchesToFeetEquality() { assertEquals(new QuantityLength(12.0, LengthUnit.INCHES), new QuantityLength(1.0, LengthUnit.FEET)); }
    @Test void testFeetToFeetInequality() { assertNotEquals(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(2.0, LengthUnit.FEET)); }
    @Test void testInchesToInchesInequality() { assertNotEquals(new QuantityLength(1.0, LengthUnit.INCHES), new QuantityLength(2.0, LengthUnit.INCHES)); }
    @Test void testNullEquality() { assertNotEquals(new QuantityLength(1.0, LengthUnit.FEET), null); }
    @Test void testTypeEquality() { assertNotEquals(new QuantityLength(1.0, LengthUnit.FEET), new Object()); }
    @Test void testSameReferenceEquality() { QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET); assertEquals(q, q); }
    @Test void testValueInequality() { assertNotEquals(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(11.0, LengthUnit.INCHES)); }
}
