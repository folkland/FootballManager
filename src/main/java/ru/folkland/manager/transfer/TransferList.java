package ru.folkland.manager.transfer;

import ru.folkland.constants.Constants;
import ru.folkland.generate.GeneratePlayer;
import ru.folkland.manager.player.FootballPosition;
import ru.folkland.manager.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author folkland
 */
public class TransferList {

    private List<Player> freePlayers;
    private List<Player> toTransferPlayers;

    public TransferList(int startCount) {
        freePlayers = new ArrayList<>();
        toTransferPlayers = new ArrayList<>();
        needMorePlayers(startCount);
    }

    public void choosedPlayer(Player player) {
        if (freePlayers.contains(player)) {
            freePlayers.remove(player);
        }
        if (toTransferPlayers.contains(player)) {
            toTransferPlayers.remove(player);
        }
    }

    public void choosedPlayers(List<Player> players) {
        for (Player player: players) {
            choosedPlayer(player);
        }
    }

    public void needMorePlayers(int count) {
        for (int i = 0; i < count; i++) {
            freePlayers.add(GeneratePlayer.createPlayer(i));
        }
    }

    private void generateNewPlayers() {
        int playerCount = Constants.RANDOM.nextInt(20);
        for (int i = 0; i < playerCount; i++) {
            freePlayers.add(GeneratePlayer.createPlayer(i));
        }
    }

    public void addFreePlayer(Player player) {
        freePlayers.add(player);
    }

    public void addToTransferPlayer(Player player) {
        toTransferPlayers.add(player);
    }

    public List<Player> getFreePlayers() {
        return freePlayers;
    }

    public List<Player> getToTransferPlayers() {
        return toTransferPlayers;
    }

    public List<Player> getPlayerForPosition(FootballPosition position) {
        List<Player> allPlayers = new ArrayList<>();
        allPlayers.addAll(freePlayers);
        allPlayers.addAll(toTransferPlayers);
        List<Player> foundedPlayers = new ArrayList<>();
        for (Player player: allPlayers) {
            if (position.equals(player.getPosition())) {
                foundedPlayers.add(player);
            }
        }
        Collections.sort(foundedPlayers);
        return foundedPlayers;
    }
}
