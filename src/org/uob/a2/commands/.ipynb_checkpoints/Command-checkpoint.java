package org.uob.a2.commands;

import org.uob.a2.gameobjects.GameState;

/**
 * Represents an abstract command that can be executed within the game.
 * 
 * <p>
 * Subclasses should define specific types of commands and their behavior by 
 * implementing the {@link #execute(GameState)} method.
 * </p>
 */
public abstract class Command {
    public CommandType commandType;
    public String value;

    // Constructor initializing default values as blank
    public Command() {
        this.commandType = null;
        this.value = "";
    }

    // Method to execute the command using the provided game state, and returns a string describing the outcome of the command execution
    public abstract String execute(GameState gameState);
}
