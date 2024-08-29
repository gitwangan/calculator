package org.example.calculator.utils;

public enum Operation {
    ADD("+") {
        @Override
        public Number apply(Number x, Number y) {
            return x.doubleValue() + y.doubleValue();
        }
    },
    SUBTRACT("-") {
        @Override
        public Number apply(Number x, Number y) {
            return x.doubleValue() - y.doubleValue();
        }
    },
    MULTIPLY("*") {
        @Override
        public Number apply(Number x, Number y) {
            return x.doubleValue() * y.doubleValue();
        }
    },
    DIVIDE("/") {
        @Override
        public Number apply(Number x, Number y) {
            if (y.intValue() == 0) {
                throw new ArithmeticException("Cannot divide by zero");
            }
            return x.doubleValue() / y.doubleValue();
        }
    };

    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    public abstract Number apply(Number num1, Number num2);

    public static Operation fromSymbol(String symbol) {
        for (Operation operation : Operation.values()) {
            if (operation.symbol.equals(symbol)) {
                return operation;
            }
        }
        throw new IllegalArgumentException("Unknown symbol: " + symbol);
    }

}
