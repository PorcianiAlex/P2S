package it.polimi.ingsw.GC_21.CLIENT;

import java.io.IOException;
import java.util.concurrent.Future;

import it.polimi.ingsw.GC_21.VIEW.PassInput;

public class TimerThread extends Thread {
	private Connections client;
	private Future future;

	public TimerThread(Connections client, Future future) {
		this.client = client;
		this.future = future;
		
	}
	
	@Override
	public void run() {
		try {
			this.sleep(10000);
			client.sendInput(new PassInput());
			future.cancel(true);
		} catch (InterruptedException | IOException e) {
			System.out.println("Bravo, my dear");
		}
	}

}
