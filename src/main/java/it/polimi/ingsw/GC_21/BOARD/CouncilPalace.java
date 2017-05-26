package it.polimi.ingsw.GC_21.BOARD;

import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.Immediate;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Privileges;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;

public class CouncilPalace {
	private MultipleActionSpace multipleActionSpace;
	
	public CouncilPalace() {
		multipleActionSpace = new MultipleActionSpace(1, new Effect(new Possession(1, 0, 0, 0, 0, 0, 0), 1));
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

	public CouncilPalace(MultipleActionSpace multipleActionSpace) {
		super();
		this.multipleActionSpace = multipleActionSpace;
	}

	@Override
	public String toString() {
		return "CouncilPalace:[" + multipleActionSpace + "]";
	}
	
	

}