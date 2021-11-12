package impl;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

import listener.TestFlowListener2;

//@Listeners({TestFlowListener2.class})
public class FailedTest {

	@Test
	public void passed_1() {
		assertTrue(true);
	}
	
	@Test
	public void passed_2() {
		assertTrue(true);
	}
	@Test
	public void passed_3() {
		assertTrue(true);
	}
	@Test
	public void failed_1() {
		assertTrue(false);
	}
}
