package it.polimi.ingsw.GC_21.BOARD;

public enum FamilyMemberColor {
Black, Orange, White, Neutral;

	public String toString(){
		if (this.equals(FamilyMemberColor.Black)){
			return "Black";
		}
		if (this.equals(FamilyMemberColor.Orange)){
			return "Orange";
		}
		if (this.equals(FamilyMemberColor.White)){
			return "White";
		}
		else {
			return "Neutral";
		}
	
	}
}
