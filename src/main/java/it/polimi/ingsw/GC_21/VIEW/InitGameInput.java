package it.polimi.ingsw.GC_21.VIEW;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import it.polimi.ingsw.GC_21.CLIENT.TimerThread;
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
	    	remoteView.notifyInit();
	      remoteView.notifyClose();
	    } else { execute(remoteView); } 
	  } 
	

	@Override
	public void inputFromCli() throws InterruptedException  {
		String string = takeInput(this);
		if (!string.equals("start")) {
			System.out.println("Send Start please");
			inputFromCli();
		}
		else {
			start = true;
		}
	}

}
