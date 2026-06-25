package org.uob.a2.gameobjects;

public class GameState {
    private Map map;
    private Player player;
    private Room currentRoom;

    // Default constructor leaves attributes as null
    public GameState() {
        this.map = null;
        this.player = null;
        this.currentRoom = null;
    }

    // Parameterized constructor initializes the attributes
    public GameState(Map map, Player player) {
        this.map = map;
        this.player = player;
        this.currentRoom = map.getCurrentRoom();
    }


    // Method to set Current Room
    public void setCurrentRoom(Room room) {
        if (map != null) {
            map.setCurrentRoom(room.getId());
        }
        this.currentRoom = room;
    }

    public void setMap(Map map) {
        this.map = map;
        this.currentRoom = map.getCurrentRoom(); // Sync current room with map
    }

    // Getter for map
    public Map getMap() {
        return map;
    }

    // Getter for currentRoom
    public Room getCurrentRoom() {
        return map.getCurrentRoom();
    }

    // Setter for player
    public void setPlayer(Player player) {
        this.player = player;
    }

    // Getter for player
    public Player getPlayer() {
        return player;
    }

    // Additional Method to useEquipmentOnTarget
    public String useEquipmentOnTarget(String equipmentName, String targetName) {
        Equipment equipment = player.getEquipment(equipmentName);
        if (equipment == null) {
            return "You don't have the equipment: " + equipmentName;
        }

        GameObject target = currentRoom.getFeatureByName(targetName);
        if (target == null) {
            target = currentRoom.getItemByName(targetName);
            if (target == null) {
                return "There is no " + targetName + " here.";
            }
        }

        return equipment.use(target, this);
    }

    // Method to get the current room's name and description 
    public String getCurrentRoomDescription() { 
        Room currentRoom = getCurrentRoom(); 
        if (currentRoom != null) { 
            return "You are now in: " + currentRoom.getName() + " (ID: " + currentRoom.getId() + ")\n" + currentRoom.getDescription(); 
        } 
        return "You are not in a valid room."; 
    }

    @Override
    public String toString() {
        return "GameState {" +
               "map=" + (map != null ? map.toString() : "null") + ", " +
               "player=" + (player != null ? player.toString() : "null") +
               '}';
    }
}
