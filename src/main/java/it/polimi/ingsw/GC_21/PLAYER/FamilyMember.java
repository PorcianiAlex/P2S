package it.polimi.ingsw.GC_21.PLAYER;

import java.io.Serializable;
import java.lang.reflect.Member;

import it.polimi.ingsw.GC_21.BOARD.*;

public class FamilyMember implements Serializable{
	private final Player ownerPlayer;
	private Boolean placed;
	private Dice associatedDice;
	private final FamilyMemberColor familyMemberColor;
	private int actionValue;
	
	
	public FamilyMember(Dice dice, Player player) {
		this.associatedDice = dice;
		this.placed = false;
		this.familyMemberColor = dice.getdiceColor();
		this.ownerPlayer = player;
		this.actionValue = dice.getValue();
	}
	
	public int getActionValue() {
		return actionValue;
	}

	public void setActionValue(int actionValue) {
		this.actionValue = actionValue;
	}

	public FamilyMember(Player player) {
		this.associatedDice = new Dice();
		this.placed = false;
		this.familyMemberColor = FamilyMemberColor.Neutral;
		this.ownerPlayer = player;
	}
	
	public static FamilyMember[] factoryFamilyMembers(Dice[] dices, Player player){
		FamilyMember[] familyMembers = new FamilyMember[4];
		for (int i = 0; i < familyMembers.length - 1; i++) {
			familyMembers[i]=new FamilyMember(dices[i], player);
		}
		familyMembers[3] = new FamilyMember(player);
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

	public Player getOwnerPlayer() {
		return ownerPlayer;
	}

	
	@Override
	public String toString() {
		return familyMemberColor + "family Member=[  Member Value=" + associatedDice.getValue()  + ", Placed=" + placed + " ]";
	}
	
	
	
	

}