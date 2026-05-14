package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.exception.DatabaseException;
import com.app.quantitymeasurement.util.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuantityMeasurementDatabaseRepository.class);
    private final ConnectionPool connectionPool;

    public QuantityMeasurementDatabaseRepository() {
        this.connectionPool = ConnectionPool.getInstance();
        initSchema();
    }

    private void initSchema() {
        String sql = "CREATE TABLE IF NOT EXISTS quantity_measurement_entity (" +
                     "id VARCHAR(36) PRIMARY KEY," +
                     "operation VARCHAR(50) NOT NULL," +
                     "operand1 VARCHAR(255)," +
                     "operand2 VARCHAR(255)," +
                     "result VARCHAR(255)," +
                     "measurement_type VARCHAR(50)," +
                     "has_error BOOLEAN NOT NULL," +
                     "error_message VARCHAR(500)," +
                     "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                     ");";
        try {
            Connection conn = connectionPool.acquire();
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.execute();
            } finally {
                connectionPool.release(conn);
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to initialize database schema", e);
            throw new DatabaseException("Failed to initialize database schema", e);
        }
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {
        String sql = "INSERT INTO quantity_measurement_entity (id, operation, operand1, operand2, result, measurement_type, has_error, error_message) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        try {
            conn = connectionPool.acquire();
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, entity.getId());
                stmt.setString(2, entity.getOperation());
                stmt.setString(3, entity.getOperand1());
                stmt.setString(4, entity.getOperand2());
                stmt.setString(5, entity.getResult());
                stmt.setString(6, entity.getMeasurementType());
                stmt.setBoolean(7, entity.hasError());
                stmt.setString(8, entity.getErrorMessage());
                
                stmt.executeUpdate();
                conn.commit();
                LOGGER.info("Saved entity id={}", entity.getId());
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to save entity: {}", entity.getId(), e);
            throw new DatabaseException("Failed to save entity", e);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    LOGGER.error("Failed to reset auto-commit", e);
                }
                connectionPool.release(conn);
            }
        }
    }

    @Override
    public List<QuantityMeasurementEntity> findAll() {
        return getAllMeasurements();
    }

    public List<QuantityMeasurementEntity> getAllMeasurements() {
        List<QuantityMeasurementEntity> list = new ArrayList<>();
        String sql = "SELECT * FROM quantity_measurement_entity ORDER BY created_at ASC";
        
        try {
            Connection conn = connectionPool.acquire();
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            } finally {
                connectionPool.release(conn);
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to retrieve measurements", e);
            throw new DatabaseException("Failed to retrieve measurements", e);
        }
        return list;
    }

    public List<QuantityMeasurementEntity> getMeasurementsByType(String type) {
        List<QuantityMeasurementEntity> list = new ArrayList<>();
        String sql = "SELECT * FROM quantity_measurement_entity WHERE measurement_type = ?";
        
        try {
            Connection conn = connectionPool.acquire();
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, type);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        list.add(mapRow(rs));
                    }
                }
            } finally {
                connectionPool.release(conn);
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to retrieve measurements by type", e);
            throw new DatabaseException("Failed to retrieve measurements by type", e);
        }
        return list;
    }

    public List<QuantityMeasurementEntity> getMeasurementsByOperation(String operation) {
        List<QuantityMeasurementEntity> list = new ArrayList<>();
        String sql = "SELECT * FROM quantity_measurement_entity WHERE operation = ?";
        
        try {
            Connection conn = connectionPool.acquire();
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, operation);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        list.add(mapRow(rs));
                    }
                }
            } finally {
                connectionPool.release(conn);
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to retrieve measurements by operation", e);
            throw new DatabaseException("Failed to retrieve measurements by operation", e);
        }
        return list;
    }

    public void deleteAll() {
        String sql = "DELETE FROM quantity_measurement_entity";
        try {
            Connection conn = connectionPool.acquire();
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.executeUpdate();
                LOGGER.info("Deleted all measurements");
            } finally {
                connectionPool.release(conn);
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to delete measurements", e);
            throw new DatabaseException("Failed to delete measurements", e);
        }
    }

    public int getTotalCount() {
        String sql = "SELECT COUNT(*) FROM quantity_measurement_entity";
        try {
            Connection conn = connectionPool.acquire();
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } finally {
                connectionPool.release(conn);
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to count measurements", e);
            throw new DatabaseException("Failed to count measurements", e);
        }
        return 0;
    }

    private QuantityMeasurementEntity mapRow(ResultSet rs) throws SQLException {
        if (rs.getBoolean("has_error")) {
            return new QuantityMeasurementEntity(rs.getString("operation"), true, rs.getString("error_message"));
        } else if (rs.getString("operand2") != null) {
            return new QuantityMeasurementEntity(rs.getString("operation"), rs.getString("operand1"), rs.getString("operand2"), rs.getString("result"), rs.getString("measurement_type"));
        } else {
            return new QuantityMeasurementEntity(rs.getString("operation"), rs.getString("operand1"), rs.getString("result"), rs.getString("measurement_type"));
        }
    }
}
