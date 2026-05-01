package QuantityMeasurementApp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MeasurementApplicationTests {

    // --- UC10: Generic Quantity<U> - Length ---

    @Test
    void testGenericQuantity_LengthEquality_FeetToInches() {
        assertEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(12.0, LengthUnit.INCHES));
    }

    @Test
    void testGenericQuantity_LengthEquality_YardToFeet() {
        assertEquals(new Quantity<>(1.0, LengthUnit.YARDS), new Quantity<>(3.0, LengthUnit.FEET));
    }

    @Test
    void testGenericQuantity_LengthConversion_FeetToInches() {
        assertEquals(new Quantity<>(12.0, LengthUnit.INCHES),
                new Quantity<>(1.0, LengthUnit.FEET).convertTo(LengthUnit.INCHES));
    }

    @Test
    void testGenericQuantity_LengthAddition_CrossUnit() {
        assertEquals(new Quantity<>(2.0, LengthUnit.FEET),
                new Quantity<>(1.0, LengthUnit.FEET).add(new Quantity<>(12.0, LengthUnit.INCHES)));
    }

    // --- UC10: Generic Quantity<U> - Weight ---

    @Test
    void testGenericQuantity_WeightEquality_KgToGram() {
        assertEquals(new Quantity<>(1.0, WeightUnit.KILOGRAM), new Quantity<>(1000.0, WeightUnit.GRAM));
    }

    @Test
    void testGenericQuantity_WeightConversion_KgToGram() {
        assertEquals(new Quantity<>(1000.0, WeightUnit.GRAM),
                new Quantity<>(1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM));
    }

    @Test
    void testGenericQuantity_WeightAddition_CrossUnit() {
        assertEquals(new Quantity<>(2.0, WeightUnit.KILOGRAM),
                new Quantity<>(1.0, WeightUnit.KILOGRAM).add(new Quantity<>(1000.0, WeightUnit.GRAM)));
    }

    // --- UC10: Cross-category prevention ---

    @Test
    void testCrossCategoryPrevention_LengthVsWeight() {
        assertFalse(new Quantity<>(1.0, LengthUnit.FEET).equals(new Quantity<>(1.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testConstructor_NullUnit_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, null));
    }

    @Test
    void testConstructor_NaNValue_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }

    @Test
    void testGenericQuantity_SameReference() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        assertTrue(q.equals(q));
    }

    @Test
    void testGenericQuantity_NullComparison() {
        assertFalse(new Quantity<>(1.0, LengthUnit.FEET).equals(null));
    }
}
