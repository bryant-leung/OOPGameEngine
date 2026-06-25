// Added a new class for the FishingMiniGame at roomID: TB

package org.uob.a2.gameobjects;

import java.util.Random;

public class FishingMiniGame {
    private static final int MAX_TRIES = 10; // Makes sure game ends within 10 tries
    private int tries;
    private boolean completed;

    public FishingMiniGame() {
        this.tries = 0;
        this.completed = false;
    }

    public FishingResult fish() {
        if (completed) {
            return null; // Mini-game is completed
        }

        tries++;
        Random random = new Random();
        double chance = random.nextDouble();

        if (tries >= MAX_TRIES) {
            completed = true;
            return FishingResult.YIN_RELIC; // Forcibly adds the relic into player inventory when game ends 
        }

        // Determine fishing result based on probability 
        // Nothing 30%, Magikarp 20%, Staryu 30&, relic 20%
        if (chance < 0.30) {
            return FishingResult.NOTHING;
        } else if (chance < 0.50) {
            return FishingResult.MAGIKARP;
        } else if (chance < 0.80) {
            return FishingResult.STARYU;
        } else {
            return FishingResult.YIN_RELIC;
        }
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) { 
        this.completed = completed; 
    }
}
