package org.uob.a2.gameobjects;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Item> inventory;
    private ArrayList<Equipment> equipment;
    private Room currentRoom;
    private int score;

    public Player() {
        // Declare player name as unnamed player initially
        // The name will be updated in status player after player has typed in their name in initial conversation with professor
        this.name = "Unnamed Player";
        this.inventory = new ArrayList<>();
        this.equipment = new ArrayList<>();
        this.currentRoom = new Room();
        this.score = 0;
    }

    public Player(String name) {
        this.name = name;
        this.inventory = new ArrayList<>();
        this.equipment = new ArrayList<>();
        this.currentRoom = null; 
    }

    // Getters and setters and methods to check whether inventory has a certain item/equipment
    public String getName() {
        return name;
    }

    public void setName(String name) { 
        this.name = name; 
    }

    public ArrayList<Item> getItems() {
        return this.inventory;
    }

    public boolean hasItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public Item getItem(String itemName) {
        for (Item item : inventory) { 
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item; 
            } 
        } 
        return null;
    }

    public void addItem(Item item) {
        this.inventory.add(item);
    }

    public void removeItem(String itemName) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().equalsIgnoreCase(itemName)) {
                inventory.remove(i);
                break;
            }
        }
    }


    public ArrayList<Equipment> getEquipment() {
        return this.equipment;
    }

    public boolean hasEquipment(String equipmentName) {
        for (Equipment equip : equipment) {
            if (equip.getName().equalsIgnoreCase(equipmentName)) {
                return true;
            }
        }
        return false;
    }

    public Equipment getEquipment(String equipmentName) {
        for (Equipment equip : equipment) {
            if (equip.getName().equalsIgnoreCase(equipmentName)) {
                return equip;
            }
        }
        return null;
    }

    public Equipment getEquipmentByName(String equipmentName) { 
        for (Equipment equip : equipment) { 
            if (equip.getName().equalsIgnoreCase(equipmentName)) { 
                return equip; 
            } 
        } 
        return null; 
    }

    public void addEquipment(Equipment equipment) {
        this.equipment.add(equipment);
    }

    public Item getItemByName(String itemName) { 
        for (Item item : inventory) { 
            if (item.getName().equalsIgnoreCase(itemName)) { 
                return item; 
            } 
        } 
        return null; 
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public ArrayList<Item> getInventory() {
        return this.inventory;
    }

    // Getter for score 
    public int getScore() { 
        return score; 
    } 
    // Setter for score 
    public void setScore(int score) { 
        this.score = score; 
    } 
    // Method to increase the score by a certain amount 
    public void increaseScore(int amount) { 
        this.score += amount; 
    } 
    // Method to decrease the score by a certain amount 
    public void decreaseScore(int amount) { 
        this.score -= amount; 
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Player Name: " + this.name + "\nInventory:\n");
        for (Item i : this.inventory) {
            out.append("- ").append(i.getDescription()).append("\n");
        }
        out.append("Equipment:\n");
        for (Equipment e : this.equipment) {
            out.append("- ").append(e.getDescription()).append("\n");
        }
        return out.toString();
    }
}
