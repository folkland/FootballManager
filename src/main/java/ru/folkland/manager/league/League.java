package ru.folkland.manager.league;

import ru.folkland.manager.clubs.Club;

import java.util.ArrayList;
import java.util.List;

public class League {

	private int id;
	private String country;
	
	private List<Club> clubs;
	
	private Season season;

	public League() {
		clubs = new ArrayList<>();
	}

	private void addClub(Club club) {
		clubs.add(club);
	}

	void addClubs(List<Club> clubs) {
		for (Club club : clubs) {
			addClub(club);
		}
	}

	void createSeason() {
		season = new Season(clubs);
	}
}
