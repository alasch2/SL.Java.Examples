package io.sl.ex.junit.calculator.tests.failures;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import io.sl.ex.junit.calculator.CalculatorUse;

public class CalculatorUseTestSetupErrorTest {

	@Before
	public void setUp() throws Exception {
		throw new RuntimeException("Fake exception in test setup");
	}

	@Test
	public void testCalcSub_realObjects_setupException() {
		System.out.println("----------------See this 'testCalcSub_realObjects_setupException' is running .........");
		System.out.println("Current class loader:" + getClass().getClassLoader().toString());
		CalculatorUse mgr = new CalculatorUse();
		assertTrue(mgr.calcSub(1, 1) == 0);
	}

}
