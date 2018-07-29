package io.sl.ex.junittestng.calculator;

public class CalculatorFactory {

	public static Calculator createCalculator() {
		return new Calculator();
	}

	private CalculatorFactory() {
	}

}
