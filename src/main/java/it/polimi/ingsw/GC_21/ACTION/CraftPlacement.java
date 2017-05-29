package it.polimi.ingsw.GC_21.ACTION;

import java.util.ArrayList;

import it.polimi.ingsw.GC_21.BOARD.ActionSpace;
import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.BOARD.CraftArea;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.BOARD.MultipleActionSpace;
import it.polimi.ingsw.GC_21.BOARD.SingleActionSpace;
import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.ToCallBeforeCraft;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Servants;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CraftPlacement extends PlacementAction {
	private final CraftType craftType;
	private final CraftArea craftArea;
	

	public CraftPlacement(Player playerInAction, int actionValue, FamilyMember selectedFamilyMember, Servants servantsToConvert, CraftType craftType, ActionSpace selectedActionSpace, Board board) {
		super(playerInAction, actionValue, selectedFamilyMember, selectedActionSpace, servantsToConvert, board);
		this.craftType = craftType;
		this.craftArea = board.getHarvestArea();
	}
	
	public static CraftPlacement factoryCraftPlacement(Player playerInAction, FamilyMemberColor familyMemberColor,
			Board board, int servantsNumber, CraftType craftType, String spaceType) {
		FamilyMember selectedFamilyMember = playerInAction.getSpecificFamilyMember(familyMemberColor);
		int actionValue = selectedFamilyMember.getDiceAssociated().getValue();
		Servants servantsToConvert = new Servants(servantsNumber);
		ActionSpace selectedActionSpace = board.getHarvestArea().getMultipleActionSpace();
		CraftPlacement craftPlacement = new CraftPlacement(playerInAction, actionValue, selectedFamilyMember, servantsToConvert, craftType, selectedActionSpace, board);
		return craftPlacement;
	}
	
	@Override
	public boolean checkAction() {
		return super.checkAction();
	}

	
	@Override
	public boolean checkOtherFamilyMember() {
		return craftArea.checkFamilyMemberColor(selectedFamilyMember.getColor());
	}
	
	@Override
	public void Execute() {	
		super.Execute();
		CraftAction craftAction = new CraftAction(playerInAction, craftType, actionValue);
		int indexOfToCallBeforeCraftArray = this.playerInAction.getMyPersonalBoard().getToCallBeforeCraftEffects().size();
		ArrayList<ToCallBeforeCraft> effectsOnTheGo = this.playerInAction.getMyPersonalBoard().getToCallBeforeCraftEffects();
		for (int i = 0; i < indexOfToCallBeforeCraftArray; i++) {
			((Effect) effectsOnTheGo.get(indexOfToCallBeforeCraftArray)).activateEffect(playerInAction, craftAction);
		}
		craftAction.Execute();
	}
}