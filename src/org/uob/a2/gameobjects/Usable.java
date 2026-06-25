package org.uob.a2.gameobjects;

/**
 * Represents an interface for objects that can be used within the game.
 * 
 * <p>
 * Objects implementing this interface must define methods to manage their use
 * information and provide their name.
 * </p>
 */
public interface Usable {
    // Setter for the use information of the object
    void setUseInformation(UseInformation useInfo);

    // Getter for the use information for the object
    UseInformation getUseInformation();

    // Returns the name of the object
    String getName();

    // Method to use the object on a target within the game 
    String use(GameObject target, GameState gameState);

}

