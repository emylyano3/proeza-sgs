@echo off
pushd ..
call mvn versions:display-plugin-updates

popd
pause