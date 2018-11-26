package io.sl.ex.surefire.outofmemory.tests;

import org.aml.java2raml.Config;
import org.aml.java2raml.Java2Raml;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class RAMLTypeConverterTest {

	@Test
    public void testAgingPolicyJaxbConversion() {
        Java2Raml java2Raml = new Java2Raml();
        java2Raml.add(AgingPolicyJaxb.class);

        Config c = new Config();
        c.setPathToLookForClasses(".");
        System.out.println(java2Raml.processConfigToString(ClassLoader.getSystemClassLoader(), c));
    }

    @Test
    public void testSuspensionPolicyJaxbConversion() {
        Java2Raml java2Raml = new Java2Raml();
        java2Raml.add(ScheduledPolicyJaxb.class);

        Config c = new Config();
        c.setPathToLookForClasses(".");
        System.out.println(java2Raml.processConfigToString(ClassLoader.getSystemClassLoader(), c));
    }
	
}
    
class AgingPolicyJaxb {
	
}

class ScheduledPolicyJaxb {
	
}
