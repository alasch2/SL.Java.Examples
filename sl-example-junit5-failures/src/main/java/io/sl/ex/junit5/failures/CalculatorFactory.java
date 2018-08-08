package io.sl.ex.junit5.failures;

public class CalculatorFactory {

	public static Calculator createCalculator() {
		return new Calculator();
	}

	private CalculatorFactory() {
	}

}
