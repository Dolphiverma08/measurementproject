package QuantityMeasurementApp;

import java.io.Serializable;
import java.util.UUID;

public class QuantityMeasurementEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String operation;
    private String operand1;
    private String operand2;
    private String result;
    private boolean hasError;
    private String errorMessage;
    private String measurementType;

    public QuantityMeasurementEntity(String operation, String operand1, String result, String measurementType) {
        this.id = UUID.randomUUID().toString();
        this.operation = operation;
        this.operand1 = operand1;
        this.result = result;
        this.measurementType = measurementType;
        this.hasError = false;
    }

    public QuantityMeasurementEntity(String operation, String operand1, String operand2, String result, String measurementType) {
        this.id = UUID.randomUUID().toString();
        this.operation = operation;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.result = result;
        this.measurementType = measurementType;
        this.hasError = false;
    }

    public QuantityMeasurementEntity(String operation, boolean hasError, String errorMessage) {
        this.id = UUID.randomUUID().toString();
        this.operation = operation;
        this.hasError = hasError;
        this.errorMessage = errorMessage;
    }

    public String getId() { return id; }
    public String getOperation() { return operation; }
    public String getOperand1() { return operand1; }
    public String getOperand2() { return operand2; }
    public String getResult() { return result; }
    public boolean hasError() { return hasError; }
    public String getErrorMessage() { return errorMessage; }
    public String getMeasurementType() { return measurementType; }

    @Override
    public String toString() {
        if (hasError) {
            return "Operation: " + operation + " | Error: " + errorMessage;
        }
        if (operand2 != null) {
            return "Operation: " + operation + " | " + operand1 + " and " + operand2 + " | Result: " + result;
        }
        return "Operation: " + operation + " | " + operand1 + " | Result: " + result;
    }
}
