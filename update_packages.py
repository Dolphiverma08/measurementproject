import os
import re

src_dir = r"src\main\java\com\app\quantitymeasurement"
test_dir = r"src\test\java\com\app\quantitymeasurement"

# Mapping from class to new package
class_to_pkg = {
    "QuantityMeasurementApp": "com.app.quantitymeasurement",
    "QuantityMeasurementController": "com.app.quantitymeasurement.controller",
    "IQuantityMeasurementService": "com.app.quantitymeasurement.services",
    "QuantityMeasurementServiceImpl": "com.app.quantitymeasurement.services",
    "IQuantityMeasurementRepository": "com.app.quantitymeasurement.repository",
    "QuantityMeasurementCacheRepository": "com.app.quantitymeasurement.repository",
    "QuantityMeasurementDatabaseRepository": "com.app.quantitymeasurement.repository",
    "QuantityDTO": "com.app.quantitymeasurement.entity",
    "QuantityModel": "com.app.quantitymeasurement.entity",
    "QuantityMeasurementEntity": "com.app.quantitymeasurement.entity",
    "QuantityMeasurementException": "com.app.quantitymeasurement.exception",
    "DatabaseException": "com.app.quantitymeasurement.exception",
    "IMeasurable": "com.app.quantitymeasurement.unit",
    "Quantity": "com.app.quantitymeasurement.unit",
    "QuantityLength": "com.app.quantitymeasurement.unit",
    "QuantityWeight": "com.app.quantitymeasurement.unit",
    "LengthUnit": "com.app.quantitymeasurement.unit",
    "WeightUnit": "com.app.quantitymeasurement.unit",
    "VolumeUnit": "com.app.quantitymeasurement.unit",
    "TemperatureUnit": "com.app.quantitymeasurement.unit",
    "ApplicationConfig": "com.app.quantitymeasurement.util",
    "ConnectionPool": "com.app.quantitymeasurement.util",
}

def update_file(file_path):
    with open(file_path, "r", encoding="utf-8") as f:
        content = f.read()
    
    # 1. Update package
    file_name = os.path.basename(file_path)
    class_name = os.path.splitext(file_name)[0]
    
    # Handle Test classes mappings
    pkg = "com.app.quantitymeasurement"
    if "controller" in file_path:
        pkg = "com.app.quantitymeasurement.controller"
    elif "services" in file_path or "service" in file_path:
        pkg = "com.app.quantitymeasurement.services"
    elif "repository" in file_path:
        pkg = "com.app.quantitymeasurement.repository"
    elif "entity" in file_path:
        pkg = "com.app.quantitymeasurement.entity"
    elif "exception" in file_path:
        pkg = "com.app.quantitymeasurement.exception"
    elif "unit" in file_path:
        pkg = "com.app.quantitymeasurement.unit"
    elif "util" in file_path:
        pkg = "com.app.quantitymeasurement.util"
    elif "integrationTests" in file_path:
        pkg = "com.app.quantitymeasurement.integrationTests"
        
    content = re.sub(r'^package\s+QuantityMeasurementApp;', f'package {pkg};', content, flags=re.MULTILINE)
    
    # 2. Add imports for classes not in the same package
    imports_to_add = set()
    for cls, target_pkg in class_to_pkg.items():
        if target_pkg != pkg and re.search(r'\b' + cls + r'\b', content):
            imports_to_add.add(f"import {target_pkg}.{cls};")
            
    # Add imports after package declaration
    if imports_to_add:
        imports_str = "\n".join(imports_to_add)
        content = re.sub(r'^(package\s+.*?;)', r'\1\n\n' + imports_str, content, flags=re.MULTILINE)
        
    with open(file_path, "w", encoding="utf-8") as f:
        f.write(content)

for root, _, files in os.walk("src"):
    for file in files:
        if file.endswith(".java"):
            update_file(os.path.join(root, file))

print("Packages updated.")
