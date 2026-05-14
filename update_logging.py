import os
import re

def update_file(file_path):
    with open(file_path, "r", encoding="utf-8") as f:
        content = f.read()

    changed = False

    if "System.out.println" in content or "System.err.println" in content:
        # Add imports if not present
        if "org.slf4j.Logger" not in content:
            content = re.sub(r'^(package\s+.*?;)', r'\1\n\nimport org.slf4j.Logger;\nimport org.slf4j.LoggerFactory;', content, flags=re.MULTILINE)
            changed = True
        
        # Add logger field if not present
        file_name = os.path.basename(file_path)
        class_name = os.path.splitext(file_name)[0]
        
        if "private static final Logger LOGGER" not in content:
            content = re.sub(r'(public class ' + class_name + r' .*?\{)', r'\1\n    private static final Logger LOGGER = LoggerFactory.getLogger(' + class_name + r'.class);', content, flags=re.DOTALL)
            changed = True
        
        # Replace System.out.println with LOGGER.info
        content = re.sub(r'System\.out\.println\((.*?)\);', r'LOGGER.info(\1);', content)
        # Handle empty println
        content = re.sub(r'LOGGER\.info\(\);', r'LOGGER.info("");', content)
        # Replace System.err.println with LOGGER.error
        content = re.sub(r'System\.err\.println\((.*?)\);', r'LOGGER.error(\1);', content)
        
        changed = True

    if changed:
        with open(file_path, "w", encoding="utf-8") as f:
            f.write(content)

for root, _, files in os.walk("src/main/java"):
    for file in files:
        if file.endswith(".java"):
            update_file(os.path.join(root, file))

print("Logging updated.")
