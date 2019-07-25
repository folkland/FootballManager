package ru.folkland.manager.league;

import java.util.ArrayList;
import java.util.List;

import ru.folkland.manager.clubs.Club;

public class League {

	private int id;
	private String country;
	
	private List<Club> clubs;
	
	private Season season;

	public League() {
		clubs = new ArrayList<>();
	}

	public void addClub(Club club) {
		clubs.add(club);
	}

	public void addClubs(List<Club> clubs) {
		for (Club club: clubs) {
			addClub(club);
		}
	}

	public void createSeason() {
		season = new Season(clubs);
	}
}
