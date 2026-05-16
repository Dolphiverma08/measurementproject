package QuantityMeasurementApp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class UC2_FeetAndInchEqualityTest {
    @Test void testInchesEquality_SameValue() { assertEquals(new QuantityLength(1.0, LengthUnit.INCHES), new QuantityLength(1.0, LengthUnit.INCHES)); }
    @Test void testInchesEquality_DifferentValue() { assertNotEquals(new QuantityLength(1.0, LengthUnit.INCHES), new QuantityLength(2.0, LengthUnit.INCHES)); }
    @Test void testInchesNullable_NullValue() { assertFalse(new QuantityLength(5.0, LengthUnit.INCHES).equals(null)); }
    @Test void testInchesEquality_ClassComparison() { assertFalse(new QuantityLength(1.0, LengthUnit.INCHES).equals("String")); }
    @Test void testInchesEquality_SameReference() { QuantityLength q = new QuantityLength(1.0, LengthUnit.INCHES); assertTrue(q.equals(q)); }
}
