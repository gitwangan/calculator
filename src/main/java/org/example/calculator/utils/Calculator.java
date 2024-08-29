package org.example.calculator.utils;

public class Calculator {
    private Number curVal = 0;

    public Number calculate(Operation op, Number num1, Number num2) {
        return op.apply(num1, num2);
    }

    public Calculator chaining(Operation op, Number val) {
        curVal = calculate(op, curVal, val);
        return this;
    }

    public Calculator reset() {
        curVal = 0;
        return this;
    }

    public Number getCurVal() {
        return curVal;
    }

}
