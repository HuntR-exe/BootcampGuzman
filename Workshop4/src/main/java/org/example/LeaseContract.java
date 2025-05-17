package org.example;

public class LeaseContract extends Contract {
    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        super(date, customerName, customerEmail, vehicle);
    }

    @Override
    public double getTotalPrice() {
        double price = getVehicle().getPrice();
        double expectedValue = price * 0.5;
        double leaseFee = price * 0.07;
        return leaseFee + (price - expectedValue);
    }

    @Override
    public double getMonthlyPayment() {
        return getTotalPrice() * 0.04 / (1 - Math.pow(1 + 0.04, -36));
    }
}
