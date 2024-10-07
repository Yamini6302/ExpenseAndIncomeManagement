package com.ust.expense_planner.repository;
import com.ust.expense_planner.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    // Method to find income by a specific date
    List<Income> findByDate(LocalDate date);

    // Method to find income between two dates
    List<Income> findByDateBetween(LocalDate startDate, LocalDate endDate);
}