package it.polimi.ingsw.GC_21.VIEW;

import java.io.IOException;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;

public class InitGameInput extends InputForm{
	private boolean start;
	
	
public InitGameInput(boolean start) {
		this.start = start;
	}

public InitGameInput() {
}




@Override
	public void execute(RemoteView remoteView) {
	    Game game = remoteView.getGame();
	    if(start || game.getPlayers().size()==4) {
	    	/*while (game.getPlayers().size() < 2) {
	    	System.out.println("Waiting for players...");
			}*/
	      game.executeGame(); 
	      try {
			remoteView.getAdapter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    } else { execute(remoteView); } 
	  } 
	

	@Override
	public void inputFromCli(Scanner keyboard) {
		super.inputFromCli(keyboard);
		String string = keyboard.next();
		if (!"start".equals(string)) {
			System.out.println("Send Start please");
			inputFromCli(keyboard);
		}
		else {
			start = true;
		}
	}

}
