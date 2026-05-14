package com.app.quantitymeasurement.unit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TemperatureTest {

    @Test
    public void testTemperatureEquality_CelsiusToCelsius_SameValue() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        assertEquals(q1, q2);
    }

    @Test
    public void testTemperatureEquality_FahrenheitToFahrenheit_SameValue() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> q2 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        assertEquals(q1, q2);
    }

    @Test
    public void testTemperatureEquality_KelvinToKelvin_SameValue() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(273.15, TemperatureUnit.KELVIN);
        Quantity<TemperatureUnit> q2 = new Quantity<>(273.15, TemperatureUnit.KELVIN);
        assertEquals(q1, q2);
    }

    @Test
    public void testTemperatureEquality_CelsiusToFahrenheit_0Celsius32Fahrenheit() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        assertEquals(q1, q2);
    }

    @Test
    public void testTemperatureEquality_CelsiusToFahrenheit_100Celsius212Fahrenheit() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT);
        assertEquals(q1, q2);
    }

    @Test
    public void testTemperatureEquality_CelsiusToKelvin_0Celsius() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(273.15, TemperatureUnit.KELVIN);
        assertEquals(q1, q2);
    }

    @Test
    public void testTemperatureEquality_100CelsiusTo373_15Kelvin() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(373.15, TemperatureUnit.KELVIN);
        assertEquals(q1, q2);
    }

    @Test
    public void testTemperatureEquality_CelsiusToFahrenheit_Negative40Equal() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(-40.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(-40.0, TemperatureUnit.FAHRENHEIT);
        assertEquals(q1, q2);
    }

    @Test
    public void testTemperatureConversion_CelsiusToFahrenheit_VariousValues() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = q1.convertTo(TemperatureUnit.FAHRENHEIT);
        assertEquals(new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT), q2);
    }

    @Test
    public void testTemperatureConversion_FahrenheitToCelsius_VariousValues() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> q2 = q1.convertTo(TemperatureUnit.CELSIUS);
        assertEquals(new Quantity<>(0.0, TemperatureUnit.CELSIUS), q2);
    }

    @Test
    public void testTemperatureUnsupportedOperation_Add() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, () -> q1.add(q2));
        assertEquals("Temperature does not support ADD", exception.getMessage());
    }

    @Test
    public void testTemperatureUnsupportedOperation_Subtract() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, () -> q1.subtract(q2));
        assertEquals("Temperature does not support SUBTRACT", exception.getMessage());
    }

    @Test
    public void testTemperatureUnsupportedOperation_Divide() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, () -> q1.divide(q2));
        assertEquals("Temperature does not support DIVIDE", exception.getMessage());
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void testTemperatureVsLengthIncompatibility() {
        Quantity q1 = new Quantity(100.0, TemperatureUnit.CELSIUS);
        Quantity q2 = new Quantity(100.0, LengthUnit.FEET);
        assertNotEquals(q1, q2);
    }

    @Test
    public void testOperationSupportMethods_TemperatureUnit_Addition() {
        assertFalse(TemperatureUnit.CELSIUS.supportsArithmetic());
    }

    @Test
    public void testOperationSupportMethods_LengthUnit_Addition() {
        assertTrue(LengthUnit.FEET.supportsArithmetic());
    }
}
