package ru.folkland.manager.league;

import ru.folkland.manager.clubs.Club;
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

    private List<Club> clubs;

    private List<SeasonTour> seasonTourList;

    //общее количество туров в чемпионате
    private int allTourCount;

    //количество уже проведенных туров
    private int pastTour;

    public SeasonShedule(TournamentTable tTable) {
        this.tTable = tTable;
        this.clubs = tTable.getTable();
        seasonTourList = new ArrayList<>();
        allTourCount = clubs.size() * 2 - 2;
        pastTour = 0;
    }

    /**
     * Генерируем расписание для каждого тура сезона
     */
    public void generateShedule() {
        int middleSeason = clubs.size() - 1;
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
        List<Club> usedClubs = new ArrayList<>();
        for (int i = 0; i < clubs.size() && usedClubs.size() != clubs.size(); i++) {
            Club club1 = clubs.get(i);
            if (!usedClubs.contains(club1)) {
                boolean b = true;
                int j = i + 1 + tourCount;
                while (b) {
                    if (j == 0) j = clubs.size() - 1;
                    if (j >= clubs.size()) j = j - clubs.size();
                    Club club2 = clubs.get(clubs.size() - j);
                    j--;
                    if (!club1.equals(club2) && !usedClubs.contains(club2)) {
                        usedClubs.add(club1);
                        usedClubs.add(club2);
                        if (ring) {
                            seasonTour.addMatch(club1, club2);
                        } else seasonTour.addMatch(club2, club1);
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
    public void playNextTour() throws IllegalArgumentException {
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

    public void playAllTour() {
        for (int i = 0; i < allTourCount; i++) {
            playNextTour();
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
