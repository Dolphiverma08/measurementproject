package QuantityMeasurementApp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class UC10_GenericQuantityTest {
    @Test void testGenericFeetEquality() { assertEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(1.0, LengthUnit.FEET)); }
    @Test void testGenericKgEquality() { assertEquals(new Quantity<>(1.0, WeightUnit.KG), new Quantity<>(1000.0, WeightUnit.GRAMS)); }
    @Test void testGenericAddition_Length() { assertEquals(new Quantity<>(14.0, LengthUnit.INCHES), new Quantity<>(1.0, LengthUnit.FEET).add(new Quantity<>(2.0, LengthUnit.INCHES), LengthUnit.INCHES)); }
    @Test void testGenericAddition_Weight() { assertEquals(new Quantity<>(1010.0, WeightUnit.GRAMS), new Quantity<>(1.0, WeightUnit.KG).add(new Quantity<>(10.0, WeightUnit.GRAMS), WeightUnit.GRAMS)); }
    @Test void testGenericInequality_LengthAndWeight() { Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET); Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KG); assertNotEquals(length, weight); }
    @Test void testGenericNullEquality() { assertNotEquals(new Quantity<>(1.0, LengthUnit.FEET), null); }
    @Test void testGenericSameRefEquality() { Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET); assertEquals(q, q); }
    @Test void testGenericDifferentTypeEquality() { assertNotEquals(new Quantity<>(1.0, LengthUnit.FEET), new Object()); }
    @Test void testGenericDifferentValueEquality() { assertNotEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(2.0, LengthUnit.FEET)); }
    @Test void testGenericDifferentUnitEquality() { assertNotEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(1.0, LengthUnit.INCHES)); }
    @Test void testGenericConversion_Length() { assertEquals(new Quantity<>(12.0, LengthUnit.INCHES), new Quantity<>(1.0, LengthUnit.FEET).convertTo(LengthUnit.INCHES)); }
    @Test void testGenericConversion_Weight() { assertEquals(new Quantity<>(1000.0, WeightUnit.GRAMS), new Quantity<>(1.0, WeightUnit.KG).convertTo(WeightUnit.GRAMS)); }
    @Test void testAddNegativeValues_Generic() { assertEquals(new Quantity<>(10.0, LengthUnit.INCHES), new Quantity<>(12.0, LengthUnit.INCHES).add(new Quantity<>(-2.0, LengthUnit.INCHES), LengthUnit.INCHES)); }
    @Test void testAddZeroValue_Generic() { assertEquals(new Quantity<>(1.0, WeightUnit.KG), new Quantity<>(1.0, WeightUnit.KG).add(new Quantity<>(0.0, WeightUnit.GRAMS), WeightUnit.KG)); }
    @Test void testLargeValueAddition_Generic() { assertEquals(new Quantity<>(2.0, WeightUnit.TONNE), new Quantity<>(1.0, WeightUnit.TONNE).add(new Quantity<>(1000.0, WeightUnit.KG), WeightUnit.TONNE)); }
    @Test void testPrecisionAddition_Generic() { assertEquals(new Quantity<>(3.0, LengthUnit.INCHES), new Quantity<>(2.0, LengthUnit.INCHES).add(new Quantity<>(2.5, LengthUnit.CENTIMETERS), LengthUnit.INCHES)); }
    @Test void testTypeSafety_CompilerCheck() { Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET); Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES); assertEquals(q1, q2); }
    @Test void testQuantityCreation_ZeroValue() { assertEquals(0.0, new Quantity<>(0.0, LengthUnit.FEET).value); }
    @Test void testQuantityCreation_PositiveValue() { assertEquals(10.0, new Quantity<>(10.0, WeightUnit.KG).value); }
    @Test void testQuantityCreation_NegativeValue() { assertEquals(-5.0, new Quantity<>(-5.0, LengthUnit.INCHES).value); }
    @Test void testAddNullThrowsException_Generic() { assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, LengthUnit.FEET).add(null, LengthUnit.FEET)); }
    @Test void testConvertToNullThrowsException_Generic() { assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, LengthUnit.FEET).convertTo(null)); }
    @Test void testAddWithNullTargetUnitThrowsException_Generic() { assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, LengthUnit.FEET).add(new Quantity<>(1.0, LengthUnit.INCHES), null)); }
    @Test void testGenericEquality_SameValueSameUnit() { assertEquals(new Quantity<>(5.0, LengthUnit.FEET), new Quantity<>(5.0, LengthUnit.FEET)); }
    @Test void testGenericEquality_DifferentValueSameUnit() { assertNotEquals(new Quantity<>(5.0, LengthUnit.FEET), new Quantity<>(10.0, LengthUnit.FEET)); }
    @Test void testGenericEquality_SameValueDifferentUnit() { assertNotEquals(new Quantity<>(5.0, LengthUnit.FEET), new Quantity<>(5.0, LengthUnit.INCHES)); }
    @Test void testGenericEquality_Reference() { Quantity<WeightUnit> q = new Quantity<>(10.0, WeightUnit.KG); assertTrue(q.equals(q)); }
    @Test void testGenericInchEquality() { assertEquals(new Quantity<>(12.0, LengthUnit.INCHES), new Quantity<>(1.0, LengthUnit.FEET)); }
    @Test void testGenericTonneEquality() { assertEquals(new Quantity<>(1.0, WeightUnit.TONNE), new Quantity<>(1000.0, WeightUnit.KG)); }
}
