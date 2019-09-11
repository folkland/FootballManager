package ru.folkland.test;

import ru.folkland.generate.GenerateClub;
import ru.folkland.manager.clubs.Club;

/**
 * @author folkland
 */
public class TestClubFormation {

    public static void main(String[] args) {
        Club club = GenerateClub.createClub(0);
        System.out.println(club.toString());
        System.out.println(club.getTotalClubStreinght());
    }
}
