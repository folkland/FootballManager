package ru.folkland.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.folkland.manager.clubs.Club;
import ru.folkland.manager.clubs.FootballScheme;
import ru.folkland.manager.league.ILeague;
import ru.folkland.web.model.CreateClubRequest;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/my-club")
public class MyClubControllers {

    private final ILeague league;

    @PostMapping("/create")
    public void createMyOwnClub(@RequestBody CreateClubRequest request) {
        System.out.println(request.toString());
        league.addClub(new Club(request.getName(), FootballScheme.valueOf(request.getScheme())));
    }
}
