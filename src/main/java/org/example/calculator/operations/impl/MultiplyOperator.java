package org.example.calculator.operations.impl;

import org.example.calculator.exception.OperationOverflowException;
import org.example.calculator.operations.OperationStrategy;

public class MultiplyOperator implements OperationStrategy {
    @Override
    public Number apply(Number x, Number y) {
        if (x.doubleValue() * y.doubleValue() > Integer.MAX_VALUE || x.doubleValue() * y.doubleValue() < Integer.MIN_VALUE) {
            throw new OperationOverflowException("multiply operation " + x + " * " + y + " overflows");
        }
        return x.doubleValue() * y.doubleValue();
    }
}
