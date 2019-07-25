package ru.folkland.manager.league;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.folkland.manager.clubs.Club;
import ru.folkland.manager.clubs.Team;
import ru.folkland.manager.match.Winner;

/**
 * Турнирная таблица лиги
 * @author Farhutdinov
 *
 */
public class TournamentTable {

	private List<Team> table;
	
	public TournamentTable(List<Club> clubs) {
		table = new ArrayList<>();
		for (Club club: clubs) {
			Team team = new Team(club);
			table.add(team);
		}
	}

	//очищаем результаты в начале сезона
	public void clearTournament() {
		for (Team team: table) {
			team.clearStats();
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
	public void setPoints(Team home, Team guest, Winner winner) {
		switch(winner) {
			case home: home.win(); guest.lose(); break;
			case draw: home.draw(); guest.draw(); break;
			case guest: home.lose(); guest.win(); break;
			default: break;
		}
	}
	
	public List<Team> getTable() {
		return table;
	}

	public void setTable(List<Team> table) {
		this.table = table;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("  | Team name | MC | P | W | D | L\n");
		int i = 1;
		for (Team team: table) {
			str.append(i);
			str.append("| ");
			str.append(team.toString());
			str.append("\n");
			i++;
		}
		return str.toString();
	}
}
