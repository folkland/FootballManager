package ru.folkland.manager.clubs;

import lombok.Getter;
import lombok.Setter;

/**
 * Количество необходимых позиций для матча
 *
 * @author folkland
 */
@Getter
@Setter
public class Scheme {
	private int forward;
	private int midfielder;
	private int defender;
	
	Scheme (FootballScheme scheme) {
		switch(scheme) {
		case s343: setForward(3); setMidfielder(4); setDefender(3); break;
		case s352: setForward(2); setMidfielder(5); setDefender(3); break;
		case s433: setForward(3); setMidfielder(3); setDefender(4); break;
		case s442: setForward(2); setMidfielder(4); setDefender(4); break;
		case s451: setForward(1); setMidfielder(5); setDefender(4); break;
		}
	}
}