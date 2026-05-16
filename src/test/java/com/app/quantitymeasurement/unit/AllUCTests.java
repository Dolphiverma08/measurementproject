package com.app.quantitymeasurement.unit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AllUCTests {

    // --- UC1: Length Equality ---
    @Test
    void testEquality_FeetToFeet() {
        assertEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(1.0, LengthUnit.FEET));
        assertNotEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(2.0, LengthUnit.FEET));
    }

    @Test
    void testEquality_Null() {
        assertNotEquals(new Quantity<>(1.0, LengthUnit.FEET), null);
    }

    @Test
    void testEquality_DifferentType() {
        assertNotEquals(new Quantity<>(1.0, LengthUnit.FEET), new Object());
    }

    @Test
    void testEquality_FeetToInches() {
        assertEquals(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(12.0, LengthUnit.INCHES));
        assertEquals(new Quantity<>(12.0, LengthUnit.INCHES), new Quantity<>(1.0, LengthUnit.FEET));
    }

    // --- UC2: Length Addition ---
    @Test
    void testAddition_InchesToInches() {
        assertEquals(new Quantity<>(4.0, LengthUnit.INCHES), 
                     new Quantity<>(2.0, LengthUnit.INCHES).add(new Quantity<>(2.0, LengthUnit.INCHES)));
    }

    @Test
    void testAddition_FeetToInches() {
        assertEquals(new Quantity<>(24.0, LengthUnit.INCHES), 
                     new Quantity<>(1.0, LengthUnit.FEET).add(new Quantity<>(12.0, LengthUnit.INCHES), LengthUnit.INCHES));
    }

    // --- UC4: Volume Equality ---
    @Test
    void testEquality_GallonToGallon() {
        assertEquals(new Quantity<>(1.0, VolumeUnit.GALLON), new Quantity<>(1.0, VolumeUnit.GALLON));
    }

    @Test
    void testEquality_GallonToLitre() {
        assertEquals(new Quantity<>(1.0, VolumeUnit.GALLON), new Quantity<>(3.78541, VolumeUnit.LITRE));
    }

    // --- UC5: Volume Addition ---
    @Test
    void testAddition_GallonToLitre() {
        assertEquals(new Quantity<>(7.57, VolumeUnit.LITRE), 
                     new Quantity<>(1.0, VolumeUnit.GALLON).add(new Quantity<>(3.78, VolumeUnit.LITRE), VolumeUnit.LITRE));
    }

    // --- UC7: Weight Equality ---
    @Test
    void testEquality_KgToGrams() {
        assertEquals(new Quantity<>(1.0, WeightUnit.KILOGRAM), new Quantity<>(1000.0, WeightUnit.GRAM));
    }

    @Test
    void testEquality_PoundToKg() {
        // 2.20462 * (1/2.20462) = 1.0, which matches 1.0 * 1.0 exactly.
        assertEquals(new Quantity<>(1.0, WeightUnit.KILOGRAM), new Quantity<>(2.20462, WeightUnit.POUND));
    }

    // --- UC8: Weight Addition ---
    @Test
    void testAddition_PoundToGrams() {
        assertEquals(new Quantity<>(1.45, WeightUnit.KILOGRAM), 
                     new Quantity<>(1.0, WeightUnit.POUND).add(new Quantity<>(1000.0, WeightUnit.GRAM), WeightUnit.KILOGRAM));
    }

    // --- UC10: Category Isolation ---
    @Test
    @SuppressWarnings({"unchecked", "rawtypes"})
    void testCategoryIsolation_LengthToWeight() {
        Quantity length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertNotEquals(length, weight);
        assertThrows(IllegalArgumentException.class, () -> length.add(weight, LengthUnit.FEET));
    }

    @Test
    @SuppressWarnings({"unchecked", "rawtypes"})
    void testCategoryIsolation_LengthToVolume() {
        Quantity length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertNotEquals(length, volume);
    }
}
