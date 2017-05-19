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
	                
	                JSONArray reqarray= (JSONArray) card.get("Req");
	                
	                
	                Possession Req = new Possession((int) reqarray.get(0),(int) reqarray.get(1),(int) reqarray.get(2),
	                		(int) reqarray.get(3),(int) reqarray.get(4),(int) reqarray.get(5),(int) reqarray.get(6),
	                		(int) reqarray.get(7));
	                cardcreating.setRequirements(Req);  
	                
	                JSONArray immarray= (JSONArray) card.get("Imm");
	                
	                /*Coins coins2 = new Coins((int) immarray.get(1));
	                Woods woods2 = new Woods((int) immarray.get(1));
	                Stones stones2 = new Stones((int) immarray.get(2));
	                Servants servants2 = new Servants((int) immarray.get(3));
	                FaithPoints faithPoints2 = new FaithPoints((int) immarray.get(4));
	                VictoryPoints victoryPoints2 = new VictoryPoints((int) immarray.get(5));
	                MilitaryPoints militaryPoints2 = new MilitaryPoints((int) immarray.get(6));
	                Privileges privileges2 = new Privileges((int) immarray.get(7));*/
	               
	                Possession Imm = new Possession((int) immarray.get(0),(int) immarray.get(1),(int) immarray.get(2),
	                		(int) immarray.get(3),(int) immarray.get(4),(int) immarray.get(5),(int) immarray.get(6),
	                		(int) immarray.get(7));           
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
