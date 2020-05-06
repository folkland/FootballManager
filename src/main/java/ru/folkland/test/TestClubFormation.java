package ru.folkland.test;

import ru.folkland.generate.GenerateClub;
import ru.folkland.manager.clubs.Club;
import ru.folkland.manager.transfer.TransferList;

/**
 * @author folkland
 */
public class TestClubFormation {

    public static void main(String[] args) {
        TransferList transferList = TransferList.newTransferList(100);
        Club club = GenerateClub.createClubWithTransfer(transferList);
        System.out.println(club.toString());
        System.out.println(club.getTotalClubStrength());
        System.out.println(club.getPlayers().size());
        System.out.println(club.getBudgetOfClub() - club.getSpendedBudget());
    }
}
