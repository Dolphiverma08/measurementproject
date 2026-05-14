package com.app.quantitymeasurement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.unit.Quantity;
import com.app.quantitymeasurement.services.IQuantityMeasurementService;
import com.app.quantitymeasurement.controller.QuantityMeasurementController;
import com.app.quantitymeasurement.services.QuantityMeasurementServiceImpl;
import com.app.quantitymeasurement.unit.LengthUnit;
import com.app.quantitymeasurement.unit.WeightUnit;
import com.app.quantitymeasurement.unit.TemperatureUnit;
import com.app.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuantityMeasurementApp.class);
    
    private static QuantityMeasurementApp instance;
    private final QuantityMeasurementController controller;

    private QuantityMeasurementApp() {
        com.app.quantitymeasurement.util.ApplicationConfig config = com.app.quantitymeasurement.util.ApplicationConfig.getInstance();
        String repoType = config.getProperty("repository.type", "CACHE");
        
        IQuantityMeasurementRepository repository;
        if ("DATABASE".equalsIgnoreCase(repoType)) {
            repository = new com.app.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository();
        } else {
            repository = QuantityMeasurementCacheRepository.getInstance();
        }
        
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
        LOGGER.info("--- Quantity Measurement System Started ---\n");

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
