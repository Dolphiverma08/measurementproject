package QuantityMeasurementApp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class UC11_VolumeTest {
    @Test void test1GallonTo3_78LitersEquality() { assertEquals(new Quantity<>(1.0, VolumeUnit.GALLON), new Quantity<>(3.78, VolumeUnit.LITRE)); }
    @Test void test3_78LitersTo1GallonEquality() { assertEquals(new Quantity<>(3.78, VolumeUnit.LITRE), new Quantity<>(1.0, VolumeUnit.GALLON)); }
    @Test void test1LitreTo1000MlEquality() { assertEquals(new Quantity<>(1.0, VolumeUnit.LITRE), new Quantity<>(1000.0, VolumeUnit.ML)); }
    @Test void test1000MlTo1LitreEquality() { assertEquals(new Quantity<>(1000.0, VolumeUnit.ML), new Quantity<>(1.0, VolumeUnit.LITRE)); }
    @Test void test1GallonTo1GallonEquality() { assertEquals(new Quantity<>(1.0, VolumeUnit.GALLON), new Quantity<>(1.0, VolumeUnit.GALLON)); }
    @Test void test1LitreTo1LitreEquality() { assertEquals(new Quantity<>(1.0, VolumeUnit.LITRE), new Quantity<>(1.0, VolumeUnit.LITRE)); }
    @Test void test1MlTo1MlEquality() { assertEquals(new Quantity<>(1.0, VolumeUnit.ML), new Quantity<>(1.0, VolumeUnit.ML)); }
    @Test void testAdd1GallonAnd3_78Liters() { assertEquals(new Quantity<>(7.56, VolumeUnit.LITRE), new Quantity<>(1.0, VolumeUnit.GALLON).add(new Quantity<>(3.78, VolumeUnit.LITRE), VolumeUnit.LITRE)); }
    @Test void testAdd1LitreAnd1000Ml() { assertEquals(new Quantity<>(2.0, VolumeUnit.LITRE), new Quantity<>(1.0, VolumeUnit.LITRE).add(new Quantity<>(1000.0, VolumeUnit.ML), VolumeUnit.LITRE)); }
    @Test void testAddWithZeroValue() { assertEquals(new Quantity<>(1.0, VolumeUnit.GALLON), new Quantity<>(1.0, VolumeUnit.GALLON).add(new Quantity<>(0.0, VolumeUnit.LITRE), VolumeUnit.GALLON)); }
    @Test void testVolumeEquality_Null() { assertNotEquals(new Quantity<>(1.0, VolumeUnit.GALLON), null); }
    @Test void testVolumeEquality_SameRef() { Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.GALLON); assertEquals(q, q); }
    @Test void testVolumeEquality_DifferentType() { assertNotEquals(new Quantity<>(1.0, VolumeUnit.GALLON), new Object()); }
    @Test void testVolumeEquality_DifferentValue() { assertNotEquals(new Quantity<>(1.0, VolumeUnit.GALLON), new Quantity<>(2.0, VolumeUnit.GALLON)); }
    @Test void testVolumeEquality_DifferentUnit() { assertNotEquals(new Quantity<>(1.0, VolumeUnit.GALLON), new Quantity<>(1.0, VolumeUnit.LITRE)); }
    @Test void test1GallonToLitersConversion() { assertEquals(new Quantity<>(3.78, VolumeUnit.LITRE), new Quantity<>(1.0, VolumeUnit.GALLON).convertTo(VolumeUnit.LITRE)); }
    @Test void test1LitreToMlConversion() { assertEquals(new Quantity<>(1000.0, VolumeUnit.ML), new Quantity<>(1.0, VolumeUnit.LITRE).convertTo(VolumeUnit.ML)); }
    @Test void testAddMlAndGallon() { assertEquals(new Quantity<>(3781.0, VolumeUnit.ML), new Quantity<>(1.0, VolumeUnit.ML).add(new Quantity<>(1.0, VolumeUnit.GALLON), VolumeUnit.ML)); }
    @Test void testAddMixedUnits_LitreAndMl() { assertEquals(new Quantity<>(1500.0, VolumeUnit.ML), new Quantity<>(0.5, VolumeUnit.LITRE).add(new Quantity<>(1000.0, VolumeUnit.ML), VolumeUnit.ML)); }
    @Test void testVolumeNullTargetUnitThrowsException() { assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, VolumeUnit.LITRE).add(new Quantity<>(1.0, VolumeUnit.LITRE), null)); }
    @Test void testVolumeAddLargeValues() { assertEquals(new Quantity<>(200.0, VolumeUnit.GALLON), new Quantity<>(100.0, VolumeUnit.GALLON).add(new Quantity<>(100.0, VolumeUnit.GALLON), VolumeUnit.GALLON)); }
    @Test void testVolumeAddNegativeValues() { assertEquals(new Quantity<>(500.0, VolumeUnit.ML), new Quantity<>(1000.0, VolumeUnit.ML).add(new Quantity<>(-500.0, VolumeUnit.ML), VolumeUnit.ML)); }
    @Test void testPrecisionAddition_Volume() { assertEquals(new Quantity<>(4.78, VolumeUnit.LITRE), new Quantity<>(1.0, VolumeUnit.LITRE).add(new Quantity<>(1.0, VolumeUnit.GALLON), VolumeUnit.LITRE)); }
    @Test void testTypeSafety_VolumeAndLength() { Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE); Quantity<LengthUnit> l = new Quantity<>(1.0, LengthUnit.FEET); assertNotEquals(v, l); }
    @Test void testTypeSafety_VolumeAndWeight() { Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE); Quantity<WeightUnit> w = new Quantity<>(1.0, WeightUnit.KG); assertNotEquals(v, w); }
    @Test void testGallonToMlConversion() { assertEquals(new Quantity<>(3780.0, VolumeUnit.ML), new Quantity<>(1.0, VolumeUnit.GALLON).convertTo(VolumeUnit.ML)); }
    @Test void test0VolumeConversion() { assertEquals(new Quantity<>(0.0, VolumeUnit.LITRE), new Quantity<>(0.0, VolumeUnit.ML).convertTo(VolumeUnit.LITRE)); }
    @Test void testVolumeEquality_Reference() { Quantity<VolumeUnit> q = new Quantity<>(5.0, VolumeUnit.GALLON); assertTrue(q.equals(q)); }
    @Test void testVolumeEquality_ClassType() { Quantity<VolumeUnit> q = new Quantity<>(5.0, VolumeUnit.GALLON); assertNotEquals(q, "5 Gallon"); }
    @Test void testAddLiterAndMlInGallon() { assertEquals(new Quantity<>(0.529, VolumeUnit.GALLON), new Quantity<>(1.0, VolumeUnit.LITRE).add(new Quantity<>(1000.0, VolumeUnit.ML), VolumeUnit.GALLON)); }
    @Test void testLargeVolumeAdditionInMl() { assertEquals(new Quantity<>(7560.0, VolumeUnit.ML), new Quantity<>(1.0, VolumeUnit.GALLON).add(new Quantity<>(1.0, VolumeUnit.GALLON), VolumeUnit.ML)); }
    @Test void testVolumeConversionToSameUnit() { assertEquals(new Quantity<>(10.0, VolumeUnit.LITRE), new Quantity<>(10.0, VolumeUnit.LITRE).convertTo(VolumeUnit.LITRE)); }
    @Test void testVolumeInequality_DifferentUnitSameValue() { assertNotEquals(new Quantity<>(1.0, VolumeUnit.GALLON), new Quantity<>(1.0, VolumeUnit.LITRE)); }
    @Test void testAddMlAndLiterToLiter() { assertEquals(new Quantity<>(2.0, VolumeUnit.LITRE), new Quantity<>(1000.0, VolumeUnit.ML).add(new Quantity<>(1.0, VolumeUnit.LITRE), VolumeUnit.LITRE)); }
    @Test void testLargeMlToGallonConversion() { assertEquals(new Quantity<>(10.0, VolumeUnit.GALLON), new Quantity<>(37800.0, VolumeUnit.ML).convertTo(VolumeUnit.GALLON)); }
    @Test void testSmallVolumeEquality() { assertEquals(new Quantity<>(0.001, VolumeUnit.LITRE), new Quantity<>(1.0, VolumeUnit.ML)); }
    @Test void testVolumeInequality_SmallDiff() { assertNotEquals(new Quantity<>(1.0, VolumeUnit.ML), new Quantity<>(1.1, VolumeUnit.ML)); }
    @Test void testAddMixedUnits_GallonAndMl() { assertEquals(new Quantity<>(4780.0, VolumeUnit.ML), new Quantity<>(1.0, VolumeUnit.GALLON).add(new Quantity<>(1000.0, VolumeUnit.ML), VolumeUnit.ML)); }
    @Test void testVolumeEquality_Decimals() { assertEquals(new Quantity<>(3.78, VolumeUnit.LITRE), new Quantity<>(1.0, VolumeUnit.GALLON)); }
    @Test void testVolumeEquality_Zero() { assertEquals(new Quantity<>(0.0, VolumeUnit.LITRE), new Quantity<>(0.0, VolumeUnit.GALLON)); }
    @Test void testVolumeEquality_Precision() { assertEquals(new Quantity<>(3.781, VolumeUnit.LITRE), new Quantity<>(3.781, VolumeUnit.LITRE)); }
    @Test void testVolumeInequality_Precision() { assertNotEquals(new Quantity<>(3.781, VolumeUnit.LITRE), new Quantity<>(3.782, VolumeUnit.LITRE)); }
    @Test void testVolumeAdd_Precision() { assertEquals(new Quantity<>(4.78, VolumeUnit.LITRE), new Quantity<>(1.0, VolumeUnit.LITRE).add(new Quantity<>(1.0, VolumeUnit.GALLON), VolumeUnit.LITRE)); }
    @Test void testVolumeAdd_Decimals() { assertEquals(new Quantity<>(10.0, VolumeUnit.LITRE), new Quantity<>(5.5, VolumeUnit.LITRE).add(new Quantity<>(4.5, VolumeUnit.LITRE), VolumeUnit.LITRE)); }
    @Test void testVolumeAdd_Negative() { assertEquals(new Quantity<>(5.0, VolumeUnit.LITRE), new Quantity<>(10.0, VolumeUnit.LITRE).add(new Quantity<>(-5.0, VolumeUnit.LITRE), VolumeUnit.LITRE)); }
    @Test void testVolumeEquality_LargeValues() { assertEquals(new Quantity<>(1000.0, VolumeUnit.LITRE), new Quantity<>(1000000.0, VolumeUnit.ML)); }
    @Test void testVolumeEquality_Mixed() { assertEquals(new Quantity<>(1.0, VolumeUnit.GALLON), new Quantity<>(3.78, VolumeUnit.LITRE)); }
    @Test void testVolumeEquality_Final() { assertEquals(new Quantity<>(2.0, VolumeUnit.GALLON), new Quantity<>(7.56, VolumeUnit.LITRE)); }
}
