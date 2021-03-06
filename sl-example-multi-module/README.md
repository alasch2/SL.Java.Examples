Example of java multi-module project
Examples:
 * example-java8 - java8 specific stuff: streams, lambdas etc.; also is using lombok - ignore of autogenerated methods may be tested
 * example-junit5 - a combination of JUnit5 and JUnit4 tests.
 * example-junit-only - JUnit4 tests, some using the PowerMock.
 * example-junit-testng - a combination of JUnit4 and TestNG tests
 * example-testng-only - TestNG tests, some using the PowerMock (only for Maven plugin).
 * example-jmockit - using of jmockit for tests.
 * example-soapui - using SopaUI for tests. See the project ReadMe for details.
 * example-cucumber - using of cucumber for tests.

## Build types
Any module may be built individually or built all together.

By default the example is built without sealights.

## Build with sealights
Similar to a regular build, the build with sealights may be done for all the modules together
 or to a single module. 
 
When all the modules are built together the same application name is used for all the projects. 
 
When a single module is built, its own application name is used.

For both plugins all the plugin parameters are defined in the example root project and they are inherited by 
the modules. 
Each module redefines the **appName** plugin parameter.

## Maven examples
### Profiles
1. The default profile executes just a regular build without sealights plugin.
 The maven command is executed in the same way for either root project or any sub-project.
2. The profile **'sl'** executes a build of a sub-project as an individual application. 
It is intended for a subproject build and so is not effective if applied to the root project.
See the Examples section for usage.
3. The profile **'sl-all'** executes a build of all sub-projects with the same application name. 
It is intended for root recursive build of the root project. 
When it is applied to the sub-project, it works same as the **'sl'** profile.
See the Examples section for usage.

### Pom Settings
There are several properties, that are used by the sealights-maven-plugin, that should be defined in the `dev.properties` file, located in the root project:
1. The property `tokenfile.path` should be set to the relevant token file location.
2. The property `buildscanner.path` should be set to the actual path to the build-scanner jar.
3. The property `testlistener.path` should be set to the actual path to the test-listener jar.

**NOTE:** If the dev.properties cannot ve found or has invalid values, the build proceeds without Sealights

### Sealigts Plugin Parameters 
1. The Sealigts plugin parameters are defined in the root project pom; the sub-projects poms by default contain only **appName** parameter.
2. Parameter, which is defined in the sub-project, overrides the parameter in the root project.
3. When running build with profiles _'sl'_ or _'sl-all'_  the build number should be provided as system property
 _'-Dsl.build={build value}'_ .

### Examples
1. Build all projects as a single application. Run from the root project directory:

       mvn clean install -Psl-all -Dsl.build=12345
2. Build a particular project from the root project directory:

       mvn clean install -f example-java8 -Psl -Dsl.build=12345  
       
3. Build a particular project from the project directory:
   
       mvn clean install -Psl -Dsl.build=12345  
   
## Gradle examples
### Build parameters
1. By default the regular build is executed.
2. The option **'-P sl'** or **'-Psl'** stands for build with the sealights plugin for either root or sub-project.
See the Examples section for usage.
3. The option **'-P all'** or **'-Pall'** stands for build with the sealights plugin of all projects as a single application. 
It is affective only together with **'-P sl'**.
See the Examples section for usage.

### JUnit5 support
1. The system property **'-Djunit5=true'** should be provided for build of a project with junit5 tests. 
This property is needed for any build - with or without sealights options.
See the Examples section for usage.
2. Project with junit5 demands the min gradle version 4.6. 
If the default gradle version on the path is less than 4.6, the explicit path to the gradle 4.6 should be used 
in the build command. See the Examples section for usage.

### Plugin Parameters 
1. The plugin parameters are defined in the build.gradle of the root project; the sub-projects build.gradle contain only **appName** parameter.
2. Build number should be provided as system property _'-Dsl.build={build value}'_ on any build with _'-P sl'_ option.

### Examples
1. Build all projects with sealights as a single application. Run from the root project directory:

    _/path/to/gradle-4.6/gradle build test -P sl -P all -Djunit5=true -Dsl.build=12345_
2. Same as above example, but with the default gradle version 4.6 or higher. Run from the root project directory:
    
    _gradle build test -P sl -P all -Djunit5=true -Dsl.build=12345_
3. Build a particular project with sealights :

   _gradle build test -P sl -Dbuild=12345 -p [the project directory]_
 4. Build a particular project with sealights and junit5 tests:
 
    _/path/to/gradle-4.6/gradle build test -P sl -Djunit5=true -Dsl.build=12345 -p [the project directory]_

### Gradle 5 support
Lombok dependency in gradle 5 should be added as following (see java8 example):
    
    annotationProcessor("org.projectlombok:lombok:$versions.lombok")
    implementation("org.projectlombok:lombok:$versions.lombok") 

While in older gradle versions it is just:

    compile "org.projectlombok:lombok:${versions.lombok}"

   