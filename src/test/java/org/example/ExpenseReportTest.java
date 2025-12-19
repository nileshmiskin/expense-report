package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpenseReportTest {

    @BeforeEach
    @AfterEach
    public void setup(){
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@");
    }

    @Test
    public void printExpenseReport(){

        ExpenseReport expenseReport = new ExpenseReport();
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense(ExpenseType.BREAKFAST, 1600));
        expenses.add(new Expense(ExpenseType.DINNER, 2000));
        expenses.add(new Expense(ExpenseType.CAR_RENTAL, 6000));

        Date date = new Date();

        String expected = "Expenses "+date+"\n" +
                "Breakfast\t1600\tX\n" +
                "Dinner\t2000\t\n" +
                "Car Rental\t6000\t\n" +
                "Meal expenses: 3600\n" +
                "Total expenses: 9600\n";


        String actual = expenseReport.generateReport(expenses, date);

        Assertions.assertEquals(expected, actual);

    }

}
