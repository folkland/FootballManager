package ru.folkland.manager.match;

import ru.folkland.manager.clubs.Team;
import ru.folkland.manager.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Формируем стартовый состав на матч
 *
 * @author folkland
 */
public class TeamMainCast {

    private Team team;

    //список игроков на матч
    private List<Player> toMatch;

    //сила каждой линии на матч
    private int strengthForward;
    private int strengthDefender;
    private int strengthMidfielder;
    private int strengthGoalkeeper;

    TeamMainCast(Team team) {
        this.team = team;
        strengthGoalkeeper = 0;
        strengthDefender = 0;
        strengthMidfielder = 0;
        strengthForward = 0;
    }

    /**
     * Выясняем силу стартового состава на матч
     * @return сила стартового состава
     */
    public int playerStrength() {
        choosePlayer();
        for (Player player: toMatch) {
            switch(player.getPosition()) {
                case goalkeeper: strengthGoalkeeper = (int) player.getSkill(); break;
                case defender: strengthDefender = strengthDefender + (int) player.getSkill(); break;
                case midfielder: strengthMidfielder = strengthMidfielder + (int) player.getSkill(); break;
                case forward: strengthForward = strengthForward + (int) player.getSkill(); break;
            }
        }
        return strengthGoalkeeper + strengthDefender + strengthMidfielder + strengthForward;
    }

    /**
     * Проводим отбор в стартовый состав
     */
    private void choosePlayer() {
        toMatch = new ArrayList<>();
        List<Player> forwards = new ArrayList<>();
        List<Player> midfielders = new ArrayList<>();
        List<Player> defenders = new ArrayList<>();
        for (Player player: team.getPlayers()) {
            switch(player.getPosition()) {
                case goalkeeper:
                    if (toMatch.isEmpty()) {
                        toMatch.add(player);
                    } else if (toMatch.get(0).getSkill() < player.getSkill()) {
                        toMatch.remove(0);
                        toMatch.add(player);
                    }
                    break;
                case defender: defenders.add(player); break;
                case midfielder: midfielders.add(player); break;
                case forward: forwards.add(player); break;
            }
        }
        //дописать сортировку по листам и лучших отправить в заявку
        Collections.sort(defenders);
        Collections.sort(midfielders);
        Collections.sort(forwards);
        for (int i = 0; i < team.getScheme().getForward(); i++) {
            toMatch.add(forwards.get(i));
        }
        for (int i = 0; i < team.getScheme().getMidfielder(); i++) {
            toMatch.add(midfielders.get(i));
        }
        for (int i = 0; i < team.getScheme().getDefender(); i++) {
            toMatch.add(defenders.get(i));
        }
    }

    /**
     * Тренируем всех игроков в зависимости от их вклада в игру
     */
    public void training() {
        for (Player player: team.getPlayers()) {
            boolean isMatchPlay = toMatch.contains(player);
            player.training(isMatchPlay);
        }
    }
}
