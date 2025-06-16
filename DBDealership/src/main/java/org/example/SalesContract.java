package org.example;

import java.time.LocalDate;

public class SalesContract extends Contract {
    private double salesTax;
    private double recordingFee;
    private double processingFee;
    private boolean financeOption;

    public SalesContract(LocalDate date, String customerName, String customerEmail, Vehicle vehicle,
                         boolean financeOption) {
        super();
        setDate(date);
        setCustomerName(customerName);
        setCustomerEmail(customerEmail);
        setVehicle(vehicle);
        this.financeOption = financeOption;
        this.salesTax = vehicle.getPrice() * 0.05;
        this.recordingFee = 100;
        this.processingFee = (vehicle.getPrice() < 10000) ? 295 : 495;
    }

    @Override
    public double getTotalPrice() {
        return getVehicle().getPrice() + salesTax + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!financeOption) return 0;
        double total = getTotalPrice();
        double rate = (total >= 10000) ? 0.0425 : 0.0525;
        int term = (total >= 10000) ? 48 : 24;
        return total * rate * Math.pow(1 + rate, term) / (Math.pow(1 + rate, term) - 1);
    }
}