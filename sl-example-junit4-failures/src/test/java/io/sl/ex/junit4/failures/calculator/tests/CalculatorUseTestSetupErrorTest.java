package io.sl.ex.junit4.failures.calculator.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import io.sl.ex.junit4.failures.calculator.CalculatorUse;

public class CalculatorUseTestSetupErrorTest {

	@Before
	public void setUp() throws Exception {
		throw new RuntimeException("Fake exception in test setup");
	}

	@Test
	public void testCalcSub_shouldFaleDueToTestSetupException() {
		System.out.println("----------------See this 'testCalcSub_realObjects_setupException' is running .........");
		System.out.println("Current class loader:" + getClass().getClassLoader().toString());
		CalculatorUse mgr = new CalculatorUse();
		assertTrue(mgr.calcSub(1, 1) == 0);
	}

}
