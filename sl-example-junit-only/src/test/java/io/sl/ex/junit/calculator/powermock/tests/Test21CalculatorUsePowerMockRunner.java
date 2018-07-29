package io.sl.ex.junit.calculator.powermock.tests;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;

import io.sl.ex.junit.calculator.CalculatorFactory;
import io.sl.ex.junit.calculator.CalculatorUse;

public class Test21CalculatorUsePowerMockRunner extends BaseTestCalculatorUsePowerMockRunner {
	
	@Test
	public void testCalcDiv_wihMocks() {
		System.out.println("-----------------See this 'testCalcDiv_wihMocks' is running .........");
		System.out.println("Current class loader:" + getClass().getClassLoader().toString());
		when(calc.doDiv(1, 1)).thenReturn(1);
		when(CalculatorFactory.createCalculator()).thenReturn(calc);
		sleep(SLEEP_LONG);
		assertTrue(calcUse.calcDiv(1, 1) == 1);
		//verify(calc).doSum(1, 1);
	}

	@Test
	public void testCalcDouble_wihMocks() {
		System.out.println("-----------------See this 'testCalcDouble_wihMocks' is running .........");
		System.out.println("Current class loader:" + getClass().getClassLoader().toString());
		when(calc.doDouble(1)).thenReturn(2);
		when(CalculatorFactory.createCalculator()).thenReturn(calc);
		CalculatorUse mgr = new CalculatorUse();
		sleep(SLEEP_LONG);
		assertTrue(mgr.calcDouble(1) == 2);
	}
	
}
