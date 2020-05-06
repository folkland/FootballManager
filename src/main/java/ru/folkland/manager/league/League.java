package ru.folkland.manager.league;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.folkland.manager.clubs.Club;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Component
public class League implements ILeague {

//	private int id;
//	private String country;

    private List<Club> clubs;

    private Map<Integer, Season> seasons;

    public League() {
        clubs = new ArrayList<>();
        seasons = new HashMap<>();
    }

    @Override
    public void addClub(Club club) {
        clubs.add(club);
    }

    @Override
    public void addClubs(List<Club> clubs) {
        for (Club club : clubs) {
            addClub(club);
        }
    }

    @Override
    public void createSeason() {
        seasons.put(seasons.size(), new Season(clubs));
    }

    @Override
    public List<TeamSeason> getSeasonStatus(int id) {
        //Жеськая заглушка!
        if (seasons.size() == 0) {
            return clubs.stream().map(TeamSeason::new).collect(Collectors.toList());
        }
        return seasons.get(id).getSchedule().getTeamSeasons();
    }

    @Override
    public boolean playNextTour() {
        Season season = getLastSeason();
        return season.playNextTour();
    }

    @Override
    public List<LeagueSeasonsView> getAllSeasons() {
        return seasons.keySet().stream()
                .map(id -> new LeagueSeasonsView(id, "Season " + (id + 1)))
                .collect(Collectors.toList());
    }

    private Season getLastSeason() {
        return seasons.get(seasons.size() - 1);
    }

    private void playAllTourActualSeason() {
        Season season = getLastSeason();
        season.playAllTours();
    }

    public void startLeague(int countSeasons) {
        while (seasons.size() < countSeasons) {
            createSeason();
            playAllTourActualSeason();
            showSeasonResult();
        }
    }

    private void showSeasonResult() {
        System.out.println("=======Season " + seasons.size() + "=======");
        for (Club club : clubs) {
            System.out.println(club.getName() + " " + club.getTotalClubStrength());
        }
        System.out.println("==============");
        System.out.println(seasons.get(seasons.size() - 1).showTable());
    }
}
