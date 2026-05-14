package com.app.quantitymeasurement.entity;

import com.app.quantitymeasurement.unit.Quantity;

public class QuantityDTO {
    public interface IMeasurableUnit {
        String name();
    }

    private double value;
    private IMeasurableUnit unit;
    private String measurementType;
    private boolean hasError;
    private String errorMessage;

    public QuantityDTO(double value, IMeasurableUnit unit, String measurementType) {
        this.value = value;
        this.unit = unit;
        this.measurementType = measurementType;
        this.hasError = false;
    }

    public QuantityDTO(boolean hasError, String errorMessage) {
        this.hasError = hasError;
        this.errorMessage = errorMessage;
    }

    public double getValue() {
        return value;
    }

    public IMeasurableUnit getUnit() {
        return unit;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public boolean hasError() {
        return hasError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        if (hasError) {
            return "Error: " + errorMessage;
        }
        return "Quantity(" + value + ", " + unit.name() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        QuantityDTO other = (QuantityDTO) obj;
        if (hasError != other.hasError) return false;
        if (hasError) return errorMessage != null ? errorMessage.equals(other.errorMessage) : other.errorMessage == null;
        return Double.compare(value, other.value) == 0 && unit.name().equals(other.unit.name());
    }
}
