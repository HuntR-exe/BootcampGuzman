package org.example;

import java.io.*;

public class DealershipFileManager {
    private static final String FILE_NAME = "dealership.csv";

    public Dealership getDealership() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            String[] dealerInfo = reader.readLine().split("\\|");
            Dealership dealership = new Dealership(
                    dealerInfo[0],
                    dealerInfo[1],
                    dealerInfo[2]
            );


            String line;
            while ((line = reader.readLine()) != null) {
                dealership.addVehicle(Vehicle.fromCSV(line));
            }
            return dealership;
        } catch (IOException e) {
            System.out.println("No existing data - creating new dealership");
            return new Dealership("Default Dealership", "Unknown Address", "000-000-0000");
        }
    }

    public void saveDealership(Dealership dealership) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {

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
            System.err.println("Error saving dealership data: " + e.getMessage());
        }
    }
}
