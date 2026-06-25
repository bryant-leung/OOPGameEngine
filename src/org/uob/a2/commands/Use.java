package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the use command, allowing the player to use an item on a target in the game world.
 *
 * <p>
 * The use command enables interactions between the player, items, and targets, facilitating game mechanics such as unlocking, activating, or combining items.
 * </p>
 */

public class Use extends Command {
    private String equipmentName;
    private String targetName;

    public Use(String equipmentName, String targetName) {
        this.commandType = CommandType.USE;
        this.value = equipmentName + " on " + targetName;
        this.equipmentName = equipmentName;
        this.targetName = targetName;
    }

    @Override
    public String execute(GameState gameState) {
        Player player = gameState.getPlayer();

        // Check if the player possesses the equipment
        if (!player.hasEquipment(equipmentName)) {
            return "You do not have " + equipmentName;
        }

        Equipment equipment = player.getEquipment(equipmentName);

        // Check if the equipment has already been used
        if (equipment.getUseInformation().isUsed()) {
            return "You have already used " + equipmentName;
        }

        Room currentRoom = gameState.getCurrentRoom();
        if (currentRoom == null) {
            return "You are not in a valid room.";
        }


        // Find the target object in the room
        GameObject targetObject = currentRoom.getFeatureByName(targetName);
        if (targetObject == null) {
            targetObject = currentRoom.getItemByName(targetName);
            if (targetObject == null) {
                return "Invalid use target";
            }
        }


        // Use the equipment on the target
        UseInformation useInfo = equipment.getUseInformation();
        if (!useInfo.getTarget().equals(targetObject.getId())) {
            return "Invalid use target";
        }

        String actionResult = equipment.use(targetObject, gameState);

        // Mark the equipment as used if the action was successful
        if (actionResult.contains(useInfo.getMessage())) {
            useInfo.setUsed(true);
        }

        return actionResult;
    }

    @Override
    public String toString() {
        return "Use command for: " + equipmentName + " on " + targetName;
    }
}
