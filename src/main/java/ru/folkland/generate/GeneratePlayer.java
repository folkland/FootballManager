package ru.folkland.generate;

import ru.folkland.constants.Constants;
import ru.folkland.manager.player.FootballPosition;
import ru.folkland.manager.player.Player;

/**
 * @author folkland
 */
public class GeneratePlayer {

    public static Player createPlayer(SpringNameGenerate nameGenerate) {
        FootballPosition position = FootballPosition.values()[Constants.RANDOM.nextInt(FootballPosition.values().length)];
        return createPlayer(position, nameGenerate);
    }

    public static Player createPlayer(FootballPosition position, SpringNameGenerate nameGenerate) {
        int age = Constants.RANDOM.nextInt(15) + 18;
        int skills = Constants.RANDOM.nextInt(25) + 5 + Constants.RANDOM.nextInt(age);
        if (nameGenerate != null) return new Player(nameGenerate.getFirstName(), nameGenerate.getLastName(), age, skills, position);
        return new Player(StringGenerate.getName(7), StringGenerate.getName(12), age, skills, position);
    }
}
