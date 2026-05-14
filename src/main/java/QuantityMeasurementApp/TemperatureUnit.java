package QuantityMeasurementApp;

import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable, QuantityDTO.IMeasurableUnit {
    CELSIUS(c -> c, c -> c),
    FAHRENHEIT(f -> (f - 32.0) * 5.0 / 9.0, c -> (c * 9.0 / 5.0) + 32.0),
    KELVIN(k -> k - 273.15, c -> c + 273.15);

    private final Function<Double, Double> toBaseUnitLambda;
    private final Function<Double, Double> fromBaseUnitLambda;
    
    private final SupportsArithmetic supportsArithmetic = () -> false;

    TemperatureUnit(Function<Double, Double> toBaseUnitLambda, Function<Double, Double> fromBaseUnitLambda) {
        this.toBaseUnitLambda = toBaseUnitLambda;
        this.fromBaseUnitLambda = fromBaseUnitLambda;
    }

    @Override
    public boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    @Override
    public void validateOperationSupport(String operation) {
        throw new UnsupportedOperationException("Temperature does not support " + operation);
    }

    @Override
    public double getConversionFactor() {
        // Temperature conversion factor is not used directly due to non-linear conversion
        return 1.0; 
    }

    @Override
    public double convertToBaseUnit(double value) {
        return toBaseUnitLambda.apply(value);
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return fromBaseUnitLambda.apply(baseValue);
    }

    @Override
    public String getUnitName() {
        return this.name();
    }

    @Override
    public String getMeasurementType() {
        return "TEMPERATURE";
    }

    @Override
    public IMeasurable getUnitInstance(String name) {
        return TemperatureUnit.valueOf(name);
    }
}
