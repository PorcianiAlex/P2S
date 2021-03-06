package it.polimi.ingsw.GC_21.CLIENT;

import java.io.IOException;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.GameEndState;
import it.polimi.ingsw.GC_21.VIEW.InputForm;
import it.polimi.ingsw.GC_21.VIEW.SaveInput;

public class RunCli implements Runnable {

	private Connections client;
	private InputForm inputFormToFill;
	private InputForm inputFormToSend;
	private Object LOCK = new Object();
	private MessageToClient message;

	
	public RunCli(Connections client) {
		this.client = client;
	}

	public void start() throws ClassNotFoundException, IOException {
		Thread runCliThread = new Thread(this);
		 runCliThread.start();
		while(true) {
			synchronized (LOCK) {
				 message = client.getReceivedMessage();
			}
			 if (message != null) {
				message.setClient(client);
				try {
					inputFormToSend = message.executeCLI(LOCK);
				} catch (InterruptedException e) {
					e.printStackTrace();
					}
				synchronized (LOCK) {
						inputFormToFill = null;
						}
				}					 
			if (inputFormToSend != null) {
				client.sendInput(inputFormToSend);					
				}
			if(message.getGameEndState().equals(GameEndState.Over)) {
				client.close();
				return;
				}	
									
				
				}	
					
					
	}

	@Override
	public void run() {
		Scanner keyboard = new Scanner(System.in);
		while(true){
			try {
			String choice = keyboard.next();
			if (choice.equals("save")) {
				client.sendInput(new SaveInput());
				client.close();
				break;
			}
			if (choice.equals("Disconnect")) {
				client.close();
				break;
			}
			synchronized (LOCK) {
			if (message != null) {
				inputFormToFill = message.getInputForm();
				}
			
				while (inputFormToFill == null) {
					LOCK.wait();
					inputFormToFill = message.getInputForm();
				}
				inputFormToFill.fill(choice);
			}		
			} catch (InterruptedException | IOException e) {
					e.printStackTrace();
					}
				
			
		}
		
	}
	
}
