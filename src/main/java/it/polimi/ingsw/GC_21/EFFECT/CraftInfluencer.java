package it.polimi.ingsw.GC_21.EFFECT;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.ACTION.CraftAction;
import it.polimi.ingsw.GC_21.ACTION.PlacementAction;
import it.polimi.ingsw.GC_21.ACTION.TowerPlacement;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CraftInfluencer extends Effect implements ToCallBeforeCraft {
	private CraftType craftType;
	private int craftInfluencer;
	
	public CraftInfluencer(CraftType craftType, int craftInfluencer, Game game) {
		this.game=game;
		this.craftType = craftType;
		this.craftInfluencer = craftInfluencer;
	}
	
	
	public CraftType getCraftType() {
		return craftType;
	}


	public void setCraftType(CraftType craftType) {
		this.craftType = craftType;
	}

	public int getCraftBonus() {
		return craftInfluencer;
	}


	public void setCraftBonus(int craftBonus) {
		this.craftInfluencer = craftBonus;
	}

	@Override
	public void activateEffect(Player player, Action action) {
		if (action instanceof CraftAction && ((CraftAction) action).getCraftType().equals(this.craftType)){
			int newActionValue = this.craftInfluencer + ((CraftAction) action).getActionValue();
			((CraftAction) action).setActionValue(newActionValue);
		}
	}
	
	

}