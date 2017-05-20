package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import org.json.simple.*;
import org.json.simple.parser.*;

import it.polimi.ingsw.GC_21.EFFECT.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;


public class CardCreator {

		
	 public void create() {

	        JSONParser parser = new JSONParser();

	        try {

	            Object obj = parser.parse(new FileReader("C:\\Users\\Alex\\Desktop\\provajs.json"));
	            JSONObject card = (JSONObject) obj;
	            JSONArray cardarray= (JSONArray) card.get("Card");
	            for (Object o : cardarray) {
	                JSONObject jsonLineItem = (JSONObject) o;
	               //set from json
	                Card cardcreating = new Card((String) jsonLineItem.get("name"));
	                
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
					
	                
	            	
	            	//test
	            	System.out.println(cardcreating.getName());
	            	System.out.println(cardcreating.getRequirements().getCoins().value);
	            	System.out.println(cardcreating.getRequirements().getWoods().value);
	            	System.out.println(cardcreating.getImmediateEffect().getRewards().getCoins().value);
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
	
}
