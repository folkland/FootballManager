package ru.folkland.manager.league;

import java.util.ArrayList;
import java.util.List;

import ru.folkland.manager.clubs.Club;

/**
 * Создаем новый сезон играем игры и сохраняем результаты
 * @author Farhutdinov
 *
 */
public class Season {

	private int tourCount;
	private int[][] shedule;
	private List<String> results;
	private boolean[] check;
	private TournamentTable tTable;
	
	public Season(List<Club> clubs) {
		tTable = new TournamentTable(clubs);
		tourCount = 0;
		int size = tTable.getTable().size();
		shedule = new int[size][size];
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				if (i == j) {
					shedule[i][j] = -1;
				}
				else shedule[i][j] = 2;
			}
		}
		check = new boolean[size];
		results = new ArrayList<String>();
	}
	//играем тур
	public void playTour() {
		if (createTour())
			accept();
		tTable.sortTable();
	}
	
	//формирование тура
	public boolean createTour() {
		int size = tTable.getTable().size();
		int count = size/2;
		if ( tourCount < size ) {
			boolean b = true;
			while (b) {
				b = false;
				for (int i=0; i<size; i++)
					check[i] = true;
				for (int i=0; i<count; i++) {
					if (!searchPair()) {
						cancel();
						b = true;
						break;
					}
				}
			}
			return true;
		} else return false;
	}
	//поиск пары команд
	public boolean searchPair() {
		boolean b = false;
		int min = 0, max = tTable.getTable().size(), count = 0;
		while (!b) {
			int i = min + (int)(Math.random()*max);
			int j = min + (int)(Math.random()*max);
			if (i!=j && check[i] && check[j] && shedule[i][j]==2) {
				shedule[i][j] = 0;
				check[i] = false;
				check[j] = false;
				b = true;
			}
			count++;
			if (count > 20)
				return b;
		}
		return b;
	}
	//отигровка тура
	public void accept() {
		results.add("Тур " + tourCount);
		int size = tTable.getTable().size();
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				if (shedule[i][j] == 0) {
					Match match = new Match(tTable.getTable().get(i), tTable.getTable().get(j));
					Total total = match.playMatch();
					tTable.setPoints(tTable.getTable().get(i), tTable.getTable().get(j), total.getResult());
					results.add(tTable.getTable().get(i).getClub().getName()+ " " + total.toString() + " " + tTable.getTable().get(j).getClub().getName());
					shedule[i][j] = 1;
				}
			}
		}
		tourCount++;
	}
	//не получилось найти всем пары
	public void cancel() {
		int size = tTable.getTable().size();
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				if (shedule[i][j] == 0) {
					shedule[i][j] = 2;
				}
			}
		}
	}
	
	public String showTable() {
		return tTable.toString();
	}
	
	public void showResults() {
		for (String str: results) {
			System.out.println(str);
		}
	}
}
