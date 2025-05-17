package org.example;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final Dealership dealership;
    private final Scanner scanner;

    public UserInterface() {
        DealershipFileManager fileManager = new DealershipFileManager();
        this.dealership = fileManager.getDealership();
        this.scanner = new Scanner(System.in);
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
            System.out.println("7. List All Vehicles");
            System.out.println("8. Add Vehicle");
            System.out.println("9. Remove Vehicle");
            System.out.println("10. Sell/Lease Vehicle");
            System.out.println("0. Exit");

            int choice = getIntInput("Enter your choice: ");
            scanner.nextLine();

            switch (choice) {
                case 1 -> getByPriceRequest();
                case 2 -> getByMakeModelRequest();
                case 3 -> getByYearRequest();
                case 4 -> getByColorRequest();
                case 5 -> getByMileageRequest();
                case 6 -> getByVehicleTypeRequest();
                case 7 -> getAllVehiclesRequest();
                case 8 -> addVehicleRequest();
                case 9 -> removeVehicleRequest();
                case 10 -> saleLeaseRequest();
                case 0 -> {
                    new DealershipFileManager().saveDealership(dealership);
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }


    private void getByPriceRequest() {
        double min = getDoubleInput("Enter minimum price: ");
        double max = getDoubleInput("Enter maximum price: ");
        displayResults(dealership.getVehiclesByPrice(min, max));
    }

    private void getByMakeModelRequest() {
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        displayResults(dealership.getVehiclesByMakeModel(make, model));
    }

    private void getByYearRequest() {
        int min = getIntInput("Enter minimum year: ");
        int max = getIntInput("Enter maximum year: ");
        displayResults(dealership.getVehiclesByYear(min, max));
    }

    private void getByColorRequest() {
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        displayResults(dealership.getVehiclesByColor(color));
    }

    private void getByMileageRequest() {
        int min = getIntInput("Enter minimum mileage: ");
        int max = getIntInput("Enter maximum mileage: ");
        displayResults(dealership.getVehiclesByMileage(min, max));
    }

    private void getByVehicleTypeRequest() {
        System.out.print("Enter vehicle type: ");
        String type = scanner.nextLine().trim();
        List<Vehicle> results = dealership.getVehiclesByType(type);

        if (results.isEmpty()) {
            System.out.println("No vehicles found of type: " + type);
        } else {
            displayResults(results);
        }
    }

    private void getAllVehiclesRequest() {
        displayResults(dealership.getAllVehicles());
    }


    private void addVehicleRequest() {
        System.out.println("\n-- Add Vehicle --");
        int vin = getIntInput("VIN: ");
        int year = getIntInput("Year: ");
        scanner.nextLine(); // Clear buffer

        System.out.print("Make: ");
        String make = scanner.nextLine();

        System.out.print("Model: ");
        String model = scanner.nextLine();

        System.out.print("Type: ");
        String type = scanner.nextLine();

        System.out.print("Color: ");
        String color = scanner.nextLine();

        int odometer = getIntInput("Mileage: ");
        double price = getDoubleInput("Price: ");

        dealership.addVehicle(new Vehicle(vin, year, make, model, type, color, odometer, price));
        System.out.println("Vehicle added!");
    }

    private void removeVehicleRequest() {
        int vin = getIntInput("Enter VIN to remove: ");
        List<Vehicle> vehicles = dealership.getAllVehicles();

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVin() == vin) {
                dealership.removeVehicle(vehicle);
                System.out.println("Vehicle removed!");
                return;
            }
        }
        System.out.println("Vehicle not found!");
    }

    private void displayResults(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found");
            return;
        }
        System.out.println("\nResults:");

        System.out.println("VIN|Year|Make|Model|Type|Color|Mileage|Price");

        for (Vehicle vehicle : vehicles) {
            System.out.printf("%d|%d|%s|%s|%s|%s|%d|%.2f%n",
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

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid number!");
                scanner.nextLine();
            }
        }
    }

    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextDouble();
            } catch (Exception e) {
                System.out.println("Invalid number!");
                scanner.nextLine();
            }
        }
    }

    private void saleLeaseRequest() {
        System.out.print("Enter VIN of vehicle: ");
        int vin = getIntInput("");
        scanner.nextLine();

        Vehicle vehicle = findVehicleByVin(vin);
        if (vehicle == null) {
            System.out.println("Vehicle not found!");
            return;
        }

        System.out.print("Customer name: ");
        String name = scanner.nextLine();
        System.out.print("Customer email: ");
        String email = scanner.nextLine();
        String date = java.time.LocalDate.now().toString();

        System.out.print("Is this a (1) Sale or (2) Lease? ");
        int choice = getIntInput("");
        scanner.nextLine();

        Contract contract;
        if (choice == 1) {
            System.out.print("Finance? (true/false): ");
            boolean finance = scanner.nextLine().equalsIgnoreCase("true");
            contract = new SalesContract(date, name, email, vehicle, finance);
        } else {

            contract = new LeaseContract(date, name, email, vehicle);
        }

        new ContractFileManager().saveContract(contract);
        dealership.removeVehicle(vehicle);
        System.out.println("Contract saved and vehicle removed from inventory!");
    }

    private Vehicle findVehicleByVin(int vin) {
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) return vehicle;
        }
        return null;
    }
}