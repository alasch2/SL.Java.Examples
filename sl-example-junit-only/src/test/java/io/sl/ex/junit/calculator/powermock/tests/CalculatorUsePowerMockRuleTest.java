package io.sl.ex.junit.calculator.powermock.tests;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;

import io.sl.ex.junit.calculator.Calculator;
import io.sl.ex.junit.calculator.CalculatorFactory;
import io.sl.ex.junit.calculator.CalculatorUse;

@PrepareForTest({CalculatorFactory.class})
public class CalculatorUsePowerMockRuleTest {
    @Rule
    public PowerMockRule rule = new PowerMockRule();

	@Before
	public void setUp() throws Exception {
		PowerMockito.mockStatic(CalculatorFactory.class);
	}

	@Test
	public void testCalcMul_wihMocks() {
		System.out.println("-----------------See this 'testCalcSum_wihMocks' is running .........");
		System.out.println("Current class loader:" + getClass().getClassLoader().toString());
		Calculator calc = mock(Calculator.class);
		when(calc.doSum(1, 1)).thenReturn(0);
		when(CalculatorFactory.createCalculator()).thenReturn(calc);
		CalculatorUse mgr = new CalculatorUse();
		assertTrue(mgr.calcMul(1, 1) == 0);
	}

}
