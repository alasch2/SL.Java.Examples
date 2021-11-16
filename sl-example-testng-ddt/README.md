# TestNG implementation
Common possible problems with data driven tests
* Test parameters serialization
* In some cases customer could deny sending actual tests parameters to Sealights
* Visualization of parameters in GUI
* Detection changes of full list of parameters between multiple runs
* Seems currently agent does not support sending information about parameters, so need to agree way, how information about parameters will send.


## Main idea of implementation support of data driven test via TestNG
* Add new, or customize existing TestNG tests listener and add additional handling of org.testng.ITestResult object.
* org.testng.ITestResult allows get actual parameters of tests with org.testng.ITestResult#getParameters, if size of parameters greater than zero - test parameterized
* All test parameters are serialized using jackson, jackson should be configured with ordering objects fields and map keys
* Need to calculate hash of serialization result string, as each test could have not a lot of parameters some simple hash function could be used, like CRC32 or any other.
* Agent sent hash of serialized parameters for each tests
* Agent sent one time actual parameters of tests with hash information, to have possibility to map this information on backend side

## Advantages of using jackson serialization
* It is possible to serialize most of POJO object.
* Custom serialization for un-regular objects
* Most used library for serialization

## Disadvantages of using jackson serialization
* Not all types could be serialized
* Increasing time of tests execution(usually first type serialization could take more time)
* Possible issue with serialization big objects
* Possible issue with some dynamic fields like Date, or other representation of current time, or some random initialization fields

## Support other test frameworks
If idea of using parameters serialization is good enough, it could be implemented for other test frameworks.
The main difference will be in place where parameters could be caught. 

# Examples of data serialization:
## Test method
```
    @Test(dataProvider = "dataProvider")
    public void test(TestData testData) {
        DataDrivenTestCommon.test(testData.input, testData.expected);
    }
```
## Test data class
```
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class TestData {
        private String input;
        private String expected;
        private String comment;
        private String[] stringArray;

        private FileInputStream inputStream;

        public TestData(Object input, String expected) throws FileNotFoundException {
            this(String.valueOf(input), String.valueOf(expected), "some-comment", new String[]{"val1", "val2"}, new FileInputStream("/tmp/somefile.txt"));
        }
    }
```
## Serialization representation
```
 [{"comment":"some-comment","expected":"HELLO","input":"hello","inputStream":{"path":"/tmp/somefile.txt"},"stringArray":["val1","val2"]}]
```
## testId representation

Possible **testId** representation `DataDrivenObjectsTest.test.e25ca0df` where `e25ca0df` hex representation of CRC32 hash result based on json string, or some other format
Currently, agent working only with testId and does not support other test parameters. 
As I understand adding additional parameters will make solution more clearly, but require more changes in current classes.
