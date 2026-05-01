package QuantityMeasurementApp;

public class QuantityWeight {
    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Value must be finite");
        this.value = value;
        this.unit = unit;
    }

    public QuantityWeight add(QuantityWeight other) {
        return add(other, this.unit);
    }

    public QuantityWeight add(QuantityWeight other, WeightUnit target) {
        double sum = unit.convertToBaseUnit(this.value) + other.unit.convertToBaseUnit(other.value);
        double result = target.convertFromBaseUnit(sum);
        return new QuantityWeight(Math.round(result * 100.0) / 100.0, target);
    }

    public QuantityWeight convertTo(WeightUnit target) {
        double inBase = unit.convertToBaseUnit(this.value);
        double converted = target.convertFromBaseUnit(inBase);
        return new QuantityWeight(Math.round(converted * 100.0) / 100.0, target);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        QuantityWeight other = (QuantityWeight) obj;
        return Double.compare(unit.convertToBaseUnit(this.value), other.unit.convertToBaseUnit(other.value)) == 0;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}
