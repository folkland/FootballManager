package ru.folkland.test;

import ru.folkland.generate.GeneratePlayer;
import ru.folkland.manager.player.FootballPosition;

/**
 * @author folkland
 */
public class TestPlayerGenerate {

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            System.out.println(GeneratePlayer.createPlayer(i, FootballPosition.midfielder).toString());
        }
    }
}
