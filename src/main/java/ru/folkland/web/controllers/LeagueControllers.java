package ru.folkland.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.folkland.manager.clubs.Club;
import ru.folkland.manager.league.ILeague;
import ru.folkland.manager.league.LeagueSeasonsView;
import ru.folkland.manager.league.TeamSeason;
import ru.folkland.manager.transfer.ITransferList;
import ru.folkland.manager.transfer.TransferList;
import ru.folkland.test.CreateClubsForTest;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/league")
public class LeagueControllers {

    private final ITransferList transferList;
    private final ILeague league;

    @GetMapping("/clubs")
    public ResponseEntity<List<Club>> getLeagueClubs() {
        checkLeagueToEmpty();
        return ResponseEntity.ok(league.getClubs());
    }

    @GetMapping("/start")
    public ResponseEntity<Boolean> startSeason() {
        checkLeagueToEmpty();
        league.createSeason();
        return ResponseEntity.ok(false);
    }

    @GetMapping("/play")
    public ResponseEntity<Boolean> playNextTour() {
        checkLeagueToEmpty();
        return ResponseEntity.ok(league.playNextTour());
    }

    @GetMapping("/result")
    public ResponseEntity<List<TeamSeason>> getSeasonStatus(@RequestParam("id") int id) {
        checkLeagueToEmpty();
        return ResponseEntity.ok(league.getSeasonStatus(id));
    }

    @GetMapping("/seasons")
    public ResponseEntity<List<LeagueSeasonsView>> getAllSeasons() {
        return ResponseEntity.ok(league.getAllSeasons());
    }

    private void checkLeagueToEmpty() {
        if (league.getClubs().size() == 0) {
            List<Club> clubsList = CreateClubsForTest.getClubsList(8, (TransferList) transferList);
            league.addClubs(clubsList);
        }
    }
}
