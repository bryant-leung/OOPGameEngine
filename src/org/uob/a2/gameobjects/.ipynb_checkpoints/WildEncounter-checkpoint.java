// Additional class WildEncounter
// This basically sets up the whole pokemon encounter and random chance system
package org.uob.a2.gameobjects;

import java.util.List; 
import java.util.HashMap; 
import java.util.Arrays; 
import java.util.Collections; 
import java.util.ArrayList; 
import java.util.Random;
import java.util.Map.Entry;

public class WildEncounter {
    private static java.util.Map<Area, List<Pokemon>> areaPokemonMap = new HashMap<Area, List<Pokemon>>(); // This is basically just the pokemon objects plus an area variable
    private static java.util.Map<Area, List<String>> areaAllowedTypes = new HashMap<Area, List<String>>(); // Area types with allocated allowed types



    static {
        // System.out.println("Static initializer of WildEncounter called."); // Debug print
        // Configure allowed types for each area
        areaAllowedTypes.put(Area.FOREST, Arrays.asList("Electric", "Ghost"));
        areaAllowedTypes.put(Area.ICELAND, Arrays.asList("Dark", "Ice"));
        areaAllowedTypes.put(Area.MEADOW, Arrays.asList("Fire", "Bug"));
        areaAllowedTypes.put(Area.RIVER, Arrays.asList("Water", "Steel"));
        areaAllowedTypes.put(Area.SEA, Arrays.asList("Water", "Poison"));
        areaAllowedTypes.put(Area.TROPICA, Arrays.asList("Grass", "Electric"));
        areaAllowedTypes.put(Area.COAST, Arrays.asList("Fairy", "Rock"));
        areaAllowedTypes.put(Area.FIELDS, Arrays.asList("Normal", "Psychic"));
        areaAllowedTypes.put(Area.MISC, Collections.emptyList());

        // Declare all Pokémon
        // Name/ Type/ Catch Rate/ Spawn Rate
        List<Pokemon> allPokemon = Arrays.asList(
        // Electric
        new Pokemon("Pikachu", "Electric", 0.65, 0.4),
        new Pokemon("Electabuzz", "Electric", 0.55, 0.28),
        new Pokemon("Shinx", "Electric", 0.5, 0.45),
        new Pokemon("Luxray", "Electric", 0.3, 0.25),
        new Pokemon("Jolteon", "Electric", 0.4, 0.35),
        new Pokemon("Mareep", "Electric", 0.5, 0.5),
        new Pokemon("Magnemite", "Electric", 0.45, 0.4),
        new Pokemon("Electrode", "Electric", 0.8, 0.35),
        new Pokemon("Manectric", "Electric", 0.4, 0.3),
        new Pokemon("Raichu", "Electric", 0.35, 0.3),
    
        // Grass
        new Pokemon("Bulbasaur", "Grass", 0.6, 0.6),
        new Pokemon("Treecko", "Grass", 0.6, 0.45),
        new Pokemon("Sceptile", "Grass", 0.3, 0.25),
        new Pokemon("Turtwig", "Grass", 0.6, 0.45),
        new Pokemon("Leafeon", "Grass", 0.4, 0.35),
        new Pokemon("Snivy", "Grass", 0.7, 0.45),
        new Pokemon("Chikorita", "Grass", 0.55, 0.4),
        new Pokemon("Roselia", "Grass", 0.4, 0.35),
        new Pokemon("Tangela", "Grass", 0.45, 0.4),
        new Pokemon("Lilligant", "Grass", 0.35, 0.3),
    
        // Fire
        new Pokemon("Charmander", "Fire", 0.4, 0.5),
        new Pokemon("Cyndaquil", "Fire", 0.45, 0.4),
        new Pokemon("Magmar", "Fire", 0.35, 0.28),
        new Pokemon("Torchic", "Fire", 0.45, 0.4),
        new Pokemon("Chimchar", "Fire", 0.45, 0.4),
        new Pokemon("Flareon", "Fire", 0.4, 0.35),
        new Pokemon("Vulpix", "Fire", 0.5, 0.45),
        new Pokemon("Growlithe", "Fire", 0.45, 0.4),
        new Pokemon("Ponyta", "Fire", 0.4, 0.35),
        new Pokemon("Houndour", "Fire", 0.4, 0.3),
    
        // Water
        new Pokemon("Piplup", "Water", 0.65, 0.5),
        new Pokemon("Psyduck", "Water", 0.9, 0.6),
        new Pokemon("Lapras", "Water", 0.3, 0.22),
        new Pokemon("Magikarp", "Water", 0.99, 0.9),
        new Pokemon("Wooper", "Water", 0.8, 0.6),
        new Pokemon("Feebas", "Water", 0.9, 0.8),
        new Pokemon("Milotic", "Water", 0.25, 0.22),
        new Pokemon("Wailmer", "Water", 0.6, 0.5),
        new Pokemon("Wailord", "Water", 0.3, 0.25),
        new Pokemon("Mudkip", "Water", 0.5, 0.45),
        new Pokemon("Tentacool", "Water", 0.4, 0.6),
        new Pokemon("Horsea", "Water", 0.35, 0.5),
        new Pokemon("Krabby", "Water", 0.45, 0.7),
        new Pokemon("Staryu", "Water", 0.35, 0.5),
        new Pokemon("Vaporeon", "Water", 0.6, 0.35),
        new Pokemon("Oshawott", "Water", 0.5, 0.45),
    
        // Psychic
        new Pokemon("Gardevoir", "Psychic", 0.45, 0.3),
        new Pokemon("Wobbuffet", "Psychic", 0.5, 0.25),
        new Pokemon("Ralts", "Psychic", 0.8, 0.55),
        new Pokemon("Espeon", "Psychic", 0.4, 0.35),
        new Pokemon("Alakazam", "Psychic", 0.3, 0.25),
        new Pokemon("Slowpoke", "Psychic", 0.8, 0.45),
        new Pokemon("Starmie", "Psychic", 0.7, 0.35),
        new Pokemon("Exeggcute", "Psychic", 0.65, 0.4),
        new Pokemon("Gothita", "Psychic", 0.4, 0.35),
        new Pokemon("Munna", "Psychic", 0.45, 0.4),
    
        // Ghost
        new Pokemon("Gengar", "Ghost", 0.3, 0.25),
        new Pokemon("Gastly", "Ghost", 0.7, 0.6),
        new Pokemon("Haunter", "Ghost", 0.4, 0.5),
        new Pokemon("Spiritomb", "Ghost", 0.3, 0.25),
        new Pokemon("Misdreavus", "Ghost", 0.5, 0.45),
        new Pokemon("Banette", "Ghost", 0.4, 0.35),
        new Pokemon("Duskull", "Ghost", 0.45, 0.4),
        new Pokemon("Mimikyu", "Ghost", 0.4, 0.35),
        new Pokemon("Shedinja", "Ghost", 0.3, 0.25),
        new Pokemon("Drifloon", "Ghost", 0.4, 0.3),
    
        // Normal
        new Pokemon("Snorlax", "Normal", 0.3, 0.23),
        new Pokemon("Pidgey", "Normal", 0.9, 0.8),
        new Pokemon("Slakoth", "Normal", 0.9, 0.65),
        new Pokemon("Miltank", "Normal", 0.4, 0.35),
        new Pokemon("Rattata", "Normal", 0.9, 0.8),
        new Pokemon("Eevee", "Normal", 0.7, 0.5),
        new Pokemon("Meowth", "Normal", 0.6, 0.45),
        new Pokemon("Bidoof", "Normal", 0.85, 0.4),
        new Pokemon("Zigzagoon", "Normal", 0.85, 0.4),
        new Pokemon("Sentret", "Normal", 0.8, 0.3),
    
        // Rock
        new Pokemon("Kabutops", "Rock", 0.25, 0.23),
        new Pokemon("Larvitar", "Rock", 0.7, 0.35),
        new Pokemon("Tyranitar", "Rock", 0.25, 0.22),
        new Pokemon("Relicanth", "Rock", 0.55, 0.23),
        new Pokemon("Geodude", "Rock", 0.7, 0.45),
        new Pokemon("Onix", "Rock", 0.4, 0.35),
        new Pokemon("Rhyhorn", "Rock", 0.45, 0.4),
        new Pokemon("Nosepass", "Rock", 0.6, 0.35),
        new Pokemon("Aron", "Rock", 0.45, 0.4),
        new Pokemon("Rockruff", "Rock", 0.4, 0.35),
    
        // Poison
        new Pokemon("Zubat", "Poison", 0.9, 0.7),
        new Pokemon("Oddish", "Poison", 0.85, 0.7),
        new Pokemon("Nidoran", "Poison", 0.7, 0.6),
        new Pokemon("Ekans", "Poison", 0.8, 0.45),
        new Pokemon("Koffing", "Poison", 0.8, 0.35),
        new Pokemon("Grimer", "Poison", 0.75, 0.4),
        new Pokemon("Spinarak", "Poison", 0.4, 0.35),
        new Pokemon("Swalot", "Poison", 0.4, 0.3),
        new Pokemon("Skorupi", "Poison", 0.4, 0.35),
        new Pokemon("Roselia", "Poison", 0.45, 0.4),
        
        // Steel
        new Pokemon("Beldum", "Steel", 0.55, 0.3),
        new Pokemon("Metagross", "Steel", 0.25, 0.22),
        new Pokemon("Magnemite", "Steel", 0.45, 0.4),
        new Pokemon("Steelix", "Steel", 0.4, 0.35),
        new Pokemon("Skarmory", "Steel", 0.45, 0.4),
        new Pokemon("Mawile", "Steel", 0.4, 0.35),
        new Pokemon("Aron", "Steel", 0.45, 0.4),
        new Pokemon("Jirachi", "Steel", 0.3, 0.25),
        new Pokemon("Klink", "Steel", 0.4, 0.35),
        new Pokemon("Durant", "Steel", 0.4, 0.3),
        
        // Dark
        new Pokemon("Umbreon", "Dark", 0.4, 0.35),
        new Pokemon("Sneasel", "Dark", 0.5, 0.45),
        new Pokemon("Houndour", "Dark", 0.8, 0.3),
        new Pokemon("Murkrow", "Dark", 0.55, 0.4),
        new Pokemon("Poochyena", "Dark", 0.45, 0.4),
        new Pokemon("Carvanha", "Dark", 0.6, 0.35),
        new Pokemon("Mightyena", "Dark", 0.4, 0.35),
        new Pokemon("Scraggy", "Dark", 0.4, 0.3),
        new Pokemon("Inkay", "Dark", 0.6, 0.35),
        new Pokemon("Deino", "Dark", 0.3, 0.25),
        
        // Ice
        new Pokemon("Glaceon", "Ice", 0.4, 0.35),
        new Pokemon("Swinub", "Ice", 0.8, 0.45),
        new Pokemon("Snorunt", "Ice", 0.75, 0.4),
        new Pokemon("Vanillite", "Ice", 0.55, 0.4),
        new Pokemon("Smoochum", "Ice", 0.7, 0.35),
        new Pokemon("Bergmite", "Ice", 0.4, 0.35),
        new Pokemon("Amaura", "Ice", 0.4, 0.35),
        new Pokemon("Cryogonal", "Ice", 0.4, 0.35),
        new Pokemon("Cubchoo", "Ice", 0.45, 0.4),
        new Pokemon("Delibird", "Ice", 0.65, 0.4),
        
        // Fairy
        new Pokemon("Sylveon", "Fairy", 0.4, 0.35),
        new Pokemon("Clefairy", "Fairy", 0.65, 0.4),
        new Pokemon("Jigglypuff", "Fairy", 0.8, 0.45),
        new Pokemon("Togepi", "Fairy", 0.95, 0.4),
        new Pokemon("Ralts", "Fairy", 0.8, 0.55),
        new Pokemon("Mawile", "Fairy", 0.5, 0.35),
        new Pokemon("Marill", "Fairy", 0.8, 0.35),
        new Pokemon("Flabébé", "Fairy", 0.65, 0.4),
        new Pokemon("Dedenne", "Fairy", 0.85, 0.4),
        new Pokemon("Carbink", "Fairy", 0.4, 0.35)
        );

        // Assign Pokémon to areas based on their types
        // First searches through list of pokemon
        for (Pokemon pokemon : allPokemon) {
            // It then searches the list of Pokemon and only allows continued sorting if that pokemon contains a type of a valid type
            for (java.util.Map.Entry<Area, List<String>> entry : areaAllowedTypes.entrySet()) {
                // out of the whole values within pokemon objects, get only the one that is the TYPE
                if (entry.getValue().contains(pokemon.getType())) {
                    // iterate through their types, if their type is not present, then map it to a whole new arrayList, if alr present, it skips until all the pokemon has been iterated through and all types have been added
                    areaPokemonMap.computeIfAbsent(entry.getKey(), k -> new ArrayList<>()).add(pokemon);
                }
            }
        }
    }

    // method taking area parameter and returning a Pokemon object
    public static Pokemon encounter(Area area) {
        // used Collections of java util package here
        // attempts to retrieve the list of pokemon with given area
        // returns list of pokemon corresponding to area if an area is found
        // returns an empty list if not found
        List<Pokemon> allowedPokemons = areaPokemonMap.getOrDefault(area, Collections.emptyList());

        if (allowedPokemons.isEmpty()) {
            System.out.println("No Pokémon allowed in this area.");
            return null;
        }
        

            // Makes it such that there is no boundaries for different spawn rates
        double totalSpawnRate = 0; 
            for (Pokemon pokemon : allowedPokemons) { 
                totalSpawnRate += pokemon.getSpawnRate(); 
            }

        Random random = new Random();
        // new variable chance which gets a value between 0 and 1
        double chance = random.nextDouble() * totalSpawnRate;

        // Find which Pokémon corresponds to the random value 
            double cumulativeProbability = 0; 
            for (Pokemon pokemon : allowedPokemons) { 
                cumulativeProbability += pokemon.getSpawnRate();
                if (chance <= cumulativeProbability) { 
                    return pokemon; 
                } 
            }
        return null;
    }
}
