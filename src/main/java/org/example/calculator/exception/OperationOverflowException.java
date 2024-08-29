package org.example.calculator.exception;

public class OperationOverflowException extends RuntimeException{
    public OperationOverflowException(String message) {
        super(message);
    }
}
