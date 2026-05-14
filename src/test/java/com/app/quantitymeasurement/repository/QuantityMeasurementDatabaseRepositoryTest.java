package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementDatabaseRepositoryTest {

    private QuantityMeasurementDatabaseRepository repository;

    @BeforeEach
    void setUp() {
        repository = new QuantityMeasurementDatabaseRepository();
        repository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void saveAndFindAll_Success() {
        QuantityMeasurementEntity entity1 = new QuantityMeasurementEntity("ADD", "1.0 FEET", "12.0 INCHES", "2.0 FEET", "LENGTH");
        QuantityMeasurementEntity entity2 = new QuantityMeasurementEntity("COMPARE", "1.0 KILOGRAM", "1000.0 GRAM", "true", "WEIGHT");

        repository.save(entity1);
        repository.save(entity2);

        List<QuantityMeasurementEntity> list = repository.findAll();
        assertEquals(2, list.size());
        
        assertEquals("ADD", list.get(0).getOperation());
        assertEquals("COMPARE", list.get(1).getOperation());
    }

    @Test
    void getMeasurementsByType_Success() {
        QuantityMeasurementEntity entity1 = new QuantityMeasurementEntity("ADD", "1.0 FEET", "12.0 INCHES", "2.0 FEET", "LENGTH");
        QuantityMeasurementEntity entity2 = new QuantityMeasurementEntity("COMPARE", "1.0 KILOGRAM", "1000.0 GRAM", "true", "WEIGHT");

        repository.save(entity1);
        repository.save(entity2);

        List<QuantityMeasurementEntity> lengthList = repository.getMeasurementsByType("LENGTH");
        assertEquals(1, lengthList.size());
        assertEquals("LENGTH", lengthList.get(0).getMeasurementType());
    }

    @Test
    void saveErrorEntity_Success() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity("ADD", true, "Unsupported Operation");
        repository.save(entity);

        List<QuantityMeasurementEntity> list = repository.findAll();
        assertEquals(1, list.size());
        assertTrue(list.get(0).hasError());
        assertEquals("Unsupported Operation", list.get(0).getErrorMessage());
    }
}
