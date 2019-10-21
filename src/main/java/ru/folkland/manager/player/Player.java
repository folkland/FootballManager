package ru.folkland.manager.player;

import ru.folkland.constants.Constants;
import ru.folkland.manager.model.Person;

import java.security.SecureRandom;
import java.util.Objects;

/**
 * POJO for football player
 *
 * @author folkland
 */
public class Player extends Person implements Comparable {

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

	public Player (int id, String name, String surname, int age, int skill, FootballPosition position) {
		super(id, name, surname, age);
		this.club = -1;
		this.average = -1;
		this.contract = -1;
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
		double growth = Constants.RANDOM.nextInt(traineMax) / 100;
		skill = skill + growth;
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

	public PlayerStatus getStatus() {
		return status;
	}

	public void setStatus(PlayerStatus status) {
		this.status = status;
	}

	@Override
	public int compareTo(Object o) {
		Player player = (Player) o;
		return (int) (player.skill - skill);
	}

	@Override
	public String toString() {
		return "Player{" +
				"name=" + getName() +
				", surname=" + getSurname() +
				", age=" + getAge() +
				", skill=" + skill +
				", position=" + position +
				", club=" + club +
				", status=" + status +
				", average=" + average +
				", contract=" + contract +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Player player = (Player) o;
		return Double.compare(player.skill, skill) == 0 &&
				club == player.club &&
				Double.compare(player.average, average) == 0 &&
				contract == player.contract &&
				position == player.position &&
				status == player.status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), skill, position, club, status, average, contract);
	}
}
