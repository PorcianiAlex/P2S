package it.polimi.ingsw.GC_21.PLAYER;

import it.polimi.ingsw.GC_21.BOARD.*;

public class FamilyMember {
	protected Color color;
	protected Boolean placed = false;
	protected Dice diceAssociated;
	protected FamilyMemberColor familyMemberColor;
	
	
	public FamilyMember(Dice dice, Color color) {
		this.diceAssociated = dice;
		this.placed = false;
		this.familyMemberColor = dice.getFamilyMemberColor();
		this.color = color;
	}
	
	public FamilyMember() {
		this.diceAssociated = new Dice();
		this.placed = false;
		this.familyMemberColor = familyMemberColor.Neutral;
	}
	
	public static FamilyMember[] factoryFamilyMembers(Dice[] dices, Color color){
		FamilyMember[] familyMembers = new FamilyMember[4];
		for (int i = 0; i < familyMembers.length - 1; i++) {
			familyMembers[i]=new FamilyMember(dices[i], color);
		}
		familyMembers[3] = new FamilyMember();
		return familyMembers;
	}
	
	
	public Boolean getPlaced() {
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
	public void setFamilyMemberColor(FamilyMemberColor familyMemberColor) {
		this.familyMemberColor = familyMemberColor;
	}
	
	

}