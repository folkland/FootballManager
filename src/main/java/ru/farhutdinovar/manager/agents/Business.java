package ru.farhutdinovar.manager.agents;

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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getIncoming() {
		return incoming;
	}
	public void setIncoming(int incoming) {
		this.incoming = incoming;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
