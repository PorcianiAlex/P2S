package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

import java.io.Serializable;
import java.util.ArrayList;

import org.omg.CORBA.PUBLIC_MEMBER;

import it.polimi.ingsw.GC_21.BOARD.Dice;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevDeck;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.REMOTEVIEW.RemoteView;

public class Round implements Serializable{

	private int roundNumber;
	private Game game;
	private Turn currentTurn;
	private Player blackPlayer;
	private ArrayList<Player> turnOrder;
	
	public Round(int roundNumber, Game game) {
		this.roundNumber = roundNumber;
		this.game = game;
		getTurnOrder();
		game.getBoard().refreshBoard();
		for (int i = 0; i < game.getPlayers().size(); i++) {
			game.getPlayers().get(i).refreshPlayer();
		}
		this.placeCard();
		}
	
	private void getTurnOrder() {
		turnOrder = game.getBoard().getCouncilPalace().getTurnOrder();
		blackPlayer = game.getSpecificPlayer(Color.Black);
		ArrayList<Player> playersInGame = game.getPlayers();	
		for (int j = 0; j < playersInGame.size(); j++) {
			if (!turnOrder.contains(playersInGame.get(j))) {
				turnOrder.add(playersInGame.get(j));
			}
		}
	}

	public void executeRound() {
		if (blackPlayer != null) {
			turnOrder.remove(blackPlayer);//for black player there is a specific notify turn in which he plays all his family members at the beginning 
			for (int i = 1; i < 5; i++) {
				currentTurn = new Turn(i, game);
				game.notifyBlackTurn(blackPlayer);
			}
		}
		for (int i = 1; i < 5 ; i++) {	
			currentTurn = new Turn(i, game);
			currentTurn.executeView(turnOrder);
		}
		game.getBoard().setDices(Dice.factoryDices());
	}
	
	public void placeCard() {
		game.getBoard().getSpecificTower(DevCardType.Building).pickCards(game.getCurrentAge().buildingDeck);  
		game.getBoard().getSpecificTower(DevCardType.Territory).pickCards(game.getCurrentAge().territoryDeck);  
		game.getBoard().getSpecificTower(DevCardType.Venture).pickCards(game.getCurrentAge().ventureDeck);  
		game.getBoard().getSpecificTower(DevCardType.Character).pickCards(game.getCurrentAge().characterDeck);  
	
	}

	public int getRoundNumber() {
		return roundNumber;
	}

	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}

	public Turn getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(Turn currentTurn) {
		this.currentTurn = currentTurn;
	}

	public Player getBlackPlayer() {
		return blackPlayer;
	}

	public void setBlackPlayer(Player blackPlayer) {
		this.blackPlayer = blackPlayer;
	}

	
}