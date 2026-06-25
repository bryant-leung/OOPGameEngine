package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the status command, allowing the player to retrieve information
 * about their inventory, specific items, or their overall status.
 * 
 * <p>
 * The status command can display a list of items in the player's inventory, 
 * provide details about a specific item, or show the player's general status.
 * </p>
 */
public class Status extends Command {
    private String topic;

    // Constructor that creates a new status command for the specific topic
    public Status(String topic) {
        this.commandType = CommandType.STATUS;
        this.value = topic;
        this.topic = topic;
    }

    // Executes the status command. Retrieves and displays information based on the specific topic
    // If the topic is "inventory", it lists all items in the player's inventory
    // If the topic matches an item name, it displays the item's description
    // If the topic matches equipment name, it displays the equipment's description
    // If the topic is "room", it displays the current room's description
    // Returns a string describing the requested information
    @Override
    public String execute(GameState gameState) {
        String message = "";
        Player player = gameState.getPlayer();

        switch (topic.toLowerCase()) {
            case "room":
                if (gameState.getCurrentRoom() != null) {
                    message = "You are currently in: " + gameState.getCurrentRoom().toString();
                } else {
                    message = "You are not in a room";
                }
                break;

            case "inventory":
                List<String> inventoryItems = new ArrayList<>(); // Declare a new list for inventoryItems
                for (Item item : player.getItems()) { // Iterates through item objects and adds name of item to inventoryItemslist
                    inventoryItems.add(item.getName());
                }
                List<String> inventoryEquipment = new ArrayList<>();
                // same for equipment
                for (Equipment equip : player.getEquipment()) {
                    inventoryEquipment.add(equip.getName());
                }

                if (inventoryItems.isEmpty() && inventoryEquipment.isEmpty()) {
                    message = "Your inventory is empty";
                } else {
                    StringBuilder inventory = new StringBuilder("Your inventory: ");
                    // itererate through the inventoryItems list and append all items first, then appends all equipments
                    for (String itemName : inventoryItems) {
                        inventory.append(itemName).append(", ");
                    }
                    for (String equipmentName : inventoryEquipment) {
                        inventory.append(equipmentName).append(", ");
                    }
                    message = inventory.toString(); // Converts inventory into a message variable
                    if (message.endsWith(", ")) {
                        message = message.substring(0, message.length() - 2);
                    }// Removes the commas and space
                }
                break;

            case "player":
                message = "Player: " + player.getName() + "\n";
                message += "Inventory:\n";
                for (Item item : player.getInventory()) { 
                    message += "- " + item.getName() + ": " + item.getDescription() + "\n"; 
                }
                break;

            case "map":
                message = displayMapGrid(gameState);
                break;
                
            case "score": 
                message = displayScore(gameState); 
                break;

            default:
                Item item = null;
                for (Item i : player.getItems()) {
                    if (i.getName().equalsIgnoreCase(topic)) {
                        item = i;
                        break;
                    }
                }
                Equipment equipment = null;
                for (Equipment e : player.getEquipment()) {
                    if (e.getName().equalsIgnoreCase(topic)) {
                        equipment = e;
                        break;
                    }
                }

                if (item != null) {
                    message = item.getDescription();
                } else if (equipment != null) {
                    message = equipment.getDescription();
                } else {
                    // Return an empty string for invalid topics
                    message = "";
                }
                break;
        }
        return message;
    }

