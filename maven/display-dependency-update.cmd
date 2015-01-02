@echo off
pushd ..
call mvn versions:display-dependency-updates

popd
pause