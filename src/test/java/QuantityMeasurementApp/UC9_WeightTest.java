package QuantityMeasurementApp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class UC9_WeightTest {
    @Test void test1KgTo1000GramsEquality() { assertEquals(new Quantity<>(1.0, WeightUnit.KG), new Quantity<>(1000.0, WeightUnit.GRAMS)); }
    @Test void test1TonneTo1000KgEquality() { assertEquals(new Quantity<>(1.0, WeightUnit.TONNE), new Quantity<>(1000.0, WeightUnit.KG)); }
    @Test void testAdd10GramsAnd1Kg() { assertEquals(new Quantity<>(1010.0, WeightUnit.GRAMS), new Quantity<>(10.0, WeightUnit.GRAMS).add(new Quantity<>(1.0, WeightUnit.KG), WeightUnit.GRAMS)); }
}
