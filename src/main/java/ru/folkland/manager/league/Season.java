package ru.folkland.manager.league;

import java.util.List;

import ru.folkland.manager.clubs.Club;

/**
 * Создаем новый сезон играем игры и сохраняем результаты
 * @author Farhutdinov
 *
 */
public class Season {

	private TournamentTable tTable;
	private SeasonSchedule shedule;

	public Season(List<Club> clubs) {
		tTable = new TournamentTable(clubs);
		shedule = new SeasonSchedule(tTable);
		shedule.generateSchedule();
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
