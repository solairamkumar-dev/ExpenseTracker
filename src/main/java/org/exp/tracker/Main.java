package org.exp.tracker;

import org.exp.tracker.service.ExpenseService;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ExpenseService service = new ExpenseService();
       Scanner sc = new Scanner(System.in);

       while(true) {
           String input = sc.nextLine();
           String[] inputValues = input.split(" ",5);

           String jobName = inputValues[0];

           switch (jobName) {
               case "add":
                   if(inputValues.length<5) {
                       System.out.println("Please enter input in this format for add : <operation> --description <Description> --amount <amount> ");
                   }

                   if(inputValues[1].equals("--description") && inputValues[3].equals("--amount")) {
                       service.addExpenses(inputValues[2], Double.parseDouble(inputValues[4]));
                   }
                   break;
               case "list":
                    if(inputValues.length != 1) {
                        System.out.println("Please enter input in this format for list : <operation> ");
                    }else{
                        service.listExpenses();
                    }
                    break;
               case "summary":
                   int month = 0;
                   if(inputValues.length>3) {
                       System.out.println("Please enter input in this format for summary : <operation> --month<value> ");
                   }else{
                       if(inputValues.length==3) {
                           month = Integer.parseInt(inputValues[2]);
                       }
                       service.totalExpenses(month);

                   }
                   break;
               case "delete":
                   if(inputValues.length!=3) {
                       System.out.println("Please enter input in this format for delete : <operation> --id<value> ");
                   }
                   else{
                       service.deleteExpense(Integer.parseInt(inputValues[2]));
                   }
           }
       }
    }
    }
