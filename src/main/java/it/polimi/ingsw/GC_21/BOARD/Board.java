package it.polimi.ingsw.GC_21.BOARD;



import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevDeck;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.ExcommunicationCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;


public class Board implements Serializable{
	private Game game;
	private Dice[] dices;
	private Tower[] towers;
	private MarketArea marketArea;
	private CouncilPalace councilPalace;
	private CraftArea productionArea;
	private CraftArea harvestArea;
	private ExcommunicationCard[] excommunicationCards;
	
	public Board(Game game) throws IOException, ParseException {
		this.dices = Dice.factoryDices();
		this.game=game;
		this.towers = Tower.factoryTowers(game); 
		this.marketArea = new MarketArea(game);
		this.councilPalace = new CouncilPalace(game);
		this.productionArea = new CraftArea(CraftType.Production, game);
		this.harvestArea = new CraftArea( CraftType.Harvest, game);
		this.setEffectsOnTower();
	}


	private void setEffectsOnTower() throws IOException, ParseException { //loading spaceEffects from file
		JSONParser parser = new JSONParser();
		java.net.URL path = MarketArea.class.getResource("spaceEffects.json");
		FileReader file = new FileReader(path.getPath());
		JSONObject obj = (JSONObject) parser.parse(file);
	    JSONArray territories= (JSONArray) obj.get("territoryTower");
	    int i = 2;
		for (Object o : territories) {
           	JSONObject jsonLineItem = (JSONObject) o;      
           	int privileges = Integer.parseInt(jsonLineItem.get("Priv").toString());            
    	    JSONArray reward= (JSONArray) jsonLineItem.get("Reward");          	               
            Possession rewards = new Possession(Integer.parseInt(reward.get(0).toString()),Integer.parseInt(reward.get(1).toString()),
            		Integer.parseInt(reward.get(2).toString()), Integer.parseInt(reward.get(3).toString()),
            		Integer.parseInt(reward.get(4).toString()), Integer.parseInt(reward.get(5).toString()), 
            	    Integer.parseInt(reward.get(6).toString()));
            towers[0].getFloors()[i].getSingleActionSpace().setActionSpaceEffect(new Effect(rewards, privileges, game)); 
            i++;
		}
	    JSONArray buildings= (JSONArray) obj.get("buildingTower");
		i=2;
		for (Object o : buildings) {
           	JSONObject jsonLineItem = (JSONObject) o;      
           	int privileges = Integer.parseInt(jsonLineItem.get("Priv").toString());            
    	    JSONArray reward= (JSONArray) jsonLineItem.get("Reward");          	               
            Possession rewards = new Possession(Integer.parseInt(reward.get(0).toString()),Integer.parseInt(reward.get(1).toString()),
            		Integer.parseInt(reward.get(2).toString()), Integer.parseInt(reward.get(3).toString()),
            		Integer.parseInt(reward.get(4).toString()), Integer.parseInt(reward.get(5).toString()), 
            	    Integer.parseInt(reward.get(6).toString()));
            towers[2].getFloors()[i].getSingleActionSpace().setActionSpaceEffect(new Effect(rewards, privileges, game)); 
            i++;
		}
		  JSONArray characters= (JSONArray) obj.get("characterTower");
			i=2;
			for (Object o : characters) {
	           	JSONObject jsonLineItem = (JSONObject) o;      
	           	int privileges = Integer.parseInt(jsonLineItem.get("Priv").toString());            
	    	    JSONArray reward= (JSONArray) jsonLineItem.get("Reward");          	               
	            Possession rewards = new Possession(Integer.parseInt(reward.get(0).toString()),Integer.parseInt(reward.get(1).toString()),
	            		Integer.parseInt(reward.get(2).toString()), Integer.parseInt(reward.get(3).toString()),
	            		Integer.parseInt(reward.get(4).toString()), Integer.parseInt(reward.get(5).toString()), 
	            	    Integer.parseInt(reward.get(6).toString()));
	            towers[1].getFloors()[i].getSingleActionSpace().setActionSpaceEffect(new Effect(rewards, privileges, game)); 
	            i++;
			}
			JSONArray ventures= (JSONArray) obj.get("ventureTower");
			i=2;
			for (Object o : ventures) {
	           	JSONObject jsonLineItem = (JSONObject) o;      
	           	int privileges = Integer.parseInt(jsonLineItem.get("Priv").toString());            
	    	    JSONArray reward= (JSONArray) jsonLineItem.get("Reward");          	               
	            Possession rewards = new Possession(Integer.parseInt(reward.get(0).toString()),Integer.parseInt(reward.get(1).toString()),
	            		Integer.parseInt(reward.get(2).toString()), Integer.parseInt(reward.get(3).toString()),
	            		Integer.parseInt(reward.get(4).toString()), Integer.parseInt(reward.get(5).toString()), 
	            	    Integer.parseInt(reward.get(6).toString()));
	            towers[3].getFloors()[i].getSingleActionSpace().setActionSpaceEffect(new Effect(rewards, privileges, game)); 
	            i++;
			}
	}



