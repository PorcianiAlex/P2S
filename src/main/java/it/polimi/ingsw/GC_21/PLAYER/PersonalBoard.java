package it.polimi.ingsw.GC_21.PLAYER;

import java.util.*;

import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.*;

public class PersonalBoard {

	private PersonalCardPlace[] buildings = new PersonalCardPlace[6];
	private PersonalCardPlace[] territories = new PersonalCardPlace[6];
	private PersonalCardPlace[] characters = new PersonalCardPlace[6];
	private PersonalCardPlace[] ventures = new PersonalCardPlace[6];
	private ArrayList<PersonalPermanentEffect> personalPermanetEffect = new ArrayList<PersonalPermanentEffect>();
	private Player player;
	private BonusTile  bonusTile; 


	/**
	 * 
	 * @param CardType
	 */
	public Effect checkEffect(String CardType) {
		// TODO - implement PersonalBoard.checkEffect
		throw new UnsupportedOperationException();
	}

}