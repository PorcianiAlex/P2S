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
		JSONParser parser = new JSONParser(); //loading by file faithPointsTracking
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
		int threshold = game.getExcommHandler().getExcommThresholds()[age-1]; //take the faithPoints required not to be excommunicated
		if (this.choice || playerInAction.getMyPersonalBoard().getMyPossession().getFaithPoints().getValue()<threshold) { //if I choose to be excomm or I just don't have enough faith points
			playerInAction.getMyPersonalBoard().addPermanentEffect(game.getExcommHandler().getExcommunicationCards()[age-1].getSecondaryEffect()); //I get the malus!
			MessageToClient notifyExcommMessage = new MessageToClient(true, "Mmmh.. Bergoglio is upset! You are going to be excommunicated!!");
			game.notifyBroadcast(notifyExcommMessage);
		}
		else {
			int victoryPointsToGet;
			int currentPositionOnTracking = playerInAction.getMyPersonalBoard().getMyPossession().getFaithPoints().getValue(); //taking the victory points
			if (currentPositionOnTracking>faithPointsTracking.length){
				victoryPointsToGet = 30; 
			}
			else{ 
				victoryPointsToGet = faithPointsTracking[currentPositionOnTracking];
			
			}
			playerInAction.getMyPersonalBoard().getMyPossession().getFaithPoints().setValue(0); //setting FP to 0
			playerInAction.getMyPersonalBoard().getMyPossession().addItemToPossession(Item.factoryItem(victoryPointsToGet, ResourceType.VictoryPoints)); //earning VP
			MessageToClient notifyExcommMessage = new MessageToClient(true, "Well done " + playerInAction.getName() +", you haven't been excommunicated, but you will lose all your faith points!\n"
					+ "But you'll gain " + victoryPointsToGet + " Victory Points!");
			game.notifyBroadcast(notifyExcommMessage);
		}
	}
}
