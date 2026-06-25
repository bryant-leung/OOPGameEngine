package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;
import org.uob.a2.Game;  // Import the Game class

public class Move extends Command {
    private String direction;
    private Game game;  // Add a Game instance

    // Creates a new Move command for the specified direction
    public Move(String direction) {
        this.commandType = CommandType.MOVE;
        this.value = direction;
        this.direction = direction;
        this.game = null;  // Initialize with null
    }

    // Overloaded constructor with Game instance
    public Move(String direction, Game game) {
        this.commandType = CommandType.MOVE;
        this.value = direction;
        this.direction = direction;
        this.game = game;  // Initialize the Game instance
    }

    public String getDirection() { 
        return direction; 
    }

    @Override
    public String execute(GameState gameState) {
        Room currentRoom = gameState.getCurrentRoom();

        // Ensure the current room is not null
        if (currentRoom == null) {
            return "You are not in a valid room.";
        }

        Exit exit = currentRoom.getExit(direction);

        // Check if the exit exists and is accessible
        if (exit == null) {
            return "No exit found in that direction.";
        }

        // Check if the exit is hidden
        if (exit.getHidden()) {
            return "No exit found in that direction.";
        }

        Room nextRoom = gameState.getMap().getRoom(exit.getNextRoom());

        // Ensure the next room is not null
        if (nextRoom == null) {
            return "The exit leads to an unknown room.";
        }

        gameState.getMap().setCurrentRoom(nextRoom.getId());

        // Trigger Pokémon encounter using the Game instance when game starts
        if (game != null) {
            game.triggerPokemonEncounter();
        }

        // Return the exact expected message
        return "Moving towards " + direction + "\n";
    }

    @Override
    public String toString() {
        return "Move " + direction;
    }
}
