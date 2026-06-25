package org.uob.a2.commands;

/**
 * Represents the various types of commands that can be executed in the game.
 * 
 * <p>
 * Each command type corresponds to a specific player action or game functionality.
 * </p>
 */

// Decided to stick with the test and not add COMBINE or other extra commands here
public enum CommandType {
    MOVE("Move the player to a different location"),
    USE("Use an item or interact with a game object"),
    GET("Pick up an item"),
    DROP("Drop an item from the inventory"),
    LOOK("Look around or inspect an object"),
    STATUS("Check the player's current status"),
    HELP("Display help information"),
    QUIT("Quit the game");

    private final String description;

    // Constructor that accepts a description 
    CommandType(String description) { 
        this.description = description; 
    } 
    
    // Method to get the description of the command type 
    public String getDescription() { 
        return description; 
    }
}
