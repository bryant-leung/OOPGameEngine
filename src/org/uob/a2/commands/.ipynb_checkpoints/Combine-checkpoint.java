package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;
import java.util.Scanner;

/**
 * Represents the combine command, allowing the player to combine two items to create a new item.
 * 
 * <p>
 * This command checks if the player possesses the specified items and, if so, combines them to create a new item.
 * The new item is then added to the player's inventory, and the original items are removed.
 * </p>
 */

// I did not want to change the test and pass an enum into combine, so I implemented the combine code seperately
public class Combine extends Command {
    private Scanner scanner;

    // Constructor 
    public Combine() {
        this.scanner = new Scanner(System.in);
    }

    // Executes the combine command, prompting the user for inputs
    @Override
    public String execute(GameState gameState) {
        Player player = gameState.getPlayer();

        // Prompt for Item 1
        System.out.println("Enter the first item to combine:");
        String item1Name = scanner.nextLine();
        if (!player.hasItem(item1Name)) {
            return "You do not have " + item1Name + " in your inventory.";
        }

        // Prompt for Item 2
        System.out.println("Enter the second item to combine:");
        String item2Name = scanner.nextLine();
        if (!player.hasItem(item2Name)) {
            return "You do not have " + item2Name + " in your inventory.";
        }

        // Check if items can be combined
        Item item1 = player.getItemByName(item1Name);
        Item item2 = player.getItemByName(item2Name);
        GameObject combinedItem = combineItems(item1, item2);

        if (combinedItem != null) {
            player.getInventory().remove(item1);
            player.getInventory().remove(item2);
            
            // Add combinedItem to inventory
            // I used 2 cases for combine
            // One combine case combines two items into an item
            // One combine case combines two items into an equipment
            if (combinedItem instanceof Item) {
                player.addItem((Item) combinedItem);
            } else if (combinedItem instanceof Equipment) {
                player.addEquipment((Equipment) combinedItem);
            }

            return "You combined " + item1Name + " and " + item2Name + " to create: " + combinedItem.getName();
        }

        return "These items cannot be combined.";
    }

    // The two combine cases
    private GameObject combineItems(Item item1, Item item2) {
        // Define the logic to combine two items and return a new item
        if ((item1.getName().equals("RelicYIN") && item2.getName().equals("RelicYANG")) || (item1.getName().equals("RelicYANG") && item2.getName().equals("RelicYIN"))) { 
            return new Item("YINYANG", "Yin-Yang-Emblem", "An emblem of balance and harmony, featuring an intertwining black and white design symbolizing duality and unity.", false);     
        }
        if ((item1.getName().equals("TownEmblem") && item2.getName().equals("Yin-Yang-Emblem")) || (item1.getName().equals("Yin-Yang-Emblem") && item2.getName().equals("Town Emblem"))) {
            return new Equipment("GP", "Gym Entry Pass", "You obtained this because you are now deemed worthy enough to challenge the gym.", false, new UseInformation(false, "open", "WG", "You entered the Water-Type Gym!", "You have entered the Water-Type Gym!"));
        }
        return null;
    }

    @Override
    public String toString() {
        return "Combine command to prompt user input for items to combine.";
    }
}
