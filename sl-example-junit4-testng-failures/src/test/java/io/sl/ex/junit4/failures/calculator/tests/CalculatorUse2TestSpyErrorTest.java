package io.sl.ex.junit4.failures.calculator.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import io.sl.ex.junit4.failures.calculator.CalculatorUse2;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorUse2TestSpyErrorTest {
	
	// This spy cannot work, since Spy annotation demands default constructor
	// Test initialization fails and test is neither run nor considered in surefire reports
	@Spy 
	CalculatorUse2 calcUse2;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCalcSub_shouldFailDueToInvalidSpy() {
		System.out.println("----------------'testCalcSub_shouldFailDueToInvalidSpy' is running .........");
		assertTrue(calcUse2.calcSub(1, 1) == 0);
	}

}
