package org.example.calculator.operations.impl;

import org.example.calculator.operations.Operation;

public enum ArithmeticOperation implements Operation {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    private final String symbol;

    ArithmeticOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

}
