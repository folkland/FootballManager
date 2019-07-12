package ru.farhutdinovar.manager.player;

/**
 * POJO for footbal player layer
 */
public class Player {

	private int id;
	private String name;
	private String surname;
	
	//возраст
	private int age;
	//навык 0-100
	private double skill;
	//позиция на поле
	private FootballPosition position;
//	private int money;
//	private int reputation;
//	private int character;
	//в каком клубе играет
	private int club;
	//средний показатель в сезоне
	private double average;
	//длина контракта
	private int contract;
	
//	private int matchCount;
	
	public Player (int id, String name, String surname, int age, int skill, FootballPosition position/*, int character*/) {
//		this.money = 0;
//		this.reputation = 1;
		this.club = -1;
		this.average = -1;
		this.contract = -1;
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.skill = skill;
		this.position = position;
//		this.character = character;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getSkills() {
		return skill;
	}
	public void setSkills(Double skill) {
		this.skill = skill;
	}
	public FootballPosition getPosition() {
		return position;
	}
	public void setPosition(FootballPosition position) {
		this.position = position;
	}
//	public int getMoney() {
//		return money;
//	}
//	public void setMoney(int money) {
//		this.money = money;
//	}
//	public int getReputation() {
//		return reputation;
//	}
//	public void setReputation(int reputation) {
//		this.reputation = reputation;
//	}
//	public int getCharacter() {
//		return character;
//	}
//	public void setCharacter(int character) {
//		this.character = character;
//	}
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getContract() {
		return contract;
	}

	public void setContract(int contract) {
		this.contract = contract;
	}
}
