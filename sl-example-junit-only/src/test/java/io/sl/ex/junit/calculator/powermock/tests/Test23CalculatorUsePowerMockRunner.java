package io.sl.ex.junit.calculator.powermock.tests;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;

import io.sl.ex.junit.calculator.CalculatorFactory;

public class Test23CalculatorUsePowerMockRunner extends BaseTestCalculatorUsePowerMockRunner {
	
	@Test
	public void testCalcInc_wihMocks() {
		System.out.println("-----------------See this 'testCalcInc_wihMocks' is running .........");
		System.out.println("Current class loader:" + getClass().getClassLoader().toString());
		for (int i = 1; i<50; i++) {
			when(calc.doInc(i)).thenReturn(i+1);
			when(CalculatorFactory.createCalculator()).thenReturn(calc);
			sleep(SLEEP_SHORT);
			assertTrue(calcUse.calcInc(i) == i+1);
		}
	}

	@Test
	public void testCalcDec_wihMocks() {
		System.out.println("-----------------See this 'testCalcDec_wihMocks' is running .........");
		System.out.println("Current class loader:" + getClass().getClassLoader().toString());
		for (int i=50; i>0; i--) {
			when(calc.doDec(i)).thenReturn(i-1);
			when(CalculatorFactory.createCalculator()).thenReturn(calc);
			sleep(SLEEP_SHORT);
			assertTrue(calcUse.calcDec(i) == i-1);
		}
	}
	
}
