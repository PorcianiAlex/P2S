package it.polimi.ingsw.GC_21.PLAYER;

import it.polimi.ingsw.GC_21.BOARD.CardPlace;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.EFFECT.EffectType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Card;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevelopmentCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;

public class PersonalCardPlace extends CardPlace{
	private Possession possession;

	
public PersonalCardPlace(DevelopmentCard card, Possession possession) {
		super(card);
		this.possession = possession; 
		
	}


	public void addCard() {
		// TODO - implement PersonalCardPlace.addCard
		throw new UnsupportedOperationException();
	}

	public Possession getPossession() {
		return possession;
	}

	public void setPossession(Possession possession) {
		this.possession = possession;
	}
	

}