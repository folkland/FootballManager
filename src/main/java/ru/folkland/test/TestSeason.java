package ru.folkland.test;

import ru.folkland.manager.clubs.Club;
import ru.folkland.manager.clubs.FootballScheme;
import ru.folkland.manager.league.Season;
import ru.folkland.manager.player.FootballPosition;
import ru.folkland.manager.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author folkland
 */
public class TestSeason {

    public static void main(String[] args) {
        List<Club> clubs = new ArrayList<>();
        clubs.add(new Club(1, "Chelsea", FootballScheme.s343));
        clubs.add(new Club(2, "Man Una", FootballScheme.s343));
        clubs.add(new Club(3, "Man Cit", FootballScheme.s343));
        clubs.add(new Club(4, "Arsenal", FootballScheme.s343));
        List<Player> players = createPlayers();
        for (Club club: clubs) {
            club.setPlayers(players);
        }
        Season season = new Season(clubs);
    }

    public static List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(new Player(0, "John", "Wilshere", 18, 45, FootballPosition.goalkeeper));
        for (int i = 0; i < 3; i++) {
            players.add(new Player(0, "John", "Wilshere", 18, 45, FootballPosition.defender));
        }
        for (int i = 0; i < 4; i++) {
            players.add(new Player(0, "John", "Wilshere", 18, 45, FootballPosition.midfielder));
        }
        for (int i = 0; i < 3; i++) {
            players.add(new Player(0, "John", "Wilshere", 18, 45, FootballPosition.forward));
        }
        return players;
    }
}
