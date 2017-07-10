package it.polimi.ingsw.GC_21.BOARD;

import java.io.Serializable;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevDeck;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;

public class Floor implements Serializable{
	private Game game;
	private int floorNumber;
	private SingleActionSpace singleActionSpace;
	private DevCardPlace devCardPlace;

	
	public Floor(int floorNumber,	Game game) {
		this.game = game;
		this.floorNumber = floorNumber;
		this.singleActionSpace = new SingleActionSpace(floorNumber+(floorNumber-1), null, game);
		this.devCardPlace = new DevCardPlace(null);
	}
	
	public void setCardPlace(DevDeck devDeck) {
		this.devCardPlace.setCard(devDeck.getSingleCard());
	}
	
	public SingleActionSpace getSingleActionSpace() {
		return singleActionSpace;
	}
	public void setSingleActionSpace(SingleActionSpace singleActionSpace) {
		this.singleActionSpace = singleActionSpace;
	}
	public DevCardPlace getDevCardPlace() {
		return devCardPlace;
	}
	public void setDevCardPlace(DevCardPlace devCardPlace) {
		this.devCardPlace = devCardPlace;
	}

	@Override
	public String toString() {
		return "\n[Floor=" + floorNumber + singleActionSpace.toString() + devCardPlace.toString() + "]";
	}
	
	

}