package ru.folkland.manager.league;

import lombok.Getter;
import ru.folkland.manager.clubs.Club;

import java.util.List;

/**
 * Создаем новый сезон играем игры и сохраняем результаты
 * @author Farhutdinov
 *
 */
@Getter
public class Season {

	private SeasonSchedule schedule;

	public Season(List<Club> clubs) {
		if (clubs.size() % 2 == 1) throw new IllegalArgumentException("Count of club uneven");
		TournamentTable tTable = new TournamentTable(clubs);
		schedule = new SeasonSchedule(tTable);
		schedule.generateSchedule();
	}

	public boolean playNextTour() {
		return schedule.playNextTour();
	}

	public void playAllTours() {
		schedule.playAllTour();
	}

	public String showTable() {
		return schedule.getTTable().toString();
	}
}
