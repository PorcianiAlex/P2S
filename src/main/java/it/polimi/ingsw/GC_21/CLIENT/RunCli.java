package it.polimi.ingsw.GC_21.CLIENT;

import java.io.IOException;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.GameEndState;
import it.polimi.ingsw.GC_21.VIEW.InputForm;
import it.polimi.ingsw.GC_21.VIEW.PassInput;

public class RunCli {

	private Connections client;
	private Scanner scanner;
	
	
	public RunCli(Connections client) {
		this.client = client;
	}

	public void start() throws ClassNotFoundException, IOException {
		Scanner keyboard = new Scanner(System.in);
		client.setKeyboard(keyboard);
		while(true) {
			MessageToClient message = client.getReceivedMessage();
			if (message != null) {
				if (message instanceof ChooseActionMessage){
					System.out.println("client settato");
					((ChooseActionMessage) message).setClient(client);
				}
				InputForm inputForm = message.executeCLI(keyboard);
				if (inputForm != null) {
					client.sendInput(inputForm);
				}
				if(message.getGameEndState().equals(GameEndState.Over)) {
					client.getKeyboard().close();
					client.close();
					return;
				}
				
			}
			
		}
		
	}
	
	public void pass() {
		PassInput passInput = new PassInput();
		try {
			client.sendInput(passInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
