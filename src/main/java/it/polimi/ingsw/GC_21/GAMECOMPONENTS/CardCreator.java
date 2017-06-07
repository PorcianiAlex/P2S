package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.*;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.EFFECT.Convert;
import it.polimi.ingsw.GC_21.EFFECT.CraftInfluencer;
import it.polimi.ingsw.GC_21.EFFECT.DoCraftAction;
import it.polimi.ingsw.GC_21.EFFECT.DoPlacementAction;
import it.polimi.ingsw.GC_21.EFFECT.ForEachGet;
import it.polimi.ingsw.GC_21.EFFECT.Immediate;
import it.polimi.ingsw.GC_21.EFFECT.PlacementInfluencer;

public class CardCreator {
	
	private ArrayList<Card> cards;
	private JSONParser parser = new JSONParser();
   
	
	public ArrayList<Card> devCardsCreate(DevCardType devCardType, int age) {
		cards = new ArrayList<Card>();
		try {
		Object obj = parser.parse(new FileReader("cards.json"));
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
           	
           	 devCardCreating.setAge(age);
         	 devCardCreating.setDevCardType(devCardType);
         	 this.addImm(devCardCreating, jsonLineItem);
         	 this.AddSecEff(devCardCreating, jsonLineItem);
           	 this.addReq(devCardCreating, jsonLineItem);
           	 cards.add(devCardCreating);
            }
	     }
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return cards;
	}
	


	public ArrayList<Card> ExCardsCreate(int age) {
		cards = new ArrayList<Card>();
		try {
		Object obj = parser.parse(new FileReader("cards.json"));
	    JSONObject card = (JSONObject) obj;
	    JSONArray excardarray= (JSONArray) card.get("ExCard");
	    for (Object o : excardarray) {
           	JSONObject jsonLineItem = (JSONObject) o;       
	    	if(Integer.parseInt(jsonLineItem.get("age").toString())==age){              	
           	 ExcommunicationCard excommunicationCard = new ExcommunicationCard((String) jsonLineItem.get("name"));
           	 excommunicationCard.setAge(age);
           	 this.AddSecEff(excommunicationCard, jsonLineItem);
         	 cards.add(excommunicationCard);            	 
            }
	     }
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return cards;
	}
	
	public ArrayList<Card> LeadCardsCreate() {
		cards = new ArrayList<Card>();
		try {
		Object obj = parser.parse(new FileReader("cards.json"));
	    JSONObject card = (JSONObject) obj;
	    JSONArray cardarray= (JSONArray) card.get("LeadCard");
	    for (Object o : cardarray) {
           	JSONObject jsonLineItem = (JSONObject) o;     
    	     LeaderCard leaderCard = new LeaderCard((String) jsonLineItem.get("name"));
           	 this.addImm(leaderCard, jsonLineItem);
           	 this.addReq(leaderCard, jsonLineItem);
           	 cards.add(leaderCard);            	 
            }
	     
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return cards;
	}
	

	public void addImm(Card cardcreating, JSONObject jsonLineItem) {

	        	    int privileges = Integer.parseInt(jsonLineItem.get("Priv").toString());            
	               
	        	    JSONArray reqarray= (JSONArray) jsonLineItem.get("Req");
	                	             	               
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
						
									Convert convert = new Convert(Rew, toPay1, toTake1, toPay2, toTake2, privileges);
									cardcreating.setImmediateEffect(convert);
						break;
					case "doCraftAction": 
						
						DoCraftAction doCraftAction = new DoCraftAction(Rew, CraftType.valueOf(jsonLineItem.get("CraftType").toString()), Integer.parseInt(jsonLineItem.get("ActionValueInf").toString()), Integer.parseInt(jsonLineItem.get("ActionValueBonus").toString()), privileges);
						cardcreating.setImmediateEffect(doCraftAction);
						
						break;
					
					case "doPlacementAction":
						
						JSONArray discountArray= (JSONArray) jsonLineItem.get("Discount");
						Possession discount = new Possession(Integer.parseInt(discountArray.get(0).toString()),Integer.parseInt(discountArray.get(1).toString()),
		                		Integer.parseInt(discountArray.get(2).toString()), Integer.parseInt(discountArray.get(3).toString()),
		                		Integer.parseInt(discountArray.get(4).toString()), Integer.parseInt(discountArray.get(5).toString()), 
		                	    Integer.parseInt(discountArray.get(6).toString()));
						
						DoPlacementAction doPlacementAction;
						if("null".equals(jsonLineItem.get("DevCardType").toString())) {
							doPlacementAction = new DoPlacementAction(Rew, privileges, Integer.parseInt(jsonLineItem.get("ActionValueInf").toString()), Integer.parseInt(jsonLineItem.get("ActionValueBonus").toString()), null, discount);
						}
						else {doPlacementAction = new DoPlacementAction(Rew, privileges, Integer.parseInt(jsonLineItem.get("ActionValueInf").toString()), Integer.parseInt(jsonLineItem.get("ActionValueBonus").toString()), DevCardType.valueOf(jsonLineItem.get("DevCardType").toString()), discount);}
						cardcreating.setImmediateEffect(doPlacementAction);
						break;
					
					case "forEachGet": 
						ForEachGet forEachGet;
						
						if("null".equals((jsonLineItem.get("ForEachCard").toString()))){
							 forEachGet = new ForEachGet(Rew, privileges, null, Integer.parseInt(jsonLineItem.get("ForEachResourceIndex").toString()), ResourceType.valueOf(jsonLineItem.get("ForEachResource").toString()), Integer.parseInt(jsonLineItem.get("ForEachResourceIndex").toString()), ResourceType.valueOf(jsonLineItem.get("ResourceYouGet").toString()), Integer.parseInt(jsonLineItem.get("GettingIndex").toString()), false);
						}
						else if ("null".equals((jsonLineItem.get("ForEachResource").toString()))) {
							 forEachGet = new ForEachGet(Rew, privileges, DevCardType.valueOf(jsonLineItem.get("ForEachCard").toString()), Integer.parseInt(jsonLineItem.get("ForEachCardIndex").toString()), null, Integer.parseInt(jsonLineItem.get("ForEachResourceIndex").toString()), ResourceType.valueOf(jsonLineItem.get("ResourceYouGet").toString()), Integer.parseInt(jsonLineItem.get("GettingIndex").toString()), true);

						}
						else {  forEachGet = new ForEachGet(Rew, privileges, DevCardType.valueOf(jsonLineItem.get("ForEachCard").toString()), Integer.parseInt(jsonLineItem.get("ForEachCardIndex").toString()), ResourceType.valueOf(jsonLineItem.get("ForEachResource").toString()), Integer.parseInt(jsonLineItem.get("ForEachResourceIndex").toString()), ResourceType.valueOf(jsonLineItem.get("ResourceYouGet").toString()), Integer.parseInt(jsonLineItem.get("GettingIndex").toString()), false);
						} 
						cardcreating.setImmediateEffect(forEachGet);
						break;
					
					default: Immediate immediate = new Immediate(Rew, privileges);
					         cardcreating.setImmediateEffect(immediate);
						break;
						
						
						
					}
	                
	               
	            	             
	}
	
	public void AddSecEff(Card cardcreating, JSONObject jsonLineItem) {
		
        switch ((String) jsonLineItem.get("SecEffType")) {
		case "cartInfluencer":  			
			CraftInfluencer craftInfluencer = new CraftInfluencer(CraftType.valueOf(jsonLineItem.get("CraftType").toString()), Integer.parseInt(jsonLineItem.get("CraftInfluencer").toString()));
			cardcreating.setSecondaryEffect(craftInfluencer);
			break;
			
		case "PlacementInfluencer"	:
			
			JSONArray discountArray= (JSONArray) jsonLineItem.get("Discount");
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
