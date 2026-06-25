package org.uob.a2.commands;

import org.uob.a2.gameobjects.GameState;
import org.uob.a2.gameobjects.Room;
import org.uob.a2.gameobjects.Exit;

/**
 * Represents the help command, providing the player with instructions or information
 * about various topics related to the game.
 * 
 * <p>
 * The help command displays information on how to play the game, including details about 
 * available commands, their syntax, and their purpose.
 * </p>
 */
public class Help extends Command {
    private String topic;

    // Constructor for creating a new help command for the specified topic
    public Help(String topic) {
        this.commandType = CommandType.HELP;
        this.value = topic;
        this.topic = topic;
    }

    // Executes the help command. Provides detailed help information based on the specified topic or general game help if no specific topic is provided
    // Returns a string containing help information for the player
    @Override
    public String execute(GameState gameState) {
        StringBuilder helpMessage = new StringBuilder();

        if (topic == null || topic.isEmpty()) {
            helpMessage.append("Welcome to the game!\n")
                       .append("Available commands:\n")
                       .append("- MOVE: Use 'move <direction>' to navigate (north, south, east, west).\n")
                       .append("- LOOK: Use 'look <target>' to examine your surroundings or a specific item.\n")
                       .append("- GET: Use 'get <item>' to pick up an item.\n")
                       .append("- DROP: Use 'drop <item>' to drop an item from your inventory.\n")
                       .append("- USE: Use 'use <item> on/with <target>' to use an item.\n")
                       .append("- STATUS: Use 'status <target>' to check your status, inventory, or get details about an item.\n")
                       .append("- HELP: Use 'help <topic>' to get help on a specific topic.\n")
                       .append("- COMBINE: Use 'combine <item1> and <item2>' to combine items.\n")
                       .append("- QUIT: Use 'quit' to exit the game.\n");
        } else {
            switch (topic.toLowerCase()) {
                case "commands":
                    helpMessage.append("Available commands:\n")
                               .append("- move <exit name>: Move to a different location as defined by an exit's name (e.g., 'move north').\n")
                               .append("- look <room|exit|features>|<item name>|<equipment name>|<feature name>: Look around the current room, at an exit, at a feature, or at a specific item, equipment, or feature.\n")
                               .append("- get <item name|equipment name>: Pick up an item or equipment from the current room (e.g., 'get key').\n")
                               .append("- drop <item name|equipment name>: Drop an item or equipment from your inventory (e.g., 'drop key').\n")
                               .append("- use <equipment name> on|with <feature|item>: Use an item in your inventory on its own, or on a feature or item (e.g., 'use lamp' or 'use key on chest').\n")
                               .append("- status <inventory|player|item name|equipment name|map|score>: Check your current status, or inventory; or get more information about a specific item or equipment in your inventory (e.g., 'status player', 'status inventory', 'status key'). Also able to display the map and your score.\n")
                               .append("- help <topic>: Display this help information or get help on a specific topic (e.g., 'help move' or 'help').\n")
                               .append("- combine <item1> and <item2>: Combine two items into a new item or equipment.\n")
                               .append("- quit: Exit the game.\n")
                               .append("Possible extra commands: open chest/ Rotom \n");
                    break;
                case "move":
                    helpMessage.append("MOVE Command:\n")
                               .append("Use the 'move' command followed by a direction (north, south, east, west) to navigate.\n")
                               .append("Example: move north\n");

                    if (gameState != null && gameState.getCurrentRoom() != null) {
                        Room currentRoom = gameState.getCurrentRoom();
                        helpMessage.append("Possible exits from this room:\n");
                        for (Exit exit : currentRoom.getExits()) {
                            helpMessage.append("- ").append(exit.getDescription()).append(" (").append(exit.getId()).append(")\n");
                        }
                    } else {
                        helpMessage.append("No current room information available.\n");
                    }
                    break;
                case "look":
                    helpMessage.append("LOOK Command:\n")
                               .append("Use the 'look' command followed by a target (room, exit, features, item name, equipment name, or feature name) to examine your surroundings or a specific object.\n")
                               .append("Examples:\n")
                               .append(" - look room\n")
                               .append(" - look exit\n")
                               .append(" - look feature\n");
                    break;
                case "get":
                    helpMessage.append("GET Command:\n")
                               .append("Use the 'get' command followed by an item name or equipment name to pick it up from the current room.\n")
                               .append("Examples:\n")
                               .append(" - get pokéball\n")
                               .append(" - get rotom\n");
                    break;
                case "drop":
                    helpMessage.append("DROP Command:\n")
                               .append("Use the 'drop' command followed by an item name or equipment name to drop it from your inventory.\n")
                               .append("Examples:\n")
                               .append(" - drop pokéball\n")
                               .append(" - drop rotom\n");
                    break;
                case "use":
                    helpMessage.append("USE Command:\n")
                               .append("Use the 'use' command followed by an equipment name, optionally specifying 'on' or 'with' and a target feature or item.\n")
                               .append("Examples:\n")
                               .append(" - use key on chest\n");
                    break;
                case "status":
                    helpMessage.append("STATUS Command:\n")
                               .append("Use the 'status' command followed by a target (inventory, player, item name, equipment name, map, score) to check your current status or inventory, or get more information about a specific item or equipment.\n")
                               .append("Examples:\n")
                               .append(" - status player\n")
                               .append(" - status inventory\n")
                               .append(" - status key\n")
                               .append(" - status map\n")
                               .append(" - status score\n");
                    break;
                case "combine":
                    helpMessage.append("COMBINE Command:\n")
                               .append("Use the 'combine' command followed by two items to create a new item or equipment.\n")
                               .append("Examples:\n")
                               .append(" - combine stick and rock\n")
                               .append(" - combine egg and flour\n");
                    break;
                case "quit":
                    helpMessage.append("QUIT Command:\n")
                               .append("Use the 'quit' command to exit the game.\n");
                    break;
                default:
                    helpMessage.append("No help available for the topic: " + topic + "\n")
                               .append("Available topics: commands, move, look, get, drop, use, status, combine, quit.\n");
                    break;
            }
        }

        return helpMessage.toString();
    }

    // Returns a string representation of the help command, including its type and topic
    @Override
    public String toString() {
        return "HELP command for: " + (topic != null ? topic : "null");
    }
}
