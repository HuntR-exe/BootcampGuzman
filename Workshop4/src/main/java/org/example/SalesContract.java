package org.example;

public class SalesContract extends Contract {
    private boolean financeOption;

    public SalesContract(String date, String customerName, String customerEmail,
                         Vehicle vehicle, boolean financeOption) {
        super(date, customerName, customerEmail, vehicle);
        this.financeOption = financeOption;
    }

    @Override
    public double getTotalPrice() {
        double price = getVehicle().getPrice();
        double salesTax = price * 0.05;
        double recordingFee = 100;
        double processingFee = (price < 10000) ? 295 : 495;
        return price + salesTax + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!financeOption) return 0;

        double total = getTotalPrice();
        double rate = (total >= 10000) ? 0.0425 : 0.0525;
        int months = (total >= 10000) ? 48 : 24;

        return (total * rate) / (1 - Math.pow(1 + rate, -months));
    }

    public boolean isFinanceOption() { return financeOption; }
}