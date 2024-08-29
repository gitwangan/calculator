package org.example.calculator.exception;

import org.example.calculator.operations.Operation;

import java.util.Set;

public class UnsupportedOperationException extends IllegalArgumentException {
    public UnsupportedOperationException(String message) {
        super(message);
    }

}
