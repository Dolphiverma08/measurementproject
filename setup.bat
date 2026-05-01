@echo off

echo =====================================================
echo   BEFORE RUNNING:
echo   1. Open setup_config.txt in Notepad
echo   2. Replace YOUR_GITHUB_REPO_URL_HERE with your repo URL
echo   3. Save it
echo   4. Come back and press any key
echo =====================================================
echo.
pause

rem Read repo URL from setup_config.txt
for /f "tokens=1,2 delims==" %%a in (setup_config.txt) do (
    if "%%a"=="REPO" set MY_REPO=%%b
)

rem Check if user filled in the value
if "%MY_REPO%"=="YOUR_GITHUB_REPO_URL_HERE" (
    echo.
    echo  ERROR: You forgot to fill in setup_config.txt!
    echo  Open it in Notepad, replace the URL, save, then run this again.
    echo.
    pause
    exit
)

echo.
echo Running setup - this will take a few minutes...
echo.

rem Try common Git Bash locations
if exist "C:\Program Files\Git\bin\bash.exe" (
    "C:\Program Files\Git\bin\bash.exe" setup.sh "%MY_REPO%"
) else if exist "C:\Program Files (x86)\Git\bin\bash.exe" (
    "C:\Program Files (x86)\Git\bin\bash.exe" setup.sh "%MY_REPO%"
) else (
    echo ERROR: Git Bash not found.
    echo Make sure Git is installed from https://git-scm.com/download/win
)

echo.
pause
