package com.expense.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.entity.Expense;
import com.expense.service.ExpenseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    // Get expenses by user ID
    @GetMapping("/{userId}")
    public ResponseEntity<List<Expense>> getExpensesByUserId(@PathVariable Long userId) {
        List<Expense> expenses = expenseService.getExpensesByUserId(userId);
        return ResponseEntity.ok(expenses);
    }

    // Get unpaid expenses
    @GetMapping("/unpaid")
    public ResponseEntity<List<Expense>> getUnpaidExpenses() {
        List<Expense> expenses = expenseService.getUnpaidExpenses();
        return ResponseEntity.ok(expenses);
    }

    // Create a new expense
    @PostMapping("/create")
    public ResponseEntity<Expense> createExpense(@Valid @RequestBody Expense expense) {
        expense = expenseService.saveExpense(expense);
        return ResponseEntity.ok(expense);
    }

    
    
}
