package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

public class Get extends Command {
    private String item;

    // Creates a new get command for the specified item
    public Get(String item) {
        this.commandType = CommandType.GET;
        this.item = item;
    }

    @Override
    public String execute(GameState gameState) {
        Player player = gameState.getPlayer();
        Room currentRoom = gameState.getCurrentRoom();

        // Ensure the current room is not null
        if (currentRoom == null) {
            return "No " + item + " to get.";
        }


        // Check if the player already has the item
        if (player.hasItem(item)) {
            return "You already have " + item;
        }

        // Check if the player already has the equipment
        if (player.hasEquipment(item)) {
            return "You already have " + item;
        }

        // Check if the item is present in the current room
        Item roomItem = currentRoom.getItemByName(item);
        if (roomItem != null) {
            player.addItem(roomItem); // Add the item to the player's inventory
            currentRoom.removeItem(roomItem); // Remove it from the room
            return "You pick up: " + item;
        }

        // Check if the equipment is present in the current room
        Equipment roomEquipment = currentRoom.getEquipmentByName(item);
        if (roomEquipment != null) {
            player.addEquipment(roomEquipment); // Add the equipment to the player
            currentRoom.removeEquipment(roomEquipment); // Remove it from the room
            return "You pick up: " + item;
        }

        // If the item or equipment is not found
        return "No " + item + " to get.";
    }

    @Override
    public String toString() {
        return "Get command for: " + item;
    }
}
