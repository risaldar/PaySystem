# Environment Setup
1. Download maven from here https://maven.apache.org/download.cgi
2. Download and install the mysql workbench from here https://dev.mysql.com/downloads/installer/
3. Download jdk1.8+
4. In the .\timesheet-master\build.bat, set the JAVA_HOME to jdk path and similarly set MAVEN_HOME to the maven path.
5. In the .\timesheet-master\run.bat, set the JAVA_HOME and set CATALINA_HOME to absolute path appended by ".\PaySystem\apache-tomcat-7.0.108-windows-x64\apache-tomcat-7.0.108".
6. Open Command prompt, navigate to project repository i.e .\Paysystem\timesheet-master\, execute build.bat.
7. This will build the project.
8. Open mysql workbench and enter following two queries: 
DROP DATABASE paysystem;
Create DATABASE paysystem;
9. When the database is created for first time, only execute the create query.
10. Execute run.bat.