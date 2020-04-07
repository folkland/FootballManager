package ru.folkland.generate;

import ru.folkland.constants.Constants;
import ru.folkland.manager.player.FootballPosition;
import ru.folkland.manager.player.Player;

/**
 * @author folkland
 */
public class GeneratePlayer {

    public static Player createPlayer() {
        FootballPosition position = FootballPosition.values()[Constants.RANDOM.nextInt(FootballPosition.values().length)];
        return createPlayer(position);
    }

    public static Player createPlayer(FootballPosition position) {
        int nameLength = Constants.RANDOM.nextInt(5) + 4;
        int surnameLength = Constants.RANDOM.nextInt(10) + 4;
        int skills = Constants.RANDOM.nextInt(25) + 10;
        return new Player(StringGenerate.getName(nameLength), StringGenerate.getName(surnameLength), 18, skills, position);
    }
}
