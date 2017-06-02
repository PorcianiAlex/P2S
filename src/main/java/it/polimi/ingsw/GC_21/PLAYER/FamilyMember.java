package it.polimi.ingsw.GC_21.PLAYER;

import it.polimi.ingsw.GC_21.BOARD.*;

public class FamilyMember {
	protected final Player ownerPlayer;
	protected Boolean placed;
	protected Dice associatedDice;
	protected final FamilyMemberColor familyMemberColor;
	
	
	public FamilyMember(Dice dice, Player player) {
		this.associatedDice = dice;
		this.placed = false;
		this.familyMemberColor = dice.getdiceColor();
		this.ownerPlayer = player;
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

	
	
	
	
	
	

}