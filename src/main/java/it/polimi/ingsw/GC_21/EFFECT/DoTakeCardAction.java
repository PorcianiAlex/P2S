package it.polimi.ingsw.GC_21.EFFECT;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.ACTION.*;
import it.polimi.ingsw.GC_21.CLIENT.TakeCardMessage;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class DoTakeCardAction extends Effect {
	private final int actionValueInfluencer;
	private final DevCardType devCardType;
	private final Possession discount;	
	
	public DoTakeCardAction(Game game, Possession rewards, int privileges, int actionValueInfluencer, DevCardType devCardType, Possession discount) {
		super(rewards, privileges, game);
		this.actionValueInfluencer = actionValueInfluencer;
		this.devCardType = devCardType;
		this.discount = discount;
	}

	public int getActionValueInfluencer() {
		return actionValueInfluencer;
	}
	
	public DevCardType getDevCardType() {
		return devCardType;
	}
	
	@Override
	public void activateEffect(Player player, Action placementAction) {
		super.activateEffect(player, placementAction);
		TakeCardMessage takeCardMessage = new TakeCardMessage(devCardType, actionValueInfluencer, discount);
		game.notifyCurrent(takeCardMessage);
	}
	

}
