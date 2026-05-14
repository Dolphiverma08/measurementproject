package com.app.quantitymeasurement.services;

import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.exception.QuantityMeasurementException;
import com.app.quantitymeasurement.unit.Quantity;
import com.app.quantitymeasurement.unit.LengthUnit;
import com.app.quantitymeasurement.unit.WeightUnit;
import com.app.quantitymeasurement.unit.VolumeUnit;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.unit.TemperatureUnit;
import com.app.quantitymeasurement.unit.IMeasurable;
import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {
    
    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    private <U extends IMeasurable> Quantity<U> toQuantity(QuantityDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("DTO cannot be null");
        }
        if (dto.getUnit() == null) {
            throw new IllegalArgumentException("DTO unit cannot be null");
        }
        
        // Find the correct unit instance
        IMeasurable unitInstance = null;
        for (LengthUnit lu : LengthUnit.values()) {
            if (lu.name().equals(dto.getUnit().name())) unitInstance = lu;
        }
        if (unitInstance == null) {
            for (WeightUnit wu : WeightUnit.values()) {
                if (wu.name().equals(dto.getUnit().name())) unitInstance = wu;
            }
        }
        if (unitInstance == null) {
            for (VolumeUnit vu : VolumeUnit.values()) {
                if (vu.name().equals(dto.getUnit().name())) unitInstance = vu;
            }
        }
        if (unitInstance == null) {
            for (TemperatureUnit tu : TemperatureUnit.values()) {
                if (tu.name().equals(dto.getUnit().name())) unitInstance = tu;
            }
        }

        if (unitInstance == null) {
            throw new IllegalArgumentException("Unknown unit: " + dto.getUnit().name());
        }

        @SuppressWarnings("unchecked")
        U u = (U) unitInstance;
        return new Quantity<>(dto.getValue(), u);
    }

    private <U extends IMeasurable> U getTargetUnit(QuantityDTO.IMeasurableUnit targetUnit) {
        if (targetUnit == null) return null;
        IMeasurable unitInstance = null;
        for (LengthUnit lu : LengthUnit.values()) {
            if (lu.name().equals(targetUnit.name())) unitInstance = lu;
        }
        if (unitInstance == null) {
            for (WeightUnit wu : WeightUnit.values()) {
                if (wu.name().equals(targetUnit.name())) unitInstance = wu;
            }
        }
        if (unitInstance == null) {
            for (VolumeUnit vu : VolumeUnit.values()) {
                if (vu.name().equals(targetUnit.name())) unitInstance = vu;
            }
        }
        if (unitInstance == null) {
            for (TemperatureUnit tu : TemperatureUnit.values()) {
                if (tu.name().equals(targetUnit.name())) unitInstance = tu;
            }
        }

        if (unitInstance == null) {
            throw new IllegalArgumentException("Unknown target unit: " + targetUnit.name());
        }
        
        @SuppressWarnings("unchecked")
        U u = (U) unitInstance;
        return u;
    }

    @Override
    public QuantityDTO compare(QuantityDTO dto1, QuantityDTO dto2) {
        try {
            if (!dto1.getMeasurementType().equals(dto2.getMeasurementType())) {
                throw new QuantityMeasurementException("Cannot compare different measurement categories");
            }
            Quantity<?> q1 = toQuantity(dto1);
            Quantity<?> q2 = toQuantity(dto2);
            boolean result = q1.equals(q2);
            QuantityMeasurementEntity entity = new QuantityMeasurementEntity("COMPARE", dto1.toString(), dto2.toString(), String.valueOf(result), dto1.getMeasurementType());
            repository.save(entity);
            return new QuantityDTO(result ? 1.0 : 0.0, null, "BOOLEAN");
        } catch (Exception e) {
            QuantityMeasurementEntity errorEntity = new QuantityMeasurementEntity("COMPARE", true, e.getMessage());
            repository.save(errorEntity);
            return new QuantityDTO(true, e.getMessage());
        }
    }

    @Override
    public QuantityDTO convert(QuantityDTO source, QuantityDTO.IMeasurableUnit targetUnit) {
        try {
            @SuppressWarnings("unchecked")
            Quantity<IMeasurable> q = (Quantity<IMeasurable>) toQuantity(source);
            IMeasurable tUnit = getTargetUnit(targetUnit);
            
            Quantity<IMeasurable> result = q.convertTo(tUnit);
            QuantityMeasurementEntity entity = new QuantityMeasurementEntity("CONVERT", source.toString(), result.toString(), source.getMeasurementType());
            repository.save(entity);
            
            return new QuantityDTO(result.toString().split(" ")[0] != null ? Double.parseDouble(result.toString().split(" ")[0]) : 0, targetUnit, source.getMeasurementType());
        } catch (Exception e) {
            QuantityMeasurementEntity errorEntity = new QuantityMeasurementEntity("CONVERT", true, e.getMessage());
            repository.save(errorEntity);
            return new QuantityDTO(true, e.getMessage());
        }
    }

    @Override
    public QuantityDTO add(QuantityDTO dto1, QuantityDTO dto2, QuantityDTO.IMeasurableUnit targetUnit) {
        try {
            @SuppressWarnings("unchecked")
            Quantity<IMeasurable> q1 = (Quantity<IMeasurable>) toQuantity(dto1);
            @SuppressWarnings("unchecked")
            Quantity<IMeasurable> q2 = (Quantity<IMeasurable>) toQuantity(dto2);
            IMeasurable tUnit = targetUnit != null ? getTargetUnit(targetUnit) : null;
            
            Quantity<IMeasurable> result;
            if (tUnit != null) {
                result = q1.add(q2, tUnit);
            } else {
                result = q1.add(q2);
            }
            
            QuantityMeasurementEntity entity = new QuantityMeasurementEntity("ADD", dto1.toString(), dto2.toString(), result.toString(), dto1.getMeasurementType());
            repository.save(entity);
            
            String valStr = result.toString().split(" ")[0];
            return new QuantityDTO(Double.parseDouble(valStr), targetUnit != null ? targetUnit : dto1.getUnit(), dto1.getMeasurementType());
        } catch (Exception e) {
            QuantityMeasurementEntity errorEntity = new QuantityMeasurementEntity("ADD", true, e.getMessage());
            repository.save(errorEntity);
            return new QuantityDTO(true, e.getMessage());
        }
    }

    @Override
    public QuantityDTO subtract(QuantityDTO dto1, QuantityDTO dto2, QuantityDTO.IMeasurableUnit targetUnit) {
        try {
            @SuppressWarnings("unchecked")
            Quantity<IMeasurable> q1 = (Quantity<IMeasurable>) toQuantity(dto1);
            @SuppressWarnings("unchecked")
            Quantity<IMeasurable> q2 = (Quantity<IMeasurable>) toQuantity(dto2);
            IMeasurable tUnit = targetUnit != null ? getTargetUnit(targetUnit) : null;
            
            Quantity<IMeasurable> result;
            if (tUnit != null) {
                result = q1.subtract(q2, tUnit);
            } else {
                result = q1.subtract(q2);
            }
            
            QuantityMeasurementEntity entity = new QuantityMeasurementEntity("SUBTRACT", dto1.toString(), dto2.toString(), result.toString(), dto1.getMeasurementType());
            repository.save(entity);
            
            String valStr = result.toString().split(" ")[0];
            return new QuantityDTO(Double.parseDouble(valStr), targetUnit != null ? targetUnit : dto1.getUnit(), dto1.getMeasurementType());
        } catch (Exception e) {
            QuantityMeasurementEntity errorEntity = new QuantityMeasurementEntity("SUBTRACT", true, e.getMessage());
            repository.save(errorEntity);
            return new QuantityDTO(true, e.getMessage());
        }
    }

    @Override
    public QuantityDTO divide(QuantityDTO dto1, QuantityDTO dto2) {
        try {
            @SuppressWarnings("unchecked")
            Quantity<IMeasurable> q1 = (Quantity<IMeasurable>) toQuantity(dto1);
            @SuppressWarnings("unchecked")
            Quantity<IMeasurable> q2 = (Quantity<IMeasurable>) toQuantity(dto2);
            
            double result = q1.divide(q2);
            QuantityMeasurementEntity entity = new QuantityMeasurementEntity("DIVIDE", dto1.toString(), dto2.toString(), String.valueOf(result), dto1.getMeasurementType());
            repository.save(entity);
            
            return new QuantityDTO(result, null, "SCALAR");
        } catch (Exception e) {
            QuantityMeasurementEntity errorEntity = new QuantityMeasurementEntity("DIVIDE", true, e.getMessage());
            repository.save(errorEntity);
            return new QuantityDTO(true, e.getMessage());
        }
    }
}
