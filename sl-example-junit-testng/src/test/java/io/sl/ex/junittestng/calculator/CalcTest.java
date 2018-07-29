package io.sl.ex.junittestng.calculator;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import io.sl.ex.junittestng.calculator.Calculator;

@RunWith(Parameterized.class)
public class CalcTest {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                 { 0, 1 }, { 2, 4 }, { 3, 6 }, { 0, 1 }, { 4, 8 }, { 0, 1 }, { 5, 10 }
           });
    }

    private int input;

    private int expected;

    public CalcTest(int input, int expected) {
        this.input= input;
        this.expected= expected;
        System.out.println("inputs: " + input +" expected: " + expected);
    }

    @Test
    public void testIncAndDbl() {
        Calculator calc = new Calculator();
        if (input == 0) {
            assertEquals(expected, calc.doInc(input));
        } else {
            assertEquals(expected, calc.doDouble(input));
        }
    }
}