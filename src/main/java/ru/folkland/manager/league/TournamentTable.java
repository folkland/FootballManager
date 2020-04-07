package ru.folkland.manager.league;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.folkland.manager.clubs.Team;
import ru.folkland.manager.clubs.Club;
import ru.folkland.manager.match.Winner;

/**
 * Турнирная таблица лиги
 * @author Farhutdinov
 *
 */
public class TournamentTable {

	private List<Club> table;
	
	public TournamentTable(List<Team> teams) {
		table = new ArrayList<>();
		for (Team team : teams) {
			Club club = new Club(team);
			table.add(club);
		}
	}

	//очищаем результаты в начале сезона
	public void clearTournament() {
		for (Club club : table) {
			club.clearStats();
		}
	}
	
	//сортируем клубы после каждого тура
	public void sortTable() {
		Collections.sort(table);
	}

	/**
	 * Раздаём очки после матча
	 * @param home команда хозяев
	 * @param guest команда гостей
	 * @param winner результат встречи
	 */
	public void setPoints(Club home, Club guest, Winner winner, int homeScored, int guestScored) {
		switch(winner) {
			case home: home.win(); guest.lose(); break;
			case draw: home.draw(); guest.draw(); break;
			case guest: home.lose(); guest.win(); break;
			default: break;
		}
		home.scored(homeScored, guestScored);
		guest.scored(guestScored, homeScored);
	}
	
	public List<Club> getTable() {
		return table;
	}

	public void setTable(List<Club> table) {
		this.table = table;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(" | Team name | MC | P | W | D | L | S | M\n");
		int i = 1;
		for (Club club : table) {
			str.append(i);
			str.append("| ");
			str.append(club.toString());
			str.append("\n");
			i++;
		}
		return str.toString();
	}
}
