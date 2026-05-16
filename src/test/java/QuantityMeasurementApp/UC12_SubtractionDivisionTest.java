package QuantityMeasurementApp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class UC12_SubtractionDivisionTest {
    @Test void testSubtract2InchesFrom4Inches() { assertEquals(new Quantity<>(2.0, LengthUnit.INCHES), new Quantity<>(4.0, LengthUnit.INCHES).subtract(new Quantity<>(2.0, LengthUnit.INCHES), LengthUnit.INCHES)); }
    @Test void testDivide10KgBy2() { assertEquals(new Quantity<>(5.0, WeightUnit.KG), new Quantity<>(10.0, WeightUnit.KG).divide(2.0)); }
}
