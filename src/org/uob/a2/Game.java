package org.uob.a2; 

import java.util.Scanner; 
import java.util.List; 
import java.util.ArrayList;
import org.uob.a2.commands.*; 
import org.uob.a2.gameobjects.*; 
import org.uob.a2.parser.*; 
import org.uob.a2.utils.*; 
/** * 
Main class for the game application. Handles game setup, input parsing, and game execution. 
* 
* <p>
* 
This class initializes the game state, reads user input, processes commands, and maintains the game loop. * 
</p> 
*/



public class Game {
    private GameState gameState;
    private Scanner scanner;
    private Tokeniser tokeniser;
    private Parser parser;
    private boolean isGameStarted;
    private ItemManager itemManager;
    private Rotom rotom;
    private Player player; 
    private Room currentRoom;
    private FishingMiniGame fishingMiniGame;
    private boolean fishingGameNotCompleted;
    private boolean diamondChestOpened = false;
    private boolean pierQuestTriggered = false;
    private boolean gatherCluesStarted = false; 
    private boolean interactingWithNPCs = false; 
    private boolean zigzagoonFound = false;
    private boolean pierQuestCompleted = false;
    private boolean rubyChestOpened = false;
    
    public Game() {
        this.gameState = new GameState();
        this.scanner = new Scanner(System.in);
        this.tokeniser = new Tokeniser();
        this.isGameStarted = false;
        this.itemManager = new ItemManager(); 
        this.rotom = new Rotom(itemManager);
        this.parser = new Parser(this);
        this.player = new Player("TestPlayer");  // Initialize player object
        currentRoom = new Room("OS","Oakman's Shed","The beginning of everything. This is where you met Professor Oakman.",false);
        this.fishingMiniGame = new FishingMiniGame();
        this.fishingGameNotCompleted = true;
    }

    public void initializeGameWorld() {
        if (!isGameStarted) {
            try {
                String filePath = "data/game.txt"; 
                GameState gameState = GameStateFileParser.parse(filePath);
                this.gameState = gameState;
                this.player = gameState.getPlayer();
                gameState.getMap().setCurrentRoom("OS"); 
    
                // Add items to the Diamond Chest in room MFD
                Room roomMFD = gameState.getMap().getRoom("MFD");
                if (roomMFD != null) {
                    Container diamondChest = (Container) roomMFD.getFeatureByName("Diamond Chest");
                    if (diamondChest != null) {
                        List<Item> items = new ArrayList<>();
                        items.add(new Item("TORN_NOTE", "TornNote", "A note that displays Meadows and Fields -- two of a kind.", true));
                        diamondChest.setItems(items);
                
                        List<Equipment> equipment = new ArrayList<>();
                        equipment.add(new Equipment("DIAMOND_KEY", "DiamondKey", "A diamond key with delicate carvings.", true, new UseInformation(false, "open", "RC", "ruby chest unlocked", "You have unlocked the ruby chest!")));
                        diamondChest.setEquipment(equipment);
                    }
                }
    
                // Add the Ruby Chest to room FMB
                Room roomFMB = gameState.getMap().getRoom("FMB");
                if (roomFMB != null) {
                    Container rubyChest = (Container) roomFMB.getFeatureByName("Ruby Chest");
                    if (rubyChest == null) {
                        rubyChest = new Container("RC", "RubyChest", "A chest made out of ruby. You might need a certain key to unlock it.", true);
                        roomFMB.addFeature(rubyChest);
                    }
                    
                    List<Item> rubyItems = new ArrayList<>();
                    rubyItems.add(new Item("YANG_RELIC", "RelicYANG", "An unique relic has a carving RelicYANG that seems to be missing a piece.", false));
                    rubyChest.setItems(rubyItems);
                }


            } catch (Exception e) {
                System.err.println("Error initializing game world: " + e.getMessage());
                return;
            }
            isGameStarted = true;
            initiateIntroConvo();
            triggerPokemonEncounter();
        }
    }

