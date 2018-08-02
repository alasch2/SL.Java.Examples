package io.sl.ex.testng.failures.calculator.tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.sl.ex.junit4.failures.calculator.CalculatorUse;

public class CalculatorUseTestNGTest {

	@BeforeMethod
	public void setUp() throws Exception {
	}

	@Test
	public void testNGCalcSub_shouldBeOk() {
		System.out.println("----------------'testNGCalcSub_shouldBeOk' is running .........");
		CalculatorUse mgr = new CalculatorUse();
		assertTrue(mgr.calcSub(1, 1) == 0);
	}

	@Test
	public void testNGCalcSub_shouldFailed() {
		System.out.println("----------------'testNGCalcSub_shouldFailed' is running .........");
		CalculatorUse mgr = new CalculatorUse();
		assertFalse(mgr.calcSub(1, 1) == 0);
	}
	
	@Test(enabled = false)
	public void testNGCalcSub_shouldBeIgnored() {
		System.out.println("----------------'testNGCalcSub_shouldBeIgnored' is running .........");
		CalculatorUse mgr = new CalculatorUse();
		assertFalse(mgr.calcSub(1, 1) == 0);
	}
}
