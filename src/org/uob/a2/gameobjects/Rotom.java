// Additional class Rotom which can be used anytime to display the list of Pokemon (Since when you display inventory it displays both pokemon and other items and equipments)
package org.uob.a2.gameobjects;

import java.util.List;
import java.util.stream.Collectors;

public class Rotom {
    private ItemManager itemManager;

    // Rotom is initialized with itemManager parameter as Rotom is used to display Pokemon from inventory, which is stored using itemManager
    public Rotom(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    // Decided to implement streams here since it was taught very recently in lectures
    // It filters the stream and keeps only the Pokemon items
    // It then collects the filtered Pokemon out and throw it into a new list called pokemonItems
    public void displayPokemonInventory(List<Item> inventory) {
        List<Item> pokemonItems = inventory.stream()
                                           .filter(item -> itemManager.isPokemon(item.getId()))
                                           .collect(Collectors.toList());

        if (pokemonItems.isEmpty()) {
            System.out.println("Rotom: No Pokémon found in your inventory.");
        } else {
            System.out.println("Rotom: This is the list of Pokémon you currently have:");
            // iterate and display list of Pokemon in inventory currently
            for (int i = 0; i < pokemonItems.size(); i++) {
                System.out.println((i + 1) + ". " + pokemonItems.get(i).getName());
            }
        }
    }
}
