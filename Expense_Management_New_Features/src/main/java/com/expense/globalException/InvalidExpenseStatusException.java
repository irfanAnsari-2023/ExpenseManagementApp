package com.expense.globalException;

public class InvalidExpenseStatusException extends RuntimeException {
    public InvalidExpenseStatusException(String message) {
        super(message);
    }
}

