package com.expense.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.expense.globalException.ExpenseNotFoundException;
import com.expense.globalException.InvalidExpenseStatusException;
import com.expense.globalException.UserNotFoundException;
import com.expense.model.Expense;
import com.expense.repository.ExpenseRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense getExpenseById(Long id) throws ExpenseNotFoundException {
        Optional<Expense> expense = expenseRepository.findById(id);
        if (expense.isPresent()) {
            return expense.get();
        } else {
            throw new ExpenseNotFoundException("Expense with id " + id + " not found.");
        }
    }

    public List<Expense> getExpensesByUserId(Long userId) throws UserNotFoundException {
        List<Expense> expenses = expenseRepository.findByUserId(userId);
        if (expenses.isEmpty()) {
            throw new UserNotFoundException("Expenses for user with id " + userId + " not found.");
        }
        return expenses;
    }

    public List<Expense> getExpensesByStatus(String status) {
        return expenseRepository.findByStatus(status);
    }

    public List<Expense> getExpensesByMonthAndYear(Integer month, Integer year) {
        return expenseRepository.findByMonthAndYear(month, year);
    }

    public Page<Expense> getExpensePage(Pageable pageable) {
        return expenseRepository.findAll(pageable);
    }

    public Expense createExpense(Expense expense) {
        expense.setStatus("PENDING");
        return expenseRepository.save(expense);
    }

    public boolean updateExpenseStatus(Long id, String status) throws ExpenseNotFoundException, InvalidExpenseStatusException {
        Expense expense = getExpenseById(id);
        if (expense.getStatus().equals(status)) {
            throw new InvalidExpenseStatusException("Expense with id " + id + " already has status " + status + ".");
        }
        switch (status) {
            case "APPROVED":
                expense.setStatus("APPROVED");
                expense.setApprovedDate(new Date());
                break;
            case "REJECTED":
                expense.setStatus("REJECTED");
                expense.setRejectedDate(new Date());
                break;
            default:
                throw new InvalidExpenseStatusException("Invalid status value: " + status);
        }
        expenseRepository.save(expense);
		return false;
    }

    public boolean approveExpense(Long id) throws ExpenseNotFoundException, InvalidExpenseStatusException {
        updateExpenseStatus(id, "APPROVED");
		return false;
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }
}
