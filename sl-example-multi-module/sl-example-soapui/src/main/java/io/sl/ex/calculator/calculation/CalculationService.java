package io.sl.ex.calculator.calculation;

import org.springframework.stereotype.Service;

@Service
public class CalculationService {
    public double add(double leftOperand, double rightOperand) {
        return leftOperand + rightOperand;
    }

    public double subtract(double leftOperand, double rightOperand) {
        return leftOperand - rightOperand;
    }

    public double multiply(double leftOperand, double rightOperand) {
        return leftOperand * rightOperand;
    }

    public double divide(double leftOperand, double rightOperand) {
        return leftOperand / rightOperand;
    }

    public double squareRoot(double operand) {
        return Math.sqrt(operand);
    }
}
