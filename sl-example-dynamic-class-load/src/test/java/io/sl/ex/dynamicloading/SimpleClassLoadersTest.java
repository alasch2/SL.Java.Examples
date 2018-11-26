package io.sl.ex.dynamicloading;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SimpleClassLoadersTest {

	@Test
	public void executeWithSimpleLoader_userCreated() throws Exception {
		new UseSimpleLoaders().executeWithSimpleLoader();
		
		assertTrue("Does not reach here due to exception", true);
	}


	@Test
	public void executeWithInnerLoader_userCreated() throws Exception {
		new UseSimpleLoaders().executeWithInnerLoader();
		
		assertTrue("Does not reach here due to exception", true);
	}
}
