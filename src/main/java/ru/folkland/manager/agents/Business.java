package ru.folkland.manager.agents;

import lombok.Getter;

/**
 * Думаю это бизнесы, которые можно выбрать для инвестиций
 */
@Getter
public class Business {

	private int id;
	private String name;
	private String type;
	
	private int cost;
	private int incoming;
	
	public Business (int id, String name, String type, int cost, int incoming) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.cost = cost;
		this.incoming = incoming;
	}
}
