package com.app.quantitymeasurement.integrationTests;

import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.app.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;
import com.app.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.app.quantitymeasurement.services.IQuantityMeasurementService;
import com.app.quantitymeasurement.services.QuantityMeasurementServiceImpl;
import com.app.quantitymeasurement.unit.LengthUnit;
import com.app.quantitymeasurement.unit.TemperatureUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementIntegrationTest {

    private IQuantityMeasurementService serviceWithDb;
    private IQuantityMeasurementService serviceWithCache;
    private IQuantityMeasurementRepository dbRepository;
    private IQuantityMeasurementRepository cacheRepository;

    @BeforeEach
    void setUp() {
        dbRepository = new QuantityMeasurementDatabaseRepository();
        cacheRepository = QuantityMeasurementCacheRepository.getInstance();
        
        serviceWithDb = new QuantityMeasurementServiceImpl(dbRepository);
        serviceWithCache = new QuantityMeasurementServiceImpl(cacheRepository);
        
        ((QuantityMeasurementDatabaseRepository) dbRepository).deleteAll();
    }

    @Test
    void testIntegration_EndToEnd_LengthAddition() {
        QuantityDTO q1 = new QuantityDTO(1.0, LengthUnit.FEET, "LENGTH");
        QuantityDTO q2 = new QuantityDTO(12.0, LengthUnit.INCHES, "LENGTH");
        
        QuantityDTO result = serviceWithDb.add(q1, q2, LengthUnit.FEET);
        
        assertFalse(result.hasError());
        assertEquals(2.0, result.getValue());
        assertEquals(LengthUnit.FEET, result.getUnit());
        
        // Check DB
        assertEquals(1, dbRepository.findAll().size());
        assertEquals("ADD", dbRepository.findAll().get(0).getOperation());
    }

    @Test
    void testIntegration_EndToEnd_TemperatureUnsupported() {
        QuantityDTO q1 = new QuantityDTO(100.0, TemperatureUnit.CELSIUS, "TEMPERATURE");
        QuantityDTO q2 = new QuantityDTO(50.0, TemperatureUnit.CELSIUS, "TEMPERATURE");
        
        QuantityDTO result = serviceWithDb.add(q1, q2, TemperatureUnit.CELSIUS);
        
        assertTrue(result.hasError());
        assertEquals("Temperature does not support ADD", result.getErrorMessage());
        
        // Check DB has error entity
        assertEquals(1, dbRepository.findAll().size());
        assertTrue(dbRepository.findAll().get(0).hasError());
    }

    @Test
    void testServiceWithDatabaseRepository_Integration() {
        QuantityDTO q1 = new QuantityDTO(1.0, LengthUnit.FEET, "LENGTH");
        QuantityDTO q2 = new QuantityDTO(12.0, LengthUnit.INCHES, "LENGTH");
        
        QuantityDTO result = serviceWithDb.compare(q1, q2);
        assertFalse(result.hasError());
        assertEquals(1, dbRepository.findAll().size());
    }

    @Test
    void testServiceWithCacheRepository_Integration() {
        QuantityDTO q1 = new QuantityDTO(1.0, LengthUnit.FEET, "LENGTH");
        QuantityDTO q2 = new QuantityDTO(12.0, LengthUnit.INCHES, "LENGTH");
        
        QuantityDTO result = serviceWithCache.compare(q1, q2);
        assertFalse(result.hasError());
        assertTrue(cacheRepository.findAll().size() > 0);
    }
}
