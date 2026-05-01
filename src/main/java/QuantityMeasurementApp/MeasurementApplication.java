package QuantityMeasurementApp;

public class MeasurementApplication {

    public static void main(String[] args) {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);
        System.out.println("1.0 FEET equals 12.0 INCHES: " + q1.equals(q2));

        QuantityLength q3 = new QuantityLength(1.0, LengthUnit.INCHES);
        QuantityLength q4 = new QuantityLength(1.0, LengthUnit.INCHES);
        System.out.println("1.0 INCHES equals 1.0 INCHES: " + q3.equals(q4));
    }
}
