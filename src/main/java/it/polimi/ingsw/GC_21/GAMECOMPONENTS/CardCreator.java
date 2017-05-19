package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import org.json.simple.*;
import org.json.simple.parser.*;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;


public class CardCreator {

		
	 public static void main(String[] args) {

	        JSONParser parser = new JSONParser();

	        try {

	            Object obj = parser.parse(new FileReader("C:\\Users\\Alex\\Desktop\\provajs.json"));
	            JSONObject card = (JSONObject) obj;
	            JSONArray cardarray= (JSONArray) card.get("Card");
	            for (Object o : cardarray) {
	                JSONObject jsonLineItem = (JSONObject) o;
	                String name = (String) jsonLineItem.get("name");
	              	            	 
	            	Card cardcreating = new Card(name);	            	
	            	 System.out.println(cardcreating.getName());
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
