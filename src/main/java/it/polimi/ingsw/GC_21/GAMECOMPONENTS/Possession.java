package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

public class Possession {
	private int coin;
	private int wood;
	private int stone;
	private int servant;
	private int faithPoints;
	private int militaryPoints;
	private int victoryPoints;
	
	public boolean compare(Possession possession){
		if(this.coin >= possession.coin 
				&& this.wood >= possession.wood 
				&& this.stone >= possession.stone
				&& this.servant >= possession.servant 
				&& this.faithPoints >= possession.faithPoints 
				&& this.militaryPoints >= possession.militaryPoints
				&& this.victoryPoints >= possession.victoryPoints){
			return true;
		}
		else
			return false;
		
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public int getWood() {
		return wood;
	}

	public void setWood(int wood) {
		this.wood = wood;
	}

	public int getStone() {
		return stone;
	}

	public void setStone(int stone) {
		this.stone = stone;
	}

	public int getServant() {
		return servant;
	}

	public void setServant(int servant) {
		this.servant = servant;
	}

	public int getFaithPoints() {
		return faithPoints;
	}

	public void setFaithPoints(int faithPoints) {
		this.faithPoints = faithPoints;
	}

	public int getMilitaryPoints() {
		return militaryPoints;
	}

	public void setMilitaryPoints(int militaryPoints) {
		this.militaryPoints = militaryPoints;
	}

	public int getVictoryPoints() {
		return victoryPoints;
	}

	public void setVictoryPoints(int victoryPoints) {
		this.victoryPoints = victoryPoints;
	}
	

}