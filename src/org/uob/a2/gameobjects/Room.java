package org.uob.a2.gameobjects;

import java.util.ArrayList;

/**
 * Represents a room in the game, which is a type of {@code GameObject}.
 * 
 * <p>
 * Rooms can have items, equipment, features, and exits. They also manage navigation
 * and interactions within the game world.
 * </p>
 */
public class Room extends GameObject {
    private ArrayList<Item> items; 
    private ArrayList<Equipment> equipment; 
    private ArrayList<Exit> exits; 
    private ArrayList<Feature> features; 
    private Area area;

    public Room() {
        this("Room ID", "Unnamed Room", "No description available", false);
    }

    // Declared arraylists for each parameter
    public Room(String id, String name, String description, boolean hidden) {
        super(id, name, description, hidden); 
        this.items = new ArrayList<>(); 
        this.equipment = new ArrayList<>(); 
        this.exits = new ArrayList<>(); 
        this.features = new ArrayList<>();
    }

    public Room(String id) { 
        this(id, "Unnamed Room", "No description available", false); 
    }

    public ArrayList<Exit> getExits() {
        return this.exits;
    }

    public void addExit(Exit exit) {
        this.exits.add(exit);
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public Item getItem(String id) {
        for (Item item : items) {
            if (item.getId().equalsIgnoreCase(id)) {
                return item;
            }
        }
        return null;
    }

    public Item getItemByName(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public Feature getFeatureByName(String name) {
        for (Feature feature : features) {
            if (feature.getName().equalsIgnoreCase(name)) {
                return feature;
            }
        }
        return null;
    }

    public ArrayList<Equipment> getEquipments() {
        return this.equipment;
    }

    public Equipment getEquipmentByName(String name) {
        for (Equipment equip : equipment) {
            if (equip.getName().equalsIgnoreCase(name)) {
                return equip;
            }
        }
        return null;
    }

    public Equipment getEquipment(String id) {
        for (Equipment equip : equipment) {
            if (equip.getId().equalsIgnoreCase(id)) {
                return equip;
            }
        }
        return null;
    }

    public Exit getExit(String id) {
        for (Exit exit : exits) {
            if (exit.getId().equalsIgnoreCase(id)) {
                return exit;
            }
        }
        return null;
    }
    
    public void addEquipment(Equipment equipment) {
        this.equipment.add(equipment);
    }

    public Feature getFeature(String id) {
        for (Feature feature : features) {
            if (feature.getId().equalsIgnoreCase(id)) {
                return feature;
            }
        }
        return null;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public void removeEquipment(Equipment equipment) {
        this.equipment.remove(equipment);
    }

    public ArrayList<Feature> getFeatures() {
        return this.features;
    }

    public ArrayList<GameObject> getAll() {
        ArrayList<GameObject> allObjects = new ArrayList<>();
        allObjects.addAll(items);
        allObjects.addAll(equipment);
        allObjects.addAll(exits);
        allObjects.addAll(features);
        return allObjects;
    }

    public void addFeature(Feature feature) {
        this.features.add(feature);
    }

    public boolean hasItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasFeature(String featureName) {
        for (Feature feature : features) {
            if (feature.getName().equalsIgnoreCase(featureName)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasEquipment(String name) {
        for (Equipment equip : equipment) {
            if (equip.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean getHidden() {
        return this.hidden;
    }

    public Area getArea() { 
        return area; 
    } 
    
    public void setArea(Area area) { 
        this.area = area; 
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("[").append(getId()).append("] Room: ").append(name)
           .append("\nDescription: ").append(description)
           .append("\nIn the room there are:\n");

        for (Item i : items) {
            out.append(i).append("\n");
        }
        for (Equipment e : equipment) {
            out.append(e).append("\n");
        }
        for (Feature f : features) {
            out.append(f).append("\n");
        }
        for (Exit e : exits) {
            out.append(e).append("\n");
        }

        return out.toString();
    }
}
