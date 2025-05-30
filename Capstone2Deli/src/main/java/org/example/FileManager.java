package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class FileManager {
    public static void saveOrder(Order order) {
        String fileName = "receipt_" +
                order.getOrderTime().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(order.toString());
            System.out.println("Receipt saved as: " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving receipt: " + e.getMessage());
        }
    }
}