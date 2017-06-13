package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

public class CheckLoginMessage extends Message {
	private boolean result;
	private String string;
	
	public CheckLoginMessage(boolean result, String string) {
		this.result = result;
		this.string = string;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}
	
	

}
