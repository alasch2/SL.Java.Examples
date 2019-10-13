# SoapUI example
This example demonstrates Sealights integration with SoapUI.
The example project is a spring-boot application, which implements a calculator service. 
The service is tested with SoapUI.

**NOT SUPPORTED** 
1. Sealights does not support integration with the **soapui-maven-plugin**.
2. The example does not yet support gradle plugin.

## Prerequisites
SoapUI should be installed prior to the example execution:
1. Download from https://www.soapui.org/downloads/soapui.html the installer for the open source edition.
2. Run the installer.
3. Add system environment variable `SOAPUI_HOME` with a path to SoapUI installation root folder. 
4. There is a bug while running the SoapUI on windows for the first time. 
To overcome it, rename the folder `plugins`, which is located under `<user root>/.soapuios` directory to whatever name.
## Profiles and scripts
### Profile sl-soapui ###
In addition to profiles, defined in the root project, this example has an additional profile `sl-soapui`,
which is dedicated to run SoapUI integration tests with the Sealights.
The profile demands the system property `'-Dbsid'` to be provided with a value of an appropriate build-session-id;
       
    mvn test -P sl-soapui -Dbsid=<build-session-id>
### Application run script ###
The script `runApp.bat` may be used for running the application jar with the test-listener agent. 
Several values should be put into the script before the usage:
- path to the test-listener jar, 
- path to token file,
- path to build session id file with appropriate buildSessionId value

Using the script is optional, the application may be run with CLI:

     java <[-Dsl properties]> -javaagent:<path/to/test-listener> -jar <path/to/application-jar>
## Execution with Sealights
1. Edit the runApp.bat script and put the relevant values as was explained above.
2. Run maven build with the `sl` profile to build the project. For example, for build _12345_:
       
       mvn clean install -Psl -Dsl.build=12345
3. Find in the console log and copy the buildSessionId value into build-session-id file, which is used by the `runApp.bat`.
4. Run the application jar with CLI or with script:

       scripts/runApp.bat
5. From the other terminal run maven build with the profile `sl-soapui`. Provide the build-session-id from the step 3. For example:
       
       mvn install -P sl-soapui -Dbsid=299801ca-8095-4d51-9a66-3239a10e9e3d
6. Stop the running application after test finished.