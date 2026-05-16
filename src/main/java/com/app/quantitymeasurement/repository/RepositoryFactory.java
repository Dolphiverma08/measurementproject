package com.app.quantitymeasurement.repository;

public class RepositoryFactory {
    public static IQuantityMeasurementRepository getRepository(String type) {
        if ("DATABASE".equalsIgnoreCase(type)) {
            return new QuantityMeasurementDatabaseRepository();
        } else if ("CACHE".equalsIgnoreCase(type)) {
            return QuantityMeasurementCacheRepository.getInstance();
        }
        throw new IllegalArgumentException("Unknown repository type: " + type);
    }
}
