package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import it.polimi.ingsw.GC_21.PLAYER.Player;

public abstract class LeaderCard {
	protected String name;
	protected Possession requirements;
	protected int numberOfVenturesRequired;
	protected int numberOfCharactersRequired;
	protected int numberOfBuildingRequired;
	protected int numberOfTerritoryRequired;
	protected boolean played;

	public LeaderCard(String name, int numberOfVenturesRequired, int numberOfCharactersRequired,
			int numberOfBuildingRequired, int numberOfTerritoryRequired, boolean played) {
		super();
		this.name = name;
		this.numberOfVenturesRequired = numberOfVenturesRequired;
		this.numberOfCharactersRequired = numberOfCharactersRequired;
		this.numberOfBuildingRequired = numberOfBuildingRequired;
		this.numberOfTerritoryRequired = numberOfTerritoryRequired;
		this.played = played;
	}



	public boolean checkRequirements(Player player){
		return true;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
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





	public boolean isPlayed() {
		return played;
	}



	public void setPlayed(boolean played) {
		this.played = played;
	}



	public void callEffect(Player player) {		
	}

}
