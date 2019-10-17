package io.sl.ex.calculator.calculation.model;

import java.util.Objects;

public class ResponseBody {
    private final double result;

    public ResponseBody(double result) {
        this.result = result;
    }

    public double getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseBody responseBody = (ResponseBody) o;
        return Double.compare(responseBody.result, result) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(result);
    }
}
