package io.sl.ex.junit4.failures.calculator;

public class CalculatorFactory {

	public static Calculator createCalculator() {
		return new Calculator();
	}

	private CalculatorFactory() {
	}

}
