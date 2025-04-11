package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("What do you want to do?");
        System.out.println("Enter Multiply, Divide, Add or Subtract");
        String action = scanner.nextLine();

        System.out.println("Enter first number.");
        int num1 = scanner.nextInt();

        System.out.println("Enter second number.");
        int num2 = scanner.nextInt();
        scanner.nextLine();

        int result = Math.multiplyExact(num1, num2);


        switch (action.toLowerCase()) {
            case "multiply":
                result = Math.multiplyExact(num1, num2);
                break;
            case "divide":
                result = num1 / num2;
                break;
            case "add":
                result = num1 + num2;
                break;
            case "subtract":
                result = num1 - num2;
        }

        System.out.printf("Preparing to %s. %n", action);

        System.out.println(result);
    }
}