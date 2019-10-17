package io.sl.ex.calculator.calculation.model;

public interface Function<T, R> {
    R apply(T arg);
}
