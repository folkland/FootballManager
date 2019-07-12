package ru.folkland.manager.player;

import ru.folkland.manager.constants.Constants;
import ru.folkland.manager.model.Person;

import java.security.SecureRandom;

/**
 * POJO for football player
 *
 * @author folkland
 */
public class Player extends Person implements Comparable {

	private SecureRandom random = new SecureRandom();

	//навык 0-100
	private double skill;
	//позиция на поле
	private FootballPosition position;
	//в каком клубе играет
	private int club;
	//средний показатель в сезоне
	private double average;
	//длина контракта
	private int contract;
	
	public Player (int id, String name, String surname, int age, int skill, FootballPosition position/*, int character*/) {
		super(id, name, surname, age);
		this.club = -1;
		this.average = -1;
		this.contract = -1;
		this.skill = skill;
		this.position = position;
	}

	/**
	 * Проводит улучшение скиллов игрока
	 * @param isPlayInMatch участвовал ли игрок в матче, от этого зависит его прирост
	 */
	public void training(boolean isPlayInMatch) {
		int traineMax = Constants.MAX_TRAIN_FOR_NOT_PLAYED_MATCH;
		if (isPlayInMatch) traineMax = Constants.MAX_TRAIN_FOR_PLAYED_MATCH;
		int growth = random.nextInt(traineMax);
		skill = skill + (growth / 100);
	}

	public double getSkill() {
		return skill;
	}

	public void setSkill(double skill) {
		this.skill = skill;
	}

	public FootballPosition getPosition() {
		return position;
	}

	public void setPosition(FootballPosition position) {
		this.position = position;
	}

	public int getClub() {
		return club;
	}

	public void setClub(int club) {
		this.club = club;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public int getContract() {
		return contract;
	}

	public void setContract(int contract) {
		this.contract = contract;
	}

	@Override
	public int compareTo(Object o) {
		Player player = (Player) o;
		return (int) (getSkill() - player.getSkill());
	}
}
