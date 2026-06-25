package org.uob.a2.utils;

import org.uob.a2.gameobjects.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class GameStateFileParser {
    public GameStateFileParser() {
    }

    public static GameState parse(String filename) {
        GameState gameState = new GameState();
        Map map = new Map();
        Player player = new Player();
        Room currentRoom = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length < 2) {
                    continue;
                }

                String key = parts[0].trim();
                String value = parts[1].trim();

                switch (key.toLowerCase()) {
                    case "player":
                        player = parsePlayer(value);
                        break;
                    case "map":
                        break;
                    case "room":
                        currentRoom = parseRoom(value);
                        map.addRoom(currentRoom);
                        if (map.getCurrentRoom() == null) {
                            map.setCurrentRoom(currentRoom.getId());
                        }
                        break;
                    case "area":
                        if (currentRoom != null) {
                            try {
                                currentRoom.setArea(Area.valueOf(value.trim().toUpperCase()));
                            } catch (IllegalArgumentException e) {
                                System.err.println("Invalid Area value: " + value.trim());
                            }
                        } else {
                            System.err.println("Error: 'area' specified without a current room context.");
                        }
                        break;
                    case "item":
                        if (currentRoom != null) {
                            Item item = parseItem(value);
                            if (item != null) {
                                currentRoom.addItem(item);
                            }
                        }
                        break;
                    case "equipment":
                        if (currentRoom != null) {
                            Equipment equipment = parseEquipment(value);
                            if (equipment != null) {
                                currentRoom.addEquipment(equipment);
                            }
                        }
                        break;
                    case "container":
                        if (currentRoom != null) {
                            Container container = parseContainer(value);
                            currentRoom.addFeature(container);
                        }
                        break;
                    case "exit":
                        if (currentRoom != null) {
                            Exit exit = parseExit(value);
                            currentRoom.addExit(exit);
                        }
                        break;
                    default:
                        if (currentRoom != null && !key.equalsIgnoreCase("area")) {
                            System.err.println("Unknown key or incorrect format: " + key);
                        }
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameState.setMap(map);
        gameState.setPlayer(player);
        return gameState;
    }

    // Different parsing methods for specific parameters start here
    private static Room parseRoom(String data) {
        String[] parts = data.split(",");
        if (parts.length == 4) {
            return new Room(parts[0].trim(), parts[1].trim(), parts[2].trim(), Boolean.parseBoolean(parts[3].trim()));
        } else if (parts.length == 5) {
            Room room = new Room(parts[0].trim(), parts[1].trim(), parts[2].trim(), Boolean.parseBoolean(parts[3].trim()));
            try {
                room.setArea(Area.valueOf(parts[4].trim().toUpperCase()));
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid Area value in room: " + data);
            }
            return room;
        } else {
            System.err.println("Invalid room format: " + data);
            return null;
        }
    }

    private static Player parsePlayer(String data) {
        return new Player(data.trim());
    }

    private static Item parseItem(String data) {
        String[] parts = data.split(",");
        if (parts.length >= 4) {
            return new Item(parts[0].trim(), parts[1].trim(), parts[2].trim(), Boolean.parseBoolean(parts[3].trim()));
        } else {
            System.err.println("Invalid item format: " + data);
            return null;
        }
    }
    
    private static Equipment parseEquipment(String data) {
        String[] parts = data.split(",");
        if (parts.length >= 9) {  // Ensure the length matches the format
            UseInformation useInfo = new UseInformation(
                Boolean.parseBoolean(parts[4].trim()), parts[5].trim(), parts[6].trim(), parts[7].trim(), parts[8].trim()
            );
            return new Equipment(parts[0].trim(), parts[1].trim(), parts[2].trim(), Boolean.parseBoolean(parts[3].trim()), useInfo);
        } else {
            System.err.println("Invalid equipment format: " + data);
            return null;
        }
    }
    
    private static Container parseContainer(String data) {
        String[] parts = data.split(",");
        if (parts.length >= 4) {
            Container container = new Container(parts[0].trim(), parts[1].trim(), parts[2].trim(), Boolean.parseBoolean(parts[3].trim()));
    
            List<Item> items = new ArrayList<>();
            List<Equipment> equipmentList = new ArrayList<>();
    
            for (int i = 4; i < parts.length; i++) {
                String[] itemParts = parts[i].split(",");
                if (itemParts.length >= 4) {
                    if (itemParts[0].equalsIgnoreCase("equipment")) {
                        Equipment equipment = parseEquipment(String.join(",", itemParts));
                        if (equipment != null) {
                            equipmentList.add(equipment);
                        }
                    } else {
                        Item item = parseItem(String.join(",", itemParts));
                        if (item != null) {
                            items.add(item);
                        }
                    }
                }
            }
    
            container.setItems(items);
            container.setEquipment(equipmentList);
            return container;
        } else {
            System.err.println("Invalid container format: " + data);
            return null;
        }
    }


    private static Exit parseExit(String data) {
        String[] parts = data.split(",");
        if (parts.length >= 5) {
            return new Exit(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(), Boolean.parseBoolean(parts[4].trim()));
        } else {
            System.err.println("Invalid exit format: " + data);
            return null;
        }
    }
}
