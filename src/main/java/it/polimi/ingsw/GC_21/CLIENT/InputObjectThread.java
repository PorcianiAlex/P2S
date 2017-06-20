package it.polimi.ingsw.GC_21.CLIENT;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.VIEW.InputForm;
import it.polimi.ingsw.GC_21.fx.ViewType;

public class InputObjectThread extends Thread{
	
	private SocketClient socketClient;


	public InputObjectThread(ObjectOutputStream oos, ObjectInputStream ois, SocketClient socketClient) {
		
		this.socketClient = socketClient;
	}
	
	@Override
	public void run() {
		Scanner keyboard = new Scanner(System.in);
		while(true) {
			MessageToClient receivedMessage;
			try {
				receivedMessage = (MessageToClient) ois.readObject();
				System.out.println("Thread " + receivedMessage.description);
				if (receivedMessage.getDescription().equals("END")) {
					break;
				}
				if(socketClient.getView() == ViewType.GUI){
					socketClient.setObjForGui(receivedMessage);
				} 
				else {
					if (receivedMessage != null) {
						InputForm inputForm = receivedMessage.executeCLI(keyboard);
						oos.writeObject(inputForm);
					}				
			}
		
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //arriva dal socket server
			
		}
		try {
			ois.close();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
