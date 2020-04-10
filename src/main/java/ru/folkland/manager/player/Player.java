package ru.folkland.manager.player;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.folkland.constants.Constants;
import ru.folkland.manager.model.Person;

import java.security.SecureRandom;
import java.util.Objects;

/**
 * POJO for football player
 *
 * @author folkland
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Player extends Person implements Comparable<Player> {

	//навык 0-100
	private double skill;
	//позиция на поле
	private FootballPosition position;
	//в каком клубе играет
	private int club;
	//status
	private PlayerStatus status;
	//средний показатель в сезоне
	private double average;
	//длина контракта
	private int contract;

	public Player (String name, String surname, int age, int skill, FootballPosition position) {
		super(name, surname, age);
		this.club = -1;
		this.skill = skill;
		this.position = position;
		status = PlayerStatus.newPlayer;
	}

	/**
	 * Проводит улучшение скиллов игрока
	 * @param isPlayInMatch участвовал ли игрок в матче, от этого зависит его прирост
	 */
	public void training(boolean isPlayInMatch) {
		int traineMax = Constants.MAX_TRAIN_FOR_NOT_PLAYED_MATCH;
		if (isPlayInMatch) traineMax = Constants.MAX_TRAIN_FOR_PLAYED_MATCH;
		double growth = Constants.RANDOM.nextInt(traineMax) / 100.0;
		skill = skill + growth;
	}

	public void setClub(int club) {
		this.club = club;
	}

	public void setContract(int contract) {
		this.contract = contract;
	}

	public void setStatus(PlayerStatus status) {
		this.status = status;
	}

	@Override
	public int compareTo(Player player) {
		return (int) (player.skill - skill);
	}
}
