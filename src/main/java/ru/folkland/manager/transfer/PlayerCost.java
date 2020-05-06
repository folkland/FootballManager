package ru.folkland.manager.transfer;

import lombok.Getter;
import ru.folkland.manager.player.Player;

/**
 *
 */
@Getter
public class PlayerCost implements Comparable<PlayerCost>{

    private Player player;
    private double cost;

    public PlayerCost(Player player) {
        this.player = player;
        calculateTransferCost();
    }

    static PlayerCost newPlayerCost(Player player) {
        return new PlayerCost(player);
    }

    /**
     * Высчитывается примерная стоимость
     */
    private void calculateTransferCost() {
        double startCost = 250_000;
//        if (player.getClub() == -1) {
//            cost = startCost;
//            return;
//        }
        double contractLongCoefficient = getContractLongCoefficient();
        double ageCoefficient = getAgeCoefficient();
        double skillCoefficient = getSkillCoefficient();
        cost = startCost * skillCoefficient * contractLongCoefficient * ageCoefficient;
    }

    private double getSkillCoefficient() {
        if (player.getSkill() > 30) return 10;
        if (player.getSkill() > 25) return 8;
        if (player.getSkill() > 20) return 4;
        return 1;
    }

    private double getAgeCoefficient() {
        double ageCoef;
        if (player.getAge() < 22) ageCoef = player.getAge() - 17;
        else if (player.getAge() < 28) ageCoef = (double) (player.getAge() - 17) * 1.5;
        else ageCoef = (double) 20 / (player.getAge() - 17);
        return ageCoef;
    }

    private double getContractLongCoefficient() {
        return 1 + ((double) player.getContract() / 10);
    }

    @Override
    public int compareTo(PlayerCost playerCost) {
        if (cost != playerCost.cost) return (int) (playerCost.cost - cost);
        return player.compareTo(playerCost.player);
    }
}
