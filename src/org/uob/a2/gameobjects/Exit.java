package org.uob.a2.gameobjects;

/**
 * Represents an exit in the game, allowing the player to move from one room to another.
 * 
 * <p>
 * Exits have a destination (next room), a description, and can be hidden or visible based on game logic.
 * </p>
 */
public class Exit extends GameObject {
    private String nextRoom;
    private boolean hidden; // Added hidden attribute

    // Constructor for creating an Exit object
    public Exit(String id, String name, String description, String nextRoom, boolean hidden) {
        super(id, name, description, hidden);
        this.nextRoom = nextRoom;
        this.hidden = hidden;
    }

    // Retrieves the identifier of the next room
    public String getNextRoom() {
        return nextRoom;
    }

    // Checks if the exit is accessible (not hidden)
    public boolean isAccessible() {
        return !hidden;
    }

    // Setter for the hidden status of the exit
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    // Getter for the hidden status of the exit
    public boolean getHidden() {
        return hidden;
    }

    /**
     * Returns a string representation of the exit, including attributes inherited from {@code GameObject}
     * and the identifier of the next room.
     *
     * @return a string describing the exit
     */
    @Override
    public String toString() {
        return "GameObject {id='" + getId() + "', name='" + getName() + "', description='" + getDescription() + "', hidden=" + hidden + "}, nextRoom=" + nextRoom;
    }
}
