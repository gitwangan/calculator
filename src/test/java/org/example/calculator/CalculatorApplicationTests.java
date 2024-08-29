package org.example.calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.example.calculator.exception.OperationOverflowException;
import org.example.calculator.exception.UnsupportedOperationException;
import org.example.calculator.operations.Operation;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CalculatorApplicationTests {

    @Test
    void testCalculation() {
        // test integer calculation
        assertEquals(Double.valueOf(2 + 3), Calculator.calculate(Operation.ADD, 2, 3));
        assertEquals(Double.valueOf(2 - 3), Calculator.calculate(Operation.SUBTRACT, 2, 3));
        assertEquals(Double.valueOf(2 * 3), Calculator.calculate(Operation.MULTIPLY, 2, 3));
        assertEquals(Double.valueOf(2.0 / 3), Calculator.calculate(Operation.DIVIDE, 2, 3));

        // test exception thrown when divide by 0
        assertThrowsExactly(ArithmeticException.class, () -> Calculator.calculate(Operation.DIVIDE, 2, 0));

        // test decimal calculations, along with negative numbers
        assertEquals(Double.valueOf(2.14 + 3.5876), Calculator.calculate(Operation.ADD, 2.14, 3.5876));
        assertEquals(Double.valueOf(2.6678 - 3.8756), Calculator.calculate(Operation.SUBTRACT, 2.6678, 3.8756));
        assertEquals(Double.valueOf(2.5383 * -3.2), Calculator.calculate(Operation.MULTIPLY, 2.5383, -3.2));
        assertEquals(Double.valueOf(-2.00001 / 3.9999), Calculator.calculate(Operation.DIVIDE, -2.00001, 3.9999));

        // test invoke with symbolic operator
        assertEquals(Double.valueOf(2.14 + 3.5876), Calculator.calculate("+", 2.14, 3.5876));
        assertEquals(Double.valueOf(-2.00001 / 3.9999), Calculator.calculate("/", -2.00001, 3.9999));

        // test exception thrown for unsupported operation
        assertThrowsExactly(UnsupportedOperationException.class, () -> Calculator.calculate("^", 1,1));
        assertThrowsExactly(UnsupportedOperationException.class, () -> Calculator.calculate("|", 1,1));

        // test exception thrown when calculation is overflow
        // the calculator is designed to handle result within integer range
        assertThrowsExactly(OperationOverflowException.class, () -> Calculator.calculate("+", Integer.MAX_VALUE - 5, 6));
        assertThrowsExactly(OperationOverflowException.class, () -> Calculator.calculate("-", Integer.MIN_VALUE + 5, 6));
        assertThrowsExactly(OperationOverflowException.class, () -> Calculator.calculate("*", Integer.MAX_VALUE / 2, 2.1));
    }

    @Test
    void testChaining() {
        Calculator calculator = new Calculator();

        // test chaining calculation of integers
        assertEquals(Double.valueOf(((((0 + 2.0) / 3) - 3) * 99) - 99),
                calculator.chaining(Operation.ADD, 2)
                    .chaining(Operation.DIVIDE, 3)
                    .chaining("-", 3)
                    .chaining("*", 99)
                    .chaining(Operation.SUBTRACT, 99)
                    .getRes());

        assertEquals(Double.valueOf(((((0 - 2.3854) * -3.87999) * -3.001) + 99) / 99.99),
                calculator.chaining(Operation.SUBTRACT, 2.3854)
                    .chaining("*", -3.87999)
                    .chaining(Operation.MULTIPLY, -3.001)
                    .chaining("+", 99)
                    .chaining("/", 99.99)
                    .getRes());

        // test exception thrown when there is a divided by 0 in chaining
        assertThrowsExactly(ArithmeticException.class, () ->
                calculator.chaining(Operation.ADD, 2)
                    .chaining(Operation.DIVIDE, 0)
                    .chaining("-", 3)
                    .chaining("*", 99)
                    .chaining(Operation.SUBTRACT, 99)
                    .getRes());

        // test exception thrown when there is a unsupported operation in chaining
        assertThrowsExactly(UnsupportedOperationException.class, () ->
                calculator.chaining(Operation.SUBTRACT, 2.3854)
                        .chaining("*", -3.87999)
                        .chaining(Operation.MULTIPLY, -3.001)
                        .chaining("^", 99)
                        .chaining("/", 99.99)
                        .getRes());

        // test exception thrown intermediate result in a chaining calculation is overflow
        assertThrowsExactly(OperationOverflowException.class, () ->
                calculator.chaining(Operation.SUBTRACT, 2.3854)
                        .chaining("*", -3.87999)
                        .chaining(Operation.MULTIPLY, -3.001)
                        .chaining("-", Integer.MAX_VALUE)
                        .chaining("/", 99.99)
                        .getRes());
    }

}
