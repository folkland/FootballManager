package ru.folkland.manager.match;

import lombok.Getter;
import lombok.Setter;

/**
 * Результат матча
 * @author folkland
 */
@Getter
@Setter
public class Total {

	private int homeScore;
	private int guestScore;
	private Winner winner;
}
