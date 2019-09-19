SoapUI example
==============
This example demonstrates how to run a build including SoapUI tests with SeaLights. 

The run consists of the following steps:
1. Build the project.
2. Start the Spring Boot application before integration tests.
3. In the integration tests stage SoapUI Test Case runner will be executed as a Java application with the Test Listener agent.
3. Stop the Spring Boot application.

Running
-------
Profile `run-soapui-test-cases` is dedicated for building this module with SeaLights integration.

Currently, running the build requires passing an appropriate build session id as a value of the `bsid` parameter.

Change the path to the test listener jar in the module's pom `test.listener.jar.path` property.

Also, the `soapui.classpath` property should be set to point to jars necessary to run SoapUI (e.g. if you have SoapUI 5.5.0 installed: `%SOAPUI_HOME%\soapui-5.5.0.jar;%SOAPUI_HOME%\..\lib*` with the environment variable `%SOAPUI_HOME%` replaced by the actual path). 

A command like the on below should be run from `sl-example-soapui` project's root:   
```
mvn clean install -Prun-soapui-test-cases -Dbsid=299801ca-8095-4d51-9a66-3239a10e9e3d
```