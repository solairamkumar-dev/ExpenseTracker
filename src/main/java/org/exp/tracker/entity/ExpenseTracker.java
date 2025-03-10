package org.exp.tracker.entity;



import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class ExpenseTracker {

    private int id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // to make date as string format
    private LocalDate date;
    private String description;
    private double amount;

    public ExpenseTracker(){}

    public ExpenseTracker(int id, String description, double amount) {
        this.id = id;
        this.date = LocalDate.now();
        this.description = description;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
