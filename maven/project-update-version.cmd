@echo off
pushd ..
set VERSION=%1
set SVN_USER=%2
set SVN_PASS=%3

if "%VERSION%"=="" (
	echo project version:
	set /p VERSION=
)
call mvn versions:set -DnewVersion=%VERSION%
call mvn versions:commit

if "%SVN_USER%"=="" (
	echo svn user:
	set /p SVN_USER=
)
if "%SVN_PASS%"=="" (
	echo svn pass:
	set /p SVN_PASS=
)
call svn ci --username %SVN_USER% --password %SVN_PASS% -m "#no-id Actualizacion de las versiones de los modulos de linea: %VERSION%"

popd
pause