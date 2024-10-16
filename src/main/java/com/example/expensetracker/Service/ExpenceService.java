package com.example.expensetracker.Service;

import com.example.expensetracker.Model.Expense;

import java.util.List;

public interface ExpenceService {
    List<Expense> getAllExpense();
    Expense getExpenseById(Long id);
    void addExpense(Expense expense);
    boolean deleteExpense(Long id);

    boolean updateExpense(Long id , Expense expense);

}
