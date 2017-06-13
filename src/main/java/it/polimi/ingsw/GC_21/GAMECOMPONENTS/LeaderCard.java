package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

public class LeaderCard extends Card {
	protected int numberOfVenturesRequired;
	protected int numberOfCharactersRequired;
	protected int numberOfBuildingRequired;
	protected int numberOfTerritoryRequired;
	
	
	public LeaderCard(String name) {
		super(name);
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
	
	
}