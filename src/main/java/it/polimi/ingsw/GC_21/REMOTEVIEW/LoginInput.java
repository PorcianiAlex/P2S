package it.polimi.ingsw.GC_21.REMOTEVIEW;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import it.polimi.ingsw.GC_21.CONTROLLER.LoginController;

public class LoginInput extends InputForm {
	private String username;
	private String psw;
	private boolean insert;
	

	
	public LoginInput(String username, String psw, boolean insert) {
		this.username = username;
		this.psw = psw;
		this.insert = insert;
	}
	
	public LoginInput() {
	}

	@Override
	public void execute(RemoteView remoteView) {
			super.execute(remoteView);
			remoteView.setUsername(username);
			LoginController loginController = new LoginController(username, psw, insert);
			remoteView.notifyController(loginController);
	}
	@Override
	public void inputFromCli() throws InterruptedException {
			try {
				registerOrLogin();
				chooseUsername();
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
	}
	
	public void registerOrLogin() throws InterruptedException {
		System.out.println("Hi, do you want to Register (1) or Login (2) ?");
		insert = true;
		String choice = takeInput(this);
	    switch (choice) { 
	    case "1": insert = true; 
	    	break; 
	    case "2": insert =false;; 
	    	break;
	    default :System.out.println("Invalid input, Register or Login"); 
	    	registerOrLogin();
	    	break;
	    }
	}
	
	public void chooseUsername() throws FileNotFoundException, IOException, ParseException, InterruptedException {
	    System.out.println("Enter your username: ");
		username = takeInput(this);
		System.out.println("Enter your password: ");
		psw = takeInput(this);
	}
	
	

}
