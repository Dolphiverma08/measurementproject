package com.app.quantitymeasurement.unit;

import com.app.quantitymeasurement.entity.QuantityDTO;

public enum WeightUnit implements IMeasurable, QuantityDTO.IMeasurableUnit {
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

    @Override
    public String getUnitName() {
        return name();
    }

    @Override
    public String getMeasurementType() {
        return "WEIGHT";
    }

    @Override
    public IMeasurable getUnitInstance(String name) {
        return WeightUnit.valueOf(name);
    }
}
