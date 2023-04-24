package com.expense.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.globalException.ExpenseNotFoundException;
import com.expense.globalException.InvalidExpenseStatusException;
import com.expense.globalException.ResourceNotFoundException;
import com.expense.globalException.UserNotFoundException;
import com.expense.model.Expense;
import com.expense.service.ExpenseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;
    
    // getting the expense with id
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        Expense expense = expenseService.getExpenseById(id);
        if (expense == null) {
            throw new ExpenseNotFoundException("Expense with id " + id + " not found.");
        }
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    // getting the expense with user id
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Expense>> getExpensesForUser(@PathVariable Long userId) {
        List<Expense> expenses = expenseService.getExpensesByUserId(userId);
        if (expenses.isEmpty()) {
            throw new UserNotFoundException("Expenses not found for user with id " + userId + ".");
        }
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    // updating the expense 
    @PutMapping("/{id}/status/{status}")
    public ResponseEntity<Void> updateExpenseStatus(@PathVariable Long id, @PathVariable String status) {
        boolean updated = expenseService.updateExpenseStatus(id, status);
        if (updated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new ExpenseNotFoundException("Expense with id " + id + " not found.");
        }
    }

    // for approving the expense
    @PostMapping("/{id}/approve")
    public ResponseEntity<Void> approveExpense(@PathVariable Long id) {
        boolean approved = expenseService.approveExpense(id);
        if (approved) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new ExpenseNotFoundException("Expense with id " + id + " not found.");
        }
    }
    
    // for rejecting the expense
    @PostMapping("/{id}/reject")
    public ResponseEntity<Void> rejectExpense(@PathVariable Long id) {
        Expense expense = expenseService.getExpenseById(id);
        if (expense == null) {
            throw new ResourceNotFoundException("Expense with id " + id + " not found.");
        }
        if (!expense.getStatus().equals("unpaid")) {
            throw new InvalidExpenseStatusException("Expense with id " + id + " cannot be rejected as it is already " + expense.getStatus() + ".");
        }
        expenseService.updateExpenseStatus(id, "rejected");
        return ResponseEntity.ok().build();
    }

    // getting the expense by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Expense>> getExpensesByStatus(@PathVariable String status) {
        List<Expense> expenses = expenseService.getExpensesByStatus(status);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    // getting the expense by month and year
    @GetMapping("/month/{month}/year/{year}")
    public ResponseEntity<List<Expense>> getExpensesByMonthAndYear(@PathVariable Integer month, @PathVariable Integer year) {
        List<Expense> expenses = expenseService.getExpensesByMonthAndYear(month, year);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    // getting the expense in a page format 
    @GetMapping("/page")
    public ResponseEntity<Page<Expense>> getExpensePage(@PageableDefault(size = 10) Pageable pageable) {
        Page<Expense> expenses = expenseService.getExpensePage(pageable);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    // adding the expense
    @PostMapping("/add")
    public ResponseEntity<Expense> addExpense(@Valid @RequestBody Expense expense) {
        expense.setStatus("PENDING");
        Expense createdExpense = expenseService.createExpense(expense);
        return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
    }
    
    // getting the list of all expenses
    @GetMapping("/approved")
    public ResponseEntity<List<Expense>> getAllApprovedExpenses() {
        List<Expense> expenses = expenseService.getAllExpenses();
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }
    
    // getting the list pending expenses
    @GetMapping("/pending")
    public ResponseEntity<List<Expense>> getAllPendingExpenses() {
        List<Expense> expenses = expenseService.getExpensesByStatus("PENDING");
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }
    
    // getting the list of rejected expenses
    @GetMapping("/rejected")
    public ResponseEntity<List<Expense>> getAllRejectedExpenses() {
        List<Expense> expenses = expenseService.getExpensesByStatus("REJECTED");
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }
    
}

   

   
