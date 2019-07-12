package ru.farhutdinovar.test;

import java.util.ArrayList;
import java.util.List;

import ru.farhutdinovar.manager.clubs.Club;
import ru.farhutdinovar.manager.clubs.FootballScheme;

public class Test {


	private List<Club> clubs;
	private int matchCount;
	private int[][] shedule;
	private List<String> results;
	private boolean[] check;
	
	public static void main(String[] args) {
		Test test = new Test();
		test.clubs = new ArrayList<>();
		test.clubs.add(new Club(1, "Chelsea", FootballScheme.s343));
		test.clubs.add(new Club(2, "MU", FootballScheme.s343));
		test.clubs.add(new Club(3, "MC", FootballScheme.s343));
		test.clubs.add(new Club(4, "Arsenal", FootballScheme.s343));
		test.matchCount = 0;
		int size = test.clubs.size();
		test.shedule = new int[size][size];
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				if (i == j) {
					test.shedule[i][j] = -1;
				}
				else test.shedule[i][j] = 2;
			}
		}
		test.check = new boolean[size];
		for (int i=0; i<6;i++) {
			test.createTour();
			test.accept();
		}
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				System.out.print(test.shedule[i][j] + " ");
			}
			System.out.println();
		}
	}
	//формирование тура
	public boolean createTour() {
		int k = 0;
		int size = clubs.size();
		int count = size/2;
		if ( matchCount < size ) {
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
		int min = 0, max = clubs.size(), count = 0;
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
			if (count > 19) {
				return b;
			}
		}
		return b;
	}
			//не получилось найти всем пары
			public void cancel() {
				System.out.println("cancel");
				int size = clubs.size();
				for (int i=0; i<size; i++) {
					for (int j=0; j<size; j++) {
						if (shedule[i][j] == 0) {
							shedule[i][j] = 2;
						}
					}
				}
			}
			//тур сыгран
			public void accept() {
				int size = clubs.size();
				for (int i=0; i<size; i++) {
					for (int j=0; j<size; j++) {
						if (shedule[i][j] == 0) {
							shedule[i][j] = 1;
						}
					}
				}
			}
}
