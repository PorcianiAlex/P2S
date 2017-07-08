package it.polimi.ingsw.GC_21.UTILITIES;

import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.EFFECT.Convert;
import it.polimi.ingsw.GC_21.EFFECT.CraftInfluencer;
import it.polimi.ingsw.GC_21.EFFECT.DoCraftAction;
import it.polimi.ingsw.GC_21.EFFECT.DoTakeCardAction;
import it.polimi.ingsw.GC_21.EFFECT.EarningInfluencer;
import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.ForEachGet;
import it.polimi.ingsw.GC_21.EFFECT.LoseYourDevCardType;
import it.polimi.ingsw.GC_21.EFFECT.PlacementInfluencer;
import it.polimi.ingsw.GC_21.EFFECT.VictoryPointsInfluencer;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Card;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.CraftCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevelopmentCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.ExcommunicationCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.ResourceType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Ventures;

public class CardCreator implements Serializable{
	private Game game;
	private ArrayList<Card> cards;   
	public CardCreator(Game game) {
		this.game = game;
	}

	public ArrayList<Card> devCardsCreate(DevCardType devCardType, int age) {
		JSONParser parser = new JSONParser(); 
		cards = new ArrayList<Card>();
		try {
		java.net.URL path = CardCreator.class.getResource("DevCards.json");
		FileReader file = new FileReader(path.getPath());
		Object obj = parser.parse(file);
	    JSONObject card = (JSONObject) obj;
	    JSONArray cardarray= (JSONArray) card.get("DevCard");
	    for (Object o : cardarray) {
           	JSONObject jsonLineItem = (JSONObject) o;       
	    	if(Integer.parseInt(jsonLineItem.get("age").toString())==age && devCardType.equals(DevCardType.valueOf(jsonLineItem.get("DevType").toString()))){              	
           	DevelopmentCard devCardCreating;
	    	if (devCardType.equals(DevCardType.Venture)) {
				devCardCreating = new Ventures((String) jsonLineItem.get("name"));
				this.addSecondReq((Ventures) devCardCreating, jsonLineItem);
			}
	    	if (devCardType.equals(DevCardType.Building) || devCardType.equals(DevCardType.Territory)) {
				devCardCreating = new CraftCard((String) jsonLineItem.get("name"));
				((CraftCard) devCardCreating).setRequiredValueForCraft(Integer.parseInt(jsonLineItem.get("craftValue").toString()));
			}
           	else {devCardCreating = new DevelopmentCard((String) jsonLineItem.get("name"));}
           	devCardCreating.setID(jsonLineItem.get("ID").toString());
           	 devCardCreating.setAge(age);
         	 devCardCreating.setDevCardType(devCardType);
         	 this.addImm(devCardCreating, jsonLineItem);
         	 this.AddSecEff(devCardCreating, jsonLineItem);
           	 this.addReq(devCardCreating, jsonLineItem);
           	 cards.add(devCardCreating);
           	 //System.out.println("Card just created " + devCardCreating.getName() + " with ID: " + devCardCreating.getID());
            }
	     }
		} catch (Exception e) {
            e.printStackTrace();
        } 
		return cards;
	}
	


	public ArrayList<Card> ExCardsCreate(int age) {
		JSONParser parser = new JSONParser();
		cards = new ArrayList<Card>();
		try {
		java.net.URL path = CardCreator.class.getResource("ExCards.json");
		FileReader file = new FileReader(path.getPath());
		Object obj = parser.parse(file);
	    JSONObject card = (JSONObject) obj;
	    JSONArray excardarray= (JSONArray) card.get("ExcommCard");
	    for (Object o : excardarray) {
           	JSONObject jsonLineItem = (JSONObject) o;       
	    	if(Integer.parseInt(jsonLineItem.get("age").toString())==age){              	
           	 ExcommunicationCard excommunicationCard = new ExcommunicationCard((String) jsonLineItem.get("name"));
           	 excommunicationCard.setAge(age);
           	 this.AddSecEff(excommunicationCard, jsonLineItem);
            excommunicationCard.setID(jsonLineItem.get("ID").toString());

         	 cards.add(excommunicationCard);  
           	 //System.out.println("Card just created " + excommunicationCard.getName() + " with ID: " + excommunicationCard.getID());

            }
	     }
		} catch (Exception e) {
            e.printStackTrace();
        }
		return cards;
	}
	
