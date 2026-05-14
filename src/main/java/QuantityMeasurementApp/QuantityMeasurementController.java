package QuantityMeasurementApp;

public class QuantityMeasurementController {
    
    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    public void performCompare(QuantityDTO dto1, QuantityDTO dto2) {
        System.out.println("--- COMPARE ---");
        QuantityDTO result = service.compare(dto1, dto2);
        displayResult(result);
    }

    public void performConvert(QuantityDTO source, QuantityDTO.IMeasurableUnit targetUnit) {
        System.out.println("--- CONVERT ---");
        QuantityDTO result = service.convert(source, targetUnit);
        displayResult(result);
    }

    public void performAdd(QuantityDTO dto1, QuantityDTO dto2, QuantityDTO.IMeasurableUnit targetUnit) {
        System.out.println("--- ADD ---");
        QuantityDTO result = service.add(dto1, dto2, targetUnit);
        displayResult(result);
    }

    public void performSubtract(QuantityDTO dto1, QuantityDTO dto2, QuantityDTO.IMeasurableUnit targetUnit) {
        System.out.println("--- SUBTRACT ---");
        QuantityDTO result = service.subtract(dto1, dto2, targetUnit);
        displayResult(result);
    }

    public void performDivide(QuantityDTO dto1, QuantityDTO dto2) {
        System.out.println("--- DIVIDE ---");
        QuantityDTO result = service.divide(dto1, dto2);
        displayResult(result);
    }

    private void displayResult(QuantityDTO result) {
        if (result.hasError()) {
            System.err.println(result.getErrorMessage());
        } else if ("BOOLEAN".equals(result.getMeasurementType())) {
            System.out.println("Equality: " + (result.getValue() == 1.0));
        } else if ("SCALAR".equals(result.getMeasurementType())) {
            System.out.println("Result: " + result.getValue());
        } else {
            System.out.println("Result: " + result.toString());
        }
        System.out.println();
    }
}
