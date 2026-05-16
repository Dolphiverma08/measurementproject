package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.exception.DatabaseException;
import com.app.quantitymeasurement.util.ConnectionPool;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    void testDatabaseRepository_SaveEntity() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity("ADD", "1.0 FEET", "12.0 INCHES", "2.0 FEET", "LENGTH");
        repository.save(entity);
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void testDatabaseRepository_RetrieveAllMeasurements() {
        repository.save(new QuantityMeasurementEntity("ADD", "1.0 FEET", "12.0 INCHES", "2.0 FEET", "LENGTH"));
        repository.save(new QuantityMeasurementEntity("COMPARE", "1.0 KILOGRAM", "1000.0 GRAM", "true", "WEIGHT"));
        List<QuantityMeasurementEntity> list = repository.findAll();
        assertEquals(2, list.size());
    }

    @Test
    void testDatabaseRepository_QueryByOperation() {
        repository.save(new QuantityMeasurementEntity("ADD", "1.0 FEET", "12.0 INCHES", "2.0 FEET", "LENGTH"));
        repository.save(new QuantityMeasurementEntity("COMPARE", "1.0 KILOGRAM", "1000.0 GRAM", "true", "WEIGHT"));
        
        List<QuantityMeasurementEntity> list = repository.getMeasurementsByOperation("ADD");
        assertEquals(1, list.size());
        assertEquals("ADD", list.get(0).getOperation());
    }

    @Test
    void testDatabaseRepository_QueryByMeasurementType() {
        repository.save(new QuantityMeasurementEntity("ADD", "1.0 FEET", "12.0 INCHES", "2.0 FEET", "LENGTH"));
        repository.save(new QuantityMeasurementEntity("COMPARE", "1.0 KILOGRAM", "1000.0 GRAM", "true", "WEIGHT"));
        
        List<QuantityMeasurementEntity> lengthList = repository.getMeasurementsByType("LENGTH");
        assertEquals(1, lengthList.size());
        assertEquals("LENGTH", lengthList.get(0).getMeasurementType());
    }

    @Test
    void testDatabaseRepository_CountMeasurements() {
        repository.save(new QuantityMeasurementEntity("ADD", "1.0 FEET", "12.0 INCHES", "2.0 FEET", "LENGTH"));
        repository.save(new QuantityMeasurementEntity("COMPARE", "1.0 KILOGRAM", "1000.0 GRAM", "true", "WEIGHT"));
        assertEquals(2, repository.getTotalCount());
    }

    @Test
    void testDatabaseRepository_DeleteAll() {
        repository.save(new QuantityMeasurementEntity("ADD", "1.0 FEET", "12.0 INCHES", "2.0 FEET", "LENGTH"));
        assertEquals(1, repository.getTotalCount());
        repository.deleteAll();
        assertEquals(0, repository.getTotalCount());
    }

    @Test
    void testSQLInjectionPrevention() {
        // Attempt injection via string input, should be safely parameterized
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity("ADD'; DROP TABLE quantity_measurement_entity;--", "1", "1", "2", "LENGTH");
        repository.save(entity);
        assertEquals(1, repository.getTotalCount());
        assertEquals("ADD'; DROP TABLE quantity_measurement_entity;--", repository.findAll().get(0).getOperation());
    }

    @Test
    void testDatabaseException_CustomException() {
        // Force an exception by passing an invalid entity (e.g. extremely long operation name exceeding column size if possible, or nulls where not allowed)
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(null, "1", "1", "2", "LENGTH");
        assertThrows(DatabaseException.class, () -> repository.save(entity));
    }

    @Test
    void testDatabaseSchema_TablesCreated() {
        // Given that save works, the schema must exist.
        repository.save(new QuantityMeasurementEntity("ADD", "1.0 FEET", "12.0 INCHES", "2.0 FEET", "LENGTH"));
        assertTrue(repository.getTotalCount() > 0);
    }

    private int getAvailable(String stats) {
        String[] parts = stats.split("\\|");
        String idlePart = parts[2].trim();
        return Integer.parseInt(idlePart.split(":")[1].trim());
    }

    @Test
    void testResourceCleanup_ConnectionClosed() {
        ConnectionPool pool = ConnectionPool.getInstance();
        int initialAvailable = getAvailable(pool.getStatistics());
        
        repository.save(new QuantityMeasurementEntity("ADD", "1", "1", "2", "LENGTH"));
        
        // Ensure connection was released back to pool
        assertEquals(initialAvailable, getAvailable(pool.getStatistics()));
    }

    @Test
    void testBatchInsert_MultipleEntities() {
        for (int i = 0; i < 50; i++) {
            repository.save(new QuantityMeasurementEntity("ADD", "1", "1", "2", "LENGTH"));
        }
        assertEquals(50, repository.getTotalCount());
    }

    @Test
    void testDatabaseRepository_ConcurrentAccess() throws InterruptedException {
        int threadCount = 20;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    repository.save(new QuantityMeasurementEntity("ADD", "1", "1", "2", "LENGTH"));
                } finally {
                    latch.countDown();
                }
            });
        }
        
        latch.await();
        assertEquals(threadCount, repository.getTotalCount());
        executor.shutdown();
    }
}
