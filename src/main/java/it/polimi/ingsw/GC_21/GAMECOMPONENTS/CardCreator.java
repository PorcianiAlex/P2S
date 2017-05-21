package it.polimi.ingsw.GC_21.GAMECOMPONENTS;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.polimi.ingsw.GC_21.EFFECT.Immediate;

public class CardCreator {

public void cardCreate() {
	
     try {
    	 JSONParser parser = new JSONParser();
         Object obj = parser.parse(new FileReader("C:\\Users\\Alex\\workspace\\prova-finale-template\\provajs.json"));
         JSONObject card = (JSONObject) obj;
         JSONArray cardarray= (JSONArray) card.get("Card");
       
         for (Object o : cardarray) {
             JSONObject jsonLineItem = (JSONObject) o;
             
             if("Dev".equals((String) jsonLineItem.get("type"))) {
            	
            	 DevelopmentCard devCardCreating = new DevelopmentCard((String) jsonLineItem.get("name"));
            	 devCardCreating.setAge(Integer.parseInt(jsonLineItem.get("age").toString()));
          	     devCardCreating.setDevCardType(DevCardType.valueOf(jsonLineItem.get("DevType").toString()));
            	 System.out.println(devCardCreating.getDevCardType());
          	     this.create(devCardCreating, jsonLineItem);             
             }
             else if("Ex".equals((String) jsonLineItem.get("type"))) {
            	 ExcommunicationCard excommunicationCard = new ExcommunicationCard((String) jsonLineItem.get("name"));
            	 excommunicationCard.setAge(Integer.parseInt(jsonLineItem.get("age").toString()));
            	 this.create(excommunicationCard, jsonLineItem); 
             }
             else if("Lead".equals((String) jsonLineItem.get("type"))) {
            	 LeaderCard leaderCard = new LeaderCard((String) jsonLineItem.get("name"));
            	 leaderCard.setDescription((String) jsonLineItem.get("desc"));
            	 this.create(leaderCard, jsonLineItem);
             }
          }
             
         
     }         

         catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
     
    
	}	
	
				public void create(Card cardcreating, JSONObject jsonLineItem) {

	        	                
	                JSONArray reqarray= (JSONArray) jsonLineItem.get("Req");
	                	             	               
	                Possession Req = new Possession(Integer.parseInt(reqarray.get(0).toString()),Integer.parseInt(reqarray.get(1).toString()),
	                		Integer.parseInt(reqarray.get(2).toString()), Integer.parseInt(reqarray.get(3).toString()),
	                		Integer.parseInt(reqarray.get(4).toString()), Integer.parseInt(reqarray.get(5).toString()), 
	                	    Integer.parseInt(reqarray.get(6).toString()),Integer.parseInt(reqarray.get(7).toString()));
	                cardcreating.setRequirements(Req);  
	                
	                JSONArray immarray= (JSONArray) jsonLineItem.get("Imm");
	                             	               
	                Possession Imm = new Possession(Integer.parseInt(immarray.get(0).toString()),Integer.parseInt(immarray.get(1).toString()),
	                		Integer.parseInt(immarray.get(2).toString()), Integer.parseInt(immarray.get(3).toString()),
	                		Integer.parseInt(immarray.get(4).toString()), Integer.parseInt(immarray.get(5).toString()), 
	                	    Integer.parseInt(immarray.get(6).toString()),Integer.parseInt(immarray.get(7).toString()));
	                cardcreating.setRequirements(Req);
	               Immediate immediate = new Immediate(Imm);
	               cardcreating.setImmediateEffect(immediate);
	             
	            }

	
}
