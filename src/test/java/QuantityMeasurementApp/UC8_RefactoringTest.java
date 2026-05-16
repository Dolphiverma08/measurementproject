package QuantityMeasurementApp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class UC8_RefactoringTest {
    @Test void testFeetToInchesEquality() { assertEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(12.0, LengthUnit.INCHES)); }
    @Test void testYardToInchesEquality() { assertEquals(new Quantity<>(1.0, LengthUnit.YARDS), new Quantity<>(36.0, LengthUnit.INCHES)); }
    @Test void testInchToCentimeterEquality() { assertEquals(new Quantity<>(1.0, LengthUnit.INCHES), new Quantity<>(2.5, LengthUnit.CENTIMETERS)); }
    @Test void testFeetToYardEquality() { assertEquals(new Quantity<>(3.0, LengthUnit.FEET), new Quantity<>(1.0, LengthUnit.YARDS)); }
}
