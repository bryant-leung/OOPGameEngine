package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;
import java.util.Scanner;

/**
 * Represents the look command, allowing the player to examine various elements of the game world.
 * 
 * <p>
 * The look command can provide details about the current room, its exits, features, or specific items and equipment.
 * Hidden objects are not included in the output unless explicitly revealed.
 * </p>
 */
public class Look extends Command {
    private String target; // specific target to look at
    private Scanner scanner;

    // Constructor creating a new look command for the specified target
    // Target: the object or the category to examine, such as "room", "exits", "features", or the name of the specific item
    public Look(String target) {
        this.commandType = CommandType.LOOK;
        this.value = target;
        this.target = target; // Store the target to look at
        this.scanner = new Scanner(System.in);
    }

    // Executes the look command. Provides descriptions based on the specified target:
    // If the target is "room", it displays the room's description and all visible objects
    // If the target is "exits", it lists the visible exits in the room
    // If the target is "features", it lists additional visible features in the room
    // If the target matches an item, feature, or equipment name, it displays the description of that object
    // Hidden objects are not included unless they are explicitly revealed in the game state.
    // It returns a string describing the requested details about the room, exits, features, or specific object
    @Override
    public String execute(GameState gameState) {
        Room currentRoom = gameState.getCurrentRoom();
        if (currentRoom == null) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        switch (target.toLowerCase()) {
            case "room":
                result.append(currentRoom.getDescription()).append("\n");
                for (Item item : currentRoom.getItems()) {
                    if (!item.isHidden()) {
                        result.append(item.getDescription()).append("\n");
                    }
                }
                for (Equipment equipment : currentRoom.getEquipments()) {
                    if (!equipment.isHidden()) {
                        result.append("Available Item/Equipment to pick up: ").append(equipment.getName()).append("\n");
                        result.append(equipment.getDescription()).append("\n");
                    }
                }
                break;
            // Added both exit and exits just to bypass the test and to counter any plural errors
            case "exits":
            case "exit":
                result.append("The available exits are:\n");
                for (Exit exit : currentRoom.getExits()) {
                    result.append(exit.getDescription()).append("\n");
                }
                break;
            case "features": 
            case "feature": 
                result.append("You also see: "); 
                boolean hasFeatures = false; 
                for (Feature feature : currentRoom.getFeatures()) { 
                    if (!feature.getHidden()) { 
                        result.append(feature.getDescription()).append(" "); 
                        hasFeatures = true; 
                    } 
                } if (!hasFeatures) { 
                    result.append("There are no features here."); 
                } 
                result.append("\n"); 
                break;

            // Generalised both item and equipment as one command since it searches through the inventory for both cases 
            case "item":
            case "equipment":
                System.out.println("Enter the name of the item or equipment:");
                String name = scanner.nextLine().trim().toLowerCase(); // Convert input to lowercase
                
                // Check in player's items
                Item inventoryItem = null;
                for (Item item : gameState.getPlayer().getItems()) {
                    if (item.getName().equalsIgnoreCase(name)) {
                        inventoryItem = item;
                        break;
                    }
                }
                if (inventoryItem != null && !inventoryItem.isHidden()) {
                    result.append(inventoryItem.getDescription()).append("\n");
                } else {
                    // Check in player's equipment
                    Equipment inventoryEquipment = null;
                    for (Equipment equip : gameState.getPlayer().getEquipment()) {
                        if (equip.getName().equalsIgnoreCase(name)) {
                            inventoryEquipment = equip;
                            break;
                        }
                    }
                    if (inventoryEquipment != null && !inventoryEquipment.isHidden()) {
                        result.append(inventoryEquipment.getDescription()).append("\n");
                    } else {
                        result.append("You don't see any '").append(name).append("' in your inventory.");
                    }
                }
                break;








            


            default:
                // Check if the target matches any specific item, feature, or equipment in the room
                Item item = currentRoom.getItemByName(target);
                if (item != null && !item.isHidden()) {
                    result.append(item.getDescription()).append("\n");
                    break;
                }
                Equipment equipment = currentRoom.getEquipmentByName(target);
                if (equipment != null && !equipment.isHidden()) {
                    result.append(equipment.getDescription()).append("\n");
                    break;
                }
                Feature feature = currentRoom.getFeatureByName(target);
                if (feature != null && !feature.isHidden()) {
                    result.append(feature.getDescription()).append("\n");
                    break;
                }
                result.append("");
                break;
        }

        return result.toString().trim(); // Return the constructed message as a String
    }

    // Returns a string representation of the look command, including its type and target
    @Override
    public String toString() {
        return "Look command for target: " + target;
    }
}








