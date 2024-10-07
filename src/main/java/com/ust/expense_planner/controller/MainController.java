package com.ust.expense_planner.controller;

import com.ust.expense_planner.service.ExpenseService;
import com.ust.expense_planner.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private IncomeService incomeService;

    @GetMapping("/expenses/total/{date}")
    public Double getTotalSpentOnDate(@PathVariable String date) {
        return expenseService.getTotalSpentOnDate(LocalDate.parse(date));
    }

    @GetMapping("/expenses/category/{date}")
    public Map<String, Double> getSpentByCategoryOnDate(@PathVariable String date) {
        return expenseService.getSpentByCategoryOnDate(LocalDate.parse(date));
    }

    @GetMapping("/income/total/{date}")
    public Double getTotalIncomeOnDate(@PathVariable String date) {
        return incomeService.getTotalIncomeOnDate(LocalDate.parse(date));
    }

    @GetMapping("/balance/{date}")
    public Double getCurrentBalance(@PathVariable String date) {
        return incomeService.getCurrentBalance(LocalDate.parse(date), expenseService);
    }


    @GetMapping("/expenses/monthly/{year}/{month}")
    public Double getTotalExpensesForMonth(@PathVariable int year, @PathVariable int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        return expenseService.getTotalSpentInMonth(yearMonth);
    }

    @GetMapping("/income/monthly/{year}/{month}")
    public Double getTotalIncomeForMonth(@PathVariable int year, @PathVariable int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        return incomeService.getTotalIncomeInMonth(yearMonth);
    }
}
