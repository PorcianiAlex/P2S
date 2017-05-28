package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import java.awt.Choice;

import javax.management.modelmbean.RequiredModelMBean;

public class Ventures extends DevelopmentCard {
	
	private Possession secondRequirement;
	private VictoryPoints finalVictoryPoints;

	
	public Ventures(String name, Possession secondRequirement, int finalVictoryPoints) {
		super(name);
		this.secondRequirement = secondRequirement;
		this.finalVictoryPoints = new VictoryPoints(finalVictoryPoints);
	}


	@Override 
	public Possession getRequirements() {
		boolean choice = chooseReq();
		if (choice==true){
			return super.getRequirements();
		}
		else return getSecondRequirement();
	}
	

	public boolean chooseReq() {
		// TODO CONTROLLER
		throw new UnsupportedOperationException();
	}
	public Possession getSecondRequirement() {
		return secondRequirement;
	}
	public void setSecondRequirement(Possession secondRequirement) {
		this.secondRequirement = secondRequirement;
	}
	

}