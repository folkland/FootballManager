package ru.folkland.manager.league;

import java.util.ArrayList;
import java.util.List;

import ru.folkland.manager.clubs.Club;

/**
 * Турнирная таблица лиги
 * @author Farhutdinov
 *
 */
public class TournamentTable {

	private List<Team> table;
	
	public TournamentTable(List<Club> clubs) {
		table = new ArrayList<Team>();
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
		table.sort(new SortingTeam());
	}
	
	//result: 0-победа хозяев, 1-ничья, 2-победа гостей
	public void setPoints(Team home, Team guest, int result) {
		switch(result) {
		case 0: home.win(); guest.lose(); break;
		case 1: home.draw(); guest.draw(); break;
		case 2: home.lose(); guest.win(); break;
		default: break;
		}
	}
	
	public List<Team> getTable() {
		return table;
	}

	public void setTable(List<Team> table) {
		this.table = table;
	}
	
	public String toString() {
		String str = "";
		int i = 1;
		for (Team team: table) {
			str = str+i+"| " + team.toString()+"/n"; 
		}
		return str;
	}
}
