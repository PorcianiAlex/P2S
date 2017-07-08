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
	    remoteView.notifyInit();
	    remoteView.getGame().notifyClose();
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
