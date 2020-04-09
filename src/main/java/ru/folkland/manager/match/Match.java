package ru.folkland.manager.match;

import ru.folkland.manager.league.TeamSeason;
import ru.folkland.constants.Constants;

public class Match {

	private TeamSeason home;
	private TeamSeason guest;

	private int homeStrength;
	private int guestStrength;
	
	public Match (TeamSeason home, TeamSeason guest) {
		this.home = home;
		this.guest = guest;
	}

	/**
	 * Запускаем проигрывание матча
	 * @return счёт в матче
	 */
	public Total playMatch () {
		TeamMainCast homeSquad = new TeamMainCast(home.getClub());
		TeamMainCast guestSquad = new TeamMainCast(guest.getClub());
		homeStrength = homeSquad.playerStrength() + Constants.HOME_CLUB_BONUS_TO_STRENGTH;
		guestStrength = guestSquad.playerStrength();
		Total total = totalScore();
		homeSquad.training();
		guestSquad.training();
		return total;
	}

	/**
	 * Проводим матч
	 * @return результат матча
	 */
	private Total totalScore() {
		Total total = new Total();
		int minimumCanScored = 0, maximumCanScored = 1;
		int homeChance = Constants.RANDOM.nextInt(homeStrength);
		int guestChance = Constants.RANDOM.nextInt(guestStrength);
		int maxWinnerTeamScored = maximumCanScored + Constants.RANDOM.nextInt(Constants.MAX_GAME_SCORED_COUNT);
		if (draw(homeChance, guestChance)) {
			int sc = scored(Constants.MAX_DRAW_SCORED_COUNT, minimumCanScored);
			total.setGuestScore(sc);
			total.setHomeScore(sc);
			total.setWinner(Winner.draw);
		} else if (homeChance > guestChance) {
			int sc = scored(maxWinnerTeamScored, maximumCanScored);
			total.setHomeScore(sc);
			total.setGuestScore(scored(sc, minimumCanScored));
			total.setWinner(Winner.home);
		} else {
			int sc = scored(maxWinnerTeamScored, maximumCanScored);
			total.setGuestScore(sc);
			total.setHomeScore(scored(sc, minimumCanScored));
			total.setWinner(Winner.guest);
		}
		return total;
	}

	/**
	 * Определяет количество забитых голов
	 * @param max максимально возможное значение, чтобы забить
	 * @param min минимальное возможное значение, чтобы забить
	 * @return количество забитых
	 */
	private int scored(int max, int min) {
		return min + Constants.RANDOM.nextInt(max);
	}

	/**
	 * Система выявления ничьи
	 * Простор для развития в части непредсказуемости игры есть, нужно думать как усложнить систему
	 * @param h сила способностей домашней команды
	 * @param g сила способностей гостевой команды
	 * @return ничья или нет
	 */
	private boolean draw(int h, int g) {
		return (h == g && Math.abs(h - g) < Constants.TEAM_STRENGTH_EQUALS);
	}

	public TeamSeason getHome() {
		return home;
	}

	public TeamSeason getGuest() {
		return guest;
	}

	@Override
	public String toString() {
		return home.getClub().getName() + " : " + guest.getClub().getName();
	}
}
