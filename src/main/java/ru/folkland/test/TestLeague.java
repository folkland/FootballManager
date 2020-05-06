package ru.folkland.test;

import ru.folkland.manager.clubs.Club;
import ru.folkland.manager.league.League;
import ru.folkland.manager.transfer.TransferList;

import java.util.List;

public class TestLeague {

    public static void main(String[] args) {
        List<Club> clubs = CreateClubsForTest.getClubsList(4, TransferList.newTransferList(100));
        League league = new League();
        league.addClubs(clubs);
        league.startLeague(3);
    }
}
