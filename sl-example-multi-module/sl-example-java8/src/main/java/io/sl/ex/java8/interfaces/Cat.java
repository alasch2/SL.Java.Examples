package io.sl.ex.java8.interfaces;

public class Cat implements Animal {
    @Override
    public void move() {
        System.out.println("Cat moves using four legs.");
    }

    @Override
    public String getName() {
        return "cat";
    }

}
