package it.polimi.ingsw.GC_21.view;

import javax.swing.text.View;

import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.controller.Controller;

public class Main {

	public static void main(String[] args) {
		
		Game game = new Game();
		
		Player player = new Player("Alex", Color.Blue);
		Player player2 = new Player("Santa", Color.Yellow);
		Player player3 = new Player("Paty", Color.Red);
		
		Controller controller = new Controller(game);
		
		RemoteView remoteView =new RemoteView(player, controller);
		
	}
}
