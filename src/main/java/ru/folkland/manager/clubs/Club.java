package ru.folkland.manager.clubs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ru.folkland.manager.player.Player;

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
		this.players = players;
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
}