	public void addImm(Card cardcreating, JSONObject jsonLineItem) {

	        	    int privileges = Integer.parseInt(jsonLineItem.get("Priv").toString());            
	               
	        	    JSONArray reqarray= (JSONArray) jsonLineItem.get("Imm");
	                	             	               
	                Possession Rew = new Possession(Integer.parseInt(reqarray.get(0).toString()),Integer.parseInt(reqarray.get(1).toString()),
	                		Integer.parseInt(reqarray.get(2).toString()), Integer.parseInt(reqarray.get(3).toString()),
	                		Integer.parseInt(reqarray.get(4).toString()), Integer.parseInt(reqarray.get(5).toString()), 
	                	    Integer.parseInt(reqarray.get(6).toString()));
	                
	              
	                switch ((String) jsonLineItem.get("EffType")) {
					case "convert":  
						   
						 JSONArray toTake1array= (JSONArray) jsonLineItem.get("toTake1");
       	               
			                Possession toTake1 = new Possession(Integer.parseInt(toTake1array.get(0).toString()),Integer.parseInt(toTake1array.get(1).toString()),
			                		Integer.parseInt(toTake1array.get(2).toString()), Integer.parseInt(toTake1array.get(3).toString()),
			                		Integer.parseInt(toTake1array.get(4).toString()), Integer.parseInt(toTake1array.get(5).toString()), 
			                	    Integer.parseInt(toTake1array.get(6).toString()));
			                
						JSONArray toTake2array= (JSONArray) jsonLineItem.get("toTake2");
       	               
			                Possession toTake2 = new Possession(Integer.parseInt(toTake2array.get(0).toString()),Integer.parseInt(toTake2array.get(1).toString()),
			                		Integer.parseInt(toTake2array.get(2).toString()), Integer.parseInt(toTake2array.get(3).toString()),
			                		Integer.parseInt(toTake2array.get(4).toString()), Integer.parseInt(toTake2array.get(5).toString()), 
			                	    Integer.parseInt(toTake2array.get(6).toString()));
							
							JSONArray topay1array= (JSONArray) jsonLineItem.get("toPay1");
  	               
							Possession toPay1 = new Possession(Integer.parseInt(topay1array.get(0).toString()),Integer.parseInt(topay1array.get(1).toString()),
										Integer.parseInt(topay1array.get(2).toString()), Integer.parseInt(topay1array.get(3).toString()),
										Integer.parseInt(topay1array.get(4).toString()), Integer.parseInt(topay1array.get(5).toString()), 
										Integer.parseInt(topay1array.get(6).toString()));
							
							 JSONArray toPay2array= (JSONArray) jsonLineItem.get("toPay2");
           	               
				                Possession toPay2 = new Possession(Integer.parseInt(toPay2array.get(0).toString()),Integer.parseInt(toPay2array.get(1).toString()),
				                		Integer.parseInt(toPay2array.get(2).toString()), Integer.parseInt(toPay2array.get(3).toString()),
				                		Integer.parseInt(toPay2array.get(4).toString()), Integer.parseInt(toPay2array.get(5).toString()), 
				                	    Integer.parseInt(toPay2array.get(6).toString()));
						
									Convert convert = new Convert(game, Rew, toPay1, toTake1, toPay2, toTake2, privileges);
									cardcreating.setImmediateEffect(convert);
						break;
					case "doCraftAction": 
						
						DoCraftAction doCraftAction = new DoCraftAction(game, Rew, CraftType.valueOf(jsonLineItem.get("CraftType").toString()), Integer.parseInt(jsonLineItem.get("ActionValueInf").toString()), Integer.parseInt(jsonLineItem.get("ActionValueBonus").toString()), privileges);
						cardcreating.setImmediateEffect(doCraftAction);
						
						break;
					
					case "doPlacementAction":
						
						JSONArray discountArray= (JSONArray) jsonLineItem.get("Discount");
						Possession discount = new Possession(Integer.parseInt(discountArray.get(0).toString()),Integer.parseInt(discountArray.get(1).toString()),
		                		Integer.parseInt(discountArray.get(2).toString()), Integer.parseInt(discountArray.get(3).toString()),
		                		Integer.parseInt(discountArray.get(4).toString()), Integer.parseInt(discountArray.get(5).toString()), 
		                	    Integer.parseInt(discountArray.get(6).toString()));
						
						DoTakeCardAction doPlacementAction;
						if("null".equals(jsonLineItem.get("DevCardType").toString())) {
							doPlacementAction = new DoTakeCardAction(game, Rew, privileges, Integer.parseInt(jsonLineItem.get("ActionValueInf").toString()), null, discount);
						}
						else {doPlacementAction = new DoTakeCardAction(game, Rew, privileges, Integer.parseInt(jsonLineItem.get("ActionValueInf").toString()), DevCardType.valueOf(jsonLineItem.get("DevCardType").toString()), discount);}
						cardcreating.setImmediateEffect(doPlacementAction);
						break;
					
					case "forEachGet": 
						ForEachGet forEachGet;
						
						if("null".equals((jsonLineItem.get("ForEachCard").toString()))){
							 forEachGet = new ForEachGet(game, Rew, privileges, null, Integer.parseInt(jsonLineItem.get("ForEachResourceIndex").toString()), ResourceType.valueOf(jsonLineItem.get("ForEachResource").toString()), Integer.parseInt(jsonLineItem.get("ForEachResourceIndex").toString()), ResourceType.valueOf(jsonLineItem.get("ResourceYouGet").toString()), Integer.parseInt(jsonLineItem.get("GettingIndex").toString()), false);
						}
						else if ("null".equals((jsonLineItem.get("ForEachResource").toString()))) {
							 forEachGet = new ForEachGet(game, Rew, privileges, DevCardType.valueOf(jsonLineItem.get("ForEachCard").toString()), Integer.parseInt(jsonLineItem.get("ForEachCardIndex").toString()), null, Integer.parseInt(jsonLineItem.get("ForEachResourceIndex").toString()), ResourceType.valueOf(jsonLineItem.get("ResourceYouGet").toString()), Integer.parseInt(jsonLineItem.get("GettingIndex").toString()), true);

						}
						else {  forEachGet = new ForEachGet(game, Rew, privileges, DevCardType.valueOf(jsonLineItem.get("ForEachCard").toString()), Integer.parseInt(jsonLineItem.get("ForEachCardIndex").toString()), ResourceType.valueOf(jsonLineItem.get("ForEachResource").toString()), Integer.parseInt(jsonLineItem.get("ForEachResourceIndex").toString()), ResourceType.valueOf(jsonLineItem.get("ResourceYouGet").toString()), Integer.parseInt(jsonLineItem.get("GettingIndex").toString()), false);
						} 
						cardcreating.setImmediateEffect(forEachGet);
						break;
					
					default: Effect immediate = new Effect(Rew, privileges, game);
					         cardcreating.setImmediateEffect(immediate);
						break;
						
						
						
					}
	                
	               
	            	             
	}
	
