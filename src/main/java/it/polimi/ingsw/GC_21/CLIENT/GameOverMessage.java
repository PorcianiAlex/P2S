package it.polimi.ingsw.GC_21.CLIENT;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.GameEndState;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.fx.FXMLGameController;

public class GameOverMessage extends MessageToClient{
	private Board board;
	private ArrayList<Player> players;
	private ArrayList<Player> victoryPointsRanking;
	
	public GameOverMessage(boolean result, String description, Board board, ArrayList<Player> players,
			ArrayList<Player> victoryPointsRanking) {
		super(result, false, description);
		this.board = board;
		this.players = players;
		this.victoryPointsRanking = victoryPointsRanking;
		this.gameEndState = GameEndState.Over;
	}
	
	public void executeGUI(FXMLGameController gameController) {		
		StringBuilder totalRanking = new StringBuilder();
		totalRanking.append("***UPDATE ON YOUR STATS***\n");
		JSONParser parser = new JSONParser();
		Object obj = new Object();
			try {
				obj = parser.parse(new FileReader("Users.json"));
			} catch (IOException | ParseException e1) {
				e1.printStackTrace();
			}
		    JSONObject users = (JSONObject) obj;
		    JSONArray usersarray= (JSONArray) users.get("users");
		for (int i = 0; i < players.size(); i++) {
		    int victoryPointEarned = 0;
	    	for (Object o : usersarray) {
	    		JSONObject jsonLineItem = (JSONObject) o;
	    		if(players.get(i).getName().equals(jsonLineItem.get("name").toString())){
	    			totalRanking.append(players.get(i).getName() + ", your total VP: " + Integer.parseInt(jsonLineItem.get("VictoryPoints").toString()) + ", your wins: " + Integer.parseInt(jsonLineItem.get("numberOfWins").toString()) + "\n");
	    		}
	    			}
	    		}
		gameController.gameOver(description, totalRanking, victoryPointsRanking);
		
	}


}