    // Methods to get and set the current room using the map from gameState 
    public Room getCurrentRoom() { 
        return gameState.getMap().getCurrentRoom(); 
    } 

    public void setCurrentRoom(String roomId) { 
        gameState.getMap().setCurrentRoom(roomId); 
    }

    public void movePlayer(String direction) {
        if (!isValidDirection(direction)) {
            System.out.println("Invalid direction. Please enter north, south, east, or west.");
            return;
        }
        
        Room currentRoom = getCurrentRoom();
        boolean validDirection = false;
        
        for (Exit exit : currentRoom.getExits()) {
            if ((exit.getDescription().toLowerCase().contains(direction.toLowerCase()) || exit.getId().toLowerCase().contains(direction.toLowerCase()))
                    && exit.isAccessible()) {
                setCurrentRoom(exit.getNextRoom());
                System.out.println("You have moved " + direction + " to " + getCurrentRoom().getName());
                displayMap();
                validDirection = true;
                triggerPokemonEncounter();
                break;
            }
        }
        
        if (!validDirection) {
            System.out.println("You can't move in that direction. Please try another direction.");
        }
    }
    
    private boolean isValidDirection(String direction) {
        return direction.equals("north") || direction.equals("south") || direction.equals("east") || direction.equals("west");
    }

    public void displayMap() {
        Room currentRoom = getCurrentRoom();
    }

    public void run() {
        System.out.println("Welcome to the Pokemon -- Shores of Discovery by Bryant Leung!");
        System.out.println("Type 'start game' to begin.");
    
        String commandText = ""; 
    
        // Loop to wait for "start game" input
        while (true) {
            System.out.print("> ");
            commandText = scanner.nextLine();
            if (commandText.equalsIgnoreCase("start game")) {
                System.out.println("The game has started! Welcome to Shores of Discovery!");
                initializeGameWorld();
                break;
            } else {
                System.out.println("Please type 'start game' to begin.");
            }
        }
    
            boolean gameRunning = true;
            while (gameRunning) {
                System.out.print("> ");
                commandText = scanner.nextLine();
            
                if (isValidDirection(commandText)) {
                    Move moveCommand = new Move(commandText, this);  // Pass the Game instance
                    String result = moveCommand.execute(gameState);
                    System.out.println(result);
            
            
                    checkForContainers(gameState.getCurrentRoom()); 
            
                } else if (commandText.equalsIgnoreCase("rotom")) {
                    displayPokemonInventory();
                } else if (commandText.equalsIgnoreCase("open chest")) {
                    openChest(gameState.getCurrentRoom());
                } else if (commandText.equalsIgnoreCase("combine")) {
                    Combine combineCommand = new Combine();
                    String result = combineCommand.execute(gameState);
                    System.out.println(result);
                } else if (commandText.equalsIgnoreCase("enter gym")) {
                    enterGym();
                    System.out.println("You have entered the Gym. You have completed Shores of Discovery!");
                    System.exit(0);
                } else {
                    String result = processCommand(commandText);
                    System.out.println(result);
                }
            
                // Check if the current command was "quit" to end the game loop
                if (commandText.equalsIgnoreCase("quit")) {
                    gameRunning = false;
                }
            
                if ("ES".equals(getCurrentRoom().getId())) {
                    EquipmentShop shop = new EquipmentShop(player, scanner);
                    shop.purchaseItem();
                }
    
                if (!diamondChestOpened && gameState.getCurrentRoom().getId().equals("MFD")) {
                    System.out.println("You suddenly stumbled upon a chest coated in blue turquoise colour here.");
                    }
                
                if (diamondChestOpened && !rubyChestOpened && gameState.getCurrentRoom().getId().equals("FMB")) {
                    System.out.println("The moment you arrive here, a Ruby-Red chest slowly rises up from the platform in the ground.");
                    System.out.println("Perhaps this is where the torn note you obtained just now was pointing to.");
                    System.out.println("Try using your DiamondKey on RubyChest");
                    rubyChestOpened = true;
                }
            
                if (getCurrentRoom().getId().equals("TB") && fishingGameNotCompleted) {
                    startFishingMiniGame();
                }
            
                // Quest method calls following the same format
                if (getCurrentRoom().getId().equals("P") && !pierQuestTriggered) {
                    triggerPierQuest();
                    pierQuestTriggered = true;
                } else if (pierQuestTriggered && !gatherCluesStarted) {
                    gatherClues();
                    gatherCluesStarted = true;
                } else if (pierQuestTriggered && gatherCluesStarted && !interactingWithNPCs && !zigzagoonFound) {
                    interactWithNPCs();
                } else if (pierQuestTriggered && gatherCluesStarted && interactingWithNPCs && !zigzagoonFound) {
                    // Ensure the NPC interactions have led to the Zigzagoon being found
                    if (getCurrentRoom().getId().equals("MWRA")) {
                        findZigzagoon();
                        zigzagoonFound = true;
                    }
                } else if (pierQuestTriggered && zigzagoonFound && !pierQuestCompleted && getCurrentRoom().getId().equals("P")) {
                    returnToPier();
                    pierQuestCompleted = true;
                }
            }
    
        System.out.println("Though your data will not be saved, you can always start all over again! See you!");
    }       

