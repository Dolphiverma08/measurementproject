package com.app.quantitymeasurement.unit;

import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    private enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (Double.compare(b, 0.0) == 0) throw new ArithmeticException("Cannot divide by zero");
            return a / b;
        });

        private final DoubleBinaryOperator compute;

        ArithmeticOperation(DoubleBinaryOperator compute) {
            this.compute = compute;
        }

        public double apply(double a, double b) {
            return compute.applyAsDouble(a, b);
        }
    }

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
        validateArithmeticOperands(other, target, true);
        double result = performBaseArithmetic(other, ArithmeticOperation.ADD);
        return new Quantity<>(round(target.convertFromBaseUnit(result)), target);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U target) {
        validateArithmeticOperands(other, target, true);
        double result = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        return new Quantity<>(round(target.convertFromBaseUnit(result)), target);
    }

    public double divide(Quantity<U> other) {
        validateArithmeticOperands(other, null, false);
        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    public Quantity<U> convertTo(U target) {
        double converted = target.convertFromBaseUnit(unit.convertToBaseUnit(this.value));
        return new Quantity<>(round(converted), target);
    }

    private void validateArithmeticOperands(Quantity<U> other, U target, boolean targetRequired) {
        if (other == null) throw new IllegalArgumentException("Operand cannot be null");
        if (!Double.isFinite(other.value)) throw new IllegalArgumentException("Operand value must be finite");
        if (!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Cannot operate on different measurement categories");
        if (targetRequired && target == null) throw new IllegalArgumentException("Target unit cannot be null");
    }

    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation operation) {
        this.unit.validateOperationSupport(operation.name());
        double a = unit.convertToBaseUnit(this.value);
        double b = other.unit.convertToBaseUnit(other.value);
        return operation.apply(a, b);
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
