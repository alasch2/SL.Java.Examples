package io.sl.ex.junit.calculator;

public class CalculatorFactory {

	public static Calculator createCalculator() {
		return new Calculator();
	}

	private CalculatorFactory() {
	}

}
