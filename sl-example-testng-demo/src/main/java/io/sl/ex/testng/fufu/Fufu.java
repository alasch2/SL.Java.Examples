package io.sl.ex.testng.fufu;

public class Fufu {
    private int foo = 100;

    public void reset() {
        this.foo = 0;
    }

    public void setFoo(int foo) {
        this.foo = foo;
    }

    public int getFoo() {
        return foo;
    }
}
