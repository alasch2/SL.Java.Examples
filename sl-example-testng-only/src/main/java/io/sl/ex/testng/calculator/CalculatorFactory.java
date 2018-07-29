package io.sl.ex.testng.calculator;

public class CalculatorFactory {

	public static Calculator createCalculator() {
		return new Calculator();
	}

	private CalculatorFactory() {
	}

}
