package it.polimi.ingsw.GC_21.BOARD;

import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;

public class MarketArea implements Serializable{
	private SingleActionSpace[] singleActionSpace;
	private Game game;

	public MarketArea(Game game) throws IOException, ParseException{
		this.singleActionSpace = new SingleActionSpace[4];
		this.game=game;
		JSONParser parser = new JSONParser();
		java.net.URL path = MarketArea.class.getResource("spaceEffects.json");
		FileReader file = new FileReader(path.getPath());
		JSONObject obj = (JSONObject) parser.parse(file);
	    JSONArray marketEffects= (JSONArray) obj.get("marketArea");
	    int i = 0;
		for (Object o : marketEffects) {
           	JSONObject jsonLineItem = (JSONObject) o;      
           	int privileges = Integer.parseInt(jsonLineItem.get("Priv").toString());            
    	    JSONArray reward= (JSONArray) jsonLineItem.get("Reward");          	               
            Possession rewards = new Possession(Integer.parseInt(reward.get(0).toString()),Integer.parseInt(reward.get(1).toString()),
            		Integer.parseInt(reward.get(2).toString()), Integer.parseInt(reward.get(3).toString()),
            		Integer.parseInt(reward.get(4).toString()), Integer.parseInt(reward.get(5).toString()), 
            	    Integer.parseInt(reward.get(6).toString()));
            singleActionSpace[i] = new SingleActionSpace(1, new Effect(rewards, privileges, game), game);
            i++;
		}
	}

	public SingleActionSpace[] getSingleActionSpace() {
		return singleActionSpace;
	}

	public void setSingleActionSpace(SingleActionSpace[] singleActionSpace) {
		this.singleActionSpace = singleActionSpace;
	}

	@Override
	public String toString() {
		return "MarketArea:" + Arrays.toString(singleActionSpace) + "]";
	}

	
	
}