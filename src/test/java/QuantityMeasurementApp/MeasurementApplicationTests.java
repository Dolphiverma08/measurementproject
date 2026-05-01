package QuantityMeasurementApp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MeasurementApplicationTests {

    // --- UC8: Length (preserved) ---

    @Test
    void testLength_FeetToInches_Equality() {
        assertEquals(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(12.0, LengthUnit.INCHES));
    }

    @Test
    void testLength_Addition_CrossUnit() {
        QuantityLength result = new QuantityLength(1.0, LengthUnit.FEET).add(new QuantityLength(12.0, LengthUnit.INCHES));
        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    // --- UC9: Weight Equality ---

    @Test
    void testEquality_KilogramToKilogram_SameValue() {
        assertEquals(new QuantityWeight(1.0, WeightUnit.KILOGRAM), new QuantityWeight(1.0, WeightUnit.KILOGRAM));
    }

    @Test
    void testEquality_KilogramToGram_EquivalentValue() {
        assertEquals(new QuantityWeight(1.0, WeightUnit.KILOGRAM), new QuantityWeight(1000.0, WeightUnit.GRAM));
    }

    @Test
    void testEquality_GramToKilogram_EquivalentValue() {
        assertEquals(new QuantityWeight(1000.0, WeightUnit.GRAM), new QuantityWeight(1.0, WeightUnit.KILOGRAM));
    }

    @Test
    void testEquality_KilogramToPound_EquivalentValue() {
        assertEquals(new QuantityWeight(1.0, WeightUnit.KILOGRAM), new QuantityWeight(2.20462, WeightUnit.POUND));
    }

    @Test
    void testEquality_WeightVsLength_Incompatible() {
        assertFalse(new QuantityWeight(1.0, WeightUnit.KILOGRAM).equals(new QuantityLength(1.0, LengthUnit.FEET)));
    }

    @Test
    void testEquality_Weight_NullComparison() {
        assertFalse(new QuantityWeight(1.0, WeightUnit.KILOGRAM).equals(null));
    }

    @Test
    void testEquality_Weight_SameReference() {
        QuantityWeight w = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        assertTrue(w.equals(w));
    }

    // --- UC9: Weight Conversion ---

    @Test
    void testConversion_KilogramToGram() {
        assertEquals(new QuantityWeight(1000.0, WeightUnit.GRAM),
                new QuantityWeight(1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM));
    }

    @Test
    void testConversion_GramToKilogram() {
        assertEquals(new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1000.0, WeightUnit.GRAM).convertTo(WeightUnit.KILOGRAM));
    }

    // --- UC9: Weight Addition ---

    @Test
    void testAddition_KilogramPlusKilogram() {
        assertEquals(new QuantityWeight(3.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1.0, WeightUnit.KILOGRAM).add(new QuantityWeight(2.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testAddition_KilogramPlusGram_CrossUnit() {
        assertEquals(new QuantityWeight(2.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1.0, WeightUnit.KILOGRAM).add(new QuantityWeight(1000.0, WeightUnit.GRAM)));
    }

    @Test
    void testAddition_ExplicitTarget_Gram() {
        assertEquals(new QuantityWeight(2000.0, WeightUnit.GRAM),
                new QuantityWeight(1.0, WeightUnit.KILOGRAM).add(new QuantityWeight(1000.0, WeightUnit.GRAM), WeightUnit.GRAM));
    }

    @Test
    void testConstructor_NullUnit_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityWeight(1.0, null));
    }
}
