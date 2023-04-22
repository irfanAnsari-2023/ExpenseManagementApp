package com.expense.service;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ExpenseNotFoundException extends RuntimeException {
    public ExpenseNotFoundException(String message) {
        super(message);
    }
}
