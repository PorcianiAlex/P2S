package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.BOARD.ActionSpace;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class PlacementActionWithNoFamilyMember extends PlacementAction {
	private final DevCardType devCardType;
	private final Possession discount;
	
	public PlacementActionWithNoFamilyMember(Player playerInAction, int actionValue, FamilyMember selectedFamilyMember,
			ActionSpace selectedActionSpace, DevCardType devCardType, Possession discount) {
		super(playerInAction, actionValue, selectedFamilyMember, selectedActionSpace);
		this.devCardType = devCardType;
		this.discount = discount;
	}
	public int getActionValue() {
		return actionValue;
	}
	public ActionSpace getSelectedActionSpace() {
		return selectedActionSpace;
	}
	public DevCardType getDevCardType() {
		return devCardType;
	}
	public Possession getDiscount() {
		return discount;
	}
	
	
}
