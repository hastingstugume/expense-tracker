package com.sybyl.apis.demoapi.repository;

import com.sybyl.apis.demoapi.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepo extends JpaRepository<Expense, Long> {
}
