package io.sl.ex.testng.fufu;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestFufu2 {
	
	@Test
	public void testSetFoo() {
		Fufu fufu = new Fufu();
		fufu.setFoo(123);
		assertEquals(fufu.getFoo(), 123);
	}

}
