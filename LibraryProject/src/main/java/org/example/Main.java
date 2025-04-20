package org.example;

import java.awt.event.WindowEvent;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Books[] inventory = new Books[20];

        Books book1 = (new Books(0, "4567", "Whispers in the Dark", false, "Alice Smith"));
        inventory[0] = book1;

        Books book2 = (new Books(1, "4568", "The Hollow Echoes", true));
        inventory[1] = book2;

        Books book3 = (new Books(2, "4569", "Tears of the Forgotten", false, "David Carter"));
        inventory[2] = book3;

        Books book4 = (new Books(3, "4570", "Asylum of Shadows", true));
        inventory[3] = book4;

        Books book5 = (new Books(4, "4571", "The Silent Mourning", false, "Emily Rose"));
        inventory[4] = book5;

        Books book6 = (new Books(5, "4572", "Beneath the Weeping Willow", true));
        inventory[5] = book6;

        Books book7 = (new Books(6, "4573", "Bloodline of the Damned", false, "Michael Vorn"));
        inventory[6] = book7;

        Books book8 = (new Books(7, "4574", "Pages of Regret", true));
        inventory[7] = book8;

        Books book9 = (new Books(8, "4575", "The Neverending Night", false, "Sarah Connor"));
        inventory[8] = book9;

        Books book10 = (new Books(9, "4576", "Dust Memories", true));
        inventory[9] = book10;

        Books book11 = (new Books(10, "4577", "Screams in the Static", false, "John Doe"));
        inventory[10] = book11;

        Books book12 = (new Books(11, "4578", "Crimson Requiem", true));
        inventory[11] = book12;

        Books book13 = (new Books(12, "4579", "The Weight of Absence", false, "Emma Graves"));
        inventory[12] = book13;

        Books book14 = (new Books(13, "4580", "Fractured Reflections", true));
        inventory[13] = book14;

        Books book15 = (new Books(14, "4581", "Veil of the Forgotten", false, "Victor Hollow"));
        inventory[14] = book15;

        Books book16 = (new Books(15, "4582", "Chamber of Broken Dolls", true));
        inventory[15] = book16;

        Books book17 = (new Books(16, "4583", "The Dark That Remains", false, "Luna Blackwood"));
        inventory[16] = book17;

        Books book18 = (new Books(17, "4584", "Echoes from the Void", true));
        inventory[17] = book18;

        Books book19 = (new Books(18, "4585", "A Thousand Goodbyes", false, "Oliver Twist"));
        inventory[18] = book19;

        Books book20 = (new Books(19, "4586", "The Last Breath Club", true));
        inventory[19] = book20;


        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to the Library.");
            System.out.println(".....Please Select an Option.....");
            System.out.println("===========================");
            Thread.sleep(900);

            System.out.println("(1) Book list");
            Thread.sleep(600);
            System.out.println("(2) Book Check-In");
            Thread.sleep(600);
            System.out.println("(3) Book Check-Out");
            Thread.sleep(600);
            System.out.println("(4) Quit");

            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    showBooks(inventory);
                    break;

                case 2:
                    bookCheckIn(inventory, scanner);
                    break;

                case 3:
                    bookCheckOut(inventory);
                    break;

                case 4:
                    System.exit(0);
                    break;

            }
        }

    }

    public static void showBooks(Books[] inventory) {
        for (int i = 0; i < inventory.length; i++) {
            Books currentBook = inventory[i];
            if(currentBook != null && currentBook.isCheckedOut()) {
                System.out.println(currentBook.getBoolTitle());
            }
        }

    }

    public static void bookCheckIn(Books[] inventory, Scanner scanner) {
        scanner.nextLine();
        System.out.println("What book is being Returned?");
        String returnedBook = scanner.nextLine();

        boolean isCheckedIn = false;

        for(int i = 0; i < inventory.length; i++){
            if (inventory[i] != null && inventory[i].getBoolTitle().equalsIgnoreCase(returnedBook)) {
                if (inventory[i].isCheckedOut()) {
                    System.out.println("Book is already Checked-In.");
                }
                else {
                    inventory[i].setCheckedOut(true);
                    System.out.println("Book Successfully Checked-In.");
                }
                isCheckedIn = true;
                break;
            }
        }

        if (!isCheckedIn) {
            System.out.println("Book not Available.");
        }
    }

    public static void bookCheckOut(Books[] inventory) {
        for (int i = 0; i < inventory.length; i++){
            Books currentBook = inventory[i];
            if (currentBook != null && !currentBook.isCheckedOut()) {
                System.out.printf("The book %s, %d, %s is currently Checked-Out by %s %n", currentBook.getBoolTitle(), currentBook.getBookID(), currentBook.getBookISBN(), currentBook.getCheckedOutTo());
            }
        }
    }
}