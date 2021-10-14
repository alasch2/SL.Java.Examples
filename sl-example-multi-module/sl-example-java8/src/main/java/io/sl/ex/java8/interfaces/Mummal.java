package io.sl.ex.java8.interfaces;

public interface Mummal {
    String getName();

    default void showName() {
        System.out.println(getName());
    }
}
