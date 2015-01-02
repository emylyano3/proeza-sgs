@echo off
pushd ..
call mvn versions:display-property-updates

popd
pause