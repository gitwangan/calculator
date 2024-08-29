package org.example.calculator;

import org.example.calculator.operations.Operation;

public class Calculator {
    private Number curVal = 0;

    public static Number calculate(Operation op, Number num1, Number num2) {
        return OperationFactory.getOperation(op).apply(num1, num2);
    }

    // for ease of use, one can just declare operations by symbols +,-,*,/
    // for unsupported symbols like %, UnsupportedOperationException will be thrown
    public static Number calculate(String symbol, Number num1, Number num2) {
        return OperationFactory.getOperationFromSymbol(symbol).apply(num1, num2);
    }

    public Calculator chaining(Operation op, Number val) {
        curVal = calculate(op, curVal, val);
        return this;
    }

    public Calculator chaining(String symbol, Number val) {
        curVal = calculate(symbol, curVal, val);
        return this;
    }

    // show intermediate result in chaining
    public Number getCurVal() {
        return curVal;
    }

    // terminates chaining, show result, and reset
    public Number getRes() {
        Number res = curVal;
        curVal = 0;
        return res;
    }

}
