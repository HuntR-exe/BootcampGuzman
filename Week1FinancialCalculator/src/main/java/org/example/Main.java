package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to your Financial App!");
        System.out.println("=====================================");
        System.out.println("Choose a Calculator....");
        System.out.println("(A) Mortgage Calculator");
        System.out.println("(B) Value Calculator");
        System.out.println("(C) ...Coming Soon...");
        String option = scanner.nextLine();

        if (option.equalsIgnoreCase("A")) {
            System.out.println("Welcome to the Mortgage Calculator!");


            System.out.println("Enter the loan amount: ");
            double principal = scanner.nextDouble();

            System.out.println("Enter the annual interest rate as a decimal: ");
            double annualInterestRate = scanner.nextDouble();

            System.out.println("Enter loan length in years: ");
            int loanTermYears = scanner.nextInt();

            double monthlyInterestRate = (annualInterestRate / 100) / 12;

            int numberOfPayments = loanTermYears * 12;

            double monthlyPayment = principal * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments)) / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

            double totalInterest = (monthlyPayment * numberOfPayments) - principal;

            System.out.printf("Monthly payment: $%.2f%n", monthlyPayment);
            System.out.printf("Total Interest Paid: $%.2f%n", totalInterest);


        }
        else if (option.equalsIgnoreCase("B")) {
            System.out.println("Welcome to the Value Calculator!");


            System.out.println("Enter deposit amount:");
            double deposit = scanner.nextDouble();

            System.out.println("Enter annual interest rate as a decimal:");
            double annualRate = scanner.nextDouble();

            System.out.println("Enter number of years:");
            int years = scanner.nextInt();

            double rateDecimal = annualRate / 100;
            double dailyRate = rateDecimal / 365;
            int totalDays = 365 * years;

            double futureValue = deposit * Math.pow(1 + dailyRate, totalDays);

            double totalInterest = futureValue - deposit;

            System.out.printf("Future Value: $%.2f%n", futureValue);
            System.out.printf("Total Interest Earned: $%.2f%n", totalInterest);



        }
        else{
            System.out.println("Invalid Option, Please re-run the program and try again.");
        }
    }
}