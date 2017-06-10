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

public class DoTakeCardAction extends Immediate {
	private final int actionValueInfluencer;
	private final int actionValueBonus;
	private final DevCardType devCardType;
	private final Possession discount;	
	
	public DoTakeCardAction(Game game, Possession rewards, int privileges, int actionValueInfluencer, int actionValueBonus, DevCardType devCardType, Possession discount) {
		super(rewards, privileges, game);
		this.actionValueInfluencer = actionValueInfluencer;
		this.actionValueBonus = actionValueBonus;
		this.devCardType = devCardType;
		this.discount = discount;
	}

	public int getActionValueInfluencer() {
		return actionValueInfluencer;
	}
	
	public DevCardType getDevCardType() {
		return devCardType;
	}
	
	public void activateEffect(Player player, Action placementAction) {
		/*super.activateEffect(player, placementAction);
		PlacementAction placementAction2 = (PlacementAction) placementAction;
		int floor = player.getMyView().selectFloor();
		Board board = player.getGame().getBoard();
		SingleActionSpace selectedActionSpace = player.getGame().getBoard().getSpecificTower(devCardType).getFloors()[floor].getSingleActionSpace();
		int servantsToConvert = player.getMyView().chooseHowManyServants();
		if (actionValueInfluencer==0){
			int newActionValue = placementAction2.getActionValue() + actionValueBonus;
			PlacementAction rewardAction = new PlacementAction(player, newActionValue, null, selectedActionSpace, null, board);
			player.getGame().getController().update(rewardAction);
		}
		else  {
			int newActionValue = actionValueInfluencer + actionValueBonus;
			PlacementAction rewardAction = new PlacementAction(player, newActionValue, null, selectedActionSpace, null, board);
			player.getGame().getController().update(rewardAction);

		}*/
	}
	

}
