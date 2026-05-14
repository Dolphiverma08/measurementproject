package QuantityMeasurementApp;

public class QuantityMeasurementApp {
    
    private static QuantityMeasurementApp instance;
    private final QuantityMeasurementController controller;

    private QuantityMeasurementApp() {
        IQuantityMeasurementRepository repository = QuantityMeasurementCacheRepository.getInstance();
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
        this.controller = new QuantityMeasurementController(service);
    }

    public static synchronized QuantityMeasurementApp getInstance() {
        if (instance == null) {
            instance = new QuantityMeasurementApp();
        }
        return instance;
    }

    public void run() {
        System.out.println("--- Quantity Measurement System Started ---\n");

        QuantityDTO length1 = new QuantityDTO(1.0, LengthUnit.FEET, "LENGTH");
        QuantityDTO length2 = new QuantityDTO(12.0, LengthUnit.INCHES, "LENGTH");
        controller.performCompare(length1, length2);

        QuantityDTO weight1 = new QuantityDTO(1.0, WeightUnit.KILOGRAM, "WEIGHT");
        QuantityDTO weight2 = new QuantityDTO(1000.0, WeightUnit.GRAM, "WEIGHT");
        controller.performAdd(weight1, weight2, WeightUnit.KILOGRAM);

        QuantityDTO temp1 = new QuantityDTO(100.0, TemperatureUnit.CELSIUS, "TEMPERATURE");
        QuantityDTO temp2 = new QuantityDTO(50.0, TemperatureUnit.CELSIUS, "TEMPERATURE");
        controller.performAdd(temp1, temp2, TemperatureUnit.CELSIUS); // Expect error
    }

    public static void main(String[] args) {
        QuantityMeasurementApp.getInstance().run();
    }
}
