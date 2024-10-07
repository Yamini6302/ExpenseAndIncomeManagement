package com.ust.expense_planner.service;

import com.ust.expense_planner.model.Expense;
import com.ust.expense_planner.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Double getTotalSpentOnDate(LocalDate date) {
        return expenseRepository.findByDate(date).stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    public Map<String, Double> getSpentByCategoryOnDate(LocalDate date) {
        List<Expense> expenses = expenseRepository.findByDate(date);
        Map<String, Double> categoryTotals = new HashMap<>();

        for (Expense expense : expenses) {
            categoryTotals.merge(expense.getCategory(), expense.getAmount(), Double::sum);
        }

        return categoryTotals;
    }

    public Double getTotalSpentInMonth(YearMonth yearMonth) {
        LocalDate startDate = yearMonth.atDay(1); // First day of the month
        LocalDate endDate = yearMonth.atEndOfMonth(); // Last day of the month

        List<Expense> expenses = expenseRepository.findByDateBetween(startDate, endDate);

        return expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }
}
