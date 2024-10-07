package com.ust.expense_planner.repository;

import com.ust.expense_planner.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // Method to find expenses by a specific date
    List<Expense> findByDate(LocalDate date);

    // Method to find expenses between two dates
    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
