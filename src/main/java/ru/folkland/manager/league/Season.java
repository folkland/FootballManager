package ru.folkland.manager.league;

import java.util.List;

import ru.folkland.manager.clubs.Team;

/**
 * Создаем новый сезон играем игры и сохраняем результаты
 * @author Farhutdinov
 *
 */
public class Season {

	private TournamentTable tTable;
	private SeasonShedule shedule;

	public Season(List<Team> teams) {
		tTable = new TournamentTable(teams);
		shedule = new SeasonShedule(tTable);
		shedule.generateShedule();
	}

	public void playNextTour() {
		shedule.playNextTour();
	}

	public void playAllTours() {
		shedule.playAllTour();
	}

	public String showTable() {
		return tTable.toString();
	}
}
