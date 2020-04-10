package ru.folkland.test;

import ru.folkland.generate.GenerateClub;
import ru.folkland.manager.clubs.Club;
import ru.folkland.manager.transfer.TransferList;

import java.util.ArrayList;
import java.util.List;

class CreateClubsForTest {

    static List<Club> getClubsList(int clubCount) {
        List<Club> clubs = new ArrayList<>();
        TransferList transferList = new TransferList(100);
        while (clubs.size() < clubCount)
            clubs.add(GenerateClub.createClubWithTransfer(transferList));
        return clubs;
    }
}
