@echo off

@rem download JDK 8 from here https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html

set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_281
set PATH=%JAVA_HOME%\bin;%PATH%

set MAVEN_HOME="C:\Drive_ITU\assignments\TEST\apache-maven-3.6.3-bin\apache-maven-3.6.3"
set PATH=%MAVEN_HOME%\bin;%PATH%

call %MAVEN_HOME%\bin\mvn.cmd --global-settings .m2\settings.xml %*
