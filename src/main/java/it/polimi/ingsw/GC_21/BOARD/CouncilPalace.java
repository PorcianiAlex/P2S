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
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CouncilPalace implements Serializable {
	private MultipleActionSpace multipleActionSpace;
	private Game game;
	
	public CouncilPalace(Game game) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		java.net.URL path = CouncilPalace.class.getResource("spaceEffects.json");
		FileReader file = new FileReader(path.getPath());
		JSONObject obj = (JSONObject) parser.parse(file);
	    JSONObject councilPalace= (JSONObject) obj.get("councilPalace");
        int privileges = Integer.parseInt(councilPalace.get("Priv").toString());            
    	JSONArray reward= (JSONArray) councilPalace.get("Reward");          	               
    	Possession rewards = new Possession(Integer.parseInt(reward.get(0).toString()),Integer.parseInt(reward.get(1).toString()),
            		Integer.parseInt(reward.get(2).toString()), Integer.parseInt(reward.get(3).toString()),
            		Integer.parseInt(reward.get(4).toString()), Integer.parseInt(reward.get(5).toString()), 
            	    Integer.parseInt(reward.get(6).toString()));
    	multipleActionSpace = new MultipleActionSpace(1, new Effect(rewards, privileges, game), game);
		this.game=game;
	}

	public MultipleActionSpace getMultipleActionSpace() {
		return multipleActionSpace;
	}

	public void setMultipleActionSpace(MultipleActionSpace multipleActionSpace) {
		this.multipleActionSpace = multipleActionSpace;
	}
	
	public ArrayList<Player> getTurnOrder() {
		ArrayList<FamilyMember> familyMembers = multipleActionSpace.getFamilyMembers();//family members in council palace
		ArrayList<Player> turnOrder = new ArrayList<Player>();//Players that take a new turn position
		for (int i = 0; i < familyMembers.size(); i++) {
				Player player = familyMembers.get(i).getOwnerPlayer();
				if (!turnOrder.contains(player)) {
					turnOrder.add(player);
				}
		}		
		return turnOrder;
	}



	
	@Override
	public String toString() {
		return "CouncilPalace:[" + multipleActionSpace.toString() + "]";
	}
	
	

}