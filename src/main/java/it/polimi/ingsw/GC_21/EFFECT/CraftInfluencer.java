package it.polimi.ingsw.GC_21.EFFECT;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.ACTION.CraftAction;
import it.polimi.ingsw.GC_21.ACTION.PlacementAction;
import it.polimi.ingsw.GC_21.ACTION.TowerPlacement;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CraftInfluencer implements ToCallBeforeCraft {
	private CraftType craftType;
	private int craftBonus;
	
	public CraftInfluencer(CraftType craftType, int craftBonus) {
		super();
		this.craftType = craftType;
		this.craftBonus = craftBonus;
	}
	
	
	public CraftType getCraftType() {
		return craftType;
	}


	public void setCraftType(CraftType craftType) {
		this.craftType = craftType;
	}

	public int getCraftBonus() {
		return craftBonus;
	}


	public void setCraftBonus(int craftBonus) {
		this.craftBonus = craftBonus;
	}

	@Override
	public void activateEffect(Player player, Action action) {
		if (action instanceof CraftAction && ((CraftAction) action).getCraftType().equals(this.craftType)){
			int newActionValue = this.craftBonus + ((CraftAction) action).getActionValue();
			((CraftAction) action).setActionValue(newActionValue);
		}
	}
	
	

}