	public void placeCardOnBoard(DevDeck territoryDeck, DevDeck buildingDeck, DevDeck characterDeck, DevDeck ventureDeck){
		towers[0].pickCards(territoryDeck);
		towers[1].pickCards(buildingDeck);
		towers[2].pickCards(characterDeck);
		towers[3].pickCards(ventureDeck);
	}



	public void refreshBoard() {
		for (int i = 0; i < towers.length; i++) {
			for (int j = 0; j < towers[i].getFloors().length; j++) {
				towers[i].getFloors()[j].getSingleActionSpace().setFamilyMemberLocated(null);
				towers[i].getFloors()[j].getSingleActionSpace().setBusy(false);
			}
		}
		for (int i = 0; i < productionArea.getMultipleActionSpace().getFamilyMembers().size(); i++) {
			productionArea.getMultipleActionSpace().getFamilyMembers().remove(i);
		}
		productionArea.getSingleActionSpace().setFamilyMemberLocated(null);
		productionArea.getSingleActionSpace().setBusy(false);
		for (int i = 0; i < harvestArea.getMultipleActionSpace().getFamilyMembers().size(); i++) {
			harvestArea.getMultipleActionSpace().getFamilyMembers().remove(i);
		}
		harvestArea.getSingleActionSpace().setFamilyMemberLocated(null);
		harvestArea.getSingleActionSpace().setBusy(false);
		for (int i = 0; i < councilPalace.getMultipleActionSpace().getFamilyMembers().size(); i++) {
			councilPalace.getMultipleActionSpace().getFamilyMembers().remove(i);
		}
		for (int i = 0; i < marketArea.getSingleActionSpace().length; i++) {
			marketArea.getSingleActionSpace()[i].setFamilyMemberLocated(null);
			marketArea.getSingleActionSpace()[i].setBusy(false);
		}
	}
		
	
	
	
	

	public Dice[] getDices() {
		return dices;
	}



	public void setDices(Dice[] dices) {
		this.dices = dices;
	}



	public Tower[] getTowers() {
		return towers;
	}
	
	public Tower getSpecificTower(DevCardType towerType) {
		for (int i = 0; i < towers.length; i++) {
			if (towers[i].getDevCardType().equals(towerType)) {
				return towers[i];
			}
		}
		return null;
	}



	public void setTowers(Tower[] towers) {
		this.towers = towers;
	}



	public MarketArea getMarketArea() {
		return marketArea;
	}



	public void setMarketArea(MarketArea marketArea) {
		this.marketArea = marketArea;
	}



	public CouncilPalace getCouncilPalace() {
		return councilPalace;
	}



	public void setCouncilPalace(CouncilPalace councilPalace) {
		this.councilPalace = councilPalace;
	}
	
	public CraftArea getSpecificCraftArea(CraftType craftType) {
		if (craftType.equals(CraftType.Harvest)) {
			return getHarvestArea();
		}
		else if (craftType.equals(CraftType.Production)) {
			return getProductionArea();
		}
		else {
			return null;
		}
	}



	public CraftArea getProductionArea() {
		return productionArea;
	}



	public void setProductionArea(CraftArea productionArea) {
		this.productionArea = productionArea;
	}



	public CraftArea getHarvestArea() {
		return harvestArea;
	}



	public void setHarvestArea(CraftArea harvestArea) {
		this.harvestArea = harvestArea;
	}


	public String toString() {
		return " " + dices[0].toString() + " \n " + dices[1].toString() + " \n " + dices[2].toString() + " \n" + game.getExcommHandler().toString() + " \n " + towers[0].toString() + " \n " + towers[1].toString() + " \n " + 
				towers[2].toString() + " \n " + towers[3].toString() + " \n " + marketArea.toString() +" \n " + productionArea.toString() + " \n " + harvestArea.toString()
				+ " \n " + councilPalace.toString();
	}


	public ExcommunicationCard[] getExcommunicationCards() {
		return excommunicationCards;
	}


	public void setExcommunicationCards(ExcommunicationCard[] excommunicationCards) {
		this.excommunicationCards = excommunicationCards;
	}


	

	
}