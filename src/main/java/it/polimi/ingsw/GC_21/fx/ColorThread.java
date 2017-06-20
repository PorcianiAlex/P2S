package it.polimi.ingsw.GC_21.fx;

import java.io.IOException;
import java.rmi.RemoteException;

import it.polimi.ingsw.GC_21.CLIENT.Connections;
import it.polimi.ingsw.GC_21.CLIENT.MessageToClient;
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
		/*while(true) {
		System.out.println("thread in attesa");
		MessageToClient mess;
		try {
			mess = client.getReceivedMessage();
			System.out.println("thread: "+mess.getDescription());
			texttarget.setText(mess.getDescription());
		if ("Ready to Play".equals(mess)) {
			return;
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		
		}*/
		
		
	}
	
	
	
}
