package io.sl.ex.junit.calculator;

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
	
	public int calcDiv(int a, int b) {
		return calc.doDiv(a, b);
	}
	
	public int calcDouble(int a) {
		return calc.doDouble(a);
	}

	public int calcInc(int a) {
		return calc.doInc(a);
	}

	public int calcDec(int a) {
		return calc.doDec(a);
	}

	public int calcMod(int a, int b) {
		return calc.doMod(a, b);
	}

	public int calcPower(int a) {
		return calc.doPower(a);
	}
}
