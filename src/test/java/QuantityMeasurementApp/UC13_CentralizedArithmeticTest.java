package QuantityMeasurementApp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class UC13_CentralizedArithmeticTest {
    @Test void testCentralizedAdd() { assertEquals(new Quantity<>(14.0, LengthUnit.INCHES), new Quantity<>(1.0, LengthUnit.FEET).add(new Quantity<>(2.0, LengthUnit.INCHES), LengthUnit.INCHES)); }
    @Test void testCentralizedSubtract() { assertEquals(new Quantity<>(10.0, LengthUnit.INCHES), new Quantity<>(1.0, LengthUnit.FEET).subtract(new Quantity<>(2.0, LengthUnit.INCHES), LengthUnit.INCHES)); }
    @Test void testCentralizedMultiply() { assertEquals(new Quantity<>(2.0, LengthUnit.FEET), new Quantity<>(1.0, LengthUnit.FEET).multiply(2.0, LengthUnit.FEET)); }
    @Test void testCentralizedDivide() { assertEquals(new Quantity<>(6.0, LengthUnit.INCHES), new Quantity<>(1.0, LengthUnit.FEET).divide(2.0, LengthUnit.INCHES)); }
    @Test void testArithmetic_NullOperand() { assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, LengthUnit.FEET).add(null, LengthUnit.FEET)); }
    @Test void testArithmetic_NullTargetUnit() { assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, LengthUnit.FEET).add(new Quantity<>(1.0, LengthUnit.FEET), null)); }
    @Test void testArithmetic_DifferentCategories() { Quantity raw1 = new Quantity(1.0, LengthUnit.FEET); Quantity raw2 = new Quantity(1.0, WeightUnit.KG); assertThrows(IllegalArgumentException.class, () -> raw1.add(raw2, LengthUnit.FEET)); }
    @Test void testCentralizedArithmetic_Zero() { assertEquals(new Quantity<>(0.0, LengthUnit.FEET), new Quantity<>(1.0, LengthUnit.FEET).multiply(0.0, LengthUnit.FEET)); }
    @Test void testCentralizedArithmetic_Negative() { assertEquals(new Quantity<>(-1.0, LengthUnit.FEET), new Quantity<>(1.0, LengthUnit.FEET).multiply(-1.0, LengthUnit.FEET)); }
    @Test void testCentralizedArithmetic_Large() { assertEquals(new Quantity<>(2.0, WeightUnit.TONNE), new Quantity<>(1.0, WeightUnit.TONNE).add(new Quantity<>(1000.0, WeightUnit.KG), WeightUnit.TONNE)); }
    @Test void testCentralizedArithmetic_Chain() { Quantity<LengthUnit> res = new Quantity<>(1.0, LengthUnit.FEET).add(new Quantity<>(12.0, LengthUnit.INCHES), LengthUnit.FEET).subtract(new Quantity<>(1.0, LengthUnit.FEET), LengthUnit.FEET); assertEquals(new Quantity<>(1.0, LengthUnit.FEET), res); }
    @Test void testCentralizedArithmetic_DivideByZero() { assertThrows(ArithmeticException.class, () -> new Quantity<>(1.0, LengthUnit.FEET).divide(0.0, LengthUnit.FEET)); }
    @Test void testCentralizedArithmetic_SameUnitAdd() { assertEquals(new Quantity<>(2.0, LengthUnit.FEET), new Quantity<>(1.0, LengthUnit.FEET).add(new Quantity<>(1.0, LengthUnit.FEET), LengthUnit.FEET)); }
    @Test void testCentralizedArithmetic_SameUnitSub() { assertEquals(new Quantity<>(0.0, LengthUnit.FEET), new Quantity<>(1.0, LengthUnit.FEET).subtract(new Quantity<>(1.0, LengthUnit.FEET), LengthUnit.FEET)); }
    @Test void testCentralizedArithmetic_ConversionAdd() { assertEquals(new Quantity<>(24.0, LengthUnit.INCHES), new Quantity<>(1.0, LengthUnit.FEET).add(new Quantity<>(1.0, LengthUnit.FEET), LengthUnit.INCHES)); }
    @Test void testCentralizedArithmetic_ConversionSub() { assertEquals(new Quantity<>(12.0, LengthUnit.INCHES), new Quantity<>(2.0, LengthUnit.FEET).subtract(new Quantity<>(1.0, LengthUnit.FEET), LengthUnit.INCHES)); }
    @Test void testCentralizedArithmetic_WeightAdd() { assertEquals(new Quantity<>(2000.0, WeightUnit.GRAMS), new Quantity<>(1.0, WeightUnit.KG).add(new Quantity<>(1.0, WeightUnit.KG), WeightUnit.GRAMS)); }
    @Test void testCentralizedArithmetic_VolumeAdd() { assertEquals(new Quantity<>(2.0, VolumeUnit.GALLON), new Quantity<>(1.0, VolumeUnit.GALLON).add(new Quantity<>(3.78, VolumeUnit.LITRE), VolumeUnit.GALLON)); }
    @Test void testCentralizedArithmetic_Decimals() { assertEquals(new Quantity<>(1.5, LengthUnit.FEET), new Quantity<>(1.0, LengthUnit.FEET).add(new Quantity<>(6.0, LengthUnit.INCHES), LengthUnit.FEET)); }
    @Test void testArithmetic_SubtractLarge() { assertEquals(new Quantity<>(-12.0, LengthUnit.INCHES), new Quantity<>(1.0, LengthUnit.FEET).subtract(new Quantity<>(2.0, LengthUnit.FEET), LengthUnit.INCHES)); }
    @Test void testArithmetic_MultiplyLarge() { assertEquals(new Quantity<>(36.0, LengthUnit.INCHES), new Quantity<>(1.0, LengthUnit.YARDS).multiply(1.0, LengthUnit.INCHES)); }
    @Test void testArithmetic_DivideLarge() { assertEquals(new Quantity<>(18.0, LengthUnit.INCHES), new Quantity<>(1.0, LengthUnit.YARDS).divide(2.0, LengthUnit.INCHES)); }
    @Test void testCentralized_WeightSub() { assertEquals(new Quantity<>(500.0, WeightUnit.GRAMS), new Quantity<>(1.0, WeightUnit.KG).subtract(new Quantity<>(500.0, WeightUnit.GRAMS), WeightUnit.GRAMS)); }
    @Test void testCentralized_WeightMultiply() { assertEquals(new Quantity<>(2000.0, WeightUnit.GRAMS), new Quantity<>(1.0, WeightUnit.KG).multiply(2.0, WeightUnit.GRAMS)); }
    @Test void testCentralized_WeightDivide() { assertEquals(new Quantity<>(500.0, WeightUnit.GRAMS), new Quantity<>(1.0, WeightUnit.KG).divide(2.0, WeightUnit.GRAMS)); }
    @Test void testCentralized_VolumeSub() { assertEquals(new Quantity<>(2.78, VolumeUnit.LITRE), new Quantity<>(1.0, VolumeUnit.GALLON).subtract(new Quantity<>(1000.0, VolumeUnit.ML), VolumeUnit.LITRE)); }
    @Test void testCentralized_VolumeMultiply() { assertEquals(new Quantity<>(7.56, VolumeUnit.LITRE), new Quantity<>(1.0, VolumeUnit.GALLON).multiply(2.0, VolumeUnit.LITRE)); }
    @Test void testCentralized_VolumeDivide() { assertEquals(new Quantity<>(1.89, VolumeUnit.LITRE), new Quantity<>(1.0, VolumeUnit.GALLON).divide(2.0, VolumeUnit.LITRE)); }
    @Test void testCentralized_CentimeterAdd() { assertEquals(new Quantity<>(5.0, LengthUnit.CENTIMETERS), new Quantity<>(2.5, LengthUnit.CENTIMETERS).add(new Quantity<>(2.5, LengthUnit.CENTIMETERS), LengthUnit.CENTIMETERS)); }
    @Test void testCentralized_CentimeterSub() { assertEquals(new Quantity<>(0.0, LengthUnit.CENTIMETERS), new Quantity<>(2.5, LengthUnit.CENTIMETERS).subtract(new Quantity<>(2.5, LengthUnit.CENTIMETERS), LengthUnit.CENTIMETERS)); }
    @Test void testCentralized_CentimeterMultiply() { assertEquals(new Quantity<>(5.0, LengthUnit.CENTIMETERS), new Quantity<>(2.5, LengthUnit.CENTIMETERS).multiply(2.0, LengthUnit.CENTIMETERS)); }
    @Test void testCentralized_CentimeterDivide() { assertEquals(new Quantity<>(1.25, LengthUnit.CENTIMETERS), new Quantity<>(2.5, LengthUnit.CENTIMETERS).divide(2.0, LengthUnit.CENTIMETERS)); }
    @Test void testArithmetic_LargeValueAdd() { assertEquals(new Quantity<>(200.0, LengthUnit.FEET), new Quantity<>(100.0, LengthUnit.FEET).add(new Quantity<>(100.0, LengthUnit.FEET), LengthUnit.FEET)); }
    @Test void testArithmetic_LargeValueSub() { assertEquals(new Quantity<>(0.0, LengthUnit.FEET), new Quantity<>(100.0, LengthUnit.FEET).subtract(new Quantity<>(100.0, LengthUnit.FEET), LengthUnit.FEET)); }
    @Test void testArithmetic_LargeValueMultiply() { assertEquals(new Quantity<>(1000.0, LengthUnit.FEET), new Quantity<>(100.0, LengthUnit.FEET).multiply(10.0, LengthUnit.FEET)); }
    @Test void testArithmetic_LargeValueDivide() { assertEquals(new Quantity<>(10.0, LengthUnit.FEET), new Quantity<>(100.0, LengthUnit.FEET).divide(10.0, LengthUnit.FEET)); }
    @Test void testCentralized_ZeroSubtract() { assertEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(1.0, LengthUnit.FEET).subtract(new Quantity<>(0.0, LengthUnit.FEET), LengthUnit.FEET)); }
    @Test void testCentralized_ZeroDivide() { assertThrows(ArithmeticException.class, () -> new Quantity<>(1.0, LengthUnit.FEET).divide(0.0, LengthUnit.FEET)); }
}
