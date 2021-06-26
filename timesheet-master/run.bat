@echo off

@rem download, install and run mysql server from here https://dev.mysql.com/downloads/

set JAVA_HOME=.\..\jdk1.8.0_291
set PATH=%JAVA_HOME%\bin;%PATH%

set CATALINA_HOME=".\..\apache-tomcat-7.0.108-windows-x64\apache-tomcat-7.0.108"
set PATH=%PATH%;%CATALINA_HOME%

if exist %CATALINA_HOME%\webapps\PaySystem\ (
    rmdir /s /q %CATALINA_HOME%\webapps\PaySystem\ > nul
)

if exist %CATALINA_HOME%\webapps\PaySystem.war (
    del /s %CATALINA_HOME%\webapps\PaySystem.war > nul
)

echo C:\Users\%USERNAME%\.PaySystem\
if exist C:\Users\%USERNAME%\.PaySystem\ (
    rmdir /s /q C:\Users\%USERNAME%\.PaySystem\ > nul
)

@rem restart is required to completely undeploy web app
@rem call %CATALINA_HOME%\bin\startup.bat
@rem call %CATALINA_HOME%\bin\shutdown.bat

xcopy /s /y /q target\PaySystem.war %CATALINA_HOME%\webapps > nul

if exist %CATALINA_HOME%\temp\ (
    rmdir /s /q %CATALINA_HOME%\temp\*\ > nul
)

if exist %CATALINA_HOME%\work\ (
    rmdir /s /q %CATALINA_HOME%\work\*\ > nul
)

call %CATALINA_HOME%\bin\startup.bat %*
