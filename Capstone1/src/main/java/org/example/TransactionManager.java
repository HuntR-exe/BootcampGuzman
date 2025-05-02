package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransactionManager {
    private static final String FILE_PATH = "src/main/resources/transactions.csv";


    public static void appendTransaction(Transaction transaction) {

        File file = new File(FILE_PATH);

            try (FileWriter writer = new FileWriter(file, true)) {

                if (file.length() == 0) {
                    writer.write("date|time|description|vendor|amount\n");
                }
                writer.write(transaction + "\n");
            }
            catch (IOException e) {
                System.out.println("Error saving transaction: " + e.getMessage());
            }
    }


    public static List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) return transactions;

        try (Scanner fileScanner = new Scanner(file)) {
            // Skip header
            if (fileScanner.hasNextLine()) fileScanner.nextLine();

            while(fileScanner.hasNextLine()) {
                String[] parts = fileScanner.nextLine().split("\\|");
                if(parts.length == 5) {
                    transactions.add(new Transaction(
                            LocalDate.parse(parts[0]),
                            LocalTime.parse(parts[1]),
                            parts[2],
                            parts[3],
                            Double.parseDouble(parts[4])
                    ));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }
        return transactions;
    }
}