    private void checkForContainers(Room room) {
        if ("MFD".equals(getCurrentRoom().getId())) {
            System.out.println("You see a Diamond Chest here.");
        }
    }

    private void openChest(Room room) {
        if ("MFD".equals(getCurrentRoom().getId())) {
            if (!diamondChestOpened) {
                for (Feature feature : room.getFeatures()) {
                    if (feature instanceof Container) {
                        System.out.println(((Container) feature).openContainer());
                        diamondChestOpened = true; // Set the flag to true once the chest is opened
                    }
                }
            } else {
                System.out.println("The Diamond Chest is already opened.");
            }
        } else {
            System.out.println("There is no chest to open here.");
        }
    }

    private void displayPokemonInventory() { 
        rotom.displayPokemonInventory(player.getInventory()); 
    }

    public String processCommand(String commandText) {
        // Step 1: Tokenize the user input, splitting it into different commands, variables, or instructions
        List<Token> tokens = tokeniser.tokenise(commandText);
    
        try {
            // Step 2: Parse the command-related tokens into a Command object
            Command command = parser.parse(tokens);
    
            // Step 3: Execute the parsed Command object with the current game state
            String result = command.execute(gameState);
    
            // Step 4: If the command is a Move command, print the current room's name and description
            if (command instanceof Move) {
                String direction = ((Move) command).getDirection();
                System.out.println(gameState.getCurrentRoomDescription());
            }
    
            // Return the result of the command execution
            return result;
        } catch (CommandErrorException e) {
            // Step 5: Error handling - return the error message if a CommandErrorException is thrown
            return e.getMessage();
        }
    }
    
