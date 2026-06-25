// Additional class to handle all Equipment shop purchases at RoomID: ES
package org.uob.a2.gameobjects;

import java.util.Scanner;

public class EquipmentShop {
    private Player player;
    private Scanner scanner;

    public EquipmentShop(Player player, Scanner scanner) {
        this.player = player;
        this.scanner = scanner;
    }

    public void displayItems() {
        System.out.println("Welcome to the Equipment Shop!");
        System.out.println("Here are the items available for purchase:");
        System.out.println("1. Pokéball - 1 point each");
        System.out.println("2. Ultraball - 5 points each");
    }

    public void purchaseItem() {
        displayItems();
        System.out.println("Enter the number of the item you wish to purchase:");
        // Parses only the int and trims everything else
        int choice = Integer.parseInt(scanner.nextLine().trim());
        // Parses only the int and trims everything else
        System.out.println("Enter the quantity you wish to purchase:");
        int quantity = Integer.parseInt(scanner.nextLine().trim());
        
        boolean success = false;

        switch (choice) {
            case 1:
                success = buyItem("pokeball", 1, quantity);
                break;
            case 2:
                success = buyItem("ultraball", 5, quantity);
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }

        if (success) {
            System.out.println("Purchase successful!");
        } else {
            System.out.println("Purchase failed. Not enough points or invalid choice.");
        }
    }

    // calculates the total points required and deducts it from the method decreaseScore as declared in player
    private boolean buyItem(String itemName, int cost, int quantity) {
        int totalCost = cost * quantity;
        if (player.getScore() >= totalCost) {
            for (int i = 0; i < quantity; i++) {
                player.addItem(new Item(itemName, capitalize(itemName), "A useful item for catching Pokémon.", false));
            }
            player.decreaseScore(totalCost); // Decrease total cost from score
            return true;
        }
        return false;
    }

    // Ensures uniform input to read
    // Capitalises first letter of string
    // If not first letter, convert to lower case
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
