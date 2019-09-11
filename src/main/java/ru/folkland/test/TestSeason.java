package ru.folkland.test;

import ru.folkland.generate.GenerateClub;
import ru.folkland.manager.clubs.Club;
import ru.folkland.manager.league.Season;

import java.util.ArrayList;
import java.util.List;

/**
 * @author folkland
 */
public class TestSeason {

    public static void main(String[] args) {
        List<Club> clubs = new ArrayList<>();
        clubs.add(GenerateClub.createClub(1));
        clubs.add(GenerateClub.createClub(2));
        clubs.add(GenerateClub.createClub(3));
        clubs.add(GenerateClub.createClub(4));
        Season season = new Season(clubs);
        season.playAllTours();
        System.out.println(season.showTable());
        for (Club club: clubs) {
            System.out.println(club.getName() + " " + club.getTotalClubStreinght());
        }
//        for (Player player: clubs.get(1).getPlayers()) {
//            System.out.println(player.toString());
//        }
    }
}
