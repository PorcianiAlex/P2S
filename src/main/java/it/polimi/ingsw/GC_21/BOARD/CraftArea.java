package it.polimi.ingsw.GC_21.BOARD;

import java.util.ArrayList;

import it.polimi.ingsw.GC_21.EFFECT.CraftInfluencer;
import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.Immediate;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;

public class CraftArea {
	
	private final CraftType craftType;
	private final SingleActionSpace singleActionSpace;
	private final MultipleActionSpace multipleActionSpace;
	
	public CraftArea(CraftType craftType) {
		super();
		this.craftType = craftType;
		this.singleActionSpace = new SingleActionSpace(1, null);
		this.multipleActionSpace = new MultipleActionSpace(1, new CraftInfluencer(craftType, -3));
	}

	public boolean checkFamilyMemberColor(Color color) {//return true if there is a family member with the indicated color in the singleActionSpace or in the multiple
		
		
			return singleActionSpace.getFamilyMember().getPlayerColor().equals(color) || 
					checkColorPresenceOnMultipleSpace(color);
		
	}
	
	public boolean checkColorPresenceOnMultipleSpace(Color color) {
		int i = 0;
		ArrayList<FamilyMember> familyMembersLocated = multipleActionSpace.getFamilyMembers(); 
		while (i < familyMembersLocated.size()) {
		      if (familyMembersLocated.get(i).getPlayerColor().equals(color)) {
			      return true;
		      }
			i++;
		}
		return false;
	}


	public CraftType getCraftType() {
		return craftType;
	}
	
	public ActionSpace selectActionSpace(int selectedActionSpace) {
		if (selectedActionSpace==1) {
			return getSingleActionSpace();
		}
		else if (selectedActionSpace==2) {
			return getMultipleActionSpace();
		}
		return null;
	}

	public SingleActionSpace getSingleActionSpace() {
		return singleActionSpace;
	}


	public MultipleActionSpace getMultipleActionSpace() {
		return multipleActionSpace;
	}

	@Override
	public String toString() {
		return "CraftArea [" + craftType + ", singleActionSpace=" + singleActionSpace.toString()
				+ ", multipleActionSpace=" + multipleActionSpace.toString() + "]";
	}

	
	

}