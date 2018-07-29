package io.sl.ex.junittestng.calculator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.sl.ex.junittestng.calculator.Calculator;

import static org.testng.Assert.assertEquals;

public class CalcTestNGTest {

    @DataProvider(name = "provider1")
    public static Object[][] data() {
        return new Object[][] {
                 { 0, 0 }, { 1, 1 }, { 2, 4 }, { 3, 9 }
           };
    }

    @Test(dataProvider = "provider1")
    public void testDoPower(int input, int expected) {
        System.out.println("inputs: " + input +" expected: " + expected);
        Calculator calc = new Calculator();
        assertEquals(expected, calc.doPower(input));
     }
}