package QuantityMeasurementApp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class UC1_FeetEqualityTest {
    @Test void testFeetEquality_SameValue() { assertEquals(new Quantity<>(89.6, LengthUnit.FEET), new Quantity<>(89.6, LengthUnit.FEET)); }
    @Test void testFeetEquality_DifferentValue() { assertNotEquals(new Quantity<>(89.6, LengthUnit.FEET), new Quantity<>(34.6, LengthUnit.FEET)); }
    @Test void testFeetNullable_NullValue() { assertFalse(new Quantity<>(55.0, LengthUnit.FEET).equals(null)); }
    @Test void testFeetEquality_ClassComparison() { assertFalse(new Quantity<>(89.6, LengthUnit.FEET).equals("String")); }
    @Test void testFeetEquality_SameReference() { Quantity<LengthUnit> q = new Quantity<>(89.6, LengthUnit.FEET); assertTrue(q.equals(q)); }
}
