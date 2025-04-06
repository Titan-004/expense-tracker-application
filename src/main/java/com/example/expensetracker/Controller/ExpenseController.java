package com.example.expensetracker.Controller;

import com.example.expensetracker.Model.Expense;
import com.example.expensetracker.Service.ExpenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ExpenseController {

    private ExpenceService ES;

    public ExpenseController(ExpenceService ES) {
        this.ES = ES;
    }

    @GetMapping
    public String viewHomePage(Model model){
        List<Expense> expenses = ES.getAllExpense();
        BigDecimal totalAmount  = expenses.stream().map(Expense::getAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
        model.addAttribute("expenses" , expenses );
        model.addAttribute("totalAmount", totalAmount);
        return "index";
    }

    @GetMapping("/addExpense")
    public String showAddExpensePage(Model model){
        Expense expense = new Expense();
        model.addAttribute("expense",expense);
        return "add-expense";
    }

    @PostMapping("/saveExpense")
    public String saveExpense(@ModelAttribute("expense")Expense expense, Model model){
            ES.addExpense(expense);
            return "redirect:/";
    }
    @GetMapping("editExpense/{id}")
    public String showUpdateExpensePage(@PathVariable("id")long id , Model model){
        Expense expense  = ES.getExpenseById(id);
        model.addAttribute("expense",expense);
        return "update-expense";
    }

    @PostMapping("updateExpense/{id}")
    public String updateExpense(@PathVariable("id")long id,@ModelAttribute("expense")Expense expense){
        Expense existingExpense = ES.getExpenseById(id);
        existingExpense.setDescription((expense.getDescription()));
        existingExpense.setAmount(expense.getAmount());
        ES.addExpense(existingExpense);
        return "redirect:/";
    }
    @GetMapping("deleteExpense/{id}")
    public String deleteExpense(@PathVariable("id")long id){
        ES.deleteExpense(id);
        return "redirect:/";
    }
}
