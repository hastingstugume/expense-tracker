package com.sybyl.apis.demoapi.controller;

import com.sybyl.apis.demoapi.model.Expense;
import com.sybyl.apis.demoapi.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/expenses")
@RequiredArgsConstructor
class ExpenseController {
   private final ExpenseService expenseService;

//   Endpoint for adding a single expense item to the database
    @PostMapping("/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense) {
        Expense newExpense = expenseService.addExpense(expense);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newExpense.getId()).toUri();
        return ResponseEntity.created(location).body(newExpense);
    }

//    Endpoint for returning all expense items in the db
    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

//    Endpoint for returning a single expense item by id
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable(value = "id") String id) {
        long expenseId;
        try {
            expenseId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(null);
        }
        Expense expense = expenseService.getExpenseById(expenseId);
        if (expense == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(expense);
    }

//    Endpoint for updating an expense item
    @PutMapping(("/{id}"))
    public ResponseEntity<Expense> updateExpense(@RequestBody Expense newExpense, @PathVariable(value = "id") String id) {
        long expenseId;
        try {
            expenseId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(null);
        }
        Optional<Expense> updatedExpense = expenseService.updateExpense(newExpense, expenseId);
        return updatedExpense.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

//    Endpoint for deleting an expense item
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteExpenseById(@PathVariable(value = "id") String id) {
        try {
            expenseService.deleteExpense(Long.parseLong(id));
        } catch (NumberFormatException e) {
            ResponseEntity.badRequest().body(null);
        }
    }
}
