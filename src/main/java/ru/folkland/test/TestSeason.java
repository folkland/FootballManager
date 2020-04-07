package ru.folkland.test;

import ru.folkland.generate.GenerateTeam;
import ru.folkland.manager.clubs.Team;
import ru.folkland.manager.league.Season;
import ru.folkland.manager.player.Player;
import ru.folkland.manager.transfer.TransferList;

import java.util.ArrayList;
import java.util.List;

/**
 * @author folkland
 */
public class TestSeason {

    public static void main(String[] args) {
        List<Team> teams = new ArrayList<>();
        TransferList transferList = new TransferList(100);
        teams.add(GenerateTeam.createClubWithTransfer(transferList));
        teams.add(GenerateTeam.createClubWithTransfer(transferList));
        teams.add(GenerateTeam.createClubWithTransfer(transferList));
        teams.add(GenerateTeam.createClubWithTransfer(transferList));
        Season season = new Season(teams);
        season.playAllTours();
        System.out.println(season.showTable());
        for (Team team : teams) {
            System.out.println(team.getName() + " " + team.getTotalClubStreinght());
        }
        System.out.println("==============");
        for (Player player: teams.get(0).getPlayers()) {
            System.out.println(player.toString());
        }
        System.out.println("==============");
        for (Player player: teams.get(1).getPlayers()) {
            System.out.println(player.toString());
        }
        System.out.println("==============");
        for (Player player: teams.get(2).getPlayers()) {
            System.out.println(player.toString());
        }
        System.out.println("==============");
        for (Player player: teams.get(3).getPlayers()) {
            System.out.println(player.toString());
        }
    }
}
