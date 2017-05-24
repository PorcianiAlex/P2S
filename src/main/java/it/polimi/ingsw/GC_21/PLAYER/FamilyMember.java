package it.polimi.ingsw.GC_21.PLAYER;

import it.polimi.ingsw.GC_21.BOARD.*;

public class FamilyMember {
	private String color;
	private Boolean placed = false;
	private Dice diceAssociated;
	private FamilyMemberColor familyMemberColor;
	
	
	public FamilyMember(String color, Dice dice) {
		this.color = color;
		this.diceAssociated = dice;
		this.placed = false;
		this.familyMemberColor = dice.getFamilyMemberColor();
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
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