        private void initiateIntroConvo() { 
        System.out.println("\"Oh you're finally awake.\""); 
        System.out.println("\"Greetings, traveler,\" the figure says with a warm smile. \"I am Professor Oakman, and I just so happened to find you laid down unconscious at the forest down south from here.\"");
        System.out.println("\"If I may, what is your name?\""); 
        String playerName = scanner.nextLine(); 
        System.out.println("\"Ah, it's a pleasure to meet you, " + playerName + ".\""); 
        System.out.println("\"I don't know how you ended up here, but for now, welcome to Solaris, where humans and Pokémon intertwine and form a bustling society!\"");
        System.out.println("\"Now that you've ended up here, let me give you some items that might be useful for you throughout your journey.\"");
        System.out.println("\"This is Rotom, a Pokédex-Typed Pokémon. Rotom can store the data on what Pokémon you have currently.\"");

            this.player = new Player(playerName);
            gameState.setPlayer(player);


        // Add Rotom to the player's inventory
        gameState.getPlayer().getInventory().add(itemManager.getItemById("rotom"));

        // Using the existing Rotom instance to display the player's Pokémon inventory
        rotom.displayPokemonInventory(gameState.getPlayer().getInventory());
        System.out.println("\"Haha, I guess Rotom does identify itself as a Pokémon as well!\"");

        System.out.println("\"Here are some Pokéballs as well, these Pokéballs are used to capture Pokémon, which you can then use to battle other Pokémon!\"");

        // Adding Pokéballs to the player's inventory after the conversation
        // Adding 5 Pokéballs to the player's inventory  
        for (int i = 0; i < 10; i++) { 
            gameState.getPlayer().getInventory().add(itemManager.getItemById("pokeball"));
        }

        System.out.println("\"Feel free to check your inventory anytime through <status inventory> to see what items you currently possess!\"");
        System.out.println("\"The currency we use here is called points, we can use points during transactions. Here is 100 points to start you off!\"");
            System.out.println("This whole world is shrouded with mysteries, and it is up to you traveller whether you want to roam around and explore! Obtain the secret relics and discover the darkest secrets in this world!");
            System.out.println("Before you leave, this is something left by a previous traveller and I thought it might be useful for you to grasp your first move.");
                System.out.println("MF:                              /\\         /\\ X \n" +
                   "                                /  \\         // \\\\\n" +
                   "                               /    \\       //  \\\\\n" +
                   "                                 /\\   /||\\    /  \\\n" +
                   "                               /  \\  //||\\\\  //\\\\  \n" +
                   "                              /    \\// || \\\\/    \\  \n" +
                   "                                                    \n" +
                   "                       ******          ******        ******\n" +
                   "                    **********     **********    **********\n" +
                   "               **************** **************** *************\n" +
                   "                 ***      ***     ***    ***     ***   ***\n");
            System.out.println("Alright then, best of luck with your adventures!");



        gameState.getPlayer().increaseScore(100);
    }

    public void triggerPokemonEncounter() {
    Room currentRoom = getCurrentRoom();
    Area currentArea = currentRoom.getArea();

    if (currentArea == null) {
        System.out.println("No area specified for this room. Cannot spawn Pokémon.");
        return;
    }

    if (currentArea == Area.MISC) {
        System.out.println("No Pokémon will spawn in this area.");
        return;
    }

    Pokemon wildPokemon = WildEncounter.encounter(currentArea);

    if (wildPokemon != null) {
        System.out.println("A wild " + wildPokemon.getName() + " appeared!");

        boolean hasPokeball = player.hasItem("Pokéball");
        boolean hasUltraball = player.hasItem("Ultraball");

        if (!hasPokeball && !hasUltraball) {
            System.out.println("No balls in inventory currently, unfortunately this Pokémon has run away.");
            return;
        }

        System.out.println("Do you want to try catching it with a Pokéball or Ultra Ball? (pokeball/ultraball):");
        String ballType = scanner.nextLine().trim().toLowerCase();

        boolean caught = false;
        if ("pokeball".equals(ballType) && hasPokeball) {
            caught = CatchAttempt.attemptCatch(wildPokemon);
            player.removeItem("Pokéball");
        } else if ("ultraball".equals(ballType) && hasUltraball) {
            caught = CatchAttempt.attemptCatchWithUltraBall(wildPokemon);
            player.removeItem("Ultraball");
        } else if ("pokeball".equals(ballType) && !hasPokeball) {
            System.out.println("No Pokéballs in inventory.");
        } else if ("ultraball".equals(ballType) && !hasUltraball) {
            System.out.println("No Ultraballs in inventory.");
        } else {
            System.out.println("Invalid choice or no balls in inventory.");
        }

        if (caught) {
            System.out.println("You caught " + wildPokemon.getName() + "!");
            player.addItem(new Item(wildPokemon.getName(), wildPokemon.getName(), "A wild " + wildPokemon.getName(), false));
        } else {
            System.out.println(wildPokemon.getName() + " escaped!");
        }
    } else {
        System.out.println("No Pokémon appeared this time.");
    }
}
    
