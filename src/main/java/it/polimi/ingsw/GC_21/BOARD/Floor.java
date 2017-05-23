package it.polimi.ingsw.GC_21.BOARD;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevDeck;

public class Floor {

	private int floorNumber;
	private SingleActionSpace singleActionSpace;
	private DevCardPlace devCardPlace;

	
	public Floor(int floorNumber) {
		this.floorNumber = floorNumber;
		this.singleActionSpace = new SingleActionSpace(floorNumber+(floorNumber-1), null);
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
		return "Floor [floorNumber=" + floorNumber + "]";
	}
	
	

}