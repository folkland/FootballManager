package ru.folkland.test;

import ru.folkland.manager.clubs.Club;
import ru.folkland.manager.league.Season;
import ru.folkland.manager.player.Player;
import ru.folkland.manager.transfer.TransferList;

import java.util.List;

/**
 * @author folkland
 */
public class TestSeason {

    public static void main(String[] args) {
        List<Club> clubs = CreateClubsForTest.getClubsList(4, TransferList.newTransferList(100));
        Season season = new Season(clubs);
        season.playAllTours();
        System.out.println(season.showTable());
        for (Club club : clubs) {
            System.out.println(club.getName() + " " + club.getTotalClubStrength());
        }
        System.out.println("==============");
        for (Player player: clubs.get(0).getPlayers()) {
            System.out.println(player.toString());
        }
        System.out.println("==============");
        for (Player player: clubs.get(1).getPlayers()) {
            System.out.println(player.toString());
        }
        System.out.println("==============");
        for (Player player: clubs.get(2).getPlayers()) {
            System.out.println(player.toString());
        }
        System.out.println("==============");
        for (Player player: clubs.get(3).getPlayers()) {
            System.out.println(player.toString());
        }
    }
}
