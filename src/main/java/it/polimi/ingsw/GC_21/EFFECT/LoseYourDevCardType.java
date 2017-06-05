package it.polimi.ingsw.GC_21.EFFECT;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class LoseYourDevCardType extends Effect implements ToCallBeforeFinalCount{
	DevCardType cardsToLose;

	public LoseYourDevCardType(DevCardType cardsToLose) {
		this.cardsToLose = cardsToLose;
	}


	//Sets OwnedCardNumber of a certain type to 0, in order not to be counted in the final ranking-making.
	@Override
	public void activateEffect(Player player, Action action) {
		player.getMyPersonalBoard().getOwnedCards(cardsToLose).setOwnedCardsnumber(0);
	}

	
}
