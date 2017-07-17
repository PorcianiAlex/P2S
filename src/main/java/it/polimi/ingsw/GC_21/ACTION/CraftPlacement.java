package it.polimi.ingsw.GC_21.ACTION;

import java.util.ArrayList;


import it.polimi.ingsw.GC_21.BOARD.ActionSpace;
import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.BOARD.CraftArea;
import it.polimi.ingsw.GC_21.BOARD.CraftType;

import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.ToCallBeforeCraft;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Servants;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CraftPlacement extends PlacementAction {
	private final CraftArea craftArea;
	

	public CraftPlacement(Player playerInAction, int actionValue, FamilyMember selectedFamilyMember, Servants servantsToConvert, CraftArea craftArea, ActionSpace selectedActionSpace, Board board) {
		super(playerInAction, actionValue, selectedFamilyMember, selectedActionSpace, servantsToConvert, board);
		this.craftArea = craftArea;
	}
	
	public static CraftPlacement factoryCraftPlacement(Player playerInAction, FamilyMemberColor familyMemberColor,
			Board board, int servantsNumber, CraftType craftType, int spaceType) {
		FamilyMember selectedFamilyMember = playerInAction.getSpecificFamilyMember(familyMemberColor);
		int actionValue = selectedFamilyMember.getActionValue();
		Servants servantsToConvert = new Servants(servantsNumber);
		CraftArea craftArea = board.getSpecificCraftArea(craftType);
		ActionSpace selectedActionSpace = craftArea.selectActionSpace(spaceType);
		CraftPlacement craftPlacement = new CraftPlacement(playerInAction, actionValue, selectedFamilyMember, servantsToConvert, craftArea, selectedActionSpace, board);
		return craftPlacement;
	}
	

	
	@Override
	public boolean checkOtherFamilyMember() {
		if (selectedFamilyMember.getFamilyMemberColor().equals(FamilyMemberColor.Neutral)) {
			return false;
		}
		else {
			return craftArea.checkCraftFamilyMemberPlayer(playerInAction);
		}
		
	}
	
	@Override
	public void Execute() {	
		super.Execute();
		CraftAction craftAction = new CraftAction(playerInAction, craftArea.getCraftType(), actionValue);
		int indexOfToCallBeforeCraftArray = this.playerInAction.getMyPersonalBoard().getToCallBeforeCraftEffects().size();
		ArrayList<ToCallBeforeCraft> effectsOnTheGo = this.playerInAction.getMyPersonalBoard().getToCallBeforeCraftEffects();
		for (int i = 0; i < indexOfToCallBeforeCraftArray; i++) {
			((Effect) effectsOnTheGo.get(i)).activateEffect(playerInAction, craftAction);
		}
		craftAction.Execute();
	}

	
}