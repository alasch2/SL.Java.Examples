package io.sl.ex.java8.interfaces;

public interface Animal extends Mummal {
    public static boolean canMove() {
        return true;
    }

    void move();
}
