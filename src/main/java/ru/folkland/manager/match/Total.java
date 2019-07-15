package ru.folkland.manager.match;

/**
 * Результат матча
 * @author folkland
 */
public class Total {

	private int homeScore;
	private int guestScore;
	private Winner winner;

	public int getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}

	public int getGuestScore() {
		return guestScore;
	}

	public void setGuestScore(int guestScore) {
		this.guestScore = guestScore;
	}

	public Winner getWinner() {
		return winner;
	}

	public void setWinner(Winner winner) {
		this.winner = winner;
	}
}
