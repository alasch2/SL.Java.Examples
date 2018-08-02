package io.sl.ex.testng.failures.calculator.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.sl.ex.junit4.failures.calculator.CalculatorUse;

public class CalculatorUseTestNGTestSetupErrorTest {

	@BeforeMethod
	public void setUp() throws Exception {
		throw new RuntimeException("Fake exception in test setup");
	}

	@Test
	public void testNGCalcSub_shouldFailDueToTestSetupException() {
		System.out.println("----------------'testNGCalcSub_shouldFailDueToTestSetupException' is running .........");
		CalculatorUse mgr = new CalculatorUse();
		assertTrue(mgr.calcSub(1, 1) == 0);
	}

}
