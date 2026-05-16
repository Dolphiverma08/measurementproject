package com.app.quantitymeasurement.unit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AdditionTest {

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);
        assertEquals(new Quantity<>(3.0, LengthUnit.FEET), q1.add(q2));
    }

    @Test
    void testAddition_SameUnit_InchPlusInch() {
        Quantity<LengthUnit> q1 = new Quantity<>(6.0, LengthUnit.INCHES);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);
        assertEquals(new Quantity<>(12.0, LengthUnit.INCHES), q1.add(q2));
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertEquals(new Quantity<>(2.0, LengthUnit.FEET), q1.add(q2));
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet() {
        Quantity<LengthUnit> q1 = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> q2 = new Quantity<>(1.0, LengthUnit.FEET);
        assertEquals(new Quantity<>(24.0, LengthUnit.INCHES), q1.add(q2));
    }

    @Test
    void testAddition_CrossUnit_YardPlusFeet() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> q2 = new Quantity<>(3.0, LengthUnit.FEET);
        assertEquals(new Quantity<>(2.0, LengthUnit.YARDS), q1.add(q2));
    }

    @Test
    void testAddition_CrossUnit_CentimeterPlusInch() {
        Quantity<LengthUnit> q1 = new Quantity<>(2.54, LengthUnit.CENTIMETERS);
        Quantity<LengthUnit> q2 = new Quantity<>(1.0, LengthUnit.INCHES);
        // 2.54cm + 1in = 1in + 1in = 2in = 5.08 cm
        assertEquals(new Quantity<>(5.08, LengthUnit.CENTIMETERS), q1.add(q2));
    }

    @Test
    void testAddition_Commutativity() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertEquals(q1.add(q2), q2.add(q1).convertTo(LengthUnit.FEET));
    }

    @Test
    void testAddition_WithZero() {
        Quantity<LengthUnit> q1 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(0.0, LengthUnit.INCHES);
        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), q1.add(q2));
    }

    @Test
    void testAddition_NegativeValues() {
        Quantity<LengthUnit> q1 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(-2.0, LengthUnit.FEET);
        assertEquals(new Quantity<>(3.0, LengthUnit.FEET), q1.add(q2));
    }

    @Test
    void testAddition_NullSecondOperand() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q1.add(null));
    }

    @Test
    void testAddition_LargeValues() {
        Quantity<LengthUnit> q1 = new Quantity<>(1000000.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(1000000.0, LengthUnit.FEET);
        assertEquals(new Quantity<>(2000000.0, LengthUnit.FEET), q1.add(q2));
    }

    @Test
    void testAddition_SmallValues() {
        Quantity<LengthUnit> q1 = new Quantity<>(0.001, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(0.002, LengthUnit.FEET);
        assertEquals(new Quantity<>(0.00, LengthUnit.FEET), q1.add(q2)); 
        // Note: 0.003 rounds to 0.00 with 2-decimal precision. 
        // If we want 0.003, we need higher precision.
    }
}
