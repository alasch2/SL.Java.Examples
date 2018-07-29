package io.sl.ex.junit.calculator.powermock.tests;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;

import io.sl.ex.junit.calculator.CalculatorFactory;

public class Test22CalculatorUsePowerMockRunner extends BaseTestCalculatorUsePowerMockRunner {
	
	@Test
	public void testCalcSum_wihMocks() {
		System.out.println("-----------------See this 'testCalcSum_wihMocks' is running .........");
		System.out.println("Current class loader:" + getClass().getClassLoader().toString());
		when(calc.doSum(1, 1)).thenReturn(0);
		when(CalculatorFactory.createCalculator()).thenReturn(calc);
		sleep(SLEEP_LONG);
		assertTrue(calcUse.calcSum(1, 1) == 0);
		//verify(calc).doSum(1, 1);
	}

	@Test
	public void testCalcMul_wihMocks() {
		System.out.println("-----------------See this 'testCalcMul_wihMocks' is running .........");
		System.out.println("Current class loader:" + getClass().getClassLoader().toString());
		when(calc.doSum(1, 1)).thenReturn(0);
		when(CalculatorFactory.createCalculator()).thenReturn(calc);
		sleep(SLEEP_LONG);
		assertTrue(calcUse.calcMul(1, 1) == 0);
	}
	
	@Test
	public void testCalcSub_wihMocks() {
		System.out.println("----------------See this 'testCalcSub_realObjects' is running .........");
		System.out.println("Current class loader:" + getClass().getClassLoader().toString());
		when(calc.doSub(1,1)).thenReturn(0);
		when(CalculatorFactory.createCalculator()).thenReturn(calc);
		sleep(SLEEP_LONG);
		assertTrue(calcUse.calcSub(1, 1) == 0);
	}
	
}
