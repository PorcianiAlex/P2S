package it.polimi.ingsw.GC_21.view;

import java.util.ArrayList;

import javax.swing.text.View;

import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.CardCreator;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevDeck;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.controller.Controller;

public class Main {

	public static void main(String[] args) {
				
		Player player = new Player("Alex", Color.Blue);
		Player player2 = new Player("Santa", Color.Yellow);
		Player player3 = new Player("Paty", Color.Red);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player3);
		players.add(player2);
		players.add(player);
		Game game = new Game(players.size(), players);
		
		RemoteView remoteView =new RemoteView(player, game.getController());
		
		
	}
}
