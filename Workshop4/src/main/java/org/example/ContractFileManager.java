package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {
    private static final String FILE_PATH = "src/main/resources/Contracts.csv";

    public void saveContract(Contract contract) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String line;
            if (contract instanceof SalesContract) {
                SalesContract sc = (SalesContract) contract;
                line = String.format("SALE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%s|%.2f",
                        sc.getDate(),
                        sc.getCustomerName(),
                        sc.getCustomerEmail(),
                        sc.getVehicle().getVin(),
                        sc.getVehicle().getYear(),
                        sc.getVehicle().getMake(),
                        sc.getVehicle().getModel(),
                        sc.getVehicle().getVehicleType(),
                        sc.getVehicle().getColor(),
                        sc.getVehicle().getOdometer(),
                        sc.getVehicle().getPrice(),
                        sc.getTotalPrice() * 0.05,
                        100.00,
                        (sc.getVehicle().getPrice() < 10000) ? 295.00 : 495.00,
                        sc.getTotalPrice(),
                        sc.isFinanceOption() ? "YES" : "NO",
                        sc.getMonthlyPayment()
                );
            } else {
                LeaseContract lc = (LeaseContract) contract;
                line = String.format("LEASE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f",
                        lc.getDate(),
                        lc.getCustomerName(),
                        lc.getCustomerEmail(),
                        lc.getVehicle().getVin(),
                        lc.getVehicle().getYear(),
                        lc.getVehicle().getMake(),
                        lc.getVehicle().getModel(),
                        lc.getVehicle().getVehicleType(),
                        lc.getVehicle().getColor(),
                        lc.getVehicle().getOdometer(),
                        lc.getVehicle().getPrice(),
                        lc.getVehicle().getPrice() * 0.5,
                        lc.getVehicle().getPrice() * 0.07,
                        lc.getTotalPrice(),
                        lc.getMonthlyPayment()
                );
            }
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving contract." );
        }
    }
}