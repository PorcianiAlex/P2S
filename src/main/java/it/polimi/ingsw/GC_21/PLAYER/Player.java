package it.polimi.ingsw.GC_21.PLAYER;

import java.util.*;

public class Player {

	Collection<FamilyMember> have;
	private String name;
	private String playerColor;
	private PersonalBoard myPersonalBoard;

	public void chooseAction() {
		// TODO - implement Player.chooseAction
		throw new UnsupportedOperationException();
	}

	public void chooseBonusTile() {
		// TODO - implement Player.chooseBonusTile
		throw new UnsupportedOperationException();
	}

	public Collection<FamilyMember> getHave() {
		return have;
	}

	public void setHave(Collection<FamilyMember> have) {
		this.have = have;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlayerColor() {
		return playerColor;
	}

	public void setPlayerColor(String playerColor) {
		this.playerColor = playerColor;
	}

	public PersonalBoard getMyPersonalBoard() {
		return myPersonalBoard;
	}

	public void setMyPersonalBoard(PersonalBoard myPersonalBoard) {
		this.myPersonalBoard = myPersonalBoard;
	}
	

}