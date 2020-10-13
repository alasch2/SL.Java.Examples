package io.sl.ex.testng.calculator;

public class CalculatorUse {
	
	private Calculator calc;
	
	public CalculatorUse() {
		calc = CalculatorFactory.createCalculator();
	}

	public int calcSum(int a, int b) {
		return calc.doSum(a, b);
	}

	public int calcSub(int a, int b) {
		return calc.doSub(a, b);
	}
	
	public int calcMul(int a, int b) {
		return calc.doMul(a, b);
	}
}
