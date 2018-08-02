package io.sl.ex.testng.failures.calculator.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.sl.ex.junit4.failures.calculator.CalculatorUse;

public class CalculatorUseTestNGClassSetupErrorTest {
	
	@BeforeClass
	public static void setupClass() throws Exception {
		throw new RuntimeException("Class setup: Fake exception");
	}

	@Test
	public void testNGCalcSub_shouldFailDueToClassSetupException1() {
		System.out.println("----------------'testCalcSub_shouldFailDueToClassSetupException1' is running .........");
		CalculatorUse mgr = new CalculatorUse();
		assertTrue(mgr.calcSub(1, 1) == 0);
	}

	@Test
	public void testNGCalcSub_shouldFailDueToClassSetupException2() {
		System.out.println("----------------'testCalcSub_shouldFailDueToClassSetupException2' is running .........");
		CalculatorUse mgr = new CalculatorUse();
		assertTrue(mgr.calcSub(1, 1) == 0);
	}

}
