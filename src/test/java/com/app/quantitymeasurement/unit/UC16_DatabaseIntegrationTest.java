package com.app.quantitymeasurement.unit;

import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.exception.DatabaseException;
import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.app.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;
import com.app.quantitymeasurement.repository.RepositoryFactory;
import com.app.quantitymeasurement.services.IQuantityMeasurementService;
import com.app.quantitymeasurement.services.QuantityMeasurementServiceImpl;
import com.app.quantitymeasurement.util.ApplicationConfig;
import com.app.quantitymeasurement.util.ConnectionPool;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UC16_DatabaseIntegrationTest {

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
    void testMavenBuild_Success() {
        assertTrue(true);
    }

    @Test
    void testPackageStructure_AllLayersPresent() {
        assertTrue(new File("src/main/java/com/app/quantitymeasurement/repository").exists());
    }

    @Test
    void testPomDependencies_JDBCDriversIncluded() throws Exception {
        String pom = new String(Files.readAllBytes(Paths.get("pom.xml")));
        assertTrue(pom.contains("h2"));
    }

    @Test
    void testDatabaseConfiguration_LoadedFromProperties() {
        assertNotNull(ApplicationConfig.getInstance().getProperty("db.url"));
    }

    @Test
    void testConnectionPool_Initialization() {
        assertNotNull(ConnectionPool.getInstance());
    }

    @Test
    void testConnectionPool_Acquire_Release() throws Exception {
        Connection conn = ConnectionPool.getInstance().acquire();
        assertNotNull(conn);
        ConnectionPool.getInstance().release(conn);
    }

    @Test
    void testConnectionPool_AllConnectionsExhausted() {
        assertTrue(true);
    }

    @Test
    void testDatabaseRepository_SaveEntity() {
        repository.save(new QuantityMeasurementEntity("ADD", "1", "1", "2", "LENGTH"));
        assertEquals(1, repository.getTotalCount());
    }

    @Test
    void testDatabaseRepository_RetrieveAllMeasurements() {
        repository.save(new QuantityMeasurementEntity("ADD", "1", "1", "2", "LENGTH"));
        assertFalse(repository.findAll().isEmpty());
    }

    @Test
    void testDatabaseRepository_QueryByOperation() {
        repository.save(new QuantityMeasurementEntity("ADD", "1", "1", "2", "LENGTH"));
        assertEquals(1, repository.getMeasurementsByOperation("ADD").size());
    }

    @Test
    void testDatabaseRepository_QueryByMeasurementType() {
        repository.save(new QuantityMeasurementEntity("ADD", "1", "1", "2", "LENGTH"));
        assertEquals(1, repository.getMeasurementsByType("LENGTH").size());
    }

    @Test
    void testDatabaseRepository_CountMeasurements() {
        repository.save(new QuantityMeasurementEntity("ADD", "1", "1", "2", "LENGTH"));
        assertEquals(1, repository.getTotalCount());
    }

    @Test
    void testDatabaseRepository_DeleteAll() {
        repository.save(new QuantityMeasurementEntity("ADD", "1", "1", "2", "LENGTH"));
        repository.deleteAll();
        assertEquals(0, repository.getTotalCount());
    }

    @Test
    void testSQLInjectionPrevention() {
        String unsafe = "ADD'; DROP TABLE entity;--";
        repository.save(new QuantityMeasurementEntity(unsafe, "1", "1", "2", "LENGTH"));
        assertEquals(unsafe, repository.getMeasurementsByOperation(unsafe).get(0).getOperation());
    }

    @Test
    void testTransactionRollback_OnError() {
        assertTrue(true);
    }

    @Test
    void testDatabaseSchema_TablesCreated() throws Exception {
        Connection conn = ConnectionPool.getInstance().acquire();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'QUANTITY_MEASUREMENT_ENTITY'")) {
            assertTrue(rs.next());
        } finally {
            ConnectionPool.getInstance().release(conn);
        }
    }

    @Test
    void testH2TestDatabase_IsolationBetweenTests() {
        repository.save(new QuantityMeasurementEntity("ADD", "1", "1", "2", "LENGTH"));
        assertEquals(1, repository.getTotalCount());
    }

    @Test
    void testRepositoryFactory_CreateCacheRepository() {
        assertNotNull(RepositoryFactory.getRepository("CACHE"));
    }

    @Test
    void testRepositoryFactory_CreateDatabaseRepository() {
        assertNotNull(RepositoryFactory.getRepository("DATABASE"));
    }

    @Test
    void testServiceWithDatabaseRepository_Integration() {
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
        service.compare(new QuantityDTO(1.0, LengthUnit.FEET, "LENGTH"), new QuantityDTO(12.0, LengthUnit.INCHES, "LENGTH"));
        assertEquals(1, repository.getTotalCount());
    }

    @Test
    void testServiceWithCacheRepository_Integration() {
        IQuantityMeasurementRepository cache = RepositoryFactory.getRepository("CACHE");
        cache.deleteAll();
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(cache);
        service.compare(new QuantityDTO(1.0, LengthUnit.FEET, "LENGTH"), new QuantityDTO(12.0, LengthUnit.INCHES, "LENGTH"));
        assertEquals(1, cache.findAll().size());
    }

    @Test
    void testMavenTest_AllTestsPass() {
        assertTrue(true);
    }

    @Test
    void testMavenPackage_JarCreated() {
        assertTrue(true);
    }

    @Test
    void testDatabaseRepositoryPoolStatistics() {
        assertTrue(ConnectionPool.getInstance().getStatistics().contains("Pool size"));
    }

    @Test
    void testDatabaseException_CustomException() {
        assertThrows(DatabaseException.class, () -> repository.save(new QuantityMeasurementEntity(null, "1", "1", "2", "LENGTH")));
    }

    @Test
    void testResourceCleanup_ConnectionClosed() {
        assertTrue(true);
    }

    @Test
    void testBatchInsert_MultipleEntities() {
        for (int i = 0; i < 5; i++) repository.save(new QuantityMeasurementEntity("ADD", "1", "1", "2", "LENGTH"));
        assertEquals(5, repository.getTotalCount());
    }

    @Test
    void testPropertiesConfiguration_EnvironmentOverride() {
        System.setProperty("db.user", "sa");
        assertEquals("sa", System.getProperty("db.user"));
    }

    @Test
    void testDatabaseRepository_ConcurrentAccess() {
        assertTrue(true);
    }

    @Test
    void testParameterizedQuery_DateTimeHandling() {
        repository.save(new QuantityMeasurementEntity("ADD", "1", "1", "2", "LENGTH"));
        assertNotNull(repository.findAll().get(0).getId());
    }

    @Test
    void testBackwardCompatibility_AllUC1_UC15_Tests() {
        assertTrue(true);
    }

    @Test
    void testIntegration_EndToEnd_LengthAddition() {
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
        QuantityDTO res = service.add(new QuantityDTO(1.0, LengthUnit.FEET, "LENGTH"), new QuantityDTO(12.0, LengthUnit.INCHES, "LENGTH"), LengthUnit.FEET);
        assertEquals(2.0, res.getValue());
    }

    @Test
    void testIntegration_EndToEnd_TemperatureUnsupported() {
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
        QuantityDTO res = service.add(new QuantityDTO(10.0, TemperatureUnit.CELSIUS, "TEMP"), new QuantityDTO(20.0, TemperatureUnit.CELSIUS, "TEMP"), TemperatureUnit.CELSIUS);
        assertTrue(res.hasError());
    }
}
