package ru.folkland.manager.league;

import ru.folkland.manager.clubs.Team;
import ru.folkland.manager.match.Match;
import ru.folkland.manager.match.Total;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Расписание матчей сезона
 * @author folkland
 */
public class SeasonShedule {

    private TournamentTable tTable;

    private List<Team> teams;

    private List<SeasonTour> seasonTourList;

    private int allTourCount;

    public SeasonShedule(TournamentTable tTable) {
        this.tTable = tTable;
        this.teams = tTable.getTable();
        seasonTourList = new ArrayList<>();
        allTourCount = teams.size() * 2 - 2;
    }

    /**
     * Генерируем расписание для каждого тура сезона
     */
    public void generateShedule() {
        int middleSeason = teams.size() - 1;
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
        List<Team> usedTeams = new ArrayList<>();
        for (int i = 0; i < teams.size() && usedTeams.size() != teams.size(); i++) {
            Team team1 = teams.get(i);
            if (!usedTeams.contains(team1)) {
                boolean b = true;
                int j = i + 1 + tourCount;
                while (b) {
                    if (j == 0) j = teams.size() - 1;
                    if (j >= teams.size()) j = j - teams.size();
                    Team team2 = teams.get(teams.size() - j);
                    j--;
                    if (!team1.equals(team2) && !usedTeams.contains(team2)) {
                        usedTeams.add(team1);
                        usedTeams.add(team2);
                        if (ring) {
                            seasonTour.addMatch(team1, team2);
                        } else seasonTour.addMatch(team2, team1);
                        b = false;
                    }
                }
            }
        }
        seasonTourList.add(seasonTour);
    }

    public void playFirstTour() {
        SeasonTour seasonTour = seasonTourList.get(0);
        seasonTour.playTour();
        List<Match> matches = seasonTour.getMatches();
        Map<Match, Total> matchTotalMap = seasonTour.getTourResults();
        for (Match match: matches) {
            Total total = matchTotalMap.get(match);
            tTable.setPoints(match.getHome(), match.getGuest(), total.getWinner());
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < seasonTourList.size(); i++) {
            stringBuilder.append("Tour " + (i+1) + '\n');
            stringBuilder.append(seasonTourList.get(i).toString() + '\n');
        }
        return stringBuilder.toString();
    }
}
