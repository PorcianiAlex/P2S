package it.polimi.ingsw.GC_21.PLAYER;

import it.polimi.ingsw.GC_21.BOARD.CardPlace;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.EFFECT.EffectType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Card;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevelopmentCard;

public class PersonalCardPlace extends CardPlace{

	
public PersonalCardPlace(DevelopmentCard card) {
		super(card);
		
	}

public void callEffect() {
	
	this.getCard().callEffect(EffectType.Permanent);
}

	public void addCard() {
		// TODO - implement PersonalCardPlace.addCard
		throw new UnsupportedOperationException();
	}

}