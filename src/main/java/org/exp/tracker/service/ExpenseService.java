package org.exp.tracker.service;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.exp.tracker.entity.ExpenseTracker;
import org.exp.tracker.util.LocalDates;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExpenseService {
    List<ExpenseTracker> expenses = new ArrayList<>();
    private Gson gson;
    private static final String filePath = "src/main/resources/ExpenseTracker.json";

    public ExpenseService (){
    gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDates()).create();
        expenses = expenseLoader();
    }


    public List<ExpenseTracker> expenseLoader() {

        try {
             if(Files.exists(Paths.get(filePath)))     {
                 String json = new String(Files.readAllBytes(Paths.get(filePath)));
                 return gson.fromJson(json, new TypeToken<List<ExpenseTracker>>(){}.getType());
             }

        } catch (Exception e){
            e.printStackTrace();
        }

           return  new ArrayList<>();
    }

    public void saveExpenses(){
        try {
              String json = gson.toJson(expenses);
              Files.write(Paths.get(filePath),json.getBytes());
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    public void addExpenses(String description, double amount) {
        int id;
        if(expenses!= null && !expenses.isEmpty()){
            id = expenses.size() + 1;

        }else {
            id = 1;
        }
        ExpenseTracker track = new ExpenseTracker(id, description,amount);
        expenses.add(track);
        saveExpenses();
        System.out.println("# Expense added successfully (ID: "+id+")");
    }

    public void listExpenses() {
        int width = 20;
        System.out.printf("%" + width + "s%" + width + "s%" + width + "s%"+ width + "s%n", "ID", "Date", "Description", "Amount");
        for(ExpenseTracker expense : expenses) {
            System.out.printf("%" + width + "s%" + width + "s%" + width + "s%"+ width + "s%n", expense.getId(), expense.getDate(), expense.getDescription(), expense.getAmount());
        }
    }

    public void totalExpenses(int month) {
        String monthName = "";
        if (month == 0) {
            double totalExpense = 0;
            for (ExpenseTracker tracker : expenses) {
                totalExpense += tracker.getAmount();
            }
            System.out.println("Total Expense : " + totalExpense);
        } else {
            double expensesForMonth = 0;
              for(ExpenseTracker tracker : expenses) {
                  if(tracker.getDate().getMonthValue()==month) {
                      monthName = String.valueOf(tracker.getDate().getMonth());
                      expensesForMonth  += tracker.getAmount();
                  }
              }
            System.out.println("Total expenses for "+monthName+" : "+expensesForMonth+" Rupees");
        }
    }

    public void deleteExpense(int id) {
        Iterator<ExpenseTracker> it = expenses.iterator();
        Boolean deleted = false;
        while(it.hasNext()) {
            if(it.next().getId()==id){
                deleted=true;
                it.remove();
                saveExpenses();
                System.out.println("Task Deleted Successfully for ID : " + id);
            }
        }
        if(!deleted){
            System.out.println("ID not found");
        }
    }



}
