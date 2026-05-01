@echo off
echo =====================================================
echo   Setup - Copy all branches to your GitHub
echo =====================================================
echo.
echo Step 1: Fetching all branches from the original repo...
git fetch --all

echo.
echo Step 2: Creating local copies of all branches...
for /f "tokens=*" %%a in ('git branch -r') do (
    set "branch=%%a"
    setlocal enabledelayedexpansion
    set "branch=!branch: =!"
    if not "!branch!"=="origin/HEAD->origin/dev" (
        set "localBranch=!branch:origin/=!"
        git checkout -B "!localBranch!" "!branch!" 2>nul
    )
    endlocal
)

echo.
set /p REPO_URL="Step 3: Paste YOUR GitHub repo URL here and press Enter: "
echo.

echo Step 4: Adding your GitHub as remote...
git remote add myfork %REPO_URL% 2>nul || git remote set-url myfork %REPO_URL%

echo.
echo Step 5: Pushing all 13 branches to your GitHub...
git push myfork --all

echo.
echo =====================================================
echo   Done! All branches are now on YOUR GitHub!
echo =====================================================
echo.
pause
