package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

public class Quit extends Command {

    public Quit() {
        this.commandType = CommandType.QUIT;
    }

    @Override
    public String execute(GameState gameState) {
        Player player = gameState.getPlayer();
        StringBuilder message = new StringBuilder();

        // Game-over message
        message.append("Game over:");

        // Inventory status
        if (player.getItems().isEmpty()) {
            message.append(" Your inventory is empty.");
        } else {
            message.append(" You have the following items in your inventory: ");
            for (Item item : player.getItems()) {
                message.append(item.getName().toLowerCase()).append(", ");
            }
            // Remove the last comma and space for final item/equipment
            message.setLength(message.length() - 2);
            message.append(".");
        }

        return message.toString();
    }

    @Override
    public String toString() {
        return "Quit Command";
    }
}
