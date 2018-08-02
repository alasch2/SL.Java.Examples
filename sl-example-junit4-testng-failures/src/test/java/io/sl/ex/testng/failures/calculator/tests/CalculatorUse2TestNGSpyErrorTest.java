package io.sl.ex.testng.failures.calculator.tests;

import static org.testng.Assert.assertTrue;

import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.sl.ex.junit4.failures.calculator.CalculatorUse2;

public class CalculatorUse2TestNGSpyErrorTest {
	
	@Spy 
	CalculatorUse2 calcUse2;

	@BeforeMethod
	public void setUpTest() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testNGCalcSub_shouldFailDueToInvalidSpy() {
		System.out.println("----------------'testCalcSub_shouldFailDueToInvalidSpyWithTestNG' is running .........");
		assertTrue(calcUse2.calcSub(1, 1) == 0);
	}

}
