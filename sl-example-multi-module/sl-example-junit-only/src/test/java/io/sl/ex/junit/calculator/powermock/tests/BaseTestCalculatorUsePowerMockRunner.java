package io.sl.ex.junit.calculator.powermock.tests;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import io.sl.ex.junit.calculator.Calculator;
import io.sl.ex.junit.calculator.CalculatorFactory;
import io.sl.ex.junit.calculator.CalculatorUse;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CalculatorFactory.class})
@PowerMockIgnore("javax.management.*")
public abstract class BaseTestCalculatorUsePowerMockRunner {
	
	static protected int SLEEP_LONG = 1000;
	static protected int SLEEP_SHORT = 100;
	
	@Mock
	Calculator calc;
	@Spy
	@InjectMocks
	CalculatorUse calcUse = new CalculatorUse();

	@Before
	public void setUp() throws Exception {
		PowerMockito.mockStatic(CalculatorFactory.class);
	}

	protected void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
