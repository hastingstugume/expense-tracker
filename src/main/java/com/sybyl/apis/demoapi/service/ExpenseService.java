package com.sybyl.apis.demoapi.service;

import com.sybyl.apis.demoapi.repository.ExpenseRepo;
import com.sybyl.apis.demoapi.model.Expense;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepo expenseRepo;

//    Method to return all expenses
    public List<Expense> getAllExpenses() {
        return expenseRepo.findAll();
    }

//    Method to return a single expense by ID
    public Expense getExpenseById(Long id) {
        Optional<Expense> expense = expenseRepo.findById(id);
        return expense.orElse(null);
    }

//    Method to add a single expense
    public Expense addExpense(Expense expense) {
        return expenseRepo.save(expense);
    }

//    Method to update a single expense
    public Optional<Expense> updateExpense(Expense newExpense, Long id) {
        return expenseRepo.findById(id).map(expense -> {
            if(id.equals(expense.getId())) {
                if (newExpense.getTitle() != null){
                    expense.setTitle(newExpense.getTitle());
                }
                if (newExpense.getDescription() != null){
                    expense.setDescription(newExpense.getDescription());
                }
                if(newExpense.getFrequency() != null){
                    expense.setFrequency(newExpense.getFrequency());
                }
                if(newExpense.getAmount() != expense.getAmount()){
                    expense.setAmount(newExpense.getAmount());
                }
            }
            return expenseRepo.save(expense);
        });
    }

//    Method to delete a single expense
    public void deleteExpense(Long id) {
        expenseRepo.deleteById(id);
    }
}
