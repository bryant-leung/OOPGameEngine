package org.uob.a2.gameobjects;

/**
 * Represents an item in the game, which is a type of {@code GameObject}.
 *
 * <p>
 * Items can be collected, used, or interacted with by the player.
 * This class inherits common properties from {@code GameObject}.
 * </p>
 */
public class Item extends GameObject {

    // Constructor for Item
    public Item(String id, String name, String description, boolean hidden) {
        super(id, name, description, hidden); // Calls to superclass (GameObject) constructor
    }

    // Extra constructor that constructs a new Item with a single string parameter ID
    public Item(String id) { 
        this(id, "Unnamed Item", "No description available", false); 
    }

    // Method to get the hidden status of the item 
    public boolean getHidden() { 
        return this.hidden;
    }
    
    /**
     * Returns a string representation of the item by calling the superclass's {@code toString} method.
     *
     * @return a string describing the item
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
