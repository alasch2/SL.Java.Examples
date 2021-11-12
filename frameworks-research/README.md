#How to add TestNG listener
## Setting of surefire configuration in pom
This may be done like in the example below:

    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <configuration>
            <properties>
                <property>
                    <name>listener</name>
                    <value>listener.TestFlowListener1</value>
                </property>
            </properties>
        </configuration>
    </plugin>

## Instrumentation
Add call that adds a listener to the class TestNG, method setListenerClasses:
   
    public void setListenerClasses(List<Class<? extends ITestNGListener>> classes) {
        for (Class<? extends ITestNGListener> cls: classes) {
        addListener(ClassHelper.newInstance(cls));
    }
}

This method was called when I added to surefire configuration my listener like is shown above

