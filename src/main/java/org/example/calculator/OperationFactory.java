package org.example.calculator;

import org.example.calculator.exception.UnsupportedOperationException;
import org.example.calculator.operations.OperationStrategy;
import org.example.calculator.operations.Operation;
import org.example.calculator.operations.impl.AddOperator;
import org.example.calculator.operations.impl.DivideOperator;
import org.example.calculator.operations.impl.MultiplyOperator;
import org.example.calculator.operations.impl.SubtractOperator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OperationFactory {
    private static final Map<Operation, OperationStrategy> operations = new HashMap<>();

    static {
        operations.put(Operation.ADD, new AddOperator());
        operations.put(Operation.SUBTRACT, new SubtractOperator());
        operations.put(Operation.MULTIPLY, new MultiplyOperator());
        operations.put(Operation.DIVIDE, new DivideOperator());
    }

    public static OperationStrategy getOperation(Operation operation) {
        OperationStrategy operationStrategy = operations.get(operation);
        if (null == operationStrategy) {
            throw new UnsupportedOperationException(generateExceptionMessage(operation.getSymbol(), operations.keySet()));
        }
        return operationStrategy;
    }

    public static OperationStrategy getOperationFromSymbol(String symbol) {
        for (Operation operation : operations.keySet()) {
            if (operation.getSymbol().equals(symbol)) {
                return getOperation(operation);
            }
        }
        throw new UnsupportedOperationException(generateExceptionMessage(symbol, operations.keySet()));
    }

    private static String generateExceptionMessage(String symbol, Set<Operation> supportedOperations) {
        StringBuilder sb = new StringBuilder();
        sb.append("Operation" + symbol + "is not supported. Supported operations are ");
        for (Operation operation : supportedOperations) {
            sb.append(operation.getSymbol() + ",");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

}
