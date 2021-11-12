package io.sl.ex.testng.fufu;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestFufu1 {
	
	@Test
	public void testResetFoo() {
		Fufu fufu = new Fufu();
		fufu.reset();
		assertEquals(fufu.getFoo(), 0);
	}

}
