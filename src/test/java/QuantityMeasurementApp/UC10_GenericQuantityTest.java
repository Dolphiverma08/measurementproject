package QuantityMeasurementApp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class UC10_GenericQuantityTest {
    @Test void testGenericFeetEquality() { assertEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(1.0, LengthUnit.FEET)); }
    @Test void testGenericKgEquality() { assertEquals(new Quantity<>(1.0, WeightUnit.KG), new Quantity<>(1000.0, WeightUnit.GRAMS)); }
    @Test void testGenericAddition_Length() { assertEquals(new Quantity<>(14.0, LengthUnit.INCHES), new Quantity<>(1.0, LengthUnit.FEET).add(new Quantity<>(2.0, LengthUnit.INCHES), LengthUnit.INCHES)); }
    @Test void testGenericAddition_Weight() { assertEquals(new Quantity<>(1010.0, WeightUnit.GRAMS), new Quantity<>(1.0, WeightUnit.KG).add(new Quantity<>(10.0, WeightUnit.GRAMS), WeightUnit.GRAMS)); }
    @Test void testGenericInequality_LengthAndWeight() { Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET); Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KG); assertNotEquals(length, weight); }
}
