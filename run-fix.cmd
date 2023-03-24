@ECHO OFF
ECHO.
FOR /f %%i IN ('dir target /s /b') DO RD /s /q %%i
CLS

ECHO.
ECHO Cleaning maven repository
CALL RD %home%\.m2\repository /q /s

ECHO.
ECHO Build and Install core
CALL mvn install -Dmaven.test.skip=true -f ./core

ECHO.
ECHO Build and Install infra
CALL mvn install -Dmaven.test.skip=true -f ./infra/database

ECHO.
ECHO Build and Install entrypoints
CALL mvn install -Dmaven.test.skip=true -f ./entrypoints/rest

ECHO.
ECHO Build and Install application
CALL mvn install verify

ECHO.
CALL mvn spring-boot:run -Dspring-boot.run.jvmArguments='-Dspring.profiles.active=test' -f ./configuration
