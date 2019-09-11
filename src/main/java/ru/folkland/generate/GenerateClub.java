package ru.folkland.generate;

import ru.folkland.constants.Constants;
import ru.folkland.manager.clubs.Club;
import ru.folkland.manager.clubs.FootballScheme;
import ru.folkland.manager.player.FootballPosition;

/**
 * @author folkland
 */
public class GenerateClub {

    public static Club createClub(int id) {
        return createClub(id, StringGenerate.getName(Constants.RANDOM.nextInt(7) + 4));
    }

    public static Club createClub(int id, String name) {
        FootballScheme scheme = FootballScheme.values()[Constants.RANDOM.nextInt(FootballScheme.values().length)];
        return createClub(id, name, scheme);
    }

    public static Club createClub(int id, String name, FootballScheme scheme) {
        Club club = new Club(id, name, scheme);
        int k = 0;
        club.addPlayer(GeneratePlayer.createPlayer(k, FootballPosition.goalkeeper));
        k++;
        for (int i = 0; i < club.getScheme().getDefender(); i++) {
            club.addPlayer(GeneratePlayer.createPlayer(k, FootballPosition.defender));
            k++;
        }
        for (int i = 0; i < club.getScheme().getMidfielder(); i++) {
            club.addPlayer(GeneratePlayer.createPlayer(k, FootballPosition.midfielder));
            k++;
        }
        for (int i = 0; i < club.getScheme().getForward(); i++) {
            club.addPlayer(GeneratePlayer.createPlayer(k, FootballPosition.forward));
            k++;
        }
        return club;
    }
}
