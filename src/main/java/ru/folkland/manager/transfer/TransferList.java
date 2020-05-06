package ru.folkland.manager.transfer;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.folkland.constants.Constants;
import ru.folkland.generate.GeneratePlayer;
import ru.folkland.generate.SpringNameGenerate;
import ru.folkland.manager.player.FootballPosition;
import ru.folkland.manager.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author folkland
 */
@Getter
@Component
public class TransferList implements ITransferList {

    private SpringNameGenerate nameGenerate;

    private List<PlayerCost> freePlayers;
    private List<PlayerCost> toTransferPlayers;

    @Autowired
    private  TransferList(SpringNameGenerate nameGenerate) {
        this.nameGenerate = nameGenerate;
        freePlayers = new ArrayList<>();
        toTransferPlayers = new ArrayList<>();
        pullPlayers(10);
    }

    @Override
    public void pullPlayers(int startCount) {
        needMorePlayers(startCount);
    }

    @Override
    public List<PlayerCost> getAllAvailablePlayers() {
        List<PlayerCost> allPlayers = new ArrayList<>();
        allPlayers.addAll(freePlayers);
        allPlayers.addAll(toTransferPlayers);
        return allPlayers;
    }

    @Override
    public List<PlayerCost> sortAvailablePlayers() {
        List<PlayerCost> allAvailablePlayers = getAllAvailablePlayers();
        Collections.sort(allAvailablePlayers);
        return allAvailablePlayers;
    }

    @Override
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
        Collections.sort(foundedPlayers);
        return foundedPlayers;
    }

    public static TransferList newTransferList(int startCount) {
        TransferList transferList = new TransferList(null);
        transferList.pullPlayers(startCount);
        return transferList;
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
            freePlayers.add(new PlayerCost(GeneratePlayer.createPlayer(nameGenerate)));
        }
    }

    private void generateNewPlayers() {
        int playerCount = Constants.RANDOM.nextInt(20);
        for (int i = 0; i < playerCount; i++) {
            freePlayers.add(PlayerCost.newPlayerCost(GeneratePlayer.createPlayer(nameGenerate)));
        }
    }

    public void addFreePlayer(Player player) {
        freePlayers.add(PlayerCost.newPlayerCost(player));
    }

    public double addToTransferPlayer(Player player) {
        PlayerCost playerCost = PlayerCost.newPlayerCost(player);
        toTransferPlayers.add(playerCost);
        return playerCost.getCost();
    }
}
