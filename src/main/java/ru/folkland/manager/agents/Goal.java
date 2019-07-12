package ru.folkland.manager.agents;

/**
 * Это видимо класс для описания целей, типа Взобраться на Эверест
 */
public class Goal {

	private int id;
	private String name;
	private int cost;
	
	public Goal (int id, String name, int cost) {
		this.id = id;
		this.name = name;
		this.cost = cost;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
}