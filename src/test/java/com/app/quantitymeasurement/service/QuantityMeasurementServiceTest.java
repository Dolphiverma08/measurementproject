package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.unit.LengthUnit;
import com.app.quantitymeasurement.unit.WeightUnit;
import com.app.quantitymeasurement.unit.TemperatureUnit;
import com.app.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.app.quantitymeasurement.services.IQuantityMeasurementService;
import com.app.quantitymeasurement.services.QuantityMeasurementServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementServiceTest {

    private IQuantityMeasurementService service;
    private IQuantityMeasurementRepository repository;

    @BeforeEach
    void setUp() {
        repository = QuantityMeasurementCacheRepository.getInstance();
        service = new QuantityMeasurementServiceImpl(repository);
    }

    @Test
    void testService_CompareEquality_SameUnit_Success() {
        QuantityDTO q1 = new QuantityDTO(1.0, LengthUnit.FEET, "LENGTH");
        QuantityDTO q2 = new QuantityDTO(1.0, LengthUnit.FEET, "LENGTH");
        QuantityDTO result = service.compare(q1, q2);
        
        assertFalse(result.hasError());
        assertEquals("BOOLEAN", result.getMeasurementType());
        assertEquals(1.0, result.getValue());
    }

    @Test
    void testService_CompareEquality_DifferentUnit_Success() {
        QuantityDTO q1 = new QuantityDTO(1.0, LengthUnit.FEET, "LENGTH");
        QuantityDTO q2 = new QuantityDTO(12.0, LengthUnit.INCHES, "LENGTH");
        QuantityDTO result = service.compare(q1, q2);
        
        assertFalse(result.hasError());
        assertEquals(1.0, result.getValue());
    }

    @Test
    void testService_CompareEquality_CrossCategory_Error() {
        QuantityDTO q1 = new QuantityDTO(1.0, LengthUnit.FEET, "LENGTH");
        QuantityDTO q2 = new QuantityDTO(1.0, WeightUnit.KILOGRAM, "WEIGHT");
        QuantityDTO result = service.compare(q1, q2);
        
        assertTrue(result.hasError());
    }

    @Test
    void testService_Convert_Success() {
        QuantityDTO source = new QuantityDTO(1.0, LengthUnit.FEET, "LENGTH");
        QuantityDTO result = service.convert(source, LengthUnit.INCHES);
        
        assertFalse(result.hasError());
        assertEquals(12.0, result.getValue());
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    void testService_Add_Success() {
        QuantityDTO q1 = new QuantityDTO(1.0, LengthUnit.FEET, "LENGTH");
        QuantityDTO q2 = new QuantityDTO(12.0, LengthUnit.INCHES, "LENGTH");
        QuantityDTO result = service.add(q1, q2, LengthUnit.FEET);
        
        assertFalse(result.hasError());
        assertEquals(2.0, result.getValue());
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testService_Add_UnsupportedOperation_Error() {
        QuantityDTO q1 = new QuantityDTO(100.0, TemperatureUnit.CELSIUS, "TEMPERATURE");
        QuantityDTO q2 = new QuantityDTO(50.0, TemperatureUnit.CELSIUS, "TEMPERATURE");
        QuantityDTO result = service.add(q1, q2, null);
        
        assertTrue(result.hasError());
        assertEquals("Temperature does not support ADD", result.getErrorMessage());
    }

    @Test
    void testService_Divide_Success() {
        QuantityDTO q1 = new QuantityDTO(10.0, LengthUnit.FEET, "LENGTH");
        QuantityDTO q2 = new QuantityDTO(2.0, LengthUnit.FEET, "LENGTH");
        QuantityDTO result = service.divide(q1, q2);
        
        assertFalse(result.hasError());
        assertEquals("SCALAR", result.getMeasurementType());
        assertEquals(5.0, result.getValue());
    }
}
