package ru.folkland.manager.league;

import ru.folkland.manager.match.Match;
import ru.folkland.manager.match.Total;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Расписание матчей сезона
 * @author folkland
 */
public class SeasonSchedule {

    private TournamentTable tTable;

    private List<TeamSeason> teamSeasons;

    private List<SeasonTour> seasonTourList;

    //общее количество туров в чемпионате
    private int allTourCount;

    //количество уже проведенных туров
    private int pastTour;

    SeasonSchedule(TournamentTable tTable) {
        this.tTable = tTable;
        this.teamSeasons = tTable.getTable();
        seasonTourList = new ArrayList<>();
        allTourCount = teamSeasons.size() * 2 - 2;
        pastTour = 0;
    }

    /**
     * Генерируем расписание для каждого тура сезона
     */
    void generateSchedule() {
        int middleSeason = teamSeasons.size() - 1;
        for (int i = 0; i < allTourCount; i++) {
            boolean ring = true;
            int tourCount = i;
            if (i >= middleSeason) {
                ring = false;
                tourCount = i - middleSeason;
            }
            searchPair(ring, tourCount);
        }
    }

    /**
     * Генерируем все пары на матч в одном туре
     * @param ring true - первый круг, false - второй круг
     * @param tourCount какой по счёту это тур
     */
    private void searchPair(boolean ring, int tourCount) {
        SeasonTour seasonTour = new SeasonTour();
        List<TeamSeason> usedTeamSeasons = new ArrayList<>();
        for (int i = 0; i < teamSeasons.size() && usedTeamSeasons.size() != teamSeasons.size(); i++) {
            TeamSeason teamSeason1 = teamSeasons.get(i);
            if (!usedTeamSeasons.contains(teamSeason1)) {
                boolean b = true;
                int j = i + 1 + tourCount;
                while (b) {
                    if (j == 0) j = teamSeasons.size() - 1;
                    if (j >= teamSeasons.size()) j = j - teamSeasons.size();
                    TeamSeason teamSeason2 = teamSeasons.get(teamSeasons.size() - j);
                    j--;
                    if (!teamSeason1.equals(teamSeason2) && !usedTeamSeasons.contains(teamSeason2)) {
                        usedTeamSeasons.add(teamSeason1);
                        usedTeamSeasons.add(teamSeason2);
                        if (ring) {
                            seasonTour.addMatch(teamSeason1, teamSeason2);
                        } else seasonTour.addMatch(teamSeason2, teamSeason1);
                        b = false;
                    }
                }
            }
        }
        seasonTourList.add(seasonTour);
    }

    /**
     * Отигрываем следующий тур
     */
    void playNextTour() throws IllegalArgumentException {
        if (pastTour == allTourCount) throw new IllegalArgumentException("Season has no more tour");
        SeasonTour seasonTour = seasonTourList.get(pastTour);
        pastTour++;
        seasonTour.playTour();
        List<Match> matches = seasonTour.getMatches();
        Map<Match, Total> matchTotalMap = seasonTour.getTourResults();
        for (Match match: matches) {
            Total total = matchTotalMap.get(match);
            tTable.setPoints(match.getHome(), match.getGuest(), total.getWinner(), total.getHomeScore(), total.getGuestScore());
            tTable.sortTable();
        }
    }

    void playAllTour() {
        for (int i = 0; i < allTourCount; i++) {
            playNextTour();
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < seasonTourList.size(); i++) {
            stringBuilder.append("Tour ");
            stringBuilder.append(i+1);
            stringBuilder.append('\n');
            stringBuilder.append(seasonTourList.get(i).toString());
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}
