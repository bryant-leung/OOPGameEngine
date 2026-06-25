// This handles the Rotom command which displays a list of Pokemon, this works with the Rotom class in gameobjects to make this system work
// There is a seperate class which differentiates whether an item is or is not a Pokemon

package org.uob.a2.commands;

import org.uob.a2.gameobjects.GameState;
import org.uob.a2.gameobjects.Player;
import org.uob.a2.gameobjects.Rotom;

public class RotomCommand extends Command {

    private Rotom rotom;

    public RotomCommand(Rotom rotom) {
        this.rotom = rotom;
    }

    @Override
    public String execute(GameState gameState) {
        Player player = gameState.getPlayer();
        rotom.displayPokemonInventory(player.getInventory());
        return "Displayed Pokémon inventory using Rotom.";
    }

    @Override
    public String toString() {
        return "Rotom Command";
    }
}
