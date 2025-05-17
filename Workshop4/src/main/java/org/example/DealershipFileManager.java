package org.example;

import java.io.*;

public class DealershipFileManager {
    private static final String FILE_PATH = "src/main/resources/Inventory.csv";

    public Dealership getDealership() {
        File inventoryFile = new File(FILE_PATH);


        if (!inventoryFile.exists()) {
            try {
                inventoryFile.getParentFile().mkdirs();
                inventoryFile.createNewFile();
                System.out.println("Created new inventory file: " + FILE_PATH);
                return new Dealership("Default Dealership", "Unknown Address", "000-000-0000");
            } catch (IOException e) {
                System.out.println("Error creating inventory file.");
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {

            String[] dealerInfo = reader.readLine().split("\\|");
            Dealership dealership = new Dealership(
                    dealerInfo[0].trim(),
                    dealerInfo[1].trim(),
                    dealerInfo[2].trim()
            );

            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Vehicle vehicle = Vehicle.fromCSV(line);
                    dealership.addVehicle(vehicle);
                } catch (Exception e) {
                    System.out.println("Skipping invalid vehicle entry.");
                }
            }
            return dealership;

        } catch (IOException e) {
            System.out.println("Error loading dealership.");
            return new Dealership("Default Dealership", "Unknown Address", "000-000-0000");
        }
    }

    public void saveDealership(Dealership dealership) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {

            writer.write(String.join("|",
                   dealership.getName(),
                   dealership.getAddress(),
                   dealership.getPhone()
            ));
            writer.newLine();


            for (Vehicle vehicle : dealership.getAllVehicles()) {
                writer.write(vehicle.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving dealership.");
        }
    }
}
