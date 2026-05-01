package QuantityMeasurementApp;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Value must be finite");
        this.value = value;
        this.unit = unit;
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U target) {
        validateOperand(other);
        double sum = unit.convertToBaseUnit(this.value) + other.unit.convertToBaseUnit(other.value);
        return new Quantity<>(round(target.convertFromBaseUnit(sum)), target);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U target) {
        validateOperand(other);
        double diff = unit.convertToBaseUnit(this.value) - other.unit.convertToBaseUnit(other.value);
        return new Quantity<>(round(target.convertFromBaseUnit(diff)), target);
    }

    public double divide(Quantity<U> other) {
        validateOperand(other);
        double divisor = other.unit.convertToBaseUnit(other.value);
        if (Double.compare(divisor, 0.0) == 0) throw new ArithmeticException("Cannot divide by zero");
        return unit.convertToBaseUnit(this.value) / divisor;
    }

    public Quantity<U> convertTo(U target) {
        double converted = target.convertFromBaseUnit(unit.convertToBaseUnit(this.value));
        return new Quantity<>(round(converted), target);
    }

    private void validateOperand(Quantity<U> other) {
        if (other == null) throw new IllegalArgumentException("Operand cannot be null");
        if (!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Cannot operate on different measurement categories");
    }

    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Quantity<?> other = (Quantity<?>) obj;
        if (!this.unit.getClass().equals(other.unit.getClass())) return false;
        return Double.compare(unit.convertToBaseUnit(this.value), other.unit.convertToBaseUnit(other.value)) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(unit.convertToBaseUnit(this.value));
    }

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }
}
