package ru.folkland.generate;

import ru.folkland.constants.Constants;
import ru.folkland.manager.clubs.Club;
import ru.folkland.manager.clubs.FootballScheme;
import ru.folkland.manager.player.FootballPosition;
import ru.folkland.manager.transfer.TransferList;

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

    public static Club createClubWithTransfer(int id, TransferList transferList) {
        return createClubWithTransfer(id, StringGenerate.getName(Constants.RANDOM.nextInt(7) + 4), transferList);
    }

    public static Club createClubWithTransfer(int id, String name, TransferList transferList) {
        FootballScheme scheme = FootballScheme.values()[Constants.RANDOM.nextInt(FootballScheme.values().length)];
        return createClubWithTransfer(id, name, scheme, transferList);
    }

    public static Club createClubWithTransfer(int id, String name, FootballScheme scheme, TransferList transferList) {
        Club club = new Club(id, name, scheme);
        club.compositionFormation(transferList);
        return club;
    }
}
