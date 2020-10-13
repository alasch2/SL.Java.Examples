package io.sl.ex.calculator.calculation.model;

import io.sl.ex.calculator.calculation.CalculationService;

public enum BinaryOperation {
    ADDITION(new Function<CalculationService, BinaryOperator<Double>>() {
        public BinaryOperator<Double> apply(final CalculationService arg) {
            return new BinaryOperator<Double>() {
                public Double apply(Double arg1, Double arg2) {
                    return arg.add(arg1, arg2);
                }
            };
        }
    }),
    SUBTRACTION(new Function<CalculationService, BinaryOperator<Double>>() {
        public BinaryOperator<Double> apply(final CalculationService arg) {
            return new BinaryOperator<Double>() {
                public Double apply(Double arg1, Double arg2) {
                    return arg.subtract(arg1, arg2);
                }
            };
        }
    }),
    MULTIPLICATION(new Function<CalculationService, BinaryOperator<Double>>() {
        public BinaryOperator<Double> apply(final CalculationService arg) {
            return new BinaryOperator<Double>() {
                public Double apply(Double arg1, Double arg2) {
                    return arg.multiply(arg1, arg2);
                }
            };
        }
    }),
    DIVISION(new Function<CalculationService, BinaryOperator<Double>>() {
        public BinaryOperator<Double> apply(final CalculationService arg) {
            return new BinaryOperator<Double>() {
                public Double apply(Double arg1, Double arg2) {
                    return arg.divide(arg1, arg2);
                }
            };
        }
    });

    private final Function<CalculationService, BinaryOperator<Double>> operatorProvider;

    BinaryOperation(Function<CalculationService, BinaryOperator<Double>> operatorProvider) {
        this.operatorProvider = operatorProvider;
    }

    public BinaryOperator<Double> getOperator(CalculationService calculationService) {
        return operatorProvider.apply(calculationService);
    }
}
