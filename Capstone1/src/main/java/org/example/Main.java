package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== FINANCE TRACKER ===");
            System.out.println("1. Add Deposit");
            System.out.println("2. Make Payment");
            System.out.println("3. View Ledger");
            System.out.println("4. Exit");
            System.out.print("Please Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1": addTransaction(true);
                    break;
                case "2": addTransaction(false);
                    break;
                case "3": viewLedger();
                    break;
                case "4": {
                    System.exit(0);
                }
                default: System.out.println("Invalid option!");
            }
        }
    }

    private static void addTransaction(boolean isDeposit) {
        try {
            System.out.print("Enter description: ");
            String description = scanner.nextLine();

            System.out.print("Enter vendor: ");
            String vendor = scanner.nextLine();

            System.out.print("Enter amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            if (amount <= 0) {
                System.out.println("Amount must be positive!");
                return;
            }

            if (!isDeposit) {
                amount = -amount;
            }

            Transaction transaction = new Transaction(
                    LocalDate.now(),
                    LocalTime.now(),
                    description,
                    vendor,
                    amount
            );

            TransactionManager.appendTransaction(transaction);
            System.out.println("Transaction saved!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid amount format!");
        }
    }

    private static void viewLedger() {
        List<Transaction> transactions = TransactionManager.loadTransactions();

        System.out.println("\nFilter by:");
        System.out.println("1. All Transactions");
        System.out.println("2. Deposits Only");
        System.out.println("3. Payments Only");
        System.out.print("Choose filter: ");
        String filter = scanner.nextLine();

        System.out.println("\n=== TRANSACTION LEDGER ===");

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transcation = transactions.get(i);

            if (filter.equals("2") && transcation.getAmount() < 0) continue;
            if (filter.equals("3") && transcation.getAmount() > 0) continue;

            System.out.printf("%s %s | %-20s | %-15s | %s%.2f%n",
                    transcation.getDate(),
                    transcation.getTime(),
                    transcation.getDescription(),
                    transcation.getVendor(),
                    transcation.getAmount() > 0 ? "+" : "-",
                    Math.abs(transcation.getAmount()));
        }
    }
}