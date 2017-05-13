package it.polimi.ingsw.GC_21.BOARD;

import java.util.*;

public abstract class Tower {

	private Color color;
	private Floor[] floors = new Floor[4];
	

	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}


	public Floor[] getFloors() {
		return floors;
	}


	public void setFloors(Floor[] floors) {
		this.floors = floors;
	}


	public void checkFamilyMember() {
		// TODO - implement Tower.checkFamilyMember
		throw new UnsupportedOperationException();
	}

}