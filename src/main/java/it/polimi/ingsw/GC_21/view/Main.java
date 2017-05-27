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
		
		Game game = new Game();
		Controller controller = new Controller(game);
		RemoteView remoteView = new RemoteView(game);
		remoteView.setMyObserver(controller);
		controller.addRemoteView(remoteView);
		game.setViewObserver(remoteView);
		game.executeGame();
		
	}
}
