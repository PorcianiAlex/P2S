package it.polimi.ingsw.GC_21.BOARD;

import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.Immediate;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;

public class CraftArea {
	
	private CraftType craftType;
	private ActionSpace singleActionSpace;
	private ActionSpace multipleActionSpace;
	
	
	
	public CraftArea(CraftType craftType, Effect MalusOnCraft) {
		super();
		this.craftType = craftType;
		this.singleActionSpace = new SingleActionSpace(1, null);
		this.multipleActionSpace = new MultipleActionSpace(1, MalusOnCraft);
	}

	public void checkFamilyMember() {
		// TODO - implement CraftArea.checkFamilyMember
		throw new UnsupportedOperationException();
	}

	public void callBoardCraftEffect() {
		// TODO - implement CraftArea.callBoardCraftEffect
		throw new UnsupportedOperationException();
	}

	public boolean isCovered() {
		return covered;
	}

	public void setCovered(boolean covered) {
		this.covered = covered;
	}

	public SingleActionSpace getSingleActionSpace() {
		return singleActionSpace;
	}

	public void setSingleActionSpace(SingleActionSpace singleActionSpace) {
		this.singleActionSpace = singleActionSpace;
	}

	public MultipleActionSpace getMultipleActionSpace() {
		return multipleActionSpace;
	}

	public void setMultipleActionSpace(MultipleActionSpace multipleActionSpace) {
		this.multipleActionSpace = multipleActionSpace;
	}

	
	
}