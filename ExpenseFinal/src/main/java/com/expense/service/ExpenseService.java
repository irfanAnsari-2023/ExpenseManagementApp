package com.expense.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.entity.Expense;
import com.expense.repository.ExpenseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;
    // method for getting the expense by userId
    public List<Expense> getExpensesByUserId(@NotNull Long userId) {
        return expenseRepository.findByUserId(userId);
    }
    // method for getting all the  unpaid expenses
    public List<Expense> getUnpaidExpenses() {
        return expenseRepository.findByStatus("unpaid");
    }
    // method for saving a new expense
    public Expense saveExpense(@Valid Expense expense) {
        expense.setStatus("unpaid");
        return expenseRepository.save(expense);
    }

    
}
