package ru.folkland.manager.league;

import ru.folkland.manager.clubs.Club;
import ru.folkland.manager.match.Match;
import ru.folkland.manager.match.Total;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывающий один тур чемпионата
 * @author folkland
 */
public class SeasonTour {

    private List<Match> matches;

    private Map<Match, Total> tourResults;

    public SeasonTour() {
        tourResults = new HashMap<>();
        matches = new ArrayList<>();
    }

    public void addMatch(Club home, Club guest) {
        matches.add(new Match(home, guest));
    }

    public void playTour() {
        for(Match match: matches) {
            playMatch(match);
        }
    }

    private void playMatch(Match match) {
        tourResults.put(match, match.playMatch());
    }

    public Map<Match, Total> getTourResults() {
        return tourResults;
    }

    public List<Match> getMatches() {
        return matches;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Match match: matches) {
            stringBuilder.append(match.toString() + '\n');
        }
        return stringBuilder.toString();
    }
}
