package it.polimi.ingsw.GC_21.EFFECT;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.PersonalBoard;

public abstract class Effect {
	protected Possession rewards;

	public void activateEffect(PersonalBoard personalBoard) {
		personalBoard.setMyPossession(rewards.add(personalBoard.getMyPossession()));
	}

}