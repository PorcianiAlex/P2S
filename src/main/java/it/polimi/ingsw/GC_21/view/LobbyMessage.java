package it.polimi.ingsw.GC_21.view;

import java.util.ArrayList;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Message;

public class LobbyMessage extends Message {
	private ArrayList<String> games;
	
	
	@Override
	public boolean convert() {
		ArrayList<Game> gamesInLobby = controller.getControllerManager().getGames();
		for (int i = 0; i < gamesInLobby.size(); i++) {
			Game game = gamesInLobby.get(i);
			games.add(game.toString());
		}
		return true;
	}

}