        // New method to display the 3x3 map grid with room IDs and uniform spacing
    public String displayMapGrid(GameState gameState) {
        // Get current state of map
        Map map = gameState.getMap();
        Room currentRoom = gameState.getCurrentRoom();
        StringBuilder grid = new StringBuilder("Map grid:\n");
    
        // Define the width for each cell
        final int cellWidth = 7; 
        String[][] gridArray = new String[3][3]; // Minimap declared to be 3x3
    
        // Initialize grid with empty spaces
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gridArray[i][j] = " ".repeat(cellWidth); // Fills all 9 grids as empty spaces initially
            }
        }
    
        // Set current room at the center and append (P)
        // The map changes along with player, always centered around player's current location
        gridArray[1][1] = padString(currentRoom.getId() + "(P)", cellWidth);
    
        // Set surrounding rooms with their IDs
        setGridCell(map, gridArray, currentRoom, "north", 0, 1, cellWidth);
        setGridCell(map, gridArray, currentRoom, "south", 2, 1, cellWidth);
        setGridCell(map, gridArray, currentRoom, "east", 1, 2, cellWidth);
        setGridCell(map, gridArray, currentRoom, "west", 1, 0, cellWidth);
        setGridCell(map, gridArray, currentRoom, "northwest", 0, 0, cellWidth);
        setGridCell(map, gridArray, currentRoom, "northeast", 0, 2, cellWidth);
        setGridCell(map, gridArray, currentRoom, "southwest", 2, 0, cellWidth);
        setGridCell(map, gridArray, currentRoom, "southeast", 2, 2, cellWidth);
    
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid.append(gridArray[i][j]); // Append each cell value (id)
                if (j < 2) grid.append("|");
            }
            grid.append("\n");
            if (i < 2) grid.append("-".repeat(cellWidth * 3 + 2) + "\n"); // Append horizontal seperator for the spaces right below the grid and the 2 empty spaces in between 7-cellwidth grids
        }
    
        return grid.toString();
    }
    
    // Helper method to set grid cells with room IDs and padding
    private void setGridCell(Map map, String[][] gridArray, Room currentRoom, String direction, int row, int col, int cellWidth) {
        Room adjacentRoom = getAdjacentRoom(map, currentRoom, direction);
        if (adjacentRoom != null) {
            gridArray[row][col] = padString(adjacentRoom.getId(), cellWidth);  // Use room ID with padding
        }
    }
    
    // Method to get adjacent rooms including diagonals
    private Room getAdjacentRoom(Map map, Room currentRoom, String direction) {
        switch (direction.toLowerCase()) {
            case "north":
                return map.getRoom(currentRoom.getExit("north") != null ? currentRoom.getExit("north").getNextRoom() : null);
            case "south":
                return map.getRoom(currentRoom.getExit("south") != null ? currentRoom.getExit("south").getNextRoom() : null);
            case "east":
                return map.getRoom(currentRoom.getExit("east") != null ? currentRoom.getExit("east").getNextRoom() : null);
            case "west":
                return map.getRoom(currentRoom.getExit("west") != null ? currentRoom.getExit("west").getNextRoom() : null);
            case "northwest":
                Room northWestRoom = getAdjacentRoom(map, currentRoom, "north");
                return northWestRoom != null ? getAdjacentRoom(map, northWestRoom, "west") : null;
            case "northeast":
                Room northEastRoom = getAdjacentRoom(map, currentRoom, "north");
                return northEastRoom != null ? getAdjacentRoom(map, northEastRoom, "east") : null;
            case "southwest":
                Room southWestRoom = getAdjacentRoom(map, currentRoom, "south");
                return southWestRoom != null ? getAdjacentRoom(map, southWestRoom, "west") : null;
            case "southeast":
                Room southEastRoom = getAdjacentRoom(map, currentRoom, "south");
                return southEastRoom != null ? getAdjacentRoom(map, southEastRoom, "east") : null;
            default:
                return null;
        }
    }
    
    // method to make sure width is uniform
    // if less than 7 grids, it will pad spaces until it reaches 7
    // if more than 7 grids, it will cut until reaches 7
    private String padString(String str, int width) {
        if (str.length() >= width) {
            return str.substring(0, width);
        }
        return str + " ".repeat(width - str.length());
    }



    // New method to display the player's score
    public String displayScore(GameState gameState) {
        Player player = gameState.getPlayer();
        return "Your score is: " + player.getScore();
    }

    // Returns a string representation of the status command, including its type and topic
    @Override
    public String toString() {
        return "Status Command for topic: " + topic;
    }
}
