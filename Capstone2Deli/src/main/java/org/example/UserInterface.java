package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    private Order currentOrder;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            showHomeScreen();
        }
    }

    private void showHomeScreen() {
        System.out.println("\n=== DELI-cious Subs ===");
        System.out.println("1) New Order");
        System.out.println("0) Exit");

        int choice = getIntInput("Choose an option: ", 0, 1);
        switch (choice) {
            case 1:
                startNewOrder();
                break;
            case 0:
                System.out.println("Thank you for visiting! Goodbye!");
                System.exit(0);
        }
    }

    private void startNewOrder() {
        currentOrder = new Order();
        showOrderMenu();
    }

    private void showOrderMenu() {
        while (true) {
            System.out.println("\n=== ORDER MENU ===");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");

            int choice = getIntInput("Choose an option: ", 0, 4);
            switch (choice) {
                case 1:
                    addSandwich();
                    break;
                case 2:
                    addDrink();
                    break;
                case 3:
                    addChips();
                    break;
                case 4:
                    checkout();
                    return;
                case 0:
                    System.out.println("Order canceled.");
                    currentOrder = null;
                    return;
            }
        }
    }

    private void addSandwich() {
        Sandwich sandwich = new Sandwich();

        System.out.println("\nSelect bread type:");
        sandwich.setBreadType(getOptionInput(Constants.BREAD_TYPES));

        System.out.println("\nSelect sandwich size:");
        sandwich.setSize(getOptionInput(Constants.SANDWICH_SIZES));

        addToppings(sandwich);


        System.out.print("\nWould you like the sandwich toasted? ");
        sandwich.setToasted(getYesNoInput());

        currentOrder.addSandwich(sandwich);
        System.out.println("Sandwich added to order!");
    }

    private void addToppings(Sandwich sandwich) {
        System.out.println("\n=== ADD TOPPINGS ===");


        final String[] categories = {"Meats", "Cheeses", "Regular Toppings", "Sauces", "Sides", "FINISHED"};

        while (true) {
            System.out.println("\nSelect a category:");
            for (int i = 0; i < categories.length; i++) {
                System.out.printf("%d) %s%n", i + 1, categories[i]);
            }

            int categoryChoice = getIntInput("Enter choice: ", 1, categories.length);


            if (categoryChoice == categories.length) {
                System.out.println("Finished adding toppings");
                return;
            }


            switch (categoryChoice) {
                case 1:
                    addToppingsFromCategory(sandwich, "Meats", Constants.MEAT_OPTIONS, Constants.TOPPING_MEAT);
                    break;
                case 2:
                    addToppingsFromCategory(sandwich, "Cheeses", Constants.CHEESE_OPTIONS, Constants.TOPPING_CHEESE);
                    break;
                case 3:
                    addToppingsFromCategory(sandwich, "Regular Toppings", Constants.REGULAR_OPTIONS, Constants.TOPPING_REGULAR);
                    break;
                case 4:
                    addToppingsFromCategory(sandwich, "Sauces", Constants.SAUCE_OPTIONS, Constants.TOPPING_SAUCE);
                    break;
                case 5:
                    addToppingsFromCategory(sandwich, "Sides", Constants.SIDE_OPTIONS, Constants.TOPPING_SIDE);
                    break;
            }
        }
    }

    private void addToppingsFromCategory(Sandwich sandwich, String categoryName,
                                         String[] toppings, String toppingType) {
        System.out.println("\n=== " + categoryName.toUpperCase() + " ===");


        if (categoryName.equals("Meats")) {
            System.out.printf("Regular: $%.2f | Extra: +$%.2f%n",
                    Constants.getMeatPrice(sandwich.getSize(), false),
                    Constants.getMeatPrice(sandwich.getSize(), true) - Constants.getMeatPrice(sandwich.getSize(), false));
        } else if (categoryName.equals("Cheeses")) {
            System.out.printf("Regular: $%.2f | Extra: +$%.2f%n",
                    Constants.getCheesePrice(sandwich.getSize(), false),
                    Constants.getCheesePrice(sandwich.getSize(), true) - Constants.getCheesePrice(sandwich.getSize(), false));
        } else if (categoryName.equals("Regular Toppings")) {
            System.out.println("Included | Extra: +$0.50");
        } else {
            System.out.println("Included at no extra cost");
        }


        for (int i = 0; i < toppings.length; i++) {
            System.out.printf("%d) %s%n", i + 1, toppings[i]);
        }
        System.out.println((toppings.length + 1) + ") Back to categories");

        int toppingChoice = getIntInput("Select topping: ", 1, toppings.length + 1);

        if (toppingChoice == toppings.length + 1) {
            return;
        }

        String toppingName = toppings[toppingChoice - 1];


        boolean isExtra = false;
        if (!categoryName.equals("Sauces") && !categoryName.equals("Sides")) {
            System.out.print("Make this extra? ");
            isExtra = getYesNoInput();
        }


        sandwich.addTopping(new Topping(toppingName, toppingType, isExtra));
        System.out.println("Added: " + toppingName + (isExtra ? " (extra)" : ""));
    }

    private void addDrink() {
        System.out.println("\n=== ADD DRINK ===");
        System.out.println("Available sizes:");
        for (int i = 0; i < Constants.DRINK_SIZES.length; i++) {
            System.out.printf("%d) %s ($%.2f)%n",
                    i + 1,
                    Constants.getDisplayName(Constants.DRINK_SIZES[i]),
                    Constants.getDrinkPrice(Constants.DRINK_SIZES[i]));
        }

        int sizeChoice = getIntInput("Select drink size: ", 1, Constants.DRINK_SIZES.length);
        String size = Constants.DRINK_SIZES[sizeChoice - 1];

        System.out.print("Enter drink flavor: ");
        String flavor = scanner.nextLine();

        Drink drink = new Drink(size, flavor);
        currentOrder.addDrink(drink);
        System.out.println("Drink added to order!");
    }

    private void addChips() {
        System.out.println("\n=== ADD CHIPS ===");
        System.out.println("Chips cost $1.50 per bag");
        int quantity = getIntInput("Enter number of chip bags: ", 0, 10);

        currentOrder.addChips(quantity);
        System.out.println(quantity + " chip bag(s) added to order!");
    }

    private void checkout() {
        System.out.println("\n=== ORDER SUMMARY ===");
        System.out.println(currentOrder);
        System.out.printf("TOTAL: $%.2f%n", currentOrder.calculateTotalPrice());

        System.out.println("\n1) Confirm Order");
        System.out.println("2) Cancel Order");
        int choice = getIntInput("Choose an option: ", 1, 2);

        if (choice == 1) {
            FileManager.saveOrder(currentOrder);
            System.out.println("Order confirmed! Receipt saved.");
        } else {
            System.out.println("Order canceled.");
        }
        currentOrder = null;
    }


    private int getIntInput(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.println("\nPlease enter a valid input.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private boolean getYesNoInput() {
        while (true) {
            System.out.print("(yes/no): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes")) {
                return true;
            } else if (input.equals("no")) {
                return false;
            }
            System.out.println("Please enter 'yes' or 'no'");
        }
    }

    private String getOptionInput(String[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.printf("%d) %s%n", i + 1, Constants.getDisplayName(options[i]));
        }

        int choice = getIntInput("Select: ", 1, options.length);
        return options[choice - 1];
    }
}