    public void startFishingMiniGame() {
        System.out.println("You arrive next to a lake, the people here seem to be hiding something from the lake.");
        System.out.println("The sign next to the lake simply says 'Stay away from the lake'");
        System.out.println("Side Quest <What are the locals hiding> initiated");
        while (!fishingMiniGame.isCompleted()) {
            System.out.println("Type 'cast' to cast your rod and fish.");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("cast")) {
                FishingResult result = fishingMiniGame.fish();
                switch (result) { 
                    case NOTHING: 
                        System.out.println("You caught nothing."); 
                        break; 
                    case MAGIKARP: 
                        System.out.println("You caught a Magikarp!");
                        System.out.println("But this isn't something worth hiding now is it. Lets release it back to its natural habitat.");
                        break; 
                    case STARYU: 
                        System.out.println("You caught a Staryu!");
                        System.out.println("But this isn't something worth hiding now is it. Lets release it back to its natural habitat.");
                        break; 
                    case YIN_RELIC: 
                        System.out.println("This rod casting felt somewhat heavy, with no movement within the water."); 
                        System.out.println("In no time, you hurriedly reeled out a white comma-shaped relic.");
                        gameState.getPlayer().addItem(new Item("Yin", "RelicYIN", "An unique relic that seems to be missing a piece.", false)); 
                        gameState.getPlayer().increaseScore(30); 
                        fishingGameNotCompleted = false; 
                        fishingMiniGame.setCompleted(true); // Game completed
                        break; 
                }
            } else {
                System.out.println("Invalid command, type 'cast' to fish.");
                    }
        }
        System.out.println("Congratulations you have completed the side quest <What are the locals hiding>");
    }

    public void triggerPierQuest() {
        System.out.println("As you arrive at the Pier, you see chaos everywhere.");
        System.out.println("Residents and merchants are running around in panic, shouting about the missing Unova Pearl.");
        System.out.println("A town crier approaches you and explains the situation:");
        System.out.println("Town Crier: 'The Unova Pearl has been stolen! It has protected us from the dragon of the sea. Without it, we're doomed! Please help us recover it.'");
        System.out.println("Side Quest <The Mystery of the Missing Unova Pearl> initiated");

                System.out.println("You need to gather clues to find out where the Unova Pearl might be.");
        System.out.println("Start by looking around the Pier to see if anyone has seen or heard anything suspicious.");
    
        // Hints that guide the player to interact with NPCs
        System.out.println("You hear murmurs about someone noticing something suspicious at the north side of the pier.");
        System.out.println("Another person mentions hearing strange noises early in the morning near the Tropica.");
        System.out.println("You decide to investigate these areas further.");
    
        gatherCluesStarted = true;
    }
    
    public void gatherClues() {
        System.out.println("You need to gather clues to find out where the Unova Pearl might be.");
        System.out.println("Start by looking around the Pier to see if anyone has seen or heard anything suspicious.");
    
        // Hints that guide the player to interact with NPCs
        System.out.println("You hear murmurs about someone noticing something suspicious at the north side of the pier.");
        System.out.println("Another person mentions hearing strange noises early in the morning near the Tropica.");
        System.out.println("You decide to investigate these areas further.");
    
        interactingWithNPCs = true;
    }
    
    public void interactWithNPCs() { 
        String currentRoomId = gameState.getCurrentRoom().getId(); 
        System.out.println("Current Room ID for NPC interaction: " + currentRoomId); 
        if (currentRoomId.equals("TE")) { 
            System.out.println("Upon heading north, you see a nervous merchant wandering around the vicinity."); 
            System.out.println("Nervous Merchant: 'I saw a shady figure near the storage area up north last night.'"); 
        } else if (currentRoomId.equals("TC")) { 
            System.out.println("You find an old fisherman mumbling near the storage area."); 
            System.out.println("Old Fisherman: 'My dry stock which I had stored in this shed has been tampered with this morning.'"); 
            System.out.println("The fisherman then brings you to some footprints that lead further north."); 
        } else if (currentRoomId.equals("TA")) { 
            System.out.println("You spot a child playing near the docks."); 
            System.out.println("Child: 'I saw a raccoon-like creature running away with something shiny!'"); 
            System.out.println("You see the shadow of said creature briefly rushing west."); 
        } else if (currentRoomId.equals("MWRC")) { 
            System.out.println("You continue rushing west, but a human's speed was never on par to that of a tiny creature like that."); 
        } else if (currentRoomId.equals("MWRB")) { 
            System.out.println("You get closer and closer to that creature."); 
            System.out.println("Upon closer inspection you can deduce that it's a Zigzagoon that has been causing this ruckus."); 
            System.out.println("You are getting close, but still not quite, thankfully this Zigzagoon is going in a straight line."); 
        } else if (currentRoomId.equals("MWRA")) { 
            System.out.println("You finally caught up to the Zigzagoon."); 
            System.out.println("You promptly retrieved the Unova Pearl and released the Zigzagoon back into the wild."); 
            zigzagoonFound = true; 
        } 
    }
    
    public void findZigzagoon() {
        System.out.println("You decide to head back to the Pier.");
        gameState.getPlayer().addItem(new Item("UP", "UnovaPearl", "The precious Unova Pearl that protects the Pier.", false));
        returnToPier();
    }
    
    public void returnToPier() {
        if (getCurrentRoom().getId().equals("P")) {
            System.out.println("You hand over the Unova Pearl to the town crier.");
            System.out.println("Town Crier: 'Thank you so much for recovering the Unova Pearl! You've saved us all!'");
            gameState.getPlayer().removeItem("UnovaPearl");
            gameState.getPlayer().increaseScore(50); // Reward the player
            gameState.getPlayer().addItem(new Item("TE", "TownEmblem", "You obtained this after what you've done to save the city from destruction.", false));
            System.out.println("Here's some points and our Town's emblem to assist you with your journeys!");
            System.out.println("Congratulations you have completed the side quest <The Mystery of the Missing Unova Pearl>.");
            checkForBothEmblems();
        }
    }

    public String inspectRoom(Room room) {
        StringBuilder message = new StringBuilder(room.getDescription()).append("\nYou see:\n");
        
        for (Item item : room.getItems()) {
            if (!item.isHidden()) {
                message.append("- Item: ").append(item.getName()).append(": ").append(item.getDescription()).append("\n");
            }
        }
        
        for (Equipment equipment : room.getEquipments()) {
            if (!equipment.isHidden()) {
                message.append("- Equipment: ").append(equipment.getName()).append(": ").append(equipment.getDescription()).append("\n");
            }
        }
        
        for (Feature feature : room.getFeatures()) {
            if (!feature.isHidden()) {
                message.append("- ").append(feature.getName()).append("\n");
            }
        }
        
        return message.toString();
    }

    public void checkForBothEmblems() {
    boolean hasYinYangEmblem = false;
    boolean hasTownEmblem = false;

    for (Item item : gameState.getPlayer().getInventory()) {
        if (item.getName().equals("Yin-Yang-Emblem")) {
            hasYinYangEmblem = true;
        } else if (item.getName().equals("TownEmblem")) {
            hasTownEmblem = true;
        }

        if (hasYinYangEmblem && hasTownEmblem) {
            System.out.println("You have successfully acquired both the Yin-Yang-Emblem and TownEmblem.");
            // Set Gym hidden status to false
            Room gymRoom = gameState.getMap().getRoom("WG");
            if (gymRoom != null) {
                gymRoom.setHidden(false);
                System.out.println("You are now worthy enough to enter the Water-Type Gym!");
                System.out.println("Try combining the two emblems.");
            }
            return;
        }
    }
}

    public void enterGym() { 
        Room currentRoom = gameState.getCurrentRoom(); 
        Player player = gameState.getPlayer(); 
        if (currentRoom.getId().equals("WG") && player.hasEquipment("Gym Entry Pass")) { 
            System.out.println("You have both emblems. You may now enter the Water-Type Gym!"); 
        } else { 
            System.out.println("You do not have the necessary emblems to enter the gym."); 
        } 
    }





    public static void main(String[] args) { 
        Game game = new Game(); 
        game.run(); 
    }
}


