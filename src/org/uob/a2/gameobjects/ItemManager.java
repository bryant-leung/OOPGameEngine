// To organise everything better, I made a new Item Manager class to handle the implementation of items
// This also includes the extra function of setting up whether an item is a Pokemon or an usable item
package org.uob.a2.gameobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemManager {
    private List<Item> items;
    private Map<String, Boolean> pokemonMap;

    public ItemManager() {
        items = new ArrayList<>();
        pokemonMap = new HashMap<>();
        initializeItems();
    }

    private void initializeItems() {
        // Add all items and Pokémon here, alongside a boolean value at the end to show whether its a pokemon or an item
        Item rotom = new Item("rotom", "Rotom", "A Pokédex-Typed Pokémon that can store the data on what Pokémon you have currently.", false);
        Item pokeball = new Item("pokeball", "Pokéball", "A device for catching wild Pokémon.", false);
        Item TornNote = new Item("TORN_NOTE", "TornNote", "A note that displays Meadows and Fields -- two of a kind.", true);

        items.add(rotom);
        items.add(pokeball);
        items.add(TornNote);

        // Mark Pokémon in the map
        pokemonMap.put("rotom", true);
        pokemonMap.put("pokeball", false);
        pokemonMap.put("TornNote", false);

        // Electric
        pokemonMap.put("Pikachu", true);
        pokemonMap.put("Electabuzz", true);
        pokemonMap.put("Shinx", true);
        pokemonMap.put("Luxray", true);
        pokemonMap.put("Jolteon", true);
        pokemonMap.put("Mareep", true);
        pokemonMap.put("Magnemite", true);
        pokemonMap.put("Electrode", true);
        pokemonMap.put("Manectric", true);
        pokemonMap.put("Raichu", true);

        // Grass
        pokemonMap.put("Bulbasaur", true);
        pokemonMap.put("Treecko", true);
        pokemonMap.put("Sceptile", true);
        pokemonMap.put("Turtwig", true);
        pokemonMap.put("Leafeon", true);
        pokemonMap.put("Snivy", true);
        pokemonMap.put("Chikorita", true);
        pokemonMap.put("Roselia", true);
        pokemonMap.put("Tangela", true);
        pokemonMap.put("Lilligant", true);

        // Fire
        pokemonMap.put("Charmander", true);
        pokemonMap.put("Cyndaquil", true);
        pokemonMap.put("Magmar", true);
        pokemonMap.put("Torchic", true);
        pokemonMap.put("Chimchar", true);
        pokemonMap.put("Flareon", true);
        pokemonMap.put("Vulpix", true);
        pokemonMap.put("Growlithe", true);
        pokemonMap.put("Ponyta", true);
        pokemonMap.put("Houndour", true);

        // Water
        pokemonMap.put("Piplup", true);
        pokemonMap.put("Psyduck", true);
        pokemonMap.put("Lapras", true);
        pokemonMap.put("Magikarp", true);
        pokemonMap.put("Wooper", true);
        pokemonMap.put("Feebas", true);
        pokemonMap.put("Milotic", true);
        pokemonMap.put("Wailmer", true);
        pokemonMap.put("Wailord", true);
        pokemonMap.put("Mudkip", true);
        pokemonMap.put("Tentacool", true);
        pokemonMap.put("Horsea", true);
        pokemonMap.put("Krabby", true);
        pokemonMap.put("Staryu", true);
        pokemonMap.put("Vaporeon", true);
        pokemonMap.put("Oshawott", true);

        // Psychic
        pokemonMap.put("Gardevoir", true);
        pokemonMap.put("Wobbuffet", true);
        pokemonMap.put("Ralts", true);
        pokemonMap.put("Espeon", true);
        pokemonMap.put("Alakazam", true);
        pokemonMap.put("Slowpoke", true);
        pokemonMap.put("Starmie", true);
        pokemonMap.put("Exeggcute", true);
        pokemonMap.put("Gothita", true);
        pokemonMap.put("Munna", true);

        // Ghost
        pokemonMap.put("Gengar", true);
        pokemonMap.put("Gastly", true);
        pokemonMap.put("Haunter", true);
        pokemonMap.put("Spiritomb", true);
        pokemonMap.put("Misdreavus", true);
        pokemonMap.put("Banette", true);
        pokemonMap.put("Duskull", true);
        pokemonMap.put("Mimikyu", true);
        pokemonMap.put("Shedinja", true);
        pokemonMap.put("Drifloon", true);

        // Normal
        pokemonMap.put("Snorlax", true);
        pokemonMap.put("Pidgey", true);
        pokemonMap.put("Slakoth", true);
        pokemonMap.put("Miltank", true);
        pokemonMap.put("Rattata", true);
        pokemonMap.put("Eevee", true);
        pokemonMap.put("Meowth", true);
        pokemonMap.put("Bidoof", true);
        pokemonMap.put("Zigzagoon", true);
        pokemonMap.put("Sentret", true);

        // Rock
        pokemonMap.put("Kabutops", true);
        pokemonMap.put("Larvitar", true);
        pokemonMap.put("Tyranitar", true);
        pokemonMap.put("Relicanth", true);
        pokemonMap.put("Geodude", true);
        pokemonMap.put("Onix", true);
        pokemonMap.put("Rhyhorn", true);
        pokemonMap.put("Nosepass", true);
        pokemonMap.put("Aron", true);
        pokemonMap.put("Rockruff", true);

        // Poison
        pokemonMap.put("Zubat", true);
        pokemonMap.put("Oddish", true);
        pokemonMap.put("Nidoran", true);
        pokemonMap.put("Ekans", true);
        pokemonMap.put("Koffing", true);
        pokemonMap.put("Grimer", true);
        pokemonMap.put("Spinarak", true);
        pokemonMap.put("Swalot", true);
        pokemonMap.put("Skorupi", true);
        pokemonMap.put("Roselia", true);

        // Steel
        pokemonMap.put("Beldum", true);
        pokemonMap.put("Metagross", true);
        pokemonMap.put("Magnemite", true);
        pokemonMap.put("Steelix", true);
        pokemonMap.put("Skarmory", true);
        pokemonMap.put("Mawile", true);
        pokemonMap.put("Aron", true);
        pokemonMap.put("Jirachi", true);
        pokemonMap.put("Klink", true);
        pokemonMap.put("Durant", true);

        // Dark
        pokemonMap.put("Umbreon", true);
        pokemonMap.put("Sneasel", true);
        pokemonMap.put("Houndour", true);
        pokemonMap.put("Murkrow", true);
        pokemonMap.put("Poochyena", true);
        pokemonMap.put("Carvanha", true);
        pokemonMap.put("Mightyena", true);
        pokemonMap.put("Scraggy", true);
        pokemonMap.put("Inkay", true);
        pokemonMap.put("Deino", true);

        // Ice
        pokemonMap.put("Glaceon", true);
        pokemonMap.put("Swinub", true);
        pokemonMap.put("Snorunt", true);
        pokemonMap.put("Vanillite", true);
        pokemonMap.put("Smoochum", true);
        pokemonMap.put("Bergmite", true);
        pokemonMap.put("Amaura", true);
        pokemonMap.put("Cryogonal", true);
        pokemonMap.put("Cubchoo", true);
        pokemonMap.put("Delibird", true);

        // Fairy
        pokemonMap.put("Sylveon", true);
        pokemonMap.put("Clefairy", true);
        pokemonMap.put("Jigglypuff", true);
        pokemonMap.put("Togepi", true);
        pokemonMap.put("Ralts", true);
        pokemonMap.put("Mawile", true);
        pokemonMap.put("Marill", true);
        pokemonMap.put("Flabébé", true);
        pokemonMap.put("Dedenne", true);
        pokemonMap.put("Carbink", true);
    }

    public Item getItemById(String id) {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    public List<Item> getAllItems() {
        return items;
    }

    public boolean isPokemon(String id) {
        return pokemonMap.getOrDefault(id, false);
    }
}
