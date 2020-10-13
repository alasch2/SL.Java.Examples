package io.sl.ex.junittestng.calculator;

public class Calculator {

	@Calculate
	public int doSum(int a, int b) {
		return a + b;
	}

	@Calculate
	public int doSub(int a, int b) {
		return a - b;
	}

	@Calculate
	public int doMul(int a, int b) {
		return a * b;
	}

	@Calculate
	public int doDiv(int a, int b) {
		return a / b;
	}

	@Calculate
	public int doDouble(int a) {
		return this.doMul(a,2);
	}

	@Calculate
	public int doInc(int a) {
		return this.doSum(a,1);
	}

	@Calculate
	public int doDec(int a) {
		return this.doSum(a,1);
	}

	@Calculate
	public int doMod(int a, int b) {
		return a%b;
	}

	public int doPower(int a) {
		return a*a;
	}
}