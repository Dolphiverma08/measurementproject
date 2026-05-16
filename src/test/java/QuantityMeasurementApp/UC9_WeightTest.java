package QuantityMeasurementApp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class UC9_WeightTest {
    @Test void test1KgTo1000GramsEquality() { assertEquals(new QuantityWeight(1.0, WeightUnit.KG), new QuantityWeight(1000.0, WeightUnit.GRAMS)); }
    @Test void test1000GramsTo1KgEquality() { assertEquals(new QuantityWeight(1000.0, WeightUnit.GRAMS), new QuantityWeight(1.0, WeightUnit.KG)); }
    @Test void test1TonneTo1000KgEquality() { assertEquals(new QuantityWeight(1.0, WeightUnit.TONNE), new QuantityWeight(1000.0, WeightUnit.KG)); }
    @Test void test1000KgTo1TonneEquality() { assertEquals(new QuantityWeight(1000.0, WeightUnit.KG), new QuantityWeight(1.0, WeightUnit.TONNE)); }
    @Test void test1KgTo1KgEquality() { assertEquals(new QuantityWeight(1.0, WeightUnit.KG), new QuantityWeight(1.0, WeightUnit.KG)); }
    @Test void test1GramTo1GramEquality() { assertEquals(new QuantityWeight(1.0, WeightUnit.GRAMS), new QuantityWeight(1.0, WeightUnit.GRAMS)); }
    @Test void test1TonneTo1TonneEquality() { assertEquals(new QuantityWeight(1.0, WeightUnit.TONNE), new QuantityWeight(1.0, WeightUnit.TONNE)); }
    @Test void testAdd10GramsAnd1Kg() { assertEquals(new QuantityWeight(1010.0, WeightUnit.GRAMS), new QuantityWeight(10.0, WeightUnit.GRAMS).add(new QuantityWeight(1.0, WeightUnit.KG), WeightUnit.GRAMS)); }
    @Test void testAdd1KgAnd1000Grams() { assertEquals(new QuantityWeight(2.0, WeightUnit.KG), new QuantityWeight(1.0, WeightUnit.KG).add(new QuantityWeight(1000.0, WeightUnit.GRAMS), WeightUnit.KG)); }
    @Test void testAdd1TonneAnd1000Kg() { assertEquals(new QuantityWeight(2.0, WeightUnit.TONNE), new QuantityWeight(1.0, WeightUnit.TONNE).add(new QuantityWeight(1000.0, WeightUnit.KG), WeightUnit.TONNE)); }
    @Test void testAddWithZeroValue() { assertEquals(new QuantityWeight(1.0, WeightUnit.KG), new QuantityWeight(1.0, WeightUnit.KG).add(new QuantityWeight(0.0, WeightUnit.GRAMS), WeightUnit.KG)); }
    @Test void testWeightEquality_Null() { assertNotEquals(new QuantityWeight(1.0, WeightUnit.KG), null); }
    @Test void testWeightEquality_SameRef() { QuantityWeight q = new QuantityWeight(1.0, WeightUnit.KG); assertEquals(q, q); }
    @Test void testWeightEquality_DifferentType() { assertNotEquals(new QuantityWeight(1.0, WeightUnit.KG), new Object()); }
    @Test void testWeightEquality_DifferentValue() { assertNotEquals(new QuantityWeight(1.0, WeightUnit.KG), new QuantityWeight(2.0, WeightUnit.KG)); }
    @Test void testWeightEquality_DifferentUnit() { assertNotEquals(new QuantityWeight(1.0, WeightUnit.KG), new QuantityWeight(1.0, WeightUnit.GRAMS)); }
    @Test void test1KgToGramsConversion() { assertEquals(new QuantityWeight(1000.0, WeightUnit.GRAMS), new QuantityWeight(1.0, WeightUnit.KG).convertTo(WeightUnit.GRAMS)); }
    @Test void test1000GramsToKgConversion() { assertEquals(new QuantityWeight(1.0, WeightUnit.KG), new QuantityWeight(1000.0, WeightUnit.GRAMS).convertTo(WeightUnit.KG)); }
    @Test void test1TonneToKgConversion() { assertEquals(new QuantityWeight(1000.0, WeightUnit.KG), new QuantityWeight(1.0, WeightUnit.TONNE).convertTo(WeightUnit.KG)); }
    @Test void testAddGramsAndTonne() { assertEquals(new QuantityWeight(1.0001, WeightUnit.TONNE), new QuantityWeight(100.0, WeightUnit.GRAMS).add(new QuantityWeight(1.0, WeightUnit.TONNE), WeightUnit.TONNE)); }
    @Test void testAddMixedUnits_KgAndGrams() { assertEquals(new QuantityWeight(1500.0, WeightUnit.GRAMS), new QuantityWeight(0.5, WeightUnit.KG).add(new QuantityWeight(1000.0, WeightUnit.GRAMS), WeightUnit.GRAMS)); }
    @Test void testWeightNullTargetUnitThrowsException() { assertThrows(IllegalArgumentException.class, () -> new QuantityWeight(1.0, WeightUnit.KG).add(new QuantityWeight(1.0, WeightUnit.KG), null)); }
    @Test void testWeightAddLargeValues() { assertEquals(new QuantityWeight(200.0, WeightUnit.TONNE), new QuantityWeight(100.0, WeightUnit.TONNE).add(new QuantityWeight(100.0, WeightUnit.TONNE), WeightUnit.TONNE)); }
    @Test void testWeightAddNegativeValues() { assertEquals(new QuantityWeight(500.0, WeightUnit.GRAMS), new QuantityWeight(1000.0, WeightUnit.GRAMS).add(new QuantityWeight(-500.0, WeightUnit.GRAMS), WeightUnit.GRAMS)); }
    @Test void test1TonneToGramsConversion() { assertEquals(new QuantityWeight(1000000.0, WeightUnit.GRAMS), new QuantityWeight(1.0, WeightUnit.TONNE).convertTo(WeightUnit.GRAMS)); }
    @Test void test0WeightConversion() { assertEquals(new QuantityWeight(0.0, WeightUnit.KG), new QuantityWeight(0.0, WeightUnit.GRAMS).convertTo(WeightUnit.KG)); }
    @Test void testWeightEquality_SameReference() { QuantityWeight q = new QuantityWeight(50.0, WeightUnit.KG); assertTrue(q.equals(q)); }
    @Test void testWeightEquality_ClassType() { QuantityWeight q = new QuantityWeight(50.0, WeightUnit.KG); assertNotEquals(q, "50 KG"); }
}
