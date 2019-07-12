package ru.folkland.manager.league;

public class Match {

	private Team home;
	private Team guest;
	
	private int bonus = 30;
	
	public Match (Team home, Team guest) {
		this.home = home;
		this.guest = guest;
	}

	public Total playMatch () {
		Total total = null;
		home.getClub().playerStrenght();
		guest.getClub().playerStrenght();
		home.getClub().setStreinghtTotal(home.getClub().getStreinghtTotal() + bonus);
		total = totalScore();
		return total;
	}
	
	//устанавливаем счёт в матче
	private Total totalScore() {
		Total total = new Total();
		int a = 0, b = 1, maxWinner = 7, maxDraw = 3;
		//рандомом определяем победителя
		int hChance = a + (int)(Math.random()*home.getClub().getStreinghtTotal());
		int gChance = a + (int)(Math.random()*guest.getClub().getStreinghtTotal());
		int maxScoredPerTeam = b + (int)(Math.random()*maxWinner); 
		if (hChance >= gChance) {
			if (!draw(hChance, gChance)) {
				int sc = scored(maxScoredPerTeam, b);
				total.setHomeScore(sc);
				total.setGuestScore(scored(sc, a));
				total.setResult(0);
			}
			else {
				int sc = scored(maxDraw, a);
				total.setGuestScore(sc);
				total.setHomeScore(sc);
				total.setResult(1);
			}
		}
		else {
			if (!draw(gChance, hChance)) {
				int sc = scored(maxScoredPerTeam, b);
				total.setGuestScore(sc);
				total.setHomeScore(scored(sc, a));
				total.setResult(2);
			}
			else {
				int sc = scored(maxDraw, a);
				total.setGuestScore(sc);
				total.setHomeScore(sc);
				total.setResult(1);
			}
		}
		return total;
	}
	
	//количество забитых голов
	private int scored(int max, int min) {
		return min + (int)(Math.random()*(max-1));
	}
	
	private boolean draw(int h, int g) {
		if (h-g<=10)
			return true;
		return false;
	}
	
	public Team getHome() {
		return home;
	}

	public void setHome(Team home) {
		this.home = home;
	}

	public Team getGuest() {
		return guest;
	}

	public void setGuests(Team guest) {
		this.guest = guest;
	}
}
