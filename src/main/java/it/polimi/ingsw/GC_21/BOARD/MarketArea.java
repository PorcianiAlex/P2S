package it.polimi.ingsw.GC_21.BOARD;

import java.util.*;

import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;

public class MarketArea {
	private boolean covered;
	private SingleActionSpace[] singleActionSpace = new SingleActionSpace[4];

	public boolean isCovered() {
		return covered;
	}

	public void setCovered(boolean covered) {
		this.covered = covered;
	}

	public SingleActionSpace[] getSingleActionSpace() {
		return singleActionSpace;
	}

	public void setSingleActionSpace(SingleActionSpace[] singleActionSpace) {
		this.singleActionSpace = singleActionSpace;
	}

	public void callImmediateEffect() {
		// TODO - implement MarketArea.callImmediateEffect
		throw new UnsupportedOperationException();
	}

}