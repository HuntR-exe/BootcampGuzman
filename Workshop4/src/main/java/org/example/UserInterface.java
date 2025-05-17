package org.example;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private final DealershipFileManager fileManager;
    private final Scanner scanner;

    public UserInterface() {
        this.fileManager = new DealershipFileManager();
        this.dealership = fileManager.getDealership();
        this.scanner = new Scanner(System.in);
    }

    public void display() {
        while (true) {
            System.out.println("\n---- GearHeads ~ Japan ----");

            System.out.println("1. Search by Price");
            System.out.println("2. Search by Make/Model");
            System.out.println("3. Search by Year");
            System.out.println("4. Search by Color");
            System.out.println("5. Search by Mileage");
            System.out.println("6. Search by Vehicle Type");
            System.out.println("7. List All Vehicles");
            System.out.println("8. Add Vehicle");
            System.out.println("9. Remove Vehicle");
            System.out.println("99. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> processGetAllVehiclesRequest();
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 99 -> {
                    fileManager.saveDealership(dealership);
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void processGetAllVehiclesRequest() {
    }

    private void processGetByVehicleTypeRequest() {
    }

    private void processGetByMileageRequest() {
    }

    private void processGetByColorRequest() {
    }

    private void processGetByPriceRequest() {
        System.out.print("Enter minimum price: ");
        double min = scanner.nextDouble();
        System.out.print("Enter maximum price: ");
        double max = scanner.nextDouble();
        displayVehicles(dealership.getVehiclesByPrice(min, max));
    }

    private void processGetByMakeModelRequest() {
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }

    private void processGetByYearRequest() {
        System.out.print("Enter minimum year: ");
        int min = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int max = scanner.nextInt();
        displayVehicles(dealership.getVehiclesByYear(min, max));
    }


    private void processAddVehicleRequest() {
        System.out.println("Enter vehicle details:");
        System.out.print("VIN: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Make: ");
        String make = scanner.nextLine();

        System.out.print("Model: ");
        String model = scanner.nextLine();

        System.out.print("Type: ");
        String type = scanner.nextLine();

        System.out.print("Color: ");
        String color = scanner.nextLine();

        System.out.print("Mileage: ");
        int mileage = scanner.nextInt();

        System.out.print("Price: ");
        double price = scanner.nextDouble();

        dealership.addVehicle(new Vehicle(vin, year, make, model, type, color, mileage, price));
        System.out.println("Vehicle added successfully!");
    }

    private void processRemoveVehicleRequest() {
        System.out.print("Enter VIN of vehicle to remove: ");
        int vin = scanner.nextInt();
        List<Vehicle> vehicles = dealership.getAllVehicles();

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVin() == vin) {
                dealership.removeVehicle(vehicle);
                System.out.println("Vehicle removed successfully!");
                return;
            }
        }
        System.out.println("Vehicle with VIN " + vin + " not found!");
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found!");
            return;
        }
        System.out.println("\nSearch Results:");
        System.out.println("VIN\tYear\tMake\tModel\tType\tColor\tMileage\tPrice");
        for (Vehicle vehicle : vehicles) {
            System.out.printf("%d\t%d\t%s\t%s\t%s\t%s\t%d\t%.2f%n",
                    vehicle.getVin(),
                    vehicle.getYear(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getVehicleType(),
                    vehicle.getColor(),
                    vehicle.getOdometer(),
                    vehicle.getPrice());
        }
    }
}
