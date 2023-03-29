@ECHO OFF
ECHO.
FOR /f %%i IN ('dir target /s /b') DO RD /s /q %%i
CLS

ECHO.
FOR /f %%i in ('DIR %HOME%\.m2\repository\io\github\jtsato\moviesbattle* /b') do RD /s /q %%i
CALL mvn -e clean compile
CALL mvn -e clean install -Dmaven.test.skip=true

ECHO.
CALL mvn -e clean test -Dspring.profiles.active=test
