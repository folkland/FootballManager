package ru.folkland.manager.transfer;

import org.junit.Test;
import ru.folkland.manager.player.FootballPosition;
import ru.folkland.manager.player.Player;

import static org.junit.Assert.*;

public class PlayerCostTest {

    @Test
    public void testCalculateTransferCost() {
        Player player = new Player("Poul", "Pogba", 21, 20, FootballPosition.midfielder);
        PlayerCost playerCost = new PlayerCost(player);
        playerCost.calculateTransferCost();
        System.out.println(player.toString());
        System.out.println(playerCost.getCost());

//        player.setContract(3);
        playerCost.calculateTransferCost();
        System.out.println(player.toString());
        System.out.println(playerCost.getCost());

//        player.setSkill(40);
        playerCost.calculateTransferCost();
        System.out.println(player.toString());
        System.out.println(playerCost.getCost());

//        player.setAge(25);
        playerCost.calculateTransferCost();
        System.out.println(player.toString());
        System.out.println(playerCost.getCost());

//        player.setAge(30);
        playerCost.calculateTransferCost();
        System.out.println(player.toString());
        System.out.println(playerCost.getCost());
    }
}