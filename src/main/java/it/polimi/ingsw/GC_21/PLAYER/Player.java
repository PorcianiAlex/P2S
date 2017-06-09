package it.polimi.ingsw.GC_21.PLAYER;

import java.util.*;

import javax.swing.text.View;

import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.view.RemoteView;

public class Player {

	private FamilyMember[] familyMembers;
	private String name;
	private Color playerColor;
	private PersonalBoard myPersonalBoard;
	private Game game;
	
	
	
	
	
	public Player(String name, Color playerColor, Game game) {
		this.familyMembers = FamilyMember.factoryFamilyMembers(game.getBoard().getDices(), this);
		this.name = name;
		this.game = game;
		this.game.addPlayers(this);
		this.playerColor = playerColor;
		this.myPersonalBoard = new PersonalBoard(this);
		this.myPersonalBoard.getMyPossession().add(new Possession(5, 5, 5, 5, 5, 5, 5));
		this.familyMembers = FamilyMember.factoryFamilyMembers(game.getBoard().getDices(), this);
	}

	/*public void printOnPlayer(String string){
		this.myView.getAdapter().out(string);

	}*/
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getPlayerColor() {
		return playerColor;
	}

	public void setPlayerColor(Color playerColor) {
		this.playerColor = playerColor;
	}

	public PersonalBoard getMyPersonalBoard() {
		return myPersonalBoard;
	}

	public void setMyPersonalBoard(PersonalBoard myPersonalBoard) {
		this.myPersonalBoard = myPersonalBoard;
	}
	

	
	
	public FamilyMember getSpecificFamilyMember(FamilyMemberColor familyMemberColor) {
		for (int i = 0; i < familyMembers.length; i++) {
			if (familyMembers[i].getFamilyMemberColor().equals(familyMemberColor)) {
				return familyMembers[i];
			}
		}
		return null;
	}

	

	public FamilyMember[] getFamilyMembers() {
		return familyMembers;
	}

	public void setFamilyMembers(FamilyMember[] familyMembers) {
		this.familyMembers = familyMembers;
	}

	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public String toString() {
		return "Player [Name=" + name + ", playerColor=" + playerColor + "]";
	}
	
	

}