	public void AddSecEff(Card cardcreating, JSONObject jsonLineItem) {
		
        switch ((String) jsonLineItem.get("SecEffType")) {
        case "ForEachGet": 
			ForEachGet forEachGet;
  
            Possession Rew2 = new Possession();
			if("null".equals((jsonLineItem.get("ForEachCard").toString()))){
				 forEachGet = new ForEachGet(game, Rew2, 0, null, Integer.parseInt(jsonLineItem.get("ForEachResourceIndex").toString()), ResourceType.valueOf(jsonLineItem.get("ForEachResource").toString()), Integer.parseInt(jsonLineItem.get("ForEachResourceIndex").toString()), ResourceType.valueOf(jsonLineItem.get("ResourceYouGet").toString()), Integer.parseInt(jsonLineItem.get("GettingIndex").toString()), false);
			}
			else if ("null".equals((jsonLineItem.get("ForEachResource").toString()))) {
				forEachGet = new ForEachGet(game, Rew2, 0, DevCardType.valueOf(jsonLineItem.get("ForEachCard").toString()), Integer.parseInt(jsonLineItem.get("ForEachCardIndex").toString()), null, Integer.parseInt(jsonLineItem.get("ForEachResourceIndex").toString()), ResourceType.valueOf(jsonLineItem.get("ResourceYouGet").toString()), Integer.parseInt(jsonLineItem.get("GettingIndex").toString()), true);

				//forEachGet = new ForEachGet(game, Rew2, 0, DevCardType.valueOf(jsonLineItem.get("forEachCard").toString()), Integer.parseInt(jsonLineItem.get("ForEachCardIndex").toString()), null, Integer.parseInt(jsonLineItem.get("ForEachResourceIndex").toString()), ResourceType.valueOf(jsonLineItem.get("ResourceYouGet").toString()), Integer.parseInt(jsonLineItem.get("GettingIndex").toString()), true);
			}
			else {  forEachGet = new ForEachGet(game, Rew2, 0, DevCardType.valueOf(jsonLineItem.get("forEachCard").toString()), Integer.parseInt(jsonLineItem.get("ForEachCardIndex").toString()), ResourceType.valueOf(jsonLineItem.get("ForEachResource").toString()), Integer.parseInt(jsonLineItem.get("ForEachResourceIndex").toString()), ResourceType.valueOf(jsonLineItem.get("ResourceYouGet").toString()), Integer.parseInt(jsonLineItem.get("GettingIndex").toString()), false);
			} 
			cardcreating.setSecondaryEffect(forEachGet);
			break;
        case "justImm":    
        	int privileges = Integer.parseInt(jsonLineItem.get("Priv2").toString()); 
    	    JSONArray rewarray= (JSONArray) jsonLineItem.get("Rew");

            Possession Rew = new Possession(Integer.parseInt(rewarray.get(0).toString()),Integer.parseInt(rewarray.get(1).toString()),
            		Integer.parseInt(rewarray.get(2).toString()), Integer.parseInt(rewarray.get(3).toString()),
            		Integer.parseInt(rewarray.get(4).toString()), Integer.parseInt(rewarray.get(5).toString()), 
            	    Integer.parseInt(rewarray.get(6).toString()));
            Effect effect = new Effect(Rew, privileges, game);
            cardcreating.setSecondaryEffect(effect);
        	break;
        case "convert":  
			 JSONArray toTake1array= (JSONArray) jsonLineItem.get("toTake1");
             
               Possession toTake1 = new Possession(Integer.parseInt(toTake1array.get(0).toString()),Integer.parseInt(toTake1array.get(1).toString()),
               		Integer.parseInt(toTake1array.get(2).toString()), Integer.parseInt(toTake1array.get(3).toString()),
               		Integer.parseInt(toTake1array.get(4).toString()), Integer.parseInt(toTake1array.get(5).toString()), 
               	    Integer.parseInt(toTake1array.get(6).toString()));
               
			JSONArray toTake2array= (JSONArray) jsonLineItem.get("toTake2");
             
               Possession toTake2 = new Possession(Integer.parseInt(toTake2array.get(0).toString()),Integer.parseInt(toTake2array.get(1).toString()),
               		Integer.parseInt(toTake2array.get(2).toString()), Integer.parseInt(toTake2array.get(3).toString()),
               		Integer.parseInt(toTake2array.get(4).toString()), Integer.parseInt(toTake2array.get(5).toString()), 
               	    Integer.parseInt(toTake2array.get(6).toString()));
				
				JSONArray topay1array= (JSONArray) jsonLineItem.get("toPay1");
        
				Possession toPay1 = new Possession(Integer.parseInt(topay1array.get(0).toString()),Integer.parseInt(topay1array.get(1).toString()),
							Integer.parseInt(topay1array.get(2).toString()), Integer.parseInt(topay1array.get(3).toString()),
							Integer.parseInt(topay1array.get(4).toString()), Integer.parseInt(topay1array.get(5).toString()), 
							Integer.parseInt(topay1array.get(6).toString()));
				
				 JSONArray toPay2array= (JSONArray) jsonLineItem.get("toPay2");
	               
	                Possession toPay2 = new Possession(Integer.parseInt(toPay2array.get(0).toString()),Integer.parseInt(toPay2array.get(1).toString()),
	                		Integer.parseInt(toPay2array.get(2).toString()), Integer.parseInt(toPay2array.get(3).toString()),
	                		Integer.parseInt(toPay2array.get(4).toString()), Integer.parseInt(toPay2array.get(5).toString()), 
	                	    Integer.parseInt(toPay2array.get(6).toString()));
			
						Convert convert = new Convert(game, new Possession(), toPay1, toTake1, toPay2, toTake2, 0);
						cardcreating.setSecondaryEffect(convert);
			break;
        case "VictoryPointsInfluencer":
        	if ("false".equals((jsonLineItem.get("eachResource").toString()))){
        		VictoryPointsInfluencer victoryPointsInfluencer = new VictoryPointsInfluencer(game, ResourceType.valueOf((jsonLineItem.get("forEachResource").toString())), Integer.parseInt((jsonLineItem.get("forEachResourceIndex").toString())), Integer.parseInt((jsonLineItem.get("losingIndex").toString())), false);
        		cardcreating.setSecondaryEffect(victoryPointsInfluencer);
        	}
        	else{
        		VictoryPointsInfluencer victoryPointsInfluencer = new VictoryPointsInfluencer(game, ResourceType.valueOf((jsonLineItem.get("forEachResource").toString())), Integer.parseInt((jsonLineItem.get("forEachResourceIndex").toString())), Integer.parseInt((jsonLineItem.get("losingIndex").toString())), true);
        		cardcreating.setSecondaryEffect(victoryPointsInfluencer);
        	}
        	break;
        case "LoseYourDevCardType":  			
			LoseYourDevCardType loseYourDevCardType = new LoseYourDevCardType(DevCardType.valueOf((jsonLineItem.get("DevCardType").toString())),game);
			cardcreating.setSecondaryEffect(loseYourDevCardType);
			break;
		case "CraftInfluencer":
			CraftType craftType = CraftType.valueOf(jsonLineItem.get("CraftType").toString());
			int influencerOfCraft = Integer.parseInt(jsonLineItem.get("Influencer").toString());
			CraftInfluencer craftInfluencer = new CraftInfluencer(craftType, influencerOfCraft, game);
			cardcreating.setSecondaryEffect(craftInfluencer);
			break;
		case "EarningInfluencer":
			JSONArray influencerArray= (JSONArray) jsonLineItem.get("Influencer");
			
			Possession influencer = new Possession(Integer.parseInt(influencerArray.get(0).toString()),Integer.parseInt(influencerArray.get(1).toString()),
            		Integer.parseInt(influencerArray.get(2).toString()), Integer.parseInt(influencerArray.get(3).toString()),
            		Integer.parseInt(influencerArray.get(4).toString()), Integer.parseInt(influencerArray.get(5).toString()), 
            	    Integer.parseInt(influencerArray.get(6).toString()));
			EarningInfluencer earningInfluencer = new EarningInfluencer(influencer);
			cardcreating.setSecondaryEffect(earningInfluencer);
			break;
		case "PlacementInfluencer"	:
			JSONArray discountArray= (JSONArray) jsonLineItem.get("Disc");
			Possession discount = new Possession(Integer.parseInt(discountArray.get(0).toString()),Integer.parseInt(discountArray.get(1).toString()),
            		Integer.parseInt(discountArray.get(2).toString()), Integer.parseInt(discountArray.get(3).toString()),
            		Integer.parseInt(discountArray.get(4).toString()), Integer.parseInt(discountArray.get(5).toString()), 
            	    Integer.parseInt(discountArray.get(6).toString()));
			if("null".equals((jsonLineItem.get("TowerEffected").toString()))){
				PlacementInfluencer placementInfluencer = new PlacementInfluencer( Integer.parseInt(jsonLineItem.get("ActionValueBonus").toString()), null, discount);
				cardcreating.setSecondaryEffect(placementInfluencer);
			} else {
				PlacementInfluencer placementInfluencer = new PlacementInfluencer( Integer.parseInt(jsonLineItem.get("ActionValueBonus").toString()), DevCardType.valueOf(jsonLineItem.get("TowerEffected").toString()), discount);
				cardcreating.setSecondaryEffect(placementInfluencer);
			}
		case "No" :
			break;
        }
	}
	
