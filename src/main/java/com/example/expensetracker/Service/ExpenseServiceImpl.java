package com.example.expensetracker.Service;

import com.example.expensetracker.Model.Expense;
import com.example.expensetracker.Repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenceService {

    private ExpenseRepository ER;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository ER) {
        this.ER = ER;
    }

    @Override
    public void addExpense(Expense expense) {
        if (expense!=null){
            ER.save(expense);
        }

    }

    @Override
    public List<Expense> getAllExpense() {
        return ER.findAll();
    }

    @Override
    public Expense getExpenseById(Long id) {
        return ER.findById(id).orElse(null);

    }


    @Override
    public boolean deleteExpense(Long id) {
        if (ER.existsById(id)){
            ER.deleteById(id);
            return true;
        }
       else {
           return false;
        }
    }

    @Override
    public boolean updateExpense(Long id, Expense expense) {
        Optional<Expense> existingExpense = ER.findById(id);
        if (existingExpense.isPresent()){
            Expense exp = existingExpense.get();
            exp.setAmount(expense.getAmount());
            exp.setDescription(expense.getDescription());
            ER.save(exp);
            return true;
        }
        else{
            return false;
        }

    }
}
