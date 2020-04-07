package ru.folkland.manager.clubs;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Описание элемента турнирной таблицы
 * @author Farhutdinov
 *
 */
@Getter
@EqualsAndHashCode
public class Club implements Comparable {

	private Team team;

	private int matchCount;
	private int points;
	private int victory;
	private int draw;
	private int lose;

	private int scored;
	private int missed;
	
	public Club(Team team) {
		this.team = team;
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
	
	@Override
	public String toString() {
		return team.getName()+" | "+matchCount+" | "+points+" | "+victory+" | "+draw+" | "+lose + " | " + scored + " | " + missed;
	}

	@Override
	public int compareTo(Object o) {
		Club club = (Club) o;
		if (club.points == points) {
			if (club.scored == scored) {
				return missed - club.missed;
			}
			return club.scored - scored;
		}
		return club.points - points;
	}
}
