package ru.folkland.manager.league;

import java.util.ArrayList;
import java.util.List;

import ru.folkland.manager.clubs.Team;

public class League {

	private int id;
	private String country;
	
	private List<Team> teams;
	
	private Season season;

	public League() {
		teams = new ArrayList<>();
	}

	public void addClub(Team team) {
		teams.add(team);
	}

	public void addClubs(List<Team> teams) {
		for (Team team : teams) {
			addClub(team);
		}
	}

	public void createSeason() {
		season = new Season(teams);
	}
}
