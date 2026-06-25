package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the drop command, allowing the player to drop an item from their inventory into the current room.
 * 
 * <p>
 * This command checks if the player possesses the specified item and, if so, removes it from their inventory
 * and adds it to the current room. If the player does not have the item, an error message is returned.
 * </p>
 */
public class Drop extends Command {
    private String itemName;

    // Constructor that creates a new Drop command for the specified item
    public Drop(String item) {
        this.commandType = CommandType.DROP;
        this.value = item;
        this.itemName = item;
    }

    // Executes the drop command. If the player possesses a specified item, it is removed from their inventory and added to the current room. Otherwise, an error message is returned.
    // It should return a string describing the outcome of the command
    @Override
    public String execute(GameState gameState) {
        // Get player info from game state
        Player player = gameState.getPlayer();
        Room currentRoom = gameState.getCurrentRoom();

        // Check for valid room
        if (currentRoom == null) {
            return "You are not in a valid room.";
        }

        // Check if player has a certain item in their inventory
        // And also remove item mechanism
        if (player.hasItem(itemName)) {
            Item itemToRemove = player.getItem(itemName);
            if (itemToRemove != null) {
                player.getItems().remove(itemToRemove);
                currentRoom.addItem(itemToRemove);
                return "You drop: " + itemToRemove.getName();
            }
        }

        // Check if player has a certain equipment in their inventory
        // Works the same as item but for equipment
        if (player.hasEquipment(itemName)) {
            Equipment equipmentToRemove = player.getEquipment(itemName);
            if (equipmentToRemove != null) {
                player.getEquipment().remove(equipmentToRemove);
                currentRoom.addEquipment(equipmentToRemove);
                return "You drop: " + equipmentToRemove.getName();
            }
        }

        return "You cannot drop " + itemName;
    }

    // Returns a string representation of the Drop Command, including its type and target item.
    @Override
    public String toString() {
        return "Drop command for item: " + itemName;
    }
}
