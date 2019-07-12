package ru.farhutdinovar.manager.league;

//счёт матча
public class Total {
	private int homeScore;
	private int guestScore;
	private int result;
	
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
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String toString() {
		return homeScore + ":" + guestScore;
	}
}
