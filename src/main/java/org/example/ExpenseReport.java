package org.example;

import java.util.Date;
import java.util.List;

enum ExpenseType {
    DINNER("Dinner", 5000, true), BREAKFAST("Breakfast", 1000, true), CAR_RENTAL("Car Rental");

    private String displayName;
    private int limit;
    private boolean isMeal;

    ExpenseType(String displayName){
        this.displayName = displayName;
        this.limit = Integer.MAX_VALUE;
        this.isMeal = false;
    }

    ExpenseType(String displayName, int limit, boolean isMeal){
        this.displayName = displayName;
        this.limit = limit;
        this.isMeal = isMeal;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getLimit() {
        return limit;
    }

    public boolean isMeal() {
        return isMeal;
    }
}

class Expense {
    private ExpenseType type;
    private int amount;

    public Expense(ExpenseType expenseType, int amount) {
        this.type = expenseType;
        this.amount = amount;
    }

    public ExpenseType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public boolean getOverLimitMarker() {
        return this.getAmount() > this.getType().getLimit();
    }

    public String getExpenseName() {
       return this.getType().getDisplayName();
    }

    public String getExpenseDetail(){
        String marker = getOverLimitMarker() ? "X" : "";
        return this.getExpenseName() + "\t" + this.getAmount() + "\t" + marker + "\n";
    }

    public boolean isMeal(){
        return this.getType().isMeal();
    }
}

public class ExpenseReport {

    public void printReport(List<Expense> expenses){
        String report = generateReport(expenses, new Date());
        System.out.println(report);
    }

    public String generateReport(List<Expense> expenses, Date date) {


        String report  = "";

        //HEADER
        String header = getHeader(date);

        String body = getBody(expenses);

        int mealExpenses = getMealExpenses(expenses);
        int total = getTotal(expenses);
        //FOOTER
        String footer = getFooter(mealExpenses, total);

        // Report
        report = header + body + footer;

        return report;
    }

    private static String getBody(List<Expense> expenses) {
        String body = "";
        for (Expense expense : expenses) {

            //BODY
            body = body + expense.getExpenseDetail();

        }
        return body;
    }

    private static int getTotal(List<Expense> expenses) {
        int total = 0;
        for (Expense expense: expenses){
            total += expense.getAmount();
        }
        return total;

    }


    private static int getMealExpenses(List<Expense> expenses) {
        int mealExpenses = 0;
        for (Expense expense: expenses){
            if (expense.isMeal()) {
                mealExpenses += expense.getAmount();
            }
        }
        return mealExpenses;
    }


    private String getFooter(int mealExpenses, int total) {
        return "Meal expenses: " + mealExpenses + "\n" + "Total expenses: " + total + "\n";
    }

    private String getHeader(Date date) {
        return "Expenses " + date + "\n";
    }


}