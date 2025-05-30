package org.example;

public class Constants {

    public static final String BREAD_WHITE = "WHITE";
    public static final String BREAD_WHEAT = "WHEAT";
    public static final String BREAD_RYE = "RYE";
    public static final String BREAD_WRAP = "WRAP";
    public static final String[] BREAD_TYPES = {
            BREAD_WHITE, BREAD_WHEAT, BREAD_RYE, BREAD_WRAP
    };


    public static final String SIZE_4_INCH = "4_INCH";
    public static final String SIZE_8_INCH = "8_INCH";
    public static final String SIZE_12_INCH = "12_INCH";
    public static final String[] SANDWICH_SIZES = {
            SIZE_4_INCH, SIZE_8_INCH, SIZE_12_INCH
    };


    public static final String DRINK_SMALL = "SMALL";
    public static final String DRINK_MEDIUM = "MEDIUM";
    public static final String DRINK_LARGE = "LARGE";
    public static final String[] DRINK_SIZES = {
            DRINK_SMALL, DRINK_MEDIUM, DRINK_LARGE
    };


    public static final String TOPPING_MEAT = "MEAT";
    public static final String TOPPING_CHEESE = "CHEESE";
    public static final String TOPPING_REGULAR = "REGULAR";
    public static final String TOPPING_SAUCE = "SAUCE";
    public static final String TOPPING_SIDE = "SIDE";


    public static final String[] MEAT_OPTIONS = {
            "Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon"
    };


    public static final String[] CHEESE_OPTIONS = {
            "American", "Provolone", "Cheddar", "Swiss"
    };


    public static final String[] REGULAR_OPTIONS = {
            "Lettuce", "Peppers", "Onions", "Tomatoes",
            "Jalapeños", "Cucumbers", "Pickles", "Guacamole", "Mushrooms"
    };


    public static final String[] SAUCE_OPTIONS = {
            "Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Islands", "Vinaigrette"
    };


    public static final String[] SIDE_OPTIONS = {
            "Au Jus", "Sauce"
    };


    public static double getBreadPrice(String size) {
        switch (size) {
            case SIZE_4_INCH: return 5.50;
            case SIZE_8_INCH: return 7.00;
            case SIZE_12_INCH: return 8.50;
            default: return 0.0;
        }
    }

    public static double getMeatPrice(String size, boolean isExtra) {
        switch (size) {
            case SIZE_4_INCH: return isExtra ? 1.50 : 1.00;
            case SIZE_8_INCH: return isExtra ? 3.00 : 2.00;
            case SIZE_12_INCH: return isExtra ? 4.50 : 3.00;
            default: return 0.0;
        }
    }

    public static double getCheesePrice(String size, boolean isExtra) {
        switch (size) {
            case SIZE_4_INCH: return isExtra ? 1.05 : 0.75;
            case SIZE_8_INCH: return isExtra ? 2.10 : 1.50;
            case SIZE_12_INCH: return isExtra ? 3.15 : 2.25;
            default: return 0.0;
        }
    }

    public static double getRegularToppingPrice(boolean isExtra) {
        return isExtra ? 0.50 : 0.0;
    }

    public static double getDrinkPrice(String size) {
        switch (size) {
            case DRINK_SMALL: return 2.00;
            case DRINK_MEDIUM: return 2.50;
            case DRINK_LARGE: return 3.00;
            default: return 0.0;
        }
    }

    public static String getDisplayName(String value) {
        return value.replace("_", " ").toLowerCase();
    }
}
