package com.ust.expense_planner.service;

import com.ust.expense_planner.model.Income;
import com.ust.expense_planner.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    public Double getTotalIncomeOnDate(LocalDate date) {
        return incomeRepository.findByDate(date).stream()
                .mapToDouble(Income::getAmount)
                .sum();
    }

    public Double getCurrentBalance(LocalDate date, ExpenseService expenseService) {
        return getTotalIncomeOnDate(date) - expenseService.getTotalSpentOnDate(date);
    }

    public Double getTotalIncomeInMonth(YearMonth yearMonth) {
        LocalDate startDate = yearMonth.atDay(1); // First day of the month
        LocalDate endDate = yearMonth.atEndOfMonth(); // Last day of the month

        List<Income> incomes = incomeRepository.findByDateBetween(startDate, endDate);

        return incomes.stream()
                .mapToDouble(Income::getAmount)
                .sum();
    }
}