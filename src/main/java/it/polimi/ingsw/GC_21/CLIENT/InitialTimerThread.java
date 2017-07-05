package it.polimi.ingsw.GC_21.CLIENT;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Future;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.polimi.ingsw.GC_21.VIEW.InitGameInput;
import it.polimi.ingsw.GC_21.VIEW.PassInput;

public class InitialTimerThread extends Thread {
	private Connections client;
	private Future future;

	public InitialTimerThread(Connections client, Future future) {
		this.client = client;
		this.future = future;
	}
	
	public InitialTimerThread(Connections client2) {
		this.client = client2;
	}

	@Override
	public void run() {
		try {
			JSONParser parser = new JSONParser(); //loading by file 
			java.net.URL path = TimerThread.class.getResource("timeout.json");
			FileReader file = new FileReader(path.getPath());
			JSONObject obj = (JSONObject) parser.parse(file);
           	int toSleep = Integer.parseInt(obj.get("timeout").toString()); 
			this.sleep(10000);
			InitGameInput initGameInput = new InitGameInput(true);
			client.sendInput(initGameInput);
			if (future!= null){
				future.cancel(true);
			}
		} catch (InterruptedException | IOException e) {
			System.out.println("Bravo, my dear");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

