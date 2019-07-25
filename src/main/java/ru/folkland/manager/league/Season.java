package ru.folkland.manager.league;

import java.util.ArrayList;
import java.util.List;

import ru.folkland.manager.clubs.Club;
import ru.folkland.manager.clubs.Team;
import ru.folkland.manager.match.Match;
import ru.folkland.manager.match.Total;

/**
 * Создаем новый сезон играем игры и сохраняем результаты
 * @author Farhutdinov
 *
 */
public class Season {

	private TournamentTable tTable;
	private SeasonShedule shedule;

	public Season(List<Club> clubs) {
		tTable = new TournamentTable(clubs);
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
