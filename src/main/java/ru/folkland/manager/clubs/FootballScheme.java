package ru.folkland.manager.clubs;

import lombok.Getter;
import ru.folkland.manager.player.FootballPosition;

/**
 * Схемы расстановки
 *
 * @author folkland
 */
@Getter
public enum FootballScheme {
	s343(3, 4, 3),
	s433(4, 3, 3),
	s442(4, 4, 2),
	s451(4, 5, 1),
	s352(3, 5, 2);

	private final int defender;
	private final int midfielder;
	private final int forward;

	FootballScheme(int defender, int midfielder, int forward) {
		this.defender = defender;
		this.midfielder = midfielder;
		this.forward = forward;
	}

	int countOfPosition(FootballPosition position) {
		switch (position) {
			case defender: return defender;
			case midfielder: return midfielder;
			case forward: return forward;
			default: return 1;
		}
	}
}
