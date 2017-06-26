package it.polimi.ingsw.GC_21.ACTION;

import java.io.FileReader;
import java.io.IOException;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.polimi.ingsw.GC_21.CLIENT.MessageToClient;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Item;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.ResourceType;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class ExcommAction extends Action {
	private boolean choice;
	private int[] faithPointsTracking;
	
	public ExcommAction(Player playerInAction, Game game, boolean choice) throws IOException, ParseException {
		super(playerInAction);
		this.choice = choice;
		this.game = game;
		faithPointsTracking = new int[14];
		JSONParser parser = new JSONParser();
		java.net.URL path = ExcommAction.class.getResource("faithPointsTracking.json");
		FileReader file = new FileReader(path.getPath());
		JSONObject obj = (JSONObject) parser.parse(file);
	    JSONArray victoryPoints= (JSONArray) obj.get("faithPointsTracking");
		for (int i = 0; i < faithPointsTracking.length; i++) {
			faithPointsTracking[i]=Integer.parseInt(victoryPoints.get(i).toString());
		}
	}	
	
	@Override
	public void Execute() {
		int age = game.getCurrentAge().getAgeNumber();
		int threshold = game.getExcommHandler().getExcommThresholds()[age-1];
		if (this.choice || playerInAction.getMyPersonalBoard().getMyPossession().getFaithPoints().getValue()<threshold) {
			playerInAction.getMyPersonalBoard().addPermanentEffect(game.getExcommHandler().getExcommunicationCards()[age-1].getSecondaryEffect());
			MessageToClient notifyExcommMessage = new MessageToClient(true, "Mmmh.. Bergoglio is upset! You are going to be excommunicated!!");
			game.notifyBroadcast(notifyExcommMessage);
		}
		else {
			int currentPositionOnTracking = playerInAction.getMyPersonalBoard().getMyPossession().getFaithPoints().getValue();
			int victoryPointsToGet = faithPointsTracking[currentPositionOnTracking];
			playerInAction.getMyPersonalBoard().getMyPossession().getFaithPoints().setValue(0);
			playerInAction.getMyPersonalBoard().getMyPossession().addItemToPossession(Item.factoryItem(victoryPointsToGet, ResourceType.VictoryPoints));
			MessageToClient notifyExcommMessage = new MessageToClient(true, "Well done " + playerInAction.getName() +", you haven't been excommunicated, but you will lose all your faith points!\n"
					+ "But you'll gain " + victoryPointsToGet + " Victory Points!");
			game.notifyBroadcast(notifyExcommMessage);
		}
	}
}
