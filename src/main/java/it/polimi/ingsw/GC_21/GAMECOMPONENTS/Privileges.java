package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Item;

public class Privileges extends Item {
	private final Possession woodsAndStonesReward;
	private final Possession servantsReward;
	private final Possession coinsReward;
	private final Possession militaryPointsReward;
	private final Possession faithPointsReward;
	
	public Privileges(int value, Possession woodsAndStonesReward, Possession servantsReward, Possession coinsReward,
			Possession militaryPointsReward, Possession faithPointsReward) {
		super(value,ResourceType.Privileges);
		this.woodsAndStonesReward = woodsAndStonesReward;
		this.servantsReward = servantsReward;
		this.coinsReward = coinsReward;
		this.militaryPointsReward = militaryPointsReward;
		this.faithPointsReward = faithPointsReward;
	}

	
	public Possession getWoodsAndStonesReward() {
		return woodsAndStonesReward;
	}

	public Possession getServantsReward() {
		return servantsReward;
	}

	public Possession getCoinsReward() {
		return coinsReward;
	}

	public Possession getMilitaryPointsReward() {
		return militaryPointsReward;
	}

	public Possession getFaithPointsReward() {
		return faithPointsReward;
	}

	


}
