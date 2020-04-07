package ru.folkland.test;

import ru.folkland.generate.GenerateTeam;
import ru.folkland.manager.clubs.Team;
import ru.folkland.manager.transfer.TransferList;

/**
 * @author folkland
 */
public class TestClubFormation {

    public static void main(String[] args) {
        TransferList transferList = new TransferList(100);
        Team team = GenerateTeam.createClubWithTransfer(transferList);
        System.out.println(team.toString());
        System.out.println(team.getTotalClubStreinght());
        System.out.println(team.getPlayers().size());
    }
}
