package it.polimi.ingsw.GC_21.VIEW;

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
	public void inputFromCli(Scanner keyboard) {
			try {
				registerOrLogin(keyboard);
				chooseUsername(keyboard);
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
	}
	
	public void registerOrLogin(Scanner keyboard) {
		System.out.println("Hi, do you want to Register (1) or Login (2) ?");
		insert = true;
	    String choice = keyboard.nextLine();
	    keyboard.reset();
	    switch (choice) { 
	    case "1": insert = true; 
	    	break; 
	    case "2": insert =false;; 
	    	break;
	    default :System.out.println("Invalid input, Register or Login"); 
	    	registerOrLogin(keyboard);
	    	break;
	    }
	}
	
	public void chooseUsername(Scanner keyboard) throws FileNotFoundException, IOException, ParseException {
	    System.out.println("Enter your username: ");
		username = keyboard.nextLine();
		keyboard.reset();
		System.out.println("Enter your password: ");
		psw = keyboard.nextLine();
		keyboard.reset();
	}
	
	

}
