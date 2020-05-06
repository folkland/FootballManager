package ru.folkland.test;

import ru.folkland.generate.GenerateClub;
import ru.folkland.manager.clubs.Club;
import ru.folkland.manager.transfer.TransferList;

import java.util.ArrayList;
import java.util.List;

public class CreateClubsForTest {

    public static List<Club> getClubsList(int clubCount, TransferList transferList) {
        List<Club> clubs = new ArrayList<>();
        while (clubs.size() < clubCount)
            clubs.add(GenerateClub.createClubWithTransfer(transferList));
        return clubs;
    }
}
