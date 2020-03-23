package io.sl.ex.webcalculator;

import org.junit.Test;

import io.sl.ex.webcalculator.Calculator;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    private Calculator calculator = new Calculator();

    @Test
    public void shouldAdd() {
        int sum = calculator.add(1, 2);

        assertEquals(3, sum);
    }

    @Test
    public void subtract() {
        int result = calculator.subtract(1, 2);

        assertEquals(-1, result);
    }
}