package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

public class CheckLoginMessage extends Message {
	private boolean result;
	private String string;
	
	public CheckLoginMessage(boolean result, String string) {
		this.result = result;
		this.string = string;
	}
	
	

}
