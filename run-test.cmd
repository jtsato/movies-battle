@ECHO OFF
ECHO.
FOR /f %%i IN ('dir target /s /b') DO RD /s /q %%i
CLS

ECHO.
CALL mvn -e clean test -Dspring.profiles.active=test