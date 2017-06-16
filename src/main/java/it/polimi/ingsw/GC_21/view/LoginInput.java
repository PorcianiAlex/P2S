package it.polimi.ingsw.GC_21.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import it.polimi.ingsw.GC_21.controller.LoginController;

public class LoginInput extends InputForm {
	private String username;
	private String psw;
	private boolean insert;
	

	
	public LoginInput(String username, String psw, boolean insert) {
		super();
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
	
	public void chooseUsername(Scanner keyboard) throws FileNotFoundException, IOException, ParseException {
		System.out.println("Hi, do you want to Register (1) or Login (2) ?");
		insert = true;
	    String choice = keyboard.nextLine(); 
	    switch (choice) { 
	    case "1": insert = true; 
	    break; 
	    case "2": insert =false;; 
	    break;
	    default : chooseUsername(keyboard);
	    break;
	    }
	    System.out.println("Enter your username: ");
		username = keyboard.nextLine();
		System.out.println("Enter your password: ");
		psw = keyboard.nextLine();
		
	}
	
	

}
