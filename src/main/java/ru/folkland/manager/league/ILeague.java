package ru.folkland.manager.league;

import ru.folkland.manager.clubs.Club;

import java.util.List;

public interface ILeague {

    void addClubs(List<Club> clubs);
    void addClub(Club club);

    List<Club> getClubs();
    void createSeason();
    List<TeamSeason> getSeasonStatus(int id);
    boolean playNextTour();
    List<LeagueSeasonsView> getAllSeasons();
}
