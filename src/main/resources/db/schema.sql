CREATE TABLE IF NOT EXISTS quantity_measurement_entity (
    id VARCHAR(36) PRIMARY KEY,
    operation VARCHAR(50) NOT NULL,
    operand1 VARCHAR(255),
    operand2 VARCHAR(255),
    result VARCHAR(255),
    measurement_type VARCHAR(50),
    has_error BOOLEAN NOT NULL,
    error_message VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_measurement_type ON quantity_measurement_entity(measurement_type);
CREATE INDEX idx_operation ON quantity_measurement_entity(operation);
