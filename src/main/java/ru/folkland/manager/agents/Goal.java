package ru.folkland.manager.agents;

import lombok.Getter;

/**
 * Это видимо класс для описания целей, типа Взобраться на Эверест
 */
@Getter
public class Goal {

	private int id;
	private String name;
	private int cost;
	
	public Goal (int id, String name, int cost) {
		this.id = id;
		this.name = name;
		this.cost = cost;
	}
}
