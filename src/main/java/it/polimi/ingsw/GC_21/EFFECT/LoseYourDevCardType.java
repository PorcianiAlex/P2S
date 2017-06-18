package it.polimi.ingsw.GC_21.EFFECT;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class LoseYourDevCardType extends Effect implements ToCallBeforeFinalCount, Permanent{
	DevCardType cardsToLose;

	public LoseYourDevCardType(DevCardType cardsToLose, Game game) {
		this.game=game;
		this.cardsToLose = cardsToLose;
	}

	//Sets OwnedCardNumber of a certain type to 0, in order not to be counted in the final ranking-making.
	@Override
	public void activateEffect(Player player, Action action) {
		game.notifyCurrentString("Your Development Card of type " + cardsToLose.toString() + " won't give you Victory Points in the final counting!");
		player.getMyPersonalBoard().getSpecificOwnedCards(cardsToLose).setOwnedCardsnumber(0);
	}

	
}
