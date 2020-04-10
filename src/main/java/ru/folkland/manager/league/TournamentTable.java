package ru.folkland.manager.league;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.folkland.manager.clubs.Club;
import ru.folkland.manager.match.Winner;

/**
 * Турнирная таблица лиги
 * @author Farhutdinov
 *
 */
public class TournamentTable {

	private List<TeamSeason> table;
	
	TournamentTable(List<Club> clubs) {
		table = new ArrayList<>();
		for (Club club : clubs) {
			TeamSeason teamSeason = new TeamSeason(club);
			table.add(teamSeason);
		}
	}
	
	//сортируем клубы после каждого тура
	void sortTable() {
		Collections.sort(table);
	}

	/**
	 * Раздаём очки после матча
	 * @param home команда хозяев
	 * @param guest команда гостей
	 * @param winner результат встречи
	 */
	void setPoints(TeamSeason home, TeamSeason guest, Winner winner, int homeScored, int guestScored) {
		switch(winner) {
			case home: home.win(); guest.lose(); break;
			case draw: home.draw(); guest.draw(); break;
			case guest: home.lose(); guest.win(); break;
			default: break;
		}
		home.scored(homeScored, guestScored);
		guest.scored(guestScored, homeScored);
	}
	
	List<TeamSeason> getTable() {
		return table;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(" | Team name | MC | P | W | D | L | S | M\n");
		int i = 1;
		for (TeamSeason teamSeason : table) {
			str.append(i);
			str.append("| ");
			str.append(teamSeason.toString());
			str.append("\n");
			i++;
		}
		return str.toString();
	}
}
