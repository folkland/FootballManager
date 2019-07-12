package ru.farhutdinovar.manager.clubs;

import java.util.Comparator;

import ru.farhutdinovar.manager.player.Player;

public class SortingPlayers implements Comparator<Player> {

	@Override
	public int compare(Player o1, Player o2) {
		return -(int)o1.getSkills()+(int)o2.getSkills();
	}
	
}
