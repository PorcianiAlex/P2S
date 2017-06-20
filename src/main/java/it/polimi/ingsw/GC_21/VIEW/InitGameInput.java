package it.polimi.ingsw.GC_21.VIEW;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;

public class InitGameInput extends InputForm{
	private String string;
	
@Override
	public void execute(RemoteView remoteView) {
	    Game game = remoteView.getGame();
	    if("start".equals(string) /*|| game.getPlayers().size()==4*/ ) {
	    	/*while (game.getPlayers().size() < 2) {
	    	\System.out.println("Waiting for players...");
			}*/
	      game.executeGame(); 
	    } else { execute(remoteView); } 
	  } 
	

	@Override
	public void inputFromCli(Scanner keyboard) {
		super.inputFromCli(keyboard);
		string = keyboard.nextLine();
	}

}
