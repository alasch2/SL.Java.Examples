package io.sl.ex.junit4.failures.calculator.tests;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import io.sl.ex.junit4.failures.calculator.CalculatorUse;

public class CalculatorUseClassSetupErrorTest {
	
	@BeforeClass
	public static void setupClass() throws Exception {
		throw new RuntimeException("Class setup: Fake exception");
	}

	@Test
	public void testCalcSub_shouldFailDueToClassSetupException() {
		System.out.println("---------------- 'testCalcSub_shouldFailDueToClassSetupException' is running .........");
		CalculatorUse mgr = new CalculatorUse();
		assertTrue(mgr.calcSub(1, 1) == 0);
	}

}
