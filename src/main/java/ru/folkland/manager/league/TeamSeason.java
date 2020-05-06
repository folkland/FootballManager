package ru.folkland.manager.league;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.folkland.manager.clubs.Club;

/**
 * Описание элемента турнирной таблицы
 * @author Farhutdinov
 *
 */
@Getter
@EqualsAndHashCode
public class TeamSeason implements Comparable<TeamSeason> {

	private Club club;

	private int matchCount;
	private int points;
	private int victory;
	private int draw;
	private int lose;

	private int scored;
	private int missed;
	
	TeamSeason(Club club) {
		this.club = club;
//		matchCount = 0;
//		points = 0;
//		victory = 0;
//		draw = 0;
//		lose = 0;
	}

	//результаты матчей
	void win() {
		matchCount++;
		points = points + 3;
		victory++;
	}
	void draw() {
		matchCount++;
		points++;
		draw++;
	}
	void lose() {
		matchCount++;
		lose++;
	}
	void scored(int scored, int missed) {
		this.scored += scored;
		this.missed += missed;
	}

	void seasonEnd(int seasonIncome) {
		club.oneYearLeft(seasonIncome);
	}

	public void matchPlayed() {
		club.getTotalClubStrength();
	}
	
	@Override
	public String toString() {
		return club.getName()+" | "+matchCount+" | "+points+" | "+victory+" | "+draw+" | "+lose + " | " + scored + " | " + missed;
	}

	@Override
	public int compareTo(TeamSeason teamSeason) {
		if (teamSeason.points == points) {
			if (teamSeason.scored == scored) {
				return missed - teamSeason.missed;
			}
			return teamSeason.scored - scored;
		}
		return teamSeason.points - points;
	}
}
