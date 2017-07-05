package it.polimi.ingsw.GC_21.CLIENT;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Future;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.polimi.ingsw.GC_21.ACTION.ExcommAction;
import it.polimi.ingsw.GC_21.VIEW.PassInput;

public class TimerThread extends Thread {
	private Connections client;
	private Future future;

	public TimerThread(Connections client, Future future) {
		this.client = client;
		this.future = future;
	}
	
	public TimerThread(Connections client2) {
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
			this.sleep(toSleep);
			client.sendInput(new PassInput());
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
