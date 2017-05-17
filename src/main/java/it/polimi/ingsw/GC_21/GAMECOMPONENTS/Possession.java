package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

public class Possession {
	private Coins coins;
	private Woods woods;
	private Stones stones;
	private Servants servants;
	private FaithPoints faithPoints;
	private MilitaryPoints militaryPoints;
	private VictoryPoints victoryPoints;
	
	public boolean compare(Possession possession){
		if(this.coins.getCoins() >= possession.woods.getWoods()
				&& this.woods.getWoods() >= possession.woods.getWoods()
				&& this.stones.getStones() >= possession.stones.getStones()
				&& this.servants.getServants() >= possession.servants.getServants()
				&& this.faithPoints.getFaithPoints() >= possession.faithPoints.getFaithPoints()
				&& this.militaryPoints.getMilitaryPoints() >= possession.militaryPoints.getMilitaryPoints()
				&& this.victoryPoints.getVictoryPoints() >= possession.victoryPoints.getVictoryPoints()){
			return true;
		}
		else
			return false;
		
	}

	public Coins getCoins() {
		return coins;
	}

	public void setCoins(Coins coins) {
		this.coins = coins;
	}

	public Woods getWoods() {
		return woods;
	}

	public void setWoods(Woods woods) {
		this.woods = woods;
	}

	public Stones getStones() {
		return stones;
	}

	public void setStones(Stones stones) {
		this.stones = stones;
	}

	public Servants getServants() {
		return servants;
	}

	public void setServants(Servants servants) {
		this.servants = servants;
	}

	public FaithPoints getFaithPoints() {
		return faithPoints;
	}

	public void setFaithPoints(FaithPoints faithPoints) {
		this.faithPoints = faithPoints;
	}

	public MilitaryPoints getMilitaryPoints() {
		return militaryPoints;
	}

	public void setMilitaryPoints(MilitaryPoints militaryPoints) {
		this.militaryPoints = militaryPoints;
	}

	public VictoryPoints getVictoryPoints() {
		return victoryPoints;
	}

	public void setVictoryPoints(VictoryPoints victoryPoints) {
		this.victoryPoints = victoryPoints;
	}

	

}