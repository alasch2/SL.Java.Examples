# RSpec example
This example demonstrates SeaLights integration with RSpec tests.
The example project is a spring-boot application implementing a calculator service. 
The service is tested with RSpec test script `spec/calc_service_spec.rb`.

## Prerequisites
1. Download and install a stable release of Ruby (Agent tested with version 2.6) from https://www.ruby-lang.org/en/downloads.
2. Install the following Ruby gems:
    - `sealights-rspec-agent`, gem file provided by SeaLights, see repo https://github.com/Sealights/SL.OnPremise.Agents.Ruby,
    - `rspec`, remote gem (support verified only  for version 3.8.2),
    - `rest-client`, remote gem,
    - `jwt`, remote gem.  
    
Gems are installed by command:

        gem install <[local-gem-file-path|remote-gem-name]>

## Scripts and configuration
### Application run script
The script `runApp.bat` may be used for running the calculator application jar with the test-listener agent. 
The following values should be put into the script before the usage:
- path to the test-listener jar,
- path to token file.

Using the script is optional, the application may be run with CLI:

     java <[-Dsl properties]> -javaagent:<path/to/test-listener> -jar <path/to/application-jar>

### Test run script
The script `runTest.bat` may be used for running the RSpec tests. The script will set required environment variables.
Before running it, it is necessary to put the path to the token file as value of the variable `SL_TOKEN_FILE_PATH`.

### Build session id file
File `buildSession4Tests.txt` is used when running both scripts as the source of build session id. 

## Execution with SeaLights
1. Edit the `runApp.bat` and `runTest.bat` scripts putting the relevant values as explained above.
2. Run maven build with the `sl` profile to build the project. For example, for build _build_12345_:
       
       mvn clean install -Psl -Dsl.build=build_12345
3. Find the `buildSessionId` value in the console log and copy it into `buildSession4Tests.txt` file.
4. Run the calculator service application jar with CLI or with script:

       scripts/runApp.bat
5. From another terminal run the RSpec tests:
        
        scripts/runTest.bat
6. Stop the calculation service application after tests are finished.

NOTE: It is important to run commands listed under 4 and 5 from this example's root as the scripts contain a relative path
to `buildSession4Tests.txt`.