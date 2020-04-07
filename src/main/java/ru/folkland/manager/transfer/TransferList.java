package ru.folkland.manager.transfer;

import lombok.Getter;
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
@Getter
public class TransferList {

    private List<PlayerCost> freePlayers;
    private List<PlayerCost> toTransferPlayers;

    public TransferList(int startCount) {
        freePlayers = new ArrayList<>();
        toTransferPlayers = new ArrayList<>();
        needMorePlayers(startCount);
    }

    public void choosedPlayer(PlayerCost player) {
        if (freePlayers.contains(player)) {
            freePlayers.remove(player);
        }
        if (toTransferPlayers.contains(player)) {
            toTransferPlayers.remove(player);
        }
    }

    public void choosedPlayers(List<PlayerCost> players) {
        for (PlayerCost player: players) {
            choosedPlayer(player);
        }
    }

    public void needMorePlayers(int count) {
        for (int i = 0; i < count; i++) {
            freePlayers.add(new PlayerCost(GeneratePlayer.createPlayer()));
        }
    }

    private void generateNewPlayers() {
        int playerCount = Constants.RANDOM.nextInt(20);
        for (int i = 0; i < playerCount; i++) {
            freePlayers.add(new PlayerCost(GeneratePlayer.createPlayer()));
        }
    }

    public void addFreePlayer(Player player) {
        freePlayers.add(new PlayerCost(player));
    }

    public void addToTransferPlayer(Player player) {
        toTransferPlayers.add(new PlayerCost(player));
    }

    public List<PlayerCost> getPlayerForPosition(FootballPosition position) {
        List<PlayerCost> allPlayers = new ArrayList<>();
        allPlayers.addAll(freePlayers);
        allPlayers.addAll(toTransferPlayers);
        List<PlayerCost> foundedPlayers = new ArrayList<>();
        for (PlayerCost player: allPlayers) {
            if (position.equals(player.getPlayer().getPosition())) {
                foundedPlayers.add(player);
            }
        }
        foundedPlayers.stream().forEach(PlayerCost::calculateTransferCost);
        Collections.sort(foundedPlayers);
        return foundedPlayers;
    }

    public List<PlayerCost> getAllAvailablePlayer() {
        List<PlayerCost> allPlayers = new ArrayList<>();
        allPlayers.addAll(freePlayers);
        allPlayers.addAll(toTransferPlayers);
        allPlayers.parallelStream().forEach(PlayerCost::calculateTransferCost);
        return allPlayers;
    }
}
