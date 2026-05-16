package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;

import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {
    private static QuantityMeasurementCacheRepository instance;
    private final List<QuantityMeasurementEntity> cache;

    private QuantityMeasurementCacheRepository() {
        this.cache = new ArrayList<>();
    }

    public static synchronized QuantityMeasurementCacheRepository getInstance() {
        if (instance == null) {
            instance = new QuantityMeasurementCacheRepository();
        }
        return instance;
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {
        if (entity != null) {
            cache.add(entity);
            // Optionally, serialization logic to disk could go here, 
            // but for UC15, keeping an in-memory list handles the logic properly
        }
    }

    @Override
    public List<QuantityMeasurementEntity> findAll() {
        return new ArrayList<>(cache);
    }

    @Override
    public void deleteAll() {
        cache.clear();
    }
}
