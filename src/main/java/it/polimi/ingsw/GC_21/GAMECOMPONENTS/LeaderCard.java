package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

public class LeaderCard extends Card {
	
	private String description;
	private boolean played;
	
	
	public LeaderCard(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isPlayed() {
		return played;
	}
	public void setPlayed(boolean played) {
		this.played = played;
	}

	
}