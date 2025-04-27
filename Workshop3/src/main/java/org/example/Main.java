package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Product> allProducts = FileLoader.readFile();
        ShoppingCart cart = new ShoppingCart();

        while (true) {
            System.out.println("Welcome to the store! Choose an option:");
            System.out.println("1. View all products");
            System.out.println("2. Search by SKU");
            System.out.println("3. Search by price range");
            System.out.println("4. Search by name");
            System.out.println("5. Add to cart");
            System.out.println("6. Remove from cart");
            System.out.println("7. View cart");
            System.out.println("8. Checkout");
            System.out.println("9. Exit");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    displayProducts(allProducts);
                    break;
                case 2:
                    System.out.print("Enter SKU to search: ");
                    String searchSku = scanner.nextLine();
                    Product foundProduct = findBySku(allProducts, searchSku);
                    if (foundProduct != null) {
                        displayProducts(List.of(foundProduct));
                    }
                    else {
                        System.out.println("Product not found!");
                    }
                    break;
                case 3:
                    System.out.print("Enter minimum price: ");
                    double min = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter maximum price: ");
                    double max = Double.parseDouble(scanner.nextLine());
                    List<Product> priceFiltered = filterByPriceRange(allProducts, min, max);
                    displayProducts(priceFiltered);
                    break;
                case 4:
                    System.out.print("Enter product name to search: ");
                    String searchName = scanner.nextLine();
                    List<Product> nameMatches = searchByName(allProducts, searchName);
                    displayProducts(nameMatches);
                    break;
                case 5:
                    System.out.print("Enter SKU to add to cart: ");
                    String addSku = scanner.nextLine();
                    Product addProduct = findBySku(allProducts, addSku);
                    if (addProduct != null) {
                        cart.addProductToCart(addProduct);
                        System.out.println("Product added to cart!");
                    }
                    else {
                        System.out.println("Product not found!");
                    }
                    break;
                case 6:
                    System.out.print("Enter SKU to remove from cart: ");
                    String removeSku = scanner.nextLine();
                    cart.removeProduct(removeSku);
                    break;
                case 7:
                    System.out.println("--- Your Cart ---");
                    displayProducts(cart.displayItems());
                    System.out.printf("Total: $%.2f%n", cart.getCartTotal());
                    break;
                case 8:
                    System.out.printf("Total amount: $%.2f%n", cart.getCartTotal());
                    System.out.println("Thank you for shopping with us!");
                    cart = new ShoppingCart();
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    //This method could be re-used over and over again if you play
    //your cards right!
    public static void displayProducts(List<Product> products) {
        for (Product p : products) {
            if (products.isEmpty()) {
                System.out.println("No products found");
                return;
            }
            //ChatGPT helped with this nice display c:
            System.out.printf("%-8s | %-28s | $%-6.2f | %-15s%n",
                    p.getSku(),
                    p.getProductName(),
                    p.getPrice(),
                    p.getDepartment());
        }
    }

    public static Product findBySku(List<Product> products, String sku) {
        for (Product p : products) {
            if (p.getSku().equalsIgnoreCase(sku)) {
                return p;
            }
        }
        return null; // if not found
    }

    // Returns a list of products with names that contain the search string (case-insensitive)
    public static List<Product> searchByName(List<Product> products, String name) {
        List<Product> matches = new ArrayList<>();
        String searchLower = name.toLowerCase();
        for (Product p : products) {
            if (p.getProductName().toLowerCase().contains(searchLower)) {
                matches.add(p);
            }
        }
        return matches;
    }

    // Returns a list of products within the given price range (inclusive)
    public static List<Product> filterByPriceRange(List<Product> products, double min, double max) {
        List<Product> matches = new ArrayList<>();
        for (Product p : products) {
            if (p.getPrice() >= min && p.getPrice() <= max) {
                matches.add(p);
            }
        }
        return matches;
    }
}