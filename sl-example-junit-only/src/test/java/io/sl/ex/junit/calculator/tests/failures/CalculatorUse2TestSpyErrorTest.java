package io.sl.ex.junit.calculator.tests.failures;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import io.sl.ex.junit.calculator.CalculatorUse2;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorUse2TestSpyErrorTest {
	
	@Spy 
	CalculatorUse2 calcUse2;

	@Before
	public void setUp() throws Exception {
		//throw new RuntimeException("Fake exception in test setup");
	}

	@Test
	public void testCalcSub_realObjects_spyProblem() {
		System.out.println("----------------See this 'testCalcSub_realObjects_spyProblem' is running .........");
		System.out.println("Current class loader:" + getClass().getClassLoader().toString());
		assertTrue(calcUse2.calcSub(1, 1) == 0);
	}

}
