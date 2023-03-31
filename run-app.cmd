@ECHO OFF
ECHO.
FOR /f %%i IN ('DIR target /s /b') DO RD /s /q %%i
CLS

ECHO.
FOR /f %%i in ('DIR %HOME%\.m2\repository\io\github\jtsato\moviesbattle* /b') do RD /s /q %%i
CALL mvn -e clean install -Dmaven.test.skip=true

ECHO.
CALL mvn spring-boot:run -Dspring-boot.run.jvmArguments='-Dspring.profiles.active=test' -f ./configuration
