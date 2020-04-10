package ru.folkland.manager.league;

import ru.folkland.manager.clubs.Club;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class League {

	private int id;
	private String country;
	
	private List<Club> clubs;
	
	private Map<Integer,Season> seasons;

	public League() {
		clubs = new ArrayList<>();
		seasons = new HashMap<>();
	}

	private void addClub(Club club) {
		clubs.add(club);
	}

	public void addClubs(List<Club> clubs) {
		for (Club club : clubs) {
			addClub(club);
		}
	}

	private void createSeason() {
		seasons.put(seasons.size(), new Season(clubs));
	}

	private void playAllTourActualSeason() {
		Season season = seasons.get(seasons.size() - 1);
		season.playAllTours();
	}

	public void startLeague(int countSeasons) {
		while (seasons.size() < countSeasons) {
			createSeason();
			playAllTourActualSeason();
			showSeasonResult();
		}
	}

	private void showSeasonResult() {
		System.out.println("=======Season "+seasons.size()+"=======");
		for (Club club: clubs) {
			System.out.println(club.getName() + " " + club.getTotalClubStreinght());
		}
		System.out.println("==============");
		System.out.println(seasons.get(seasons.size() - 1).showTable());
	}
}
