package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

public class CheckLobbyMessage extends Message {

	boolean create;
	int joined;
	
	public CheckLobbyMessage(boolean create, int joined) {
		super();
		this.create = create;
		this.joined = joined;
	}
	
	
	
}
