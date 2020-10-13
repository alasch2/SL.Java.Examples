package io.sl.ex.junit.calculator.powermock.tests;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;

import io.sl.ex.junit.calculator.CalculatorFactory;

public class Test4CalculatorUsePowerMockRunner extends BaseTestCalculatorUsePowerMockRunner {
	
	@Test
	public void testCalcMod_wihMocks() {
		System.out.println("-----------------See this 'testCalcMod_wihMocks' is running .........");
		System.out.println("Current class loader:" + getClass().getClassLoader().toString());
		for (int i = 1; i<50; i++) {
			when(calc.doMod(i*2,i)).thenReturn(2);
			when(CalculatorFactory.createCalculator()).thenReturn(calc);
			sleep(SLEEP_SHORT);
			assertTrue(calcUse.calcMod(i*2,i) == 2);
		}
	}

	@Test
	public void testCalcPower_wihMocks() {
		System.out.println("-----------------See this 'testCalcPower_wihMocks' is running .........");
		System.out.println("Current class loader:" + getClass().getClassLoader().toString());
		for (int i=50; i>0; i--) {
			when(calc.doPower(i)).thenReturn(i*i);
			when(CalculatorFactory.createCalculator()).thenReturn(calc);
			sleep(SLEEP_SHORT);
			assertTrue(calcUse.calcPower(i) == i*i);
		}
	}
		
}
