package ru.farhutdinovar.manager.league;

import ru.farhutdinovar.manager.clubs.Club;
/**
 * Описание элемента турнирной таблицы
 * @author Farhutdinov
 *
 */
public class Team {
	private Club club;
	private int matchCount;
	private int points;
	private int victory;
	private int draw;
	private int lose;
	
	public Team(Club club) {
		this.club = club;
		matchCount = 0;
		points = 0;
		victory = 0;
		draw = 0;
		lose = 0;
	}
	
	//очитска статистики клуба в начале сезона
	public void clearStats() {
		matchCount = 0;
		points = 0;
		victory = 0;
		draw = 0;
		lose = 0;
	}
	//результаты матчей
	public void win() {
		matchCount++;
		points = points+3;
		victory++;
	}
	public void draw() {
		matchCount++;
		points++;
		draw++;
	}
	public void lose() {
		matchCount++;
		lose++;
	}
	
	public Club getClub() {
		return club;
	}
	public void setClub(Club club) {
		this.club = club;
	}
	public int getMatchCount() {
		return matchCount;
	}
	public void setMatchCount(int matchCount) {
		this.matchCount = matchCount;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getVictory() {
		return victory;
	}
	public void setVictory(int victory) {
		this.victory = victory;
	}
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getLose() {
		return lose;
	}
	public void setLose(int lose) {
		this.lose = lose;
	}
	
	@Override
	public String toString() {
		String str = club.getName()+" "+matchCount+" | "+points+" | "+victory+" | "+draw+" | "+lose;
		return str;
	}
}
