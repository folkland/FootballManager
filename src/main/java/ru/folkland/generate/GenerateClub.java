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

    public static Club createClub(SpringNameGenerate nameGenerate) {
        return createClub(nameGenerate.clubName(), nameGenerate);
    }

    private static Club createClub(String name, SpringNameGenerate nameGenerate) {
        FootballScheme scheme = FootballScheme.values()[Constants.RANDOM.nextInt(FootballScheme.values().length)];
        return createClub(name, scheme, nameGenerate);
    }

    private static Club createClub(String name, FootballScheme scheme, SpringNameGenerate nameGenerate) {
        Club club = new Club(name, scheme);
        club.addPlayer(GeneratePlayer.createPlayer(FootballPosition.goalkeeper, nameGenerate));
        for (int i = 0; i < club.getFScheme().getDefender(); i++) {
            club.addPlayer(GeneratePlayer.createPlayer(FootballPosition.defender, nameGenerate));
        }
        for (int i = 0; i < club.getFScheme().getMidfielder(); i++) {
            club.addPlayer(GeneratePlayer.createPlayer(FootballPosition.midfielder, nameGenerate));
        }
        for (int i = 0; i < club.getFScheme().getForward(); i++) {
            club.addPlayer(GeneratePlayer.createPlayer(FootballPosition.forward, nameGenerate));
        }
        return club;
    }

    public static Club createClubWithTransfer(TransferList transferList) {
        return createClubWithTransfer(transferList.getNameGenerate().clubName(), transferList);
    }

    private static Club createClubWithTransfer(String name, TransferList transferList) {
        FootballScheme scheme = FootballScheme.values()[Constants.RANDOM.nextInt(FootballScheme.values().length)];
        return createClubWithTransfer(name, scheme, transferList);
    }

    private static Club createClubWithTransfer(String name, FootballScheme scheme, TransferList transferList) {
        Club club = new Club(name, scheme);
        club.firstCompositionFormation(transferList);
        return club;
    }
}
