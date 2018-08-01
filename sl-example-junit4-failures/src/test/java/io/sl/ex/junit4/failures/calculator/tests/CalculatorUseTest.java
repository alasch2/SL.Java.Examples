package io.sl.ex.junit4.failures.calculator.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import io.sl.ex.junit4.failures.calculator.CalculatorUse;

public class CalculatorUseTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCalcSub_shouldBeOk() {
		System.out.println("----------------See this 'testCalcSub_realObjects' is running .........");
		System.out.println("Current class loader:" + getClass().getClassLoader().toString());
		CalculatorUse mgr = new CalculatorUse();
		assertTrue(mgr.calcSub(1, 1) == 0);
	}

	@Test
	public void testCalcSub_shouldFailed() {
		System.out.println("----------------See this 'testCalcSub_failed' is running .........");
		System.out.println("Current class loader:" + getClass().getClassLoader().toString());
		CalculatorUse mgr = new CalculatorUse();
		assertFalse(mgr.calcSub(1, 1) == 0);
	}
}
