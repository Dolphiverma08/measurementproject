package com.app.quantitymeasurement.unit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UC7_AdditionTest {

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertEquals(new Quantity<>(2.0, LengthUnit.FEET), q1.add(q2, LengthUnit.FEET));
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertEquals(new Quantity<>(24.0, LengthUnit.INCHES), q1.add(q2, LengthUnit.INCHES));
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        // 2ft = 0.67 yards (rounded)
        assertEquals(new Quantity<>(0.67, LengthUnit.YARDS), q1.add(q2, LengthUnit.YARDS));
    }

    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(1.0, LengthUnit.FEET);
        // 2ft = 60.96 cm
        assertEquals(new Quantity<>(60.96, LengthUnit.CENTIMETERS), q1.add(q2, LengthUnit.CENTIMETERS));
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
        Quantity<LengthUnit> q1 = new Quantity<>(2.0, LengthUnit.YARDS);
        Quantity<LengthUnit> q2 = new Quantity<>(3.0, LengthUnit.FEET);
        assertEquals(new Quantity<>(3.0, LengthUnit.YARDS), q1.add(q2, LengthUnit.YARDS));
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
        Quantity<LengthUnit> q1 = new Quantity<>(2.0, LengthUnit.YARDS);
        Quantity<LengthUnit> q2 = new Quantity<>(3.0, LengthUnit.FEET);
        assertEquals(new Quantity<>(9.0, LengthUnit.FEET), q1.add(q2, LengthUnit.FEET));
    }

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertEquals(q1.add(q2, LengthUnit.YARDS), q2.add(q1, LengthUnit.YARDS));
    }

    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {
        Quantity<LengthUnit> q1 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(0.0, LengthUnit.INCHES);
        assertEquals(new Quantity<>(1.67, LengthUnit.YARDS), q1.add(q2, LengthUnit.YARDS));
    }

    @Test
    void testAddition_ExplicitTargetUnit_NegativeValues() {
        Quantity<LengthUnit> q1 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(-2.0, LengthUnit.FEET);
        assertEquals(new Quantity<>(36.0, LengthUnit.INCHES), q1.add(q2, LengthUnit.INCHES));
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullTargetUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q1.add(q2, null));
    }

    @Test
    void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
        Quantity<LengthUnit> q1 = new Quantity<>(500.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(1000.0, LengthUnit.FEET);
        assertEquals(new Quantity<>(18000.0, LengthUnit.INCHES), q1.add(q2, LengthUnit.INCHES));
    }

    @Test
    void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
        Quantity<LengthUnit> q1 = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        // 24in = 2ft = 0.67 yards
        assertEquals(new Quantity<>(0.67, LengthUnit.YARDS), q1.add(q2, LengthUnit.YARDS));
    }

    @Test
    void testAddition_ExplicitTargetUnit_AllUnitCombinations() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(1.0, LengthUnit.INCHES);
        assertNotNull(q1.add(q2, LengthUnit.YARDS));
        assertNotNull(q1.add(q2, LengthUnit.CENTIMETERS));
    }

    @Test
    void testAddition_ExplicitTargetUnit_PrecisionTolerance() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = q1.add(q2, LengthUnit.YARDS);
        assertEquals(0.67, result.getValue(), 0.01);
    }
}
