package org.example.calculator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.example.calculator.utils.*;

@SpringBootTest
class CalculatorApplicationTests {

    @Test
    void testCalculation() {
        Calculator calculator = new Calculator();
        // test integer calculation
        assertEquals(Double.valueOf(2 + 3), calculator.calculate(Operation.ADD, 2, 3));
        assertEquals(Double.valueOf(2 - 3), calculator.calculate(Operation.SUBTRACT, 2, 3));
        assertEquals(Double.valueOf(2 * 3), calculator.calculate(Operation.MULTIPLY, 2, 3));
        assertEquals(Double.valueOf(2.0 / 3), calculator.calculate(Operation.DIVIDE, 2, 3));

        // test exception thrown when divide by 0
        assertThrowsExactly(ArithmeticException.class, () -> calculator.calculate(Operation.DIVIDE, 2, 0));

        // test decimal calculations, along with negative numbers
        assertEquals(Double.valueOf(2.14 + 3.5876), calculator.calculate(Operation.ADD, 2.14, 3.5876));
        assertEquals(Double.valueOf(2.6678 - 3.8756), calculator.calculate(Operation.SUBTRACT, 2.6678, 3.8756));
        assertEquals(Double.valueOf(2.5383 * -3.2), calculator.calculate(Operation.MULTIPLY, 2.5383, -3.2));
        assertEquals(Double.valueOf(-2.00001 / 3.9999), calculator.calculate(Operation.DIVIDE, -2.00001, 3.9999));

        // test invoke with symbolic operator
        assertEquals(Double.valueOf(2.14 + 3.5876), calculator.calculate(Operation.fromSymbol("+"), 2.14, 3.5876));
        assertEquals(Double.valueOf(-2.00001 / 3.9999), calculator.calculate(Operation.fromSymbol("/"), -2.00001, 3.9999));

        // test exception thrown for unsupported operation
        assertThrowsExactly(IllegalArgumentException.class, () -> calculator.calculate(Operation.fromSymbol("^"), 1,1));
        assertThrowsExactly(IllegalArgumentException.class, () -> calculator.calculate(Operation.fromSymbol("|"), 1,1));

    }

    @Test
    void testChaining() {
        Calculator calculator = new Calculator();

        // test chaining calculation of integers
        assertEquals(Double.valueOf(((((0 + 2.0) / 3) - 3) * 99) - 99),
                calculator.reset().chaining(Operation.ADD, 2)
                    .chaining(Operation.DIVIDE, 3)
                    .chaining(Operation.fromSymbol("-"), 3)
                    .chaining(Operation.fromSymbol("*"), 99)
                    .chaining(Operation.SUBTRACT, 99)
                    .getCurVal());

        assertEquals(Double.valueOf(((((0 - 2.3854) * -3.87999) * -3.001) + 99) / 99.99),
                calculator.reset().chaining(Operation.SUBTRACT, 2.3854)
                    .chaining(Operation.fromSymbol("*"), -3.87999)
                    .chaining(Operation.MULTIPLY, -3.001)
                    .chaining(Operation.fromSymbol("+"), 99)
                    .chaining(Operation.fromSymbol("/"), 99.99)
                    .getCurVal());

        // test exception thrown when there is a divided by 0 in chaining
        assertThrowsExactly(ArithmeticException.class, () ->
                calculator.reset().chaining(Operation.ADD, 2)
                    .chaining(Operation.DIVIDE, 0)
                    .chaining(Operation.fromSymbol("-"), 3)
                    .chaining(Operation.fromSymbol("*"), 99)
                    .chaining(Operation.SUBTRACT, 99)
                    .getCurVal());

        // test exception thrown when there is a unsupported operation in chaining
        assertThrowsExactly(IllegalArgumentException.class, () ->
                calculator.reset().chaining(Operation.SUBTRACT, 2.3854)
                        .chaining(Operation.fromSymbol("*"), -3.87999)
                        .chaining(Operation.MULTIPLY, -3.001)
                        .chaining(Operation.fromSymbol("^"), 99)
                        .chaining(Operation.fromSymbol("/"), 99.99)
                        .getCurVal());
    }

}
