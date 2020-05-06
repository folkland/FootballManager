package ru.folkland.manager.league;

import lombok.Getter;
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
@Getter
public class SeasonTour {

    private List<Match> matches;

    private Map<Match, Total> tourResults;

    SeasonTour() {
        tourResults = new HashMap<>();
        matches = new ArrayList<>();
    }

    void addMatch(TeamSeason home, TeamSeason guest) {
        matches.add(new Match(home, guest));
    }

    void playTour() {
        for(Match match: matches) {
            playMatch(match);
        }
    }

    private void playMatch(Match match) {
        tourResults.put(match, match.playMatch());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Match match: matches) {
            stringBuilder.append(match.toString());
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}
