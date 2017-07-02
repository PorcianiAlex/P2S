package it.polimi.ingsw.GC_21.fx;

import java.io.IOException;
import java.rmi.RemoteException;

import it.polimi.ingsw.GC_21.CLIENT.Connections;
import it.polimi.ingsw.GC_21.CLIENT.MessageToClient;
import it.polimi.ingsw.GC_21.CLIENT.TurnMessage;
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
		System.out.println("thread in attesa");
		MessageToClient mess;
		try {
			mess = client.getReceivedMessage();
			texttarget.setText(mess.getDescription());
		if (mess instanceof TurnMessage) {
			colorController.gameScene();
			return;
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		
		}
		
	}
	
	
	
}
