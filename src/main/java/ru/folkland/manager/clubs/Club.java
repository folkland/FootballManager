package ru.folkland.manager.clubs;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.folkland.manager.player.FootballPosition;
import ru.folkland.manager.player.Player;
import ru.folkland.manager.player.PlayerStatus;
import ru.folkland.manager.transfer.PlayerCost;
import ru.folkland.manager.transfer.TransferList;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывающий Клуб как организацию
 */
@Getter
@EqualsAndHashCode
@ToString(of = {"id", "name", "players", "fScheme"})
public class Club {

	private static int team_counter = 0;

	private int id;
	//название каманды
	private String name;

	//список игроков
	private List<Player> players;
	
	//расстановка клуба
	private FootballScheme fScheme;
	//схема по которой играет команда
	private Scheme scheme;

	public Club(String name, FootballScheme fScheme) {
		id = team_counter;
		this.fScheme = fScheme;
		scheme = new Scheme(fScheme);
		this.name = name;
		players = new ArrayList<>();
		team_counter++;
	}

	public void addPlayer(Player player) {
		player.setClub(id);
		player.setStatus(PlayerStatus.inClub);
		players.add(player);
	}

	private void addPlayers(List<PlayerCost> playerCosts) {
		playerCosts.forEach(playerCost -> addPlayer(playerCost.getPlayer()));
	}

	public void compositionFormation(TransferList transferList) {
		transferList.needMorePlayers(20);
		composePosition(transferList, FootballPosition.goalkeeper, 1);
		composePosition(transferList, FootballPosition.defender, scheme.getDefender());
		composePosition(transferList, FootballPosition.midfielder, scheme.getMidfielder());
		composePosition(transferList, FootballPosition.forward, scheme.getForward());
	}

	private void composePosition(TransferList transferList, FootballPosition position, int needPlayerCount) {
		needPlayerCount = needPlayerCount * 2;
		List<PlayerCost> forPosition = transferList.getPlayerForPosition(position);
		while (forPosition.size() < needPlayerCount) {
			transferList.needMorePlayers(5);
			forPosition = transferList.getPlayerForPosition(position);
		}
		transferList.choosedPlayers(forPosition.subList(0, needPlayerCount));
		addPlayers(forPosition.subList(0, needPlayerCount));
	}

	public int getTotalClubStreinght() {
		return (int) players.stream().mapToDouble(Player::getSkill).sum();
	}
}
