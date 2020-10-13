package io.sl.ex.calculator.calculation.model;

public interface BinaryOperator<T> {
    T apply(T arg1, T arg2);
}
