package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import it.polimi.ingsw.GC_21.PLAYER.Player;

public class Possession {
	private Coins coins;
	private Woods woods;
	private Stones stones;
	private Servants servants;
	private FaithPoints faithPoints;
	private MilitaryPoints militaryPoints;
	private VictoryPoints victoryPoints;
	
	public Possession(int coins, int woods, int stones, int servants, int faithPoints,
			int militaryPoints, int victoryPoints) {
		this.coins = new Coins(coins);
		this.woods = new Woods(woods);
		this.stones = new Stones(stones);
		this.servants = new Servants(servants);
		this.faithPoints = new FaithPoints(faithPoints);
		this.militaryPoints = new MilitaryPoints(militaryPoints);
		this.victoryPoints = new VictoryPoints(victoryPoints);
	}

	public boolean compare(Possession possession){
		if(this.coins.compare(possession.coins) &&
				this.woods.compare(possession.woods) &&
				this.stones.compare(possession.stones) &&
				this.servants.compare(possession.servants) &&
				this.faithPoints.compare(possession.faithPoints) &&
				this.militaryPoints.compare(possession.militaryPoints) &&
				this.victoryPoints.compare(possession.victoryPoints)){
			return true;
		}
		else
			return false;
	}
	
	//Return TRUE if I have enough of that item
	public boolean compareItemToPossession(Item item){
		if (item instanceof Coins){
			return (this.coins.getValue()>=item.getValue());
		}
		if (item instanceof Servants){
			return (this.servants.getValue()>=item.getValue());
		}
		if (item instanceof Woods){
			return (this.woods.getValue()>=item.getValue());		
		}
		if (item instanceof Stones){
			return (this.stones.getValue()>=stones.getValue());
		}
		if (item instanceof MilitaryPoints){
			return (this.militaryPoints.getValue()>=item.getValue());
		}
		if (item instanceof FaithPoints){
			return (this.faithPoints.getValue()>=item.getValue());		
		}
		if (item instanceof VictoryPoints){
			return (this.victoryPoints.getValue()>=item.getValue());		
		}
		else{
			return false;
		}
	}
	
	public void add(Possession possession2){
		this.coins.setValue(this.coins.getValue() + possession2.coins.getValue());
		this.woods.setValue(this.woods.getValue() + possession2.woods.getValue());
		this.stones.setValue(this.stones.getValue() + possession2.stones.getValue());
		this.servants.setValue(this.servants.getValue() + possession2.servants.getValue());
		this.victoryPoints.setValue(this.victoryPoints.getValue() + possession2.victoryPoints.getValue());
		this.militaryPoints.setValue(this.militaryPoints.getValue() + possession2.militaryPoints.getValue());
		this.faithPoints.setValue(this.faithPoints.getValue() + possession2.faithPoints.getValue());
		}
	
	public void subtract(Possession possession2){
		this.coins.setValue(this.coins.getValue() - possession2.coins.getValue());
		this.woods.setValue(this.woods.getValue() - possession2.woods.getValue());
		this.stones.setValue(this.stones.getValue() - possession2.stones.getValue());
		this.servants.setValue(this.servants.getValue() - possession2.servants.getValue());
		this.victoryPoints.setValue(this.victoryPoints.getValue() - possession2.victoryPoints.getValue());
		this.militaryPoints.setValue(this.militaryPoints.getValue() - possession2.militaryPoints.getValue());
		this.faithPoints.setValue(this.faithPoints.getValue() - possession2.faithPoints.getValue());
		//TO DO: set all negative values to 0;
		}
	
	public Item getRequestedItem(ResourceType resourceType){
		if (resourceType.equals(ResourceType.Coins)){
			return coins;
		}
		if (resourceType.equals(ResourceType.Servants)){
			return servants;
		}
		if (resourceType.equals(ResourceType.Woods)){
			return woods;
		}
		if (resourceType.equals(ResourceType.Stones)){
			return stones;
		}
		if (resourceType.equals(ResourceType.MilitaryPoints)){
			return militaryPoints;
		}
		if (resourceType.equals(ResourceType.VictoryPoints)){
			return victoryPoints;
		}
		if (resourceType.equals(ResourceType.FaithPoints)){
			return faithPoints;
		}
		else {
			return null;
		}
		
	}
	
	public void addItemToPossession(Item item){
		if (item instanceof Coins){
			this.coins.addItems(item);
		}
		if (item instanceof Servants){
			this.servants.addItems(item);
		}
		if (item instanceof Woods){
			this.woods.addItems(item);
		}
		if (item instanceof Stones){
			this.stones.addItems(item);
		}
		if (item instanceof MilitaryPoints){
			this.militaryPoints.addItems(item);
		}
		
		if (item instanceof VictoryPoints){
			this.victoryPoints.addItems(item);
		}
		
		if (item instanceof FaithPoints){
			this.faithPoints.addItems(item);
		}
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
	
	public boolean checkRequirements(Player player){
		return player.getMyPersonalBoard().getMyPossession().compare(this);
	}

	@Override
	public String toString() {
		return "[Coins: " + coins.toString() + ", Woods: " + woods.toString() + ", Stones: " + stones.toString() + ", Servants: " + servants.toString()
				+ ", Faith Points: " + faithPoints.toString() + ", Military Points: " + militaryPoints.toString()
				+ ", Victory Points:" + victoryPoints.toString() +"]";
	}
	

	

}