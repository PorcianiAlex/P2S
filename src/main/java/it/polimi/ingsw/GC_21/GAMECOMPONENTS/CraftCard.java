package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import it.polimi.ingsw.GC_21.BOARD.CraftType;

public class CraftCard extends DevelopmentCard{

	private int requiredValueForCraft;
	private CraftType craftType;

	public int getRequiredValueForCraft() {
		return requiredValueForCraft;
	}

	public void setRequiredValueForCraft(int requiredValueForCraft) {
		this.requiredValueForCraft = requiredValueForCraft;
	}

	
	public CraftCard(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

}
