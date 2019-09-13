package ru.folkland.manager.clubs;

import java.util.Objects;

/**
 * Описание элемента турнирной таблицы
 * @author Farhutdinov
 *
 */
public class Team implements Comparable {

	private Club club;

	private int matchCount;
	private int points;
	private int victory;
	private int draw;
	private int lose;

	private int scored;
	private int missed;
	
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
		points = points + 3;
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
	public void scored(int scored, int missed) {
		this.scored += scored;
		this.missed += missed;
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
		return club.getName()+" | "+matchCount+" | "+points+" | "+victory+" | "+draw+" | "+lose + " | " + scored + " | " + missed;
	}

	@Override
	public int compareTo(Object o) {
		Team team = (Team) o;
		if (team.points == points) {
			if (team.scored == scored) {
				return missed - team.missed;
			}
			return team.scored - scored;
		}
		return team.points - points;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Team team = (Team) o;
		return Objects.equals(club, team.club);
	}

	@Override
	public int hashCode() {
		return Objects.hash(club);
	}
}
