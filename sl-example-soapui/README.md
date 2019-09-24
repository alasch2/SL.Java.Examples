# SoapUI example
This example demonstrates how to use Sealights for SoapUI tests.

Currently Sealights does not support integration with the **soapui-maven-plugin** and 
in this example SoapUI tests are run via CLI.
**NOTE** 
Example still does not provide the code coverage.
The example is not yet supported for gradle plugin

## Preconditions
SoapUI should be installed prior to the example execution:
1. Download from https://www.soapui.org/downloads/soapui.html the installer for the open source edition.
2. Run the installer.
3. Add system environment variable `SOAPUI_HOME` with a path to SoapUI installation root folder. 
4. There is a bug while running the SoapUI on windows for the first time. 
To overcome it, rename the folder `plugins`, which is located under `<user root>/.soapuios` directory to whatever name.
## Profiles
In addition to profiles, defined in the root project, this example has an additional profile `sl-soapui` which is dedicated to run SoapUI integration tests with the Sealights.
When using this profile the system property '-Dsl.bsid' must be provided with a value of appropriate build-session-id.

## Execution
1. Run maven with the `sl` profile to build the project, like in the example below:
       
       mvn test -Psl -Dsl.build=12345
2. Run maven with the profile `sl-soapui`. Use build-session-id, created on the previous step (look for it in the console log), like in the example below:
       
       mvn install -P sl-soapui -Dbsid=299801ca-8095-4d51-9a66-3239a10e9e3d
