package org.example;

public class Drink {
    private String size;
    private String flavor;

    public Drink(String size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public double calculatePrice() {
        return Constants.getDrinkPrice(size);
    }

    @Override
    public String toString() {
        return String.format("%s %s - $%.2f",
                Constants.getDisplayName(size),
                flavor,
                calculatePrice());
    }
}
