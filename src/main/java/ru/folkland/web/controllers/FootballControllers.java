package ru.folkland.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.folkland.generate.GenerateClub;
import ru.folkland.generate.GeneratePlayer;
import ru.folkland.manager.clubs.Club;
import ru.folkland.manager.player.Player;

/**
 * @author folkland
 */
@RestController
public class FootballControllers {

    @GetMapping("/player/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable int id) {
        return ResponseEntity.ok(GeneratePlayer.createPlayer());
    }

    @GetMapping("/club/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable int id) {
        return ResponseEntity.ok(GenerateClub.createClub());
    }
}
