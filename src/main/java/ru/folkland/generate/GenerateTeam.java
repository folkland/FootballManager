package ru.folkland.generate;

import ru.folkland.constants.Constants;
import ru.folkland.manager.clubs.Team;
import ru.folkland.manager.clubs.FootballScheme;
import ru.folkland.manager.player.FootballPosition;
import ru.folkland.manager.transfer.TransferList;

/**
 * @author folkland
 */
public class GenerateTeam {

    public static Team createClub() {
        return createClub(StringGenerate.getName(Constants.RANDOM.nextInt(7) + 4));
    }

    public static Team createClub(String name) {
        FootballScheme scheme = FootballScheme.values()[Constants.RANDOM.nextInt(FootballScheme.values().length)];
        return createClub(name, scheme);
    }

    public static Team createClub(String name, FootballScheme scheme) {
        Team team = new Team(name, scheme);
        team.addPlayer(GeneratePlayer.createPlayer(FootballPosition.goalkeeper));
        for (int i = 0; i < team.getScheme().getDefender(); i++) {
            team.addPlayer(GeneratePlayer.createPlayer(FootballPosition.defender));
        }
        for (int i = 0; i < team.getScheme().getMidfielder(); i++) {
            team.addPlayer(GeneratePlayer.createPlayer(FootballPosition.midfielder));
        }
        for (int i = 0; i < team.getScheme().getForward(); i++) {
            team.addPlayer(GeneratePlayer.createPlayer(FootballPosition.forward));
        }
        return team;
    }

    public static Team createClubWithTransfer(TransferList transferList) {
        return createClubWithTransfer(StringGenerate.getName(Constants.RANDOM.nextInt(7) + 4), transferList);
    }

    public static Team createClubWithTransfer(String name, TransferList transferList) {
        FootballScheme scheme = FootballScheme.values()[Constants.RANDOM.nextInt(FootballScheme.values().length)];
        return createClubWithTransfer(name, scheme, transferList);
    }

    public static Team createClubWithTransfer(String name, FootballScheme scheme, TransferList transferList) {
        Team team = new Team(name, scheme);
        team.compositionFormation(transferList);
        return team;
    }
}
