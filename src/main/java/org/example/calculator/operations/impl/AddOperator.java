package org.example.calculator.operations.impl;

import org.example.calculator.exception.OperationOverflowException;
import org.example.calculator.operations.OperationStrategy;

public class AddOperator implements OperationStrategy {
    @Override
    public Number apply(Number x, Number y) {
        if ((y.doubleValue() > 0 && x.doubleValue() > Integer.MAX_VALUE - y.doubleValue())
                || (y.doubleValue() < 0 && x.doubleValue() < Integer.MIN_VALUE - y.doubleValue())) {
            throw new OperationOverflowException("add operation " + x + " + " + y + " overflows");
        }
        return x.doubleValue() + y.doubleValue();
    }
}
