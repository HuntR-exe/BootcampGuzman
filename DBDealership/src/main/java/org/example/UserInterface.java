package org.example;

import org.example.VehicleDAO;
import org.example.Dealership;
import org.example.Vehicle;
import org.example.DataAccess;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    private final Dealership dealership;
    private final VehicleDAO vehicleDAO;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
        this.dealership = DataAccess.getDealership();
        this.vehicleDAO = new VehicleDAO();
    }

    public void display() {
        while (true) {
            System.out.println("\n==== GearHeads ~ Japan ====");
            System.out.println("1. Search by Price");
            System.out.println("2. Search by Make/Model");
            System.out.println("3. Search by Year");
            System.out.println("4. Search by Color");
            System.out.println("5. Search by Mileage");
            System.out.println("6. Search by Type");
            System.out.println("7. View All Vehicles");
            System.out.println("8. Add Vehicle");
            System.out.println("9. Remove Vehicle");
            System.out.println("10. Sell/Lease Vehicle");
            System.out.println("99. Exit");

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> processSearchByPrice();
                case 2 -> processSearchByMakeModel();
                case 3 -> processSearchByYear();
                case 4 -> processSearchByColor();
                case 5 -> processSearchByMileage();
                case 6 -> processSearchByType();
                case 7 -> displayVehicles(dealership.getInventory());
                case 8 -> processAddVehicle();
                case 9 -> processRemoveVehicle();
                case 10 -> processContract();
                case 99 -> System.exit(0);
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private void processSearchByPrice() {
        System.out.print("Enter min price: ");
        double min = scanner.nextDouble();
        System.out.print("Enter max price: ");
        double max = scanner.nextDouble();
        displayVehicles(vehicleDAO.getByPriceRange(min, max));
    }

    private void processSearchByMakeModel() {
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        List<Vehicle> results = vehicleDAO.getByMakeModel(make, model);
        displayVehicles(results);
    }

    private void processSearchByYear() {
        System.out.print("Enter minimum year: ");
        int minYear = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int maxYear = scanner.nextInt();
        scanner.nextLine();

        List<Vehicle> results = vehicleDAO.getByYearRange(minYear, maxYear);
        displayVehicles(results);
    }

    private void processSearchByColor() {
        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        List<Vehicle> results = vehicleDAO.getByColor(color);
        displayVehicles(results);
    }

    private void processSearchByMileage() {
        System.out.print("Enter minimum mileage: ");
        int minMileage = scanner.nextInt();
        System.out.print("Enter maximum mileage: ");
        int maxMileage = scanner.nextInt();
        scanner.nextLine();

        List<Vehicle> results = vehicleDAO.getByMileageRange(minMileage, maxMileage);
        displayVehicles(results);
    }

    private void processSearchByType() {
        System.out.print("Enter vehicle type: ");
        String type = scanner.nextLine();

        List<Vehicle> results = vehicleDAO.getByType(type);
        displayVehicles(results);
    }

    private void processAddVehicle() {
        System.out.println("Enter vehicle details:");
        Vehicle vehicle = new Vehicle(
                getIntInput("VIN: "),
                getIntInput("Year: "),
                getStringInput("Make: "),
                getStringInput("Model: "),
                getStringInput("Type: "),
                getStringInput("Color: "),
                getIntInput("Odometer: "),
                getDoubleInput("Price: ")
        );

        vehicleDAO.addVehicle(vehicle);
        System.out.println("Vehicle added successfully!");
    }


    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found");
            return;
        }
        System.out.println("\n==== Vehicle Inventory ====");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    private void processRemoveVehicle() {
        System.out.println("\n==== Remove Vehicle ====");
        displayVehicles(dealership.getInventory());

        System.out.print("Enter VIN of vehicle to remove: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        Vehicle toRemove = null;
        for (Vehicle v : dealership.getInventory()) {
            if (v.getVin() == vin) {
                toRemove = v;
                break;
            }
        }

        if (toRemove != null) {
            vehicleDAO.removeVehicle(toRemove.getId());
            dealership.getInventory().remove(toRemove);
            System.out.println("Vehicle with VIN " + vin + " removed successfully!");
        } else {
            System.out.println("Vehicle with VIN " + vin + " not found");
        }
    }

    private void processContract() {
        System.out.println("\n==== Process Contract ====");
        displayVehicles(dealership.getInventory());

        System.out.print("Enter VIN of vehicle: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        Vehicle vehicle = null;
        for (Vehicle v : dealership.getInventory()) {
            if (v.getVin() == vin) {
                vehicle = v;
                break;
            }
        }

        if (vehicle == null) {
            System.out.println("Vehicle not found!");
            return;
        }

        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();

        System.out.println("Choose contract type:");
        System.out.println("1. Sale");
        System.out.println("2. Lease");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        Contract contract;
        switch (choice) {
            case 1 -> {
                System.out.print("Finance option? (true/false): ");
                boolean finance = scanner.nextBoolean();
                contract = new SalesContract(LocalDate.now(), name, email, vehicle, finance);
                SalesContractDAO.addContract((SalesContract) contract);
            }
            default -> {
                System.out.println("Invalid choice");
                return;
            }
        }

        vehicleDAO.removeVehicle(vehicle.getId());
        dealership.getInventory().remove(vehicle);

        System.out.println("Contract processed successfully!");
        System.out.printf("Total price: $%.2f%n", contract.getTotalPrice());
        System.out.printf("Monthly payment: $%.2f%n", contract.getMonthlyPayment());
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private int getIntInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }

    private double getDoubleInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextDouble();
    }
}