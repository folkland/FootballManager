package ru.folkland.manager.league;

import ru.folkland.manager.clubs.Team;

import java.util.Comparator;

public class SortingTeam implements Comparator<Team> {

	@Override
	public int compare(Team o1, Team o2) {
		return -(int)o1.getPoints()+(int)o2.getPoints();
	}

}
