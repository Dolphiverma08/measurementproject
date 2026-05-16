package QuantityMeasurementApp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class UC1_FeetEqualityTest {
    @Test void testFeetEquality_SameValue() { assertEquals(new QuantityLength(89.6, LengthUnit.FEET), new QuantityLength(89.6, LengthUnit.FEET)); }
    @Test void testFeetEquality_DifferentValue() { assertNotEquals(new QuantityLength(89.6, LengthUnit.FEET), new QuantityLength(34.6, LengthUnit.FEET)); }
    @Test void testFeetNullable_NullValue() { assertFalse(new QuantityLength(55.0, LengthUnit.FEET).equals(null)); }
    @Test void testFeetEquality_ClassComparison() { assertFalse(new QuantityLength(89.6, LengthUnit.FEET).equals("String")); }
    @Test void testFeetEquality_SameReference() { QuantityLength q = new QuantityLength(89.6, LengthUnit.FEET); assertTrue(q.equals(q)); }
}
