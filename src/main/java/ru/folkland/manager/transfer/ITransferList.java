package ru.folkland.manager.transfer;

import ru.folkland.manager.player.FootballPosition;

import java.util.List;

public interface ITransferList {

    void pullPlayers(int startCount);

    List<PlayerCost> getAllAvailablePlayers();

    List<PlayerCost> sortAvailablePlayers();

    List<PlayerCost> getPlayerForPosition(FootballPosition position);
}
