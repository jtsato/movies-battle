@ECHO OFF
ECHO.
FOR /f %%i IN ('dir target /s /b') DO RD /s /q %%i
CLS

ECHO.
CALL mvn clean install -Dmaven.test.skip=true

ECHO.
CALL mvn spring-boot:run -Dspring-boot.run.jvmArguments='-Dspring.profiles.active=test' -f ./configuration
