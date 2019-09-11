package ru.folkland.constants;

import java.security.SecureRandom;

public class Constants {

    /**
     * Randomizer constant
     */
    public static SecureRandom RANDOM = new SecureRandom();

    /**
     * Player class constants
     */
    public static int MAX_TRAIN_FOR_PLAYED_MATCH = 60;
    public static int MAX_TRAIN_FOR_NOT_PLAYED_MATCH = 30;

    /**
     * Match class constants
     */
    public static int HOME_CLUB_BONUS_TO_STRENGTH = 30;
    public static int TEAM_STRENGTH_EQUALS = 10;
    public static int MAX_DRAW_SCORED_COUNT = 5; // 0-4
    public static int MAX_GAME_SCORED_COUNT = 10; //0-9
}
