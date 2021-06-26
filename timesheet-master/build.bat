@echo off

@rem download JDK 8 from here https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html

set JAVA_HOME=.\..\jdk1.8.0_291
set PATH=%JAVA_HOME%\bin;%PATH%

set MAVEN_HOME=".\..\apache-maven-3.8.1-bin\apache-maven-3.8.1"
set PATH=%MAVEN_HOME%\bin;%PATH%

call %MAVEN_HOME%\bin\mvn.cmd --global-settings .m2\settings.xml %*
