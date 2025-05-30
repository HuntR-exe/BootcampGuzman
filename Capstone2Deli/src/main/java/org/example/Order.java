package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<Sandwich> sandwiches = new ArrayList<>();
    private final List<Drink> drinks = new ArrayList<>();
    private int chips;
    private final LocalDateTime orderTime = LocalDateTime.now();

    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void addChips(int quantity) {
        chips += quantity;
    }

    public double calculateTotalPrice() {
        double total = 0;

        for (Sandwich sandwich : sandwiches) {
            total += sandwich.calculatePrice();
        }

        for (Drink drink : drinks) {
            total += drink.calculatePrice();
        }

        total += chips * 1.50;

        return total;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public int getChips() {
        return chips;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order Time: ").append(orderTime).append("\n\n");

        if (!sandwiches.isEmpty()) {
            sb.append("SANDWICHES:\n");
            for (int i = 0; i < sandwiches.size(); i++) {
                sb.append(i + 1).append(") ").append(sandwiches.get(i)).append("\n");
            }
        }

        if (!drinks.isEmpty()) {
            sb.append("\nDRINKS:\n");
            for (Drink drink : drinks) {
                sb.append("- ").append(drink).append("\n");
            }
        }

        if (chips > 0) {
            sb.append("\nCHIPS: ").append(chips).append(" bag(s) @ $1.50 each\n");
        }

        sb.append("\nSUBTOTAL: $").append(String.format("%.2f", calculateTotalPrice()));
        return sb.toString();
    }
}