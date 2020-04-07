package ru.folkland.manager.transfer;

import lombok.Getter;
import ru.folkland.manager.player.Player;

/**
 *
 */
@Getter
public class PlayerCost implements Comparable{

    private Player player;
    private double cost;

    public PlayerCost(Player player) {
        this.player = player;
    }

    /**
     * Высчитывается примерная стоимость
     */
    public void calculateTransferCost() {
        if (player.getClub() == -1) {
            cost = 0;
            return;
        }
        double contractLongKoef = 1 + ((double) player.getContract() / 10);
        double ageCoef;
        if (player.getAge() < 22) ageCoef = player.getAge() - 17;
        else if (player.getAge() < 28) ageCoef = (double) (player.getAge() - 17) * 1.5;
        else ageCoef = (double) 20 / (player.getAge() - 17);
        double startCost = 250_000;
        if (player.getSkill() > 25) startCost = 1_000_000;
        System.out.println("Contract: " + contractLongKoef);
        System.out.println("Age: " + ageCoef);
        System.out.println("Start: " + startCost);
        cost = startCost * contractLongKoef * ageCoef;
    }

    @Override
    public int compareTo(Object o) {
        return player.compareTo(((PlayerCost) o).player);
    }
}
