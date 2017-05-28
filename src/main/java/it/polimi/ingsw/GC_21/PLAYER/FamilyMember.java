package it.polimi.ingsw.GC_21.PLAYER;

import it.polimi.ingsw.GC_21.BOARD.*;

public class FamilyMember {
	protected final Color color;
	protected Boolean placed;
	protected Dice diceAssociated;
	protected final FamilyMemberColor familyMemberColor;
	
	
	public FamilyMember(Dice dice, Color color) {
		this.diceAssociated = dice;
		this.placed = false;
		this.familyMemberColor = dice.getFamilyMemberColor();
		this.color = color;
	}
	
	public FamilyMember() {
		this.diceAssociated = new Dice();
		this.placed = false;
		this.familyMemberColor = FamilyMemberColor.Neutral;
		this.color = Color.Neutral;
	}
	
	public static FamilyMember[] factoryFamilyMembers(Dice[] dices, Color color){
		FamilyMember[] familyMembers = new FamilyMember[4];
		for (int i = 0; i < familyMembers.length - 1; i++) {
			familyMembers[i]=new FamilyMember(dices[i], color);
		}
		familyMembers[3] = new FamilyMember();
		return familyMembers;
	}
	
	
	public Boolean isPlaced() {
		return placed;
	}
	
	public void setPlaced(Boolean placed) {
		this.placed = placed;
	}
	
	public Dice getDiceAssociated() {
		return diceAssociated;
	}
	
	public void setDiceAssociated(Dice diceAssociated) {
		this.diceAssociated = diceAssociated;
	}
	
	public FamilyMemberColor getFamilyMemberColor() {
		return familyMemberColor;
	}
	
	public Color getColor() {
		return color;
	}

	
	
	
	

}