package QuantityMeasurementApp;

public class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    private double toBaseFeet() {
        return value * unit.getConversionFactor();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        QuantityLength other = (QuantityLength) obj;
        return Double.compare(this.toBaseFeet(), other.toBaseFeet()) == 0;
    }

    public QuantityLength convertTo(LengthUnit target) {
        double inFeet = toBaseFeet();
        double converted = inFeet / target.getConversionFactor();
        return new QuantityLength(Math.round(converted * 100.0) / 100.0, target);
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}
