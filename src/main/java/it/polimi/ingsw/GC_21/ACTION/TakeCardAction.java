package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.BOARD.ActionSpace;
import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.BOARD.Floor;
import it.polimi.ingsw.GC_21.BOARD.Tower;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevelopmentCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Servants;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class TakeCardAction extends PlacementAction {

	public TakeCardAction(Player playerInAction, int actionValue, FamilyMember selectedFamilyMember,
			ActionSpace selectedActionSpace, Servants servantsToConvert, Board board) {
		super(playerInAction, actionValue, selectedFamilyMember, selectedActionSpace, servantsToConvert, board);
		// TODO Auto-generated constructor stub
	}
	/*private final Floor selectedFloor;
	private final Tower selectedTower;
	private final DevelopmentCard selectedCard;
	
	public TakeCardAction(Player playerInAction) {
		super(playerInAction);
	}*/

}
