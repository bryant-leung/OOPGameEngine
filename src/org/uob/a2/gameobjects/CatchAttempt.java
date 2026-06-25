// This is the to define the chances of catching a pokemon between using a normal pokeball or a special ultraball that can only be purchased in the equipment shop

package org.uob.a2.gameobjects;

import java.util.Random;

public class CatchAttempt {
    public static boolean attemptCatch(Pokemon pokemon) {
        Random random = new Random();
        // returns the boolean of whether the random generated number is smaller than the catch rate of spawned pokemon
        return random.nextDouble() < pokemon.getCatchRate();
    }

    public static boolean attemptCatchWithUltraBall(Pokemon pokemon) {
        Random random = new Random();
        double ultraBallCatchRate = pokemon.getCatchRate() * 1.5; // Increase catch rate for Ultra Ball x1.5 if used
        return random.nextDouble() < ultraBallCatchRate;
    }
}
