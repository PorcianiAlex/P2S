package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import org.json.simple.*;
import org.json.simple.parser.*;
import org.omg.CORBA.PRIVATE_MEMBER;

import it.polimi.ingsw.GC_21.EFFECT.Immediate;

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
	                Coins coins = new Coins((int) reqarray.get(0));
	                Woods woods = new Woods((int) reqarray.get(1));
	                Stones stones = new Stones((int) reqarray.get(2));
	                Servants servants = new Servants((int) reqarray.get(3));
	                FaithPoints faithPoints = new FaithPoints((int) reqarray.get(4));
	                VictoryPoints victoryPoints = new VictoryPoints((int) reqarray.get(5));
	                MilitaryPoints militaryPoints = new MilitaryPoints((int) reqarray.get(6));
	                Privileges privileges = new Privileges((int) reqarray.get(7));
	               
	                
	                Possession Req = new Possession(coins, woods, stones, servants, faithPoints, militaryPoints, victoryPoints, privileges);
	                cardcreating.setRequirements(Req);  
	                
	                
	            	
	            	//test
	            	System.out.println(cardcreating.getName());
	            	System.out.println(cardcreating.getRequirements().getCoins().value);
	            	System.out.println(cardcreating.getRequirements().getWoods().value);
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
