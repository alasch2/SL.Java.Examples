package io.sl.ex.testng.calculator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CalcTestNGTest {

    @DataProvider(name = "provider1")
    public static Object[][] data() {
        return new Object[][] {
                 { 0, 0 }, { 1, 1 }, { 2, 4 }, { 3, 9 }
           };
    }

    @Test(dataProvider = "provider1")
    public void testDoPower(int input, int expected) {
        Calculator calc = new Calculator();
        assertEquals(expected, calc.doPower(input));
     }

     @Test
     public void testNothing() {
        assertTrue(true);
     }
}