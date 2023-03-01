ECHO.
FOR /f %%i IN ('dir target /s /b') DO RD /s /q %%i
CLS

ECHO.
CALL mvn -e clean install org.pitest:pitest-maven:mutationCoverage