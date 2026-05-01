package QuantityMeasurementApp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MeasurementApplicationTests {

    // --- UC6: Addition implicit target (preserved) ---

    @Test
    void testAddition_CrossUnit_FeetPlusInches_ImplicitTarget() {
        QuantityLength result = new QuantityLength(1.0, LengthUnit.FEET).add(new QuantityLength(12.0, LengthUnit.INCHES));
        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    // --- UC7: Addition with explicit target unit ---

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        QuantityLength result = new QuantityLength(1.0, LengthUnit.FEET)
                .add(new QuantityLength(12.0, LengthUnit.INCHES), LengthUnit.FEET);
        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        QuantityLength result = new QuantityLength(1.0, LengthUnit.FEET)
                .add(new QuantityLength(12.0, LengthUnit.INCHES), LengthUnit.INCHES);
        assertEquals(new QuantityLength(24.0, LengthUnit.INCHES), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        QuantityLength result = new QuantityLength(1.0, LengthUnit.FEET)
                .add(new QuantityLength(12.0, LengthUnit.INCHES), LengthUnit.YARDS);
        assertEquals(new QuantityLength(0.67, LengthUnit.YARDS), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
        QuantityLength result = new QuantityLength(1.0, LengthUnit.YARDS)
                .add(new QuantityLength(3.0, LengthUnit.FEET), LengthUnit.YARDS);
        assertEquals(new QuantityLength(2.0, LengthUnit.YARDS), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {
        QuantityLength ab = new QuantityLength(1.0, LengthUnit.FEET)
                .add(new QuantityLength(12.0, LengthUnit.INCHES), LengthUnit.FEET);
        QuantityLength ba = new QuantityLength(12.0, LengthUnit.INCHES)
                .add(new QuantityLength(1.0, LengthUnit.FEET), LengthUnit.FEET);
        assertEquals(ab, ba);
    }

    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {
        QuantityLength result = new QuantityLength(5.0, LengthUnit.FEET)
                .add(new QuantityLength(0.0, LengthUnit.INCHES), LengthUnit.YARDS);
        assertEquals(new QuantityLength(1.67, LengthUnit.YARDS), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NegativeValues() {
        QuantityLength result = new QuantityLength(5.0, LengthUnit.FEET)
                .add(new QuantityLength(-2.0, LengthUnit.FEET), LengthUnit.INCHES);
        assertEquals(new QuantityLength(36.0, LengthUnit.INCHES), result);
    }
}
