package it.polimi.ingsw.GC_21.fx;

import java.rmi.RemoteException;

import it.polimi.ingsw.GC_21.CLIENT.Connections;
import javafx.scene.text.Text;

public class ColorThread extends Thread {

	private Text texttarget;
	private Connections client;
	private FXMLColorController colorController;
	
	
	public ColorThread(Text texttarget, Connections client, FXMLColorController cc) {
		this.texttarget = texttarget;
		this.client = client;
		this.colorController = cc;
	}



	@Override
	public void run() {
		while(true) {
		try {		
			System.out.println("thread in attesa");
			String mess = client.getMessage();
			System.out.println("thread: "+mess);
			texttarget.setText(mess);
			if ("Ready to Play".equals(mess)) {
				return;
				}
		} catch (RemoteException e) {
			e.printStackTrace();
			}
		
		}
		
		
	}
	
	
	
}
