package io.sl.ex.calculator.calculation.model;

import java.util.Objects;

public class BinaryOperationRequest {
    private BinaryOperation binaryOperation;
    private double leftOperand;
    private double rightOperand;

    private BinaryOperationRequest() {
        // for jackson deserialization
    }

    public BinaryOperationRequest(BinaryOperation binaryOperation, Double leftOperand, Double rightOperand) {
        this.binaryOperation = binaryOperation;
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    public BinaryOperation getBinaryOperation() {
        return binaryOperation;
    }

    public double getLeftOperand() {
        return leftOperand;
    }

    public double getRightOperand() {
        return rightOperand;
    }

    @Override
    public String toString() {
        return "BinaryOperationRequest{" +
                "binaryOperation=" + binaryOperation +
                ", leftOperand=" + leftOperand +
                ", rightOperand=" + rightOperand +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryOperationRequest that = (BinaryOperationRequest) o;
        return Double.compare(that.leftOperand, leftOperand) == 0 &&
                Double.compare(that.rightOperand, rightOperand) == 0 &&
                binaryOperation == that.binaryOperation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(binaryOperation, leftOperand, rightOperand);
    }
}
