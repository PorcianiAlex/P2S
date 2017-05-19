package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

public class Possession {
	private Coins coins;
	private Woods woods;
	private Stones stones;
	private Servants servants;
	private FaithPoints faithPoints;
	private MilitaryPoints militaryPoints;
	private VictoryPoints victoryPoints;
	private Privileges privileges;
	
	public Possession(int coins, int woods, int stones, int servants, int faithPoints,
			int militaryPoints, int victoryPoints, int privileges) {
		this.coins = new Coins(coins);
		this.woods = new Woods(woods);
		this.stones = new Stones(stones);
		this.servants = new Servants(servants);
		this.faithPoints = new FaithPoints(faithPoints);
		this.militaryPoints = new MilitaryPoints(militaryPoints);
		this.victoryPoints = new VictoryPoints(victoryPoints);
		this.privileges = new Privileges(privileges);
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
			return privileges;
		}
		
	}
	
	public void addItemToPossession(Item item){
		if (item instanceof Coins || item.resourceType.equals(ResourceType.Coins)){
			this.coins.addItems(item);
		}
		if (item instanceof Servants || item.resourceType.equals(ResourceType.Servants)){
			this.servants.addItems(item);
		}
		if (item instanceof Woods || item.resourceType.equals(ResourceType.Woods)){
			this.woods.addItems(item);
		}
		if (item instanceof Stones || item.resourceType.equals(ResourceType.Stones)){
			this.stones.addItems(item);
		}
		if (item instanceof MilitaryPoints || item.resourceType.equals(ResourceType.MilitaryPoints)){
			this.militaryPoints.addItems(item);
		}
		
		if (item instanceof VictoryPoints || item.resourceType.equals(ResourceType.VictoryPoints)){
			this.victoryPoints.addItems(item);
		}
		
		if (item instanceof FaithPoints || item.resourceType.equals(ResourceType.FaithPoints)){
			this.faithPoints.addItems(item);
		}
		if (item instanceof Privileges || item.resourceType.equals(ResourceType.Privileges)){
			this.privileges.addItems(item);
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
	
	

	public Privileges getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Privileges privileges) {
		this.privileges = privileges;
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