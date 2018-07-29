package io.sl.ex.junit.calculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class CalcWithPowerMockTest {

	private static Integer[] INPUTS = {0,2,3,0,4,0,5};
	private static Integer[] OUTPUTS = {1,4,6,1,8,1,10};

    @Test
    public void testIncAndDbl() {
    	for (int i =0; i<INPUTS.length; i++) {
    		int expected = OUTPUTS[i];
    		Calculator calc = new Calculator();
    		if (INPUTS[i] == 0) {
    			assertEquals(expected, calc.doInc(INPUTS[i]));
    		} else {
    			assertEquals(expected, calc.doDouble(INPUTS[i]));
    		}
    	}
    }
}