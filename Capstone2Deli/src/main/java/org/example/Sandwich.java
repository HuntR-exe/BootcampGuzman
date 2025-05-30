package org.example;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    private String breadType;
    private String size;
    private final List<Topping> toppings = new ArrayList<>();
    private boolean toasted;

    public String getBreadType() {
        return breadType;
    }

    public void setBreadType(String breadType) {
        this.breadType = breadType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public double calculatePrice() {
        double price = Constants.getBreadPrice(size);

        for (Topping topping : toppings) {
            switch (topping.getType()) {
                case Constants.TOPPING_MEAT:
                    price += Constants.getMeatPrice(size, topping.isExtra());
                    break;
                case Constants.TOPPING_CHEESE:
                    price += Constants.getCheesePrice(size, topping.isExtra());
                    break;
                case Constants.TOPPING_REGULAR:
                    price += Constants.getRegularToppingPrice(topping.isExtra());
                    break;

            }
        }

        return price;
    }

    @Override
    public String toString() {
        StringBuilder toppingsString = new StringBuilder();
        for (int i = 0; i < toppings.size(); i++) {
            Topping t = toppings.get(i);
            toppingsString.append(t.getName());
            if (t.isExtra()) {
                toppingsString.append(" (extra)");
            }
            if (i < toppings.size() - 1) {
                toppingsString.append(", ");
            }
        }

        return String.format("%s %s sandwich on %s bread%s. Toppings: %s. Price: $%.2f",
                Constants.getDisplayName(size),
                (toasted ? "toasted" : "untoasted"),
                Constants.getDisplayName(breadType),
                (toppings.isEmpty() ? " (no toppings)" : ""),
                toppingsString.toString(),
                calculatePrice());
    }
}