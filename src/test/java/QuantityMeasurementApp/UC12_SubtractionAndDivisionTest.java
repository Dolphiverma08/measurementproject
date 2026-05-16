package QuantityMeasurementApp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class UC12_SubtractionAndDivisionTest {
    @Test void testSubtract2InchesFrom4Inches() { assertEquals(new Quantity<>(2.0, LengthUnit.INCHES), new Quantity<>(4.0, LengthUnit.INCHES).subtract(new Quantity<>(2.0, LengthUnit.INCHES), LengthUnit.INCHES)); }
    @Test void testSubtract1FootFrom24Inches() { assertEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(24.0, LengthUnit.INCHES).subtract(new Quantity<>(1.0, LengthUnit.FEET), LengthUnit.FEET)); }
    @Test void testDivide10KgBy2() { assertEquals(new Quantity<>(5.0, WeightUnit.KG), new Quantity<>(10.0, WeightUnit.KG).divide(2.0)); }
    @Test void testDivide1GallonBy4() { assertEquals(new Quantity<>(0.945, VolumeUnit.LITRE), new Quantity<>(1.0, VolumeUnit.GALLON).divide(4.0, VolumeUnit.LITRE)); }
    @Test void testMultiply10InchesBy2() { assertEquals(new Quantity<>(20.0, LengthUnit.INCHES), new Quantity<>(10.0, LengthUnit.INCHES).multiply(2.0)); }
    @Test void testSubtraction_SameValue() { assertEquals(new Quantity<>(0.0, LengthUnit.INCHES), new Quantity<>(10.0, LengthUnit.INCHES).subtract(new Quantity<>(10.0, LengthUnit.INCHES), LengthUnit.INCHES)); }
    @Test void testSubtraction_Null() { assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, LengthUnit.FEET).subtract(null, LengthUnit.FEET)); }
    @Test void testDivision_ByZero() { assertThrows(ArithmeticException.class, () -> new Quantity<>(10.0, LengthUnit.FEET).divide(0.0)); }
    @Test void testSubtraction_DiffTarget() { assertEquals(new Quantity<>(0.5, LengthUnit.YARDS), new Quantity<>(36.0, LengthUnit.INCHES).subtract(new Quantity<>(1.5, LengthUnit.FEET), LengthUnit.YARDS)); }
    @Test void testDivide_WithTarget() { assertEquals(new Quantity<>(6.0, LengthUnit.INCHES), new Quantity<>(1.0, LengthUnit.FEET).divide(2.0, LengthUnit.INCHES)); }
    @Test void testSubtraction_NegativeResult() { assertTrue(new Quantity<>(5.0, LengthUnit.INCHES).subtract(new Quantity<>(10.0, LengthUnit.INCHES), LengthUnit.INCHES).value < 0); }
    @Test void testMultiply_Zero() { assertEquals(new Quantity<>(0.0, LengthUnit.INCHES), new Quantity<>(10.0, LengthUnit.INCHES).multiply(0.0)); }
    @Test void testSubtraction_MixedWeight() { assertEquals(new Quantity<>(500.0, WeightUnit.GRAMS), new Quantity<>(1.0, WeightUnit.KG).subtract(new Quantity<>(500.0, WeightUnit.GRAMS), WeightUnit.GRAMS)); }
    @Test void testSubtraction_MixedVolume() { assertEquals(new Quantity<>(2.78, VolumeUnit.LITRE), new Quantity<>(1.0, VolumeUnit.GALLON).subtract(new Quantity<>(1000.0, VolumeUnit.ML), VolumeUnit.LITRE)); }
    @Test void testDivide_Precision() { assertEquals(new Quantity<>(1.89, VolumeUnit.LITRE), new Quantity<>(1.0, VolumeUnit.GALLON).divide(2.0, VolumeUnit.LITRE)); }
    @Test void testMultiply_Precision() { assertEquals(new Quantity<>(7.56, VolumeUnit.LITRE), new Quantity<>(1.0, VolumeUnit.GALLON).multiply(2.0, VolumeUnit.LITRE)); }
    @Test void testSubtraction_ZeroValue() { assertEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(1.0, LengthUnit.FEET).subtract(new Quantity<>(0.0, LengthUnit.INCHES), LengthUnit.FEET)); }
    @Test void testMultiply_Negative() { assertEquals(new Quantity<>(-20.0, LengthUnit.INCHES), new Quantity<>(10.0, LengthUnit.INCHES).multiply(-2.0)); }
    @Test void testDivide_Negative() { assertEquals(new Quantity<>(-5.0, LengthUnit.INCHES), new Quantity<>(10.0, LengthUnit.INCHES).divide(-2.0)); }
    @Test void testDivide_SmallFactor() { assertEquals(new Quantity<>(100.0, LengthUnit.INCHES), new Quantity<>(10.0, LengthUnit.INCHES).divide(0.1)); }
    @Test void testMultiply_SmallFactor() { assertEquals(new Quantity<>(1.0, LengthUnit.INCHES), new Quantity<>(10.0, LengthUnit.INCHES).multiply(0.1)); }
    @Test void testSubtraction_Commutativity() { Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.INCHES); Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.INCHES); assertNotEquals(q1.subtract(q2, LengthUnit.INCHES), q2.subtract(q1, LengthUnit.INCHES)); }
    @Test void testSubtract_LargeFromSmall() { assertEquals(new Quantity<>(-10.0, LengthUnit.INCHES), new Quantity<>(2.0, LengthUnit.INCHES).subtract(new Quantity<>(1.0, LengthUnit.FEET), LengthUnit.INCHES)); }
    @Test void testSubtract_DifferentTypes() { Quantity raw1 = new Quantity(1.0, LengthUnit.FEET); Quantity raw2 = new Quantity(1.0, WeightUnit.KG); assertThrows(IllegalArgumentException.class, () -> raw1.subtract(raw2, LengthUnit.FEET)); }
    @Test void testSubtract_NullTargetUnit() { assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, LengthUnit.FEET).subtract(new Quantity<>(1.0, LengthUnit.FEET), null)); }
    @Test void testMultiply_Large() { assertEquals(new Quantity<>(200.0, LengthUnit.INCHES), new Quantity<>(100.0, LengthUnit.INCHES).multiply(2.0)); }
    @Test void testDivide_Large() { assertEquals(new Quantity<>(50.0, LengthUnit.INCHES), new Quantity<>(100.0, LengthUnit.INCHES).divide(2.0)); }
    @Test void testSubtract_SmallValue() { assertEquals(new Quantity<>(0.5, LengthUnit.INCHES), new Quantity<>(1.0, LengthUnit.INCHES).subtract(new Quantity<>(0.5, LengthUnit.INCHES), LengthUnit.INCHES)); }
    @Test void testDivide_Decimal() { assertEquals(new Quantity<>(2.5, LengthUnit.INCHES), new Quantity<>(5.0, LengthUnit.INCHES).divide(2.0)); }
    @Test void testMultiply_Decimal() { assertEquals(new Quantity<>(12.5, LengthUnit.INCHES), new Quantity<>(5.0, LengthUnit.INCHES).multiply(2.5)); }
    @Test void testSubtract_Centimeters() { assertEquals(new Quantity<>(5.0, LengthUnit.CENTIMETERS), new Quantity<>(10.0, LengthUnit.CENTIMETERS).subtract(new Quantity<>(5.0, LengthUnit.CENTIMETERS), LengthUnit.CENTIMETERS)); }
    @Test void testDivide_Centimeters() { assertEquals(new Quantity<>(5.0, LengthUnit.CENTIMETERS), new Quantity<>(10.0, LengthUnit.CENTIMETERS).divide(2.0)); }
    @Test void testSubtract_WeightKg() { assertEquals(new Quantity<>(0.5, WeightUnit.KG), new Quantity<>(1.0, WeightUnit.KG).subtract(new Quantity<>(500.0, WeightUnit.GRAMS), WeightUnit.KG)); }
    @Test void testDivide_WeightKg() { assertEquals(new Quantity<>(0.5, WeightUnit.KG), new Quantity<>(1.0, WeightUnit.KG).divide(2.0)); }
    @Test void testSubtract_VolumeGallon() { assertEquals(new Quantity<>(0.5, VolumeUnit.GALLON), new Quantity<>(1.0, VolumeUnit.GALLON).subtract(new Quantity<>(1.89, VolumeUnit.LITRE), VolumeUnit.GALLON)); }
    @Test void testDivide_VolumeGallon() { assertEquals(new Quantity<>(0.25, VolumeUnit.GALLON), new Quantity<>(1.0, VolumeUnit.GALLON).divide(4.0)); }
    @Test void testMultiply_VolumeLitre() { assertEquals(new Quantity<>(2.0, VolumeUnit.LITRE), new Quantity<>(1.0, VolumeUnit.LITRE).multiply(2.0)); }
    @Test void testSubtract_VolumeMl() { assertEquals(new Quantity<>(500.0, VolumeUnit.ML), new Quantity<>(1000.0, VolumeUnit.ML).subtract(new Quantity<>(500.0, VolumeUnit.ML), VolumeUnit.ML)); }
    @Test void testDivide_VolumeMl() { assertEquals(new Quantity<>(250.0, VolumeUnit.ML), new Quantity<>(1000.0, VolumeUnit.ML).divide(4.0)); }
}
