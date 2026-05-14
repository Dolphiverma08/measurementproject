package com.app.quantitymeasurement.services;

import com.app.quantitymeasurement.entity.QuantityDTO;

public interface IQuantityMeasurementService {
    QuantityDTO compare(QuantityDTO dto1, QuantityDTO dto2);
    QuantityDTO convert(QuantityDTO source, QuantityDTO.IMeasurableUnit targetUnit);
    QuantityDTO add(QuantityDTO dto1, QuantityDTO dto2, QuantityDTO.IMeasurableUnit targetUnit);
    QuantityDTO subtract(QuantityDTO dto1, QuantityDTO dto2, QuantityDTO.IMeasurableUnit targetUnit);
    QuantityDTO divide(QuantityDTO dto1, QuantityDTO dto2);
}
