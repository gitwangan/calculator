package org.example.calculator.operations.impl;

import org.example.calculator.operations.OperationStrategy;

public class DivideOperator implements OperationStrategy {
    @Override
    public Number apply(Number x, Number y) {
        if (y.intValue() == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return x.doubleValue() / y.doubleValue();
    }
}
