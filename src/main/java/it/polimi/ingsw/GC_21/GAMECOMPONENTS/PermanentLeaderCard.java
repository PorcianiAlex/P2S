package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

public class PermanentLeaderCard extends LeaderCard {
	
	public PermanentLeaderCard(String name) {
		super(name);
	}

	private boolean played;

	public boolean isPlayed() {
		return played;
	}

	public void setPlayed(boolean played) {
		this.played = played;
	}
	
	

}
