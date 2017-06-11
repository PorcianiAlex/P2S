package it.polimi.ingsw.GC_21.EFFECT;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.ACTION.*;
import it.polimi.ingsw.GC_21.ACTION.CraftAction;
import it.polimi.ingsw.GC_21.ACTION.PlacementAction;
import it.polimi.ingsw.GC_21.BOARD.ActionSpace;
import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.BOARD.Floor;
import it.polimi.ingsw.GC_21.BOARD.SingleActionSpace;
import it.polimi.ingsw.GC_21.BOARD.Tower;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.PersonalBoard;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.view.PlacementInput;

public class DoTakeCardAction extends Immediate {
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
		PlacementInput placementInput = new PlacementInput(devCardType, actionValueInfluencer, discount);
		game.notifyCurrent(placementInput);
	}
	

}
