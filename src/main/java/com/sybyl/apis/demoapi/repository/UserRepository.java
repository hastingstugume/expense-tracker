package com.sybyl.apis.demoapi.repository;

import com.sybyl.apis.demoapi.model.ExpenseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<ExpenseUser, Long> {
    Optional<ExpenseUser> findByUsername(String username);
}
