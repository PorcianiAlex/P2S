package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.BOARD.ActionSpace;
import it.polimi.ingsw.GC_21.BOARD.CraftArea;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.BOARD.MultipleActionSpace;
import it.polimi.ingsw.GC_21.BOARD.SingleActionSpace;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Servants;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CraftAction extends CraftPlacement{

	public CraftAction(Player playerInAction, int actionValue, FamilyMember selectedFamilyMember, CraftArea craftArea,
			Servants servantsToConvert, CraftType craftType, MultipleActionSpace multipleActionSpace) {
		super(playerInAction, actionValue, selectedFamilyMember, craftArea, servantsToConvert, craftType, multipleActionSpace);
		// TODO Auto-generated constructor stub
	}
	
	public CraftAction(Player playerInAction, int actionValue, FamilyMember selectedFamilyMember, CraftArea craftArea,
			Servants servantsToConvert, CraftType craftType, SingleActionSpace singleActionSpace) {
		super(playerInAction, actionValue, selectedFamilyMember, craftArea, servantsToConvert, craftType, singleActionSpace);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
