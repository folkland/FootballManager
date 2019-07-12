package ru.farhutdinovar.manager.clubs;

import java.util.ArrayList;
import java.util.List;

import ru.farhutdinovar.manager.player.Player;

public class Club {

	private int id;
	//название каманды
	private String name;
	//лига
//	private int league;
	//страна
//	private int country;
	
//	private int money; //?
//	private int sponsorContract;
	
	//список игроков
	private List<Player> players;
	//список игроков на матч
	private List<Player> toMatch;
	
	//расстановка клуба
	private FootballScheme fScheme;
	//схема по которой играет команда
	private Scheme scheme;
	
	//место в лиге
//	private int position;
	//количество очков
//	private int points;
	
	//сила каждой линии на матч
	private int streinghtForward;
	private int streinghtDefender;
	private int streinghtMidfielder;
	private int streinghtGoalkeeper;
	//сила всей команды на матч
	private int streinghtTotal;
	
	public Club (int id, String name, FootballScheme fScheme) {
		setId(id);
		this.fScheme = fScheme;
		scheme = new Scheme(fScheme);
		this.setName(name);
//		this.league = league;
	}
	
	//выясняем силу команды перед матчем
	public void playerStrenght() {
		choosePlayer();
		setGoalkeeper(0);
		setDefender(0);
		setMidfielder(0);
		setForward(0);
		for (Player player: toMatch) {
			switch(player.getPosition()) {
			case goalkeeper: setGoalkeeper((int)player.getSkills()); break;
			case defender: setDefender(getDefender() + (int)player.getSkills()); break;
			case midfielder: setMidfielder(getMidfielder() + (int)player.getSkills()); break;
			case forward: setForward(getForward() + (int)player.getSkills()); break;
			}
		}
		setStreinghtTotal(getGoalkeeper() + getDefender() + getMidfielder() + getForward());
	}
	
	//отбираем игроков на матч
	public void choosePlayer() {
		toMatch = new ArrayList<Player>();
		List<Player> forwards = new ArrayList<Player>();
		List<Player> midfielders = new ArrayList<Player>();
		List<Player> defenders = new ArrayList<Player>();
		for (Player player: players) {
			switch(player.getPosition()) {
			case goalkeeper:
				if (toMatch.isEmpty()) {
					toMatch.add(player);
				} else if (toMatch.get(0).getSkills() < player.getSkills()) {
					toMatch.remove(0);
					toMatch.add(player);
				}
				break;
			case defender: defenders.add(player); break;
			case midfielder: midfielders.add(player); break;
			case forward: forwards.add(player); break;
			}
		}
		//дописать сортировку по листам и лучших отправить в заявку
		forwards.sort(new SortingPlayers());
		midfielders.sort(new SortingPlayers());
		defenders.sort(new SortingPlayers());
		for (int i=0; i<scheme.getForward(); i++) {
			toMatch.add(forwards.get(i));
		}
		for (int i=0; i<scheme.getMidfielder(); i++) {
			toMatch.add(midfielders.get(i));
		}
		for (int i=0; i<scheme.getDefender(); i++) {
			toMatch.add(defenders.get(i));
		}
	}
	
	public void clearMatchTeam() {
		toMatch.clear();
	}
	
	//тренировка после игры для всех игроков
	public void training() {
		double maxPlay = 0.7, min = 0, maxNoPlay = 0.2;
		for (Player player: players) {
			if (toMatch.contains(player)) {
				player.setSkills(player.getSkills() + (min + Math.random()*maxPlay));
			}
			else {
				player.setSkills(player.getSkills() + (min + Math.random()*maxNoPlay));
			}
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getGoalkeeper() {
		return streinghtGoalkeeper;
	}

	public void setGoalkeeper(int goalkeeper) {
		this.streinghtGoalkeeper = goalkeeper;
	}

	public int getMidfielder() {
		return streinghtMidfielder;
	}

	public void setMidfielder(int midfielder) {
		this.streinghtMidfielder = midfielder;
	}

	public int getDefender() {
		return streinghtDefender;
	}

	public void setDefender(int defender) {
		this.streinghtDefender = defender;
	}

	public int getForward() {
		return streinghtForward;
	}

	public void setForward(int forward) {
		this.streinghtForward = forward;
	}

	public int getStreinghtTotal() {
		return streinghtTotal;
	}

	public void setStreinghtTotal(int streinghtTotal) {
		this.streinghtTotal = streinghtTotal;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean equals(Object obj) {
		if(obj == this)
			return true;
		/* obj ссылается на null */
		if(obj == null)
			return false;
		/* Удостоверимся, что ссылки имеют тот же самый тип */
		if(!(getClass() == obj.getClass()))
			return false;
		else {
			Club tmp = (Club)obj;
			if(tmp.id == this.id)
				return true;
			else
				return false;
		}
	}
}