	public void addReq(Card cardcreating, JSONObject jsonLineItem) {
        
        JSONArray reqarray= (JSONArray) jsonLineItem.get("Req");
        	             	               
        Possession Req = new Possession(Integer.parseInt(reqarray.get(0).toString()),Integer.parseInt(reqarray.get(1).toString()),
        		Integer.parseInt(reqarray.get(2).toString()), Integer.parseInt(reqarray.get(3).toString()),
        		Integer.parseInt(reqarray.get(4).toString()), Integer.parseInt(reqarray.get(5).toString()), 
        	    Integer.parseInt(reqarray.get(6).toString()));
        cardcreating.setRequirements(Req);  
	}
	
	private void addSecondReq(Ventures cardcreating, JSONObject jsonLineItem) {
		 
		JSONArray reqarray= (JSONArray) jsonLineItem.get("SecReq");
        
	        Possession Req = new Possession(Integer.parseInt(reqarray.get(0).toString()),Integer.parseInt(reqarray.get(1).toString()),
	        		Integer.parseInt(reqarray.get(2).toString()), Integer.parseInt(reqarray.get(3).toString()),
	        		Integer.parseInt(reqarray.get(4).toString()), Integer.parseInt(reqarray.get(5).toString()), 
	        	    Integer.parseInt(reqarray.get(6).toString()));
	        cardcreating.setSecondRequirement(Req);  
	        cardcreating.setFinalVictoryPoints(Integer.parseInt(jsonLineItem.get("VicPoint").toString()));
		
	}
	
}
