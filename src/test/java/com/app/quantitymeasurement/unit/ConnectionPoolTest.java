package com.app.quantitymeasurement.unit;

import com.app.quantitymeasurement.util.ConnectionPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionPoolTest {

    private ConnectionPool pool;

    @BeforeEach
    void setUp() {
        pool = ConnectionPool.getInstance();
    }

    private int getAvailable(String stats) {
        // "Pool size: 10 | Active: 0 | Idle: 10"
        String[] parts = stats.split("\\|");
        String idlePart = parts[2].trim();
        return Integer.parseInt(idlePart.split(":")[1].trim());
    }

    private int getTotal(String stats) {
        String[] parts = stats.split("\\|");
        String sizePart = parts[0].trim();
        return Integer.parseInt(sizePart.split(":")[1].trim());
    }

    @Test
    void testConnectionPool_Initialization() {
        assertNotNull(pool);
        assertTrue(getTotal(pool.getStatistics()) > 0);
    }

    @Test
    void testConnectionPool_Acquire_Release() throws SQLException {
        int availableBefore = getAvailable(pool.getStatistics());
        Connection conn = pool.acquire();
        assertNotNull(conn);
        
        int availableDuring = getAvailable(pool.getStatistics());
        assertEquals(availableBefore - 1, availableDuring);
        
        pool.release(conn);
        int availableAfter = getAvailable(pool.getStatistics());
        assertEquals(availableBefore, availableAfter);
    }

    @Test
    void testConnectionPool_AllConnectionsExhausted() throws SQLException {
        List<Connection> connections = new ArrayList<>();
        try {
            int total = getTotal(pool.getStatistics());
            for (int i = 0; i < total; i++) {
                connections.add(pool.acquire());
            }
            // Now pool should be empty, acquiring should throw RuntimeException or SQLException
            assertThrows(Exception.class, () -> pool.acquire());
        } finally {
            for (Connection conn : connections) {
                pool.release(conn);
            }
        }
    }
}
