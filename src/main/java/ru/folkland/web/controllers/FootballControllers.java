package ru.folkland.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.folkland.generate.GenerateClub;
import ru.folkland.generate.GeneratePlayer;

/**
 * @author folkland
 */
@EnableWebMvc
@Controller
public class FootballControllers {

    @Value("${welcome.message}")
    private String message;

    @GetMapping("/")
    public String getIndex() {
        return "../static/index";
    }

    @GetMapping("/player/{id}")
    public String getPlayerById(@PathVariable int id, Model model) {
        model.addAttribute("player", GeneratePlayer.createPlayer(id));
        model.addAttribute("message", message);
        return "player";
    }

    @GetMapping("/club/{id}")
    public String getClubById(@PathVariable int id, Model model) {
        model.addAttribute("club", GenerateClub.createClub(id, "Arsenal"));
        return "club";
    }
}
