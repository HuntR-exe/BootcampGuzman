package org.example;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> products;

    public ShoppingCart() {
        this.products = new ArrayList<>();
    }

    // TODO: Add a product to the cart
    // This method should take a Product object and add it to the list
    public void addProductToCart(Product product) {
        try {
            if (product == null) {
                throw new UnsupportedOperationException("Cannot add product to cart.");
            }
            products.add(product);
        } catch (UnsupportedOperationException e) {
            System.out.println("Error, please try again.");
        }
    }

    // TODO: Remove a product from the cart using its SKU
    // Steps:
    // 1. Loop through the list of products
    // 2. Check if any product's SKU matches the given SKU
    // 3. If found, remove it from the list (AFTER the loop to avoid issues while looping)
    public void removeProduct(String sku) {
        try {
            if (sku == null || sku.isEmpty()) {
                throw new UnsupportedOperationException("Incorrect SKU provided.");
            }

            Product toRemove = null;
            for (Product p : products) {
                if (p.getSku().equals(sku)) {
                    toRemove = p;
                    break;
                }
            }

            if (toRemove == null) {
                throw new UnsupportedOperationException("Product with SKU " + sku + " not found in Cart.");
            }
            products.remove(toRemove);
        } catch (UnsupportedOperationException e) {
            System.out.println("No Items in your Cart.");
        }
    }

    // TODO: Calculate and return the total cost of items in the cart
    // Hint: Loop through the list and add up the price of each product
    public double getCartTotal() {
        try {
            if (products.isEmpty()) {
                throw new UnsupportedOperationException("Cannot calculate total - cart is empty");
            }

            double total = 0.0;
            for (Product p : products) {
                if (p.getPrice() < 0) {
                    throw new UnsupportedOperationException("Incorrect price found for product: " + p.getSku());
                }
                total += p.getPrice();
            }
            return total;
        } catch (UnsupportedOperationException e) {
            System.out.println("Error, please try again.");
            return 0.0;
        }
    }

    // TODO: Display all items currently in the cart
    // Print SKU, name, price, and department of each product in a nice format
    public List<Product> displayItems() {
        return products;
    }
}