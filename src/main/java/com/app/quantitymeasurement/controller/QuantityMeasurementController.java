package com.app.quantitymeasurement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.services.IQuantityMeasurementService;

public class QuantityMeasurementController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuantityMeasurementController.class);
    
    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    public void performCompare(QuantityDTO dto1, QuantityDTO dto2) {
        LOGGER.info("--- COMPARE ---");
        QuantityDTO result = service.compare(dto1, dto2);
        displayResult(result);
    }

    public void performConvert(QuantityDTO source, QuantityDTO.IMeasurableUnit targetUnit) {
        LOGGER.info("--- CONVERT ---");
        QuantityDTO result = service.convert(source, targetUnit);
        displayResult(result);
    }

    public void performAdd(QuantityDTO dto1, QuantityDTO dto2, QuantityDTO.IMeasurableUnit targetUnit) {
        LOGGER.info("--- ADD ---");
        QuantityDTO result = service.add(dto1, dto2, targetUnit);
        displayResult(result);
    }

    public void performSubtract(QuantityDTO dto1, QuantityDTO dto2, QuantityDTO.IMeasurableUnit targetUnit) {
        LOGGER.info("--- SUBTRACT ---");
        QuantityDTO result = service.subtract(dto1, dto2, targetUnit);
        displayResult(result);
    }

    public void performDivide(QuantityDTO dto1, QuantityDTO dto2) {
        LOGGER.info("--- DIVIDE ---");
        QuantityDTO result = service.divide(dto1, dto2);
        displayResult(result);
    }

    private void displayResult(QuantityDTO result) {
        if (result.hasError()) {
            LOGGER.error(result.getErrorMessage());
        } else if ("BOOLEAN".equals(result.getMeasurementType())) {
            LOGGER.info("Equality: " + (result.getValue() == 1.0));
        } else if ("SCALAR".equals(result.getMeasurementType())) {
            LOGGER.info("Result: " + result.getValue());
        } else {
            LOGGER.info("Result: " + result.toString());
        }
        LOGGER.info("");
    }
}
