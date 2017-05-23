package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.polimi.ingsw.GC_21.EFFECT.Immediate;

public class CardCreator {
	
	private ArrayList<Card> cards;
	private JSONParser parser = new JSONParser();
   
	
	public ArrayList<Card> devCardsCreate(DevCardType devCardType, int age) {
		try {
		Object obj = parser.parse(new FileReader("C:\\Users\\Alex\\workspace\\prova-finale-template\\provajs.json"));
	    JSONObject card = (JSONObject) obj;
	    JSONArray cardarray= (JSONArray) card.get("DevCard");
	    for (Object o : cardarray) {
           	JSONObject jsonLineItem = (JSONObject) o;       
	    	if(Integer.parseInt(jsonLineItem.get("age").toString())==age && devCardType.toString().equals(DevCardType.valueOf(jsonLineItem.get("DevType").toString()))){              	
           	 DevelopmentCard devCardCreating = new DevelopmentCard((String) jsonLineItem.get("name"));
           	 devCardCreating.setAge(age);
         	 devCardCreating.setDevCardType(devCardType);
           	 this.create(devCardCreating, jsonLineItem);
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
		try {
		Object obj = parser.parse(new FileReader("C:\\Users\\Alex\\workspace\\prova-finale-template\\provajs.json"));
	    JSONObject card = (JSONObject) obj;
	    JSONArray cardarray= (JSONArray) card.get("ExCard");
	    for (Object o : cardarray) {
           	JSONObject jsonLineItem = (JSONObject) o;       
	    	if(Integer.parseInt(jsonLineItem.get("age").toString())==age){              	
           	 ExcommunicationCard excommunicationCard = new ExcommunicationCard((String) jsonLineItem.get("name"));
           	 excommunicationCard.setAge(age);
         	 this.create(excommunicationCard, jsonLineItem);
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
		try {
		Object obj = parser.parse(new FileReader("C:\\Users\\Alex\\workspace\\prova-finale-template\\provajs.json"));
	    JSONObject card = (JSONObject) obj;
	    JSONArray cardarray= (JSONArray) card.get("LeadCard");
	    for (Object o : cardarray) {
           	JSONObject jsonLineItem = (JSONObject) o;     
    	     LeaderCard leaderCard = new LeaderCard((String) jsonLineItem.get("name"));
           	 this.create(leaderCard, jsonLineItem);
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
	

	public void create(Card cardcreating, JSONObject jsonLineItem) {

	        	                
	                JSONArray reqarray= (JSONArray) jsonLineItem.get("Req");
	                	             	               
	                Possession Req = new Possession(Integer.parseInt(reqarray.get(0).toString()),Integer.parseInt(reqarray.get(1).toString()),
	                		Integer.parseInt(reqarray.get(2).toString()), Integer.parseInt(reqarray.get(3).toString()),
	                		Integer.parseInt(reqarray.get(4).toString()), Integer.parseInt(reqarray.get(5).toString()), 
	                	    Integer.parseInt(reqarray.get(6).toString()));
	                cardcreating.setRequirements(Req);  
	                
	                JSONArray immarray= (JSONArray) jsonLineItem.get("Imm");
	                             	               
	                Possession Imm = new Possession(Integer.parseInt(immarray.get(0).toString()),Integer.parseInt(immarray.get(1).toString()),
	                		Integer.parseInt(immarray.get(2).toString()), Integer.parseInt(immarray.get(3).toString()),
	                		Integer.parseInt(immarray.get(4).toString()), Integer.parseInt(immarray.get(5).toString()), 
	                	    Integer.parseInt(immarray.get(6).toString()));
	                cardcreating.setRequirements(Req);
	               Immediate immediate = new Immediate(Imm);
	               cardcreating.setImmediateEffect(immediate);
	             
	}

	
}
