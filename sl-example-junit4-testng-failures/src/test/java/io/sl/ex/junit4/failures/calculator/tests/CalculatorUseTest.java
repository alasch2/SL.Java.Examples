package io.sl.ex.junit4.failures.calculator.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import io.sl.ex.junit4.failures.calculator.CalculatorUse;

public class CalculatorUseTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCalcSub_shouldBeOk() {
		System.out.println("----------------'testCalcSub_shouldBeOk' is running .........");
		CalculatorUse mgr = new CalculatorUse();
		assertTrue(mgr.calcSub(1, 1) == 0);
	}

	@Test
	public void testCalcSub_shouldFailed() {
		System.out.println("----------------'testCalcSub_shouldFailed' is running .........");
		CalculatorUse mgr = new CalculatorUse();
		assertFalse(mgr.calcSub(1, 1) == 0);
	}

	@Ignore
	@Test
	public void testCalcSub_shouldBeIgnored() {
		System.out.println("----------------'testCalcSub_shouldBeIgnored' is running .........");
		CalculatorUse mgr = new CalculatorUse();
		assertFalse(mgr.calcSub(1, 1) == 0);
	}
}
