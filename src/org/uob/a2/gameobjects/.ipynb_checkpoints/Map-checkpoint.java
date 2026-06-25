package org.uob.a2.gameobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.nio.file.*;

/**
 * Represents the game map, which consists of a collection of rooms and the current room the player is in.
 * 
 * <p>
 * The map allows for navigation between rooms, adding new rooms, and managing the current room context.
 * </p>
 */
public class Map {
    private ArrayList<Room> rooms; // List to store rooms
    private HashMap<String, Room> roomMap; // Map to access rooms by ID
    private Room currentRoom; // Room player is currently in
    private String name;

    // Constructor for map to construct a new empty map
    public Map() {
        this.rooms = new ArrayList<>();
        this.roomMap = new HashMap<>();
        this.currentRoom = null; // Initialize current room as null
    }

    // Adds a room to the map.
    public void addRoom(Room room) {
        rooms.add(room);
        roomMap.put(room.getId(), room);
    }

    // Retrieves the current room the player is in.
    public Room getCurrentRoom() {
        return currentRoom;
    }

    // Sets the current room based on the provided room ID.
    public void setCurrentRoom(String roomId) {
        Room room = roomMap.get(roomId);
        if (room != null) {
            currentRoom = room;
        } else {
            System.out.println("Room with ID " + roomId + " is not found");
        }
    }

    // Retrieves a room by its ID.
    public Room getRoom(String roomId) {
        return roomMap.get(roomId);
    }

    // Add rooms using id
    public ArrayList<String> getRoomIds() { 
        ArrayList<String> roomIds = new ArrayList<>(); 
        for (Room room : rooms) { 
            roomIds.add(room.getId()); 
        } 
        return roomIds; 
    }

    // Method to retrieve all rooms
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    // Getter for name
    public String getName() { 
        return name; 
    } 

    // Setter for name
    public void setName(String name) { 
        this.name = name; 
    }

    /**
     * Returns a string representation of the map, including all rooms.
     *
     * @return a string describing the map and its rooms
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Map:\n");
        for (Room r : this.rooms) {
            out.append(r.toString()).append("\n");
        }
        return out.toString();
    }
}
