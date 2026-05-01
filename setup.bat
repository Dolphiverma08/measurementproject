@echo off

rem =====================================================
rem   BEFORE RUNNING THIS FILE:
rem   1. Right-click this file
rem   2. Click "Open with" then "Notepad"
rem   3. Replace PASTE_YOUR_GITHUB_URL_HERE below
rem      with your actual GitHub repo URL
rem      Example: https://github.com/YourName/QuantityMeasurementApp.git
rem   4. Save and close Notepad
rem   5. Double-click this file to run
rem =====================================================

set REPO_URL=PASTE_YOUR_GITHUB_URL_HERE

rem =====================================================
rem   DO NOT EDIT ANYTHING BELOW THIS LINE
rem =====================================================

if "%REPO_URL%"=="PASTE_YOUR_GITHUB_URL_HERE" (
    echo ERROR: You forgot to add your GitHub URL!
    echo.
    echo Right-click this file, open with Notepad,
    echo and replace PASTE_YOUR_GITHUB_URL_HERE with your URL.
    echo.
    pause
    exit
)

echo =====================================================
echo   Setup - Copying all branches to your GitHub
echo =====================================================
echo.

echo Fetching all branches...
git fetch --all
echo.

echo Creating local branches...
git checkout -B dev origin/dev
git checkout -B "feature/UC1-feet-equality" "origin/feature/UC1-feet-equality"
git checkout -B "feature/UC2-inch-equality" "origin/feature/UC2-inch-equality"
git checkout -B "feature/UC3-LengthConversion" "origin/feature/UC3-LengthConversion"
git checkout -B "feature/UC4-ExtendedUnitSupport" "origin/feature/UC4-ExtendedUnitSupport"
git checkout -B "feature/UC5-UnitToUnitConversion" "origin/feature/UC5-UnitToUnitConversion"
git checkout -B "feature/UC6-AdditionLengthUnits" "origin/feature/UC6-AdditionLengthUnits"
git checkout -B "feature/UC7-AdditionTargetUnit" "origin/feature/UC7-AdditionTargetUnit"
git checkout -B "feature/UC8-RefactorUnitEnum" "origin/feature/UC8-RefactorUnitEnum"
git checkout -B "feature/UC9-WeightMeasurement" "origin/feature/UC9-WeightMeasurement"
git checkout -B "feature/UC10-GenericQuantity" "origin/feature/UC10-GenericQuantity"
git checkout -B "feature/UC-11VolumeMeasurement" "origin/feature/UC-11VolumeMeasurement"
git checkout -B "feature/UC12-SubtractionDivision" "origin/feature/UC12-SubtractionDivision"
git checkout -B "feature/UC13-CentralizedArithmetic" "origin/feature/UC13-CentralizedArithmetic"
echo.

echo Adding your GitHub repo...
git remote add myfork "%REPO_URL%"
if errorlevel 1 git remote set-url myfork "%REPO_URL%"
echo.

echo Pushing all 13 branches to your GitHub...
git push myfork --all
echo.

echo =====================================================
echo   DONE! All 13 branches are now on your GitHub!
echo =====================================================
echo.
pause
