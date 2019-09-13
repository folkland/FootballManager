package ru.folkland.manager.clubs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ru.folkland.manager.player.FootballPosition;
import ru.folkland.manager.player.Player;
import ru.folkland.manager.player.PlayerStatus;
import ru.folkland.manager.transfer.TransferList;

/**
 * Класс описывающий Клуб как организацию
 */
public class Club {

	private int id;
	//название каманды
	private String name;

	//список игроков
	private List<Player> players;
	
	//расстановка клуба
	private FootballScheme fScheme;
	//схема по которой играет команда
	private Scheme scheme;

	public Club (int id, String name, FootballScheme fScheme) {
		setId(id);
		this.fScheme = fScheme;
		scheme = new Scheme(fScheme);
		this.setName(name);
		players = new ArrayList<>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		for (Player player: players) {
			addPlayer(player);
		}
	}

	public void addPlayer(Player player) {
		player.setClub(id);
		player.setStatus(PlayerStatus.inClub);
		players.add(player);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Scheme getScheme() {
		return scheme;
	}

	public void setScheme(Scheme scheme) {
		this.scheme = scheme;
	}

	public void compositionFormation(TransferList transferList) {
		transferList.needMorePlayers(20);
		List<Player> goalKeepers = transferList.getPlayerForPosition(FootballPosition.goalkeeper);
		while (goalKeepers.size() < 2) {
			transferList.needMorePlayers(5);
			goalKeepers = transferList.getPlayerForPosition(FootballPosition.goalkeeper);
		}
		transferList.choosedPlayers(goalKeepers.subList(0,2));
		setPlayers(goalKeepers.subList(0,2));
		List<Player> defenders = transferList.getPlayerForPosition(FootballPosition.defender);
		while (defenders.size() < scheme.getDefender() * 2) {
			transferList.needMorePlayers(5);
			defenders = transferList.getPlayerForPosition(FootballPosition.defender);
		}
		transferList.choosedPlayers(defenders.subList(0,scheme.getDefender() * 2));
		setPlayers(defenders.subList(0,scheme.getDefender() * 2));
		List<Player> midfielders = transferList.getPlayerForPosition(FootballPosition.midfielder);
		while (midfielders.size() < scheme.getMidfielder() * 2) {
			transferList.needMorePlayers(5);
			midfielders = transferList.getPlayerForPosition(FootballPosition.midfielder);
		}
		transferList.choosedPlayers(midfielders.subList(0,scheme.getMidfielder() * 2));
		setPlayers(midfielders.subList(0,scheme.getMidfielder() * 2));
		List<Player> forwards = transferList.getPlayerForPosition(FootballPosition.forward);
		while (forwards.size() < scheme.getForward() * 2) {
			transferList.needMorePlayers(5);
			forwards = transferList.getPlayerForPosition(FootballPosition.forward);
		}
		transferList.choosedPlayers(forwards.subList(0,scheme.getForward() * 2));
		setPlayers(forwards.subList(0,scheme.getForward() * 2));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Club club = (Club) o;
		return id == club.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append('[');
		for (Player player: players) {
			builder.append(player.toString());
		}
		builder.append(']');
		return "Club{" +
				"id=" + id +
				", name='" + name + '\'' +
				", players=" + builder.toString() +
				", fScheme=" + fScheme +
				'}';
	}

	public int getTotalClubStreinght() {
		int s = 0;
		for (Player player: players) {
			s += player.getSkill();
		}
		return s;
	}
}
