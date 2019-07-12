package ru.folkland.manager.match;

import ru.folkland.manager.clubs.Team;
import ru.folkland.manager.constants.Constants;

import java.security.SecureRandom;

public class Match {

	private SecureRandom random = new SecureRandom();

	private Team home;
	private Team guest;

	private int homeStrength;
	private int guestStrength;
	
	private int bonus = 30;
	
	public Match (Team home, Team guest) {
		this.home = home;
		this.guest = guest;
	}

	/**
	 * Запускаем проигрывание матча
	 * @return счёт в матче
	 */
	public Total playMatch () {
		Total total = null;
		TeamMainCast homeSquad = new TeamMainCast(home.getClub());
		TeamMainCast guestSquad = new TeamMainCast(guest.getClub());
		homeStrength = homeSquad.playerStrength() + Constants.HOME_CLUB_BONUS_TO_STRENGTH;
		guestStrength = guestSquad.playerStrength();
		total = totalScore();
		homeSquad.training();
		guestSquad.training();
		return total;
	}
	
	//устанавливаем счёт в матче
	private Total totalScore() {
		Total total = new Total();
		int a = 0, b = 1, maxWinner = 7, maxDraw = 3;
		//рандомом определяем победителя
		int homeChance = random.nextInt(homeStrength);
		int guestChance = random.nextInt(guestStrength);
		int maxScoredPerTeam = b + random.nextInt(maxWinner);//TODO I stop here
		if (draw(homeChance, guestChance)) {
			int sc = scored(maxDraw, a);
			total.setGuestScore(sc);
			total.setHomeScore(sc);
			total.setWinner(Winner.draw);
		} else if (homeChance > guestChance) {
			int sc = scored(maxScoredPerTeam, b);
			total.setHomeScore(sc);
			total.setGuestScore(scored(sc, a));
			total.setWinner(Winner.home);
		} else {
			int sc = scored(maxScoredPerTeam, b);
			total.setGuestScore(sc);
			total.setHomeScore(scored(sc, a));
			total.setWinner(Winner.guest);
		}
		return total;
	}
	
	//количество забитых голов
	private int scored(int max, int min) {
		return min + (int)(Math.random()*(max-1));
	}
	
	private boolean draw(int h, int g) {
		if (h == g) return true;
		if (Math.abs(h - g) <= 10)
			return true;
		return false;
	}
}
