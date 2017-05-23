package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.BOARD.ActionSpace;
import it.polimi.ingsw.GC_21.BOARD.Floor;
import it.polimi.ingsw.GC_21.BOARD.Tower;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Servants;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class PlacementActionWithNoFamilyMember extends TowerPlacement {
	private final DevCardType devCardType;
	private final Possession discount;
	
	public PlacementActionWithNoFamilyMember(Player playerInAction, int actionValue, FamilyMember selectedFamilyMember,
			Floor selectedFloor, Tower selectedTower, Servants servantsToConvert, DevCardType devCardType,
			Possession discount) {
		super(playerInAction, actionValue, selectedFamilyMember, selectedFloor, selectedTower, servantsToConvert);
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
