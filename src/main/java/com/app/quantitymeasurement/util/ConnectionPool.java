package com.app.quantitymeasurement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionPool {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionPool.class);
    private static ConnectionPool instance;
    private final BlockingQueue<Connection> pool;
    private final int poolSize;
    private final long timeoutMs;

    private ConnectionPool() {
        ApplicationConfig config = ApplicationConfig.getInstance();
        String url = config.getProperty("db.url", "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        String user = config.getProperty("db.user", "sa");
        String pass = config.getProperty("db.password", "");
        
        poolSize = Integer.parseInt(config.getProperty("db.pool.size", "10"));
        timeoutMs = Long.parseLong(config.getProperty("db.pool.timeout", "5000"));
        
        pool = new ArrayBlockingQueue<>(poolSize);
        
        try {
            for (int i = 0; i < poolSize; i++) {
                pool.add(DriverManager.getConnection(url, user, pass));
            }
            LOGGER.info("Connection pool initialized with {} connections", poolSize);
        } catch (SQLException e) {
            LOGGER.error("Failed to initialize connection pool", e);
            throw new RuntimeException(e);
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection acquire() throws SQLException {
        try {
            Connection conn = pool.poll(timeoutMs, TimeUnit.MILLISECONDS);
            if (conn == null) {
                throw new SQLException("Connection pool exhausted (timeout waiting for connection)");
            }
            return conn;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new SQLException("Interrupted while waiting for connection", e);
        }
    }

    public void release(Connection connection) {
        if (connection != null) {
            pool.offer(connection);
        }
    }
    
    public String getStatistics() {
        return "Pool size: " + poolSize + " | Active: " + (poolSize - pool.size()) + " | Idle: " + pool.size();
    }
}
