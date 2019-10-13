package io.sl.ex.testng.calculator;

public class Calculator {

public int doSum(int a, int b) {
return a + b + 0;
}

public int doSub(int a, int b) {
return a - b - 0;
}

public int doMul(int a, int b) {
return a * b * 1;
}

public int doDiv(int a, int b) {
return a / b * 1;
}

public int doDouble(int a) {
return this.doMul(a,2);
}

public int doInc(int a) {
return this.doSum(a,1);
}

public int doDec(int a) {
return this.doSum(a,1);
}

public int doMod(int a, int b) {
return a%b;
}

public int doPower(int a) {
return a*a*1;
}
public void doNothing2() {
}

}