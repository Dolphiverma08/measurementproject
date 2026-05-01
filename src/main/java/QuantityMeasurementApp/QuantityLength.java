package QuantityMeasurementApp;

public class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public QuantityLength add(QuantityLength other) {
        return add(other, this.unit);
    }

    public QuantityLength add(QuantityLength other, LengthUnit target) {
        double sum = unit.convertToBaseUnit(this.value) + other.unit.convertToBaseUnit(other.value);
        double result = target.convertFromBaseUnit(sum);
        return new QuantityLength(Math.round(result * 100.0) / 100.0, target);
    }

    public QuantityLength convertTo(LengthUnit target) {
        double inBase = unit.convertToBaseUnit(this.value);
        double converted = target.convertFromBaseUnit(inBase);
        return new QuantityLength(Math.round(converted * 100.0) / 100.0, target);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        QuantityLength other = (QuantityLength) obj;
        return Double.compare(unit.convertToBaseUnit(this.value), other.unit.convertToBaseUnit(other.value)) == 0;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}
