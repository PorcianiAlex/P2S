package it.polimi.ingsw.GC_21.BOARD;

import java.util.*;

import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;

public class MarketArea {
	private SingleActionSpace[] singleActionSpace;


	public MarketArea() {
		this.singleActionSpace = new SingleActionSpace[4];
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