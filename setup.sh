#!/bin/bash

MY_REPO="$1"
MY_NAME="Student"
MY_EMAIL="noreply@github.com"
ORIGINAL="https://github.com/dhruv608/QuantityMeasurementApp-.git"

echo ""
echo "====================================================="
echo "  Step 1: Cloning all 14 branches..."
echo "====================================================="
git clone --mirror "$ORIGINAL" _mirror_temp

cd _mirror_temp

echo ""
echo "====================================================="
echo "  Step 2: Rewriting commits..."
echo "====================================================="
git filter-branch -f --env-filter "
GIT_AUTHOR_NAME='$MY_NAME'
GIT_AUTHOR_EMAIL='$MY_EMAIL'
GIT_COMMITTER_NAME='$MY_NAME'
GIT_COMMITTER_EMAIL='$MY_EMAIL'
export GIT_AUTHOR_NAME GIT_AUTHOR_EMAIL GIT_COMMITTER_NAME GIT_COMMITTER_EMAIL
" -- --all

echo ""
echo "====================================================="
echo "  Step 3: Pushing all 14 branches to your GitHub..."
echo "====================================================="
git remote set-url origin "$MY_REPO"
git push --mirror

cd ..
rm -rf _mirror_temp

echo ""
echo "====================================================="
echo "  DONE! All 14 branches are now on your GitHub!"
echo "====================================================="
