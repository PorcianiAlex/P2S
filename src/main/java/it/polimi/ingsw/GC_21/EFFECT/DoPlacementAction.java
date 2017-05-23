package it.polimi.ingsw.GC_21.EFFECT;

import it.polimi.ingsw.GC_21.ACTION.CraftAction;
import it.polimi.ingsw.GC_21.ACTION.PlacementActionWithNoFamilyMember;
import it.polimi.ingsw.GC_21.BOARD.ActionSpace;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.PersonalBoard;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class DoPlacementAction extends Immediate {
	public final int actionValue;
	public final DevCardType devCardType;
	public final Possession discount;
	
	
	public DoPlacementAction(Possession rewards, int actionValue, DevCardType devCardType, Possession discount) {
		super(rewards);
		this.actionValue = actionValue;
		this.devCardType = devCardType;
		this.discount = discount;
	}

	public int getActionValue() {
		return actionValue;
	}
	
	public DevCardType getDevCardType() {
		return devCardType;
	}
	
	@Override
	public void activateEffect(Player player) {
		super.activateEffect(player);
		PlacementActionWithNoFamilyMember placementActionWithNoFamilyMember = new PlacementActionWithNoFamilyMember(player, actionValue, null, null, null, null, devCardType, discount);
		placementActionWithNoFamilyMember.Execute();
	}
	
	public static void main(String[] args) {
		DoPlacementAction doPlacementAction = new DoPlacementAction(new Possession(1, 1, 1, 1, 1, 1, 1), 1, DevCardType.Venture, new Possession(0, 0, 0, 0, 0, 0, 0));
		Player aaa = new Player("aAA", "AAA");
		doPlacementAction.activateEffect(aaa);
	}
	
}
