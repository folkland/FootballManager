package ru.folkland.manager.clubs;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.folkland.constants.Constants;
import ru.folkland.manager.player.FootballPosition;
import ru.folkland.manager.player.Player;
import ru.folkland.manager.player.PlayerStatus;
import ru.folkland.manager.transfer.PlayerCost;
import ru.folkland.manager.transfer.TransferList;

import java.util.*;

/**
 * Класс описывающий Клуб как организацию
 */
@Getter
@EqualsAndHashCode
@ToString(of = {"id", "name", "players", "fScheme"})
public class Club {

    private static final int MILLION = 1_000_000;
    private static int team_counter = 0;

    private final int id;
    //название каманды
    private final String name;

    private int budgetOfClub;
    private int spendedBudget;

    //список игроков
    private List<Player> players;
    private int strength;

    //расстановка клуба
    private FootballScheme fScheme;

    public Club(String name, FootballScheme fScheme) {
        id = team_counter;
        team_counter++;
        this.fScheme = fScheme;
//        scheme = new Scheme(fScheme);
        this.name = name;
        players = new ArrayList<>();
        budgetOfClub = (Constants.RANDOM.nextInt(Constants.AVERAGE_CLUB_BUDGET) + Constants.MIN_CLUB_BUDGET) * MILLION;
    }

    public void addPlayer(Player player) {
        player.setClub(id);
        player.setStatus(PlayerStatus.inClub);
        players.add(player);
        strength = getTotalClubStrength();
    }

    private void addPlayers(List<PlayerCost> playerCosts) {
        playerCosts.forEach(playerCost -> addPlayer(playerCost.getPlayer()));
    }

    public int getTotalClubStrength() {
        Collections.sort(players);
        strength = (int) players.stream().mapToDouble(Player::getSkill).sum();
        return strength;
    }

    public void oneYearLeft(int seasonIncome) {
        players.forEach(Player::leftOneYear);
        budgetOfClub += seasonIncome * MILLION;
    }

    public void firstCompositionFormation(TransferList transferList) {
        transferList.needMorePlayers(20);
        List<FootballPosition> positions = new ArrayList<>();
        positions.add(FootballPosition.goalkeeper);
        positions.add(FootballPosition.defender);
        positions.add(FootballPosition.midfielder);
        positions.add(FootballPosition.forward);
        while (positions.size() != 0) {
            FootballPosition position = positions.remove(Constants.RANDOM.nextInt(positions.size()));
            composePosition(transferList, position, fScheme.countOfPosition(position) * 2);
        }
        updateFormation(transferList);
    }

    private void composePosition(TransferList transferList, FootballPosition position, int needPlayerCount) {
        double averageCostOfPlayers = (budgetOfClub - spendedBudget) / (22 - players.size());
        double economyBudget = 0;
        List<PlayerCost> selected = new ArrayList<>();
        List<PlayerCost> forPosition = transferList.getPlayerForPosition(position);
        while (selected.size() < needPlayerCount) {
            PlayerCost suitablePlayer = getSuitablePlayer((int) (averageCostOfPlayers + economyBudget), forPosition);
            if (suitablePlayer == null)
                forPosition = getPlayerToPosition(transferList, position, needPlayerCount + selected.size());
            else {
                selected.add(suitablePlayer);
                transferList.choosedPlayer(suitablePlayer);
                forPosition.remove(suitablePlayer);
                economyBudget += averageCostOfPlayers - suitablePlayer.getCost();
            }
        }
        spendedBudget = spendedBudget + (int) selected.stream().mapToDouble(PlayerCost::getCost).sum();
        addPlayers(selected);
    }

    private List<PlayerCost> getPlayerToPosition(TransferList transferList, FootballPosition position, int needPlayerCount) {
        List<PlayerCost> forPosition;
        do {
            transferList.needMorePlayers(5);
            forPosition = transferList.getPlayerForPosition(position);
        } while (forPosition.size() < needPlayerCount);
        return forPosition;
    }

    private PlayerCost getSuitablePlayer(int availableBudget, List<PlayerCost> playerForPosition) {
        PlayerCost playerCost = null;
        for (PlayerCost pc : playerForPosition) {
            if (pc.getCost() < availableBudget) {
                playerCost = pc;
                break;
            }
        }
        return playerCost;
    }

    private void updateFormation(TransferList transferList) {
        double economy = budgetOfClub - spendedBudget;
        while (economy >= MILLION) {
            Player playerForSell = players.get(players.size() - 1);
            FootballPosition position = playerForSell.getPosition();
            double cost = transferList.addToTransferPlayer(playerForSell);
            economy += cost;
            players.remove(playerForSell);
            List<PlayerCost> playerToPosition = getPlayerToPosition(transferList, position, 1);
            PlayerCost suitablePlayer = getSuitablePlayer((int) economy, playerToPosition);
            economy = economy - suitablePlayer.getCost();
            addPlayer(suitablePlayer.getPlayer());
            transferList.choosedPlayer(suitablePlayer);
            spendedBudget = budgetOfClub - (int) economy;
        }
    }
}
