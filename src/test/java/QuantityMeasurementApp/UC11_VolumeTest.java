package QuantityMeasurementApp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class UC11_VolumeTest {
    @Test void test1GallonTo3_78LitersEquality() { assertEquals(new Quantity<>(1.0, VolumeUnit.GALLON), new Quantity<>(3.78, VolumeUnit.LITRE)); }
    @Test void test1LitreTo1000MlEquality() { assertEquals(new Quantity<>(1.0, VolumeUnit.LITRE), new Quantity<>(1000.0, VolumeUnit.ML)); }
}
