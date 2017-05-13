package it.polimi.ingsw.GC_21.BOARD;

import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.Immediate;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;

public class CouncilPalace {
	private MultipleActionSpace multipleActionSpace;
	
	public CouncilPalace(Immediate councilPalaceEffect) {
		super();
		this.multipleActionSpace = new MultipleActionSpace(1, councilPalaceEffect);
	}

	public MultipleActionSpace getMultipleActionSpace() {
		return multipleActionSpace;
	}

	public void setMultipleActionSpace(MultipleActionSpace multipleActionSpace) {
		this.multipleActionSpace = multipleActionSpace;
	}


	public void callImmediateEffect() {
		// TODO - implement CouncilPalace.callImmediateEffect
		throw new UnsupportedOperationException();
	}

}