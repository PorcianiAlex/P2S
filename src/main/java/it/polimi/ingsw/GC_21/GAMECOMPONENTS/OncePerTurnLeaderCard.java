package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class OncePerTurnLeaderCard extends LeaderCard {

	private Effect immediateEffect;



	public OncePerTurnLeaderCard(String name, int numberOfVenturesRequired, int numberOfCharactersRequired,
			int numberOfBuildingRequired, int numberOfTerritoryRequired, Possession requirements, boolean played,
			Effect immediateEffect) {
		super(name, numberOfVenturesRequired, numberOfCharactersRequired, numberOfBuildingRequired,
				numberOfTerritoryRequired, requirements, played);
		this.immediateEffect = immediateEffect;
	}


	public String getName() {
		return name;
	}



	public Effect getImmediateEffect() {
		return immediateEffect;
	}


	public void setImmediateEffect(Effect immediateEffect) {
		this.immediateEffect = immediateEffect;
	}


	public int getNumberOfVenturesRequired() {
		return numberOfVenturesRequired;
	}


	public void setNumberOfVenturesRequired(int numberOfVenturesRequired) {
		this.numberOfVenturesRequired = numberOfVenturesRequired;
	}


	public int getNumberOfCharactersRequired() {
		return numberOfCharactersRequired;
	}


	public void setNumberOfCharactersRequired(int numberOfCharactersRequired) {
		this.numberOfCharactersRequired = numberOfCharactersRequired;
	}


	public int getNumberOfBuildingRequired() {
		return numberOfBuildingRequired;
	}


	public void setNumberOfBuildingRequired(int numberOfBuildingRequired) {
		this.numberOfBuildingRequired = numberOfBuildingRequired;
	}


	public int getNumberOfTerritoryRequired() {
		return numberOfTerritoryRequired;
	}


	public void setNumberOfTerritoryRequired(int numberOfTerritoryRequired) {
		this.numberOfTerritoryRequired = numberOfTerritoryRequired;
	}

	@Override
	public void callEffect(Player player) {
		this.immediateEffect.activateEffect(player, null);
	}

}