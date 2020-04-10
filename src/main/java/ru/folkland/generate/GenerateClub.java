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

    public static Club createClub() {
        return createClub(FakerGenerate.clubName());
    }

    private static Club createClub(String name) {
        FootballScheme scheme = FootballScheme.values()[Constants.RANDOM.nextInt(FootballScheme.values().length)];
        return createClub(name, scheme);
    }

    private static Club createClub(String name, FootballScheme scheme) {
        Club club = new Club(name, scheme);
        club.addPlayer(GeneratePlayer.createPlayer(FootballPosition.goalkeeper));
        for (int i = 0; i < club.getScheme().getDefender(); i++) {
            club.addPlayer(GeneratePlayer.createPlayer(FootballPosition.defender));
        }
        for (int i = 0; i < club.getScheme().getMidfielder(); i++) {
            club.addPlayer(GeneratePlayer.createPlayer(FootballPosition.midfielder));
        }
        for (int i = 0; i < club.getScheme().getForward(); i++) {
            club.addPlayer(GeneratePlayer.createPlayer(FootballPosition.forward));
        }
        return club;
    }

    public static Club createClubWithTransfer(TransferList transferList) {
        return createClubWithTransfer(FakerGenerate.clubName(), transferList);
    }

    private static Club createClubWithTransfer(String name, TransferList transferList) {
        FootballScheme scheme = FootballScheme.values()[Constants.RANDOM.nextInt(FootballScheme.values().length)];
        return createClubWithTransfer(name, scheme, transferList);
    }

    private static Club createClubWithTransfer(String name, FootballScheme scheme, TransferList transferList) {
        Club club = new Club(name, scheme);
        club.compositionFormation(transferList);
        return club;
    }
}
