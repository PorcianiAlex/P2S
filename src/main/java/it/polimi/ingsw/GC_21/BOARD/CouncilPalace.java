package it.polimi.ingsw.GC_21.BOARD;


import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CouncilPalace implements Serializable {
	private MultipleActionSpace multipleActionSpace;
	private Game game;
	
	public CouncilPalace(Game game) {
		multipleActionSpace = new MultipleActionSpace(1, new Effect(new Possession(1, 0, 0, 0, 0, 0, 0), 1, game), game);
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