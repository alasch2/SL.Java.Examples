package io.sl.ex.testng.calculator;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertTrue;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@PrepareForTest(CalculatorFactory.class)
public class CalcTestNGWithPowerMockTest extends PowerMockTestCase {

	@Mock
	Calculator calc;
	
	CalculatorUse calcUse;
	
	@BeforeMethod
	public void before() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCalcSum_wihMocks() {
		System.out.println("-----------------See this 'testCalcSum_wihMocks' is running .........");
		System.out.println("Current class loader:" + getClass().getClassLoader().toString());
		PowerMockito.mockStatic(CalculatorFactory.class);
		when(calc.doSum(1, 1)).thenReturn(0);
		when(CalculatorFactory.createCalculator()).thenReturn(calc);
		calcUse = new CalculatorUse();
		
		assertTrue(calcUse.calcSum(1, 1) == 0);
	}

}