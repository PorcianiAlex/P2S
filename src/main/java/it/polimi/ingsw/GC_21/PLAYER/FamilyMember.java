package it.polimi.ingsw.GC_21.PLAYER;

import it.polimi.ingsw.GC_21.BOARD.*;

public class FamilyMember {
	protected final Color playerColor;
	protected Boolean placed;
	protected Dice associatedDice;
	protected final FamilyMemberColor familyMemberColor;
	
	
	public FamilyMember(Dice dice, Color color) {
		this.associatedDice = dice;
		this.placed = false;
		this.familyMemberColor = dice.getdicerColor();
		this.playerColor = color;
	}
	
	public FamilyMember(Color color) {
		this.associatedDice = new Dice();
		this.placed = false;
		this.familyMemberColor = FamilyMemberColor.Neutral;
		this.playerColor = color;
	}
	
	public static FamilyMember[] factoryFamilyMembers(Dice[] dices, Color color){
		FamilyMember[] familyMembers = new FamilyMember[4];
		for (int i = 0; i < familyMembers.length - 1; i++) {
			familyMembers[i]=new FamilyMember(dices[i], color);
		}
		familyMembers[3] = new FamilyMember(color);
		return familyMembers;
	}
	
	
	public Boolean isPlaced() {
		return placed;
	}
	
	public void setPlaced(Boolean placed) {
		this.placed = placed;
	}
	
	
	public FamilyMemberColor getFamilyMemberColor() {
		return familyMemberColor;
	}

	public Dice getAssociatedDice() {
		return associatedDice;
	}

	public void setAssociatedDice(Dice associatedDice) {
		this.associatedDice = associatedDice;
	}


	public Boolean getPlaced() {
		return placed;
	}

	public Color getPlayerColor() {
		return playerColor;
	}
	
	
	
	
	
	

}