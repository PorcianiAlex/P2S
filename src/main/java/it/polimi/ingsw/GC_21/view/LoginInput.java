package it.polimi.ingsw.GC_21.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.LoginMessage;

public class LoginInput extends InputFromView {
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
		this.CLI = true;
	}

	@Override
	public void execute(RemoteView remoteView) {
			super.execute(remoteView);
			remoteView.setUsername(username);
			LoginMessage loginMessage = new LoginMessage(username, psw, insert);
			remoteView.notifyMessage(loginMessage);
	}
	
	public void chooseUsername() throws FileNotFoundException, IOException, ParseException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Hi, do you want to Register (1) or Login (2) ?");
		insert = true;
	    String choice = scanner.nextLine(); 
	    switch (choice) { 
	    case "1": insert = true; 
	    break; 
	    case "2": insert =false;; 
	    break;
	    default : chooseUsername();
	    break;
	    }
	    System.out.println("Enter your username: ");
		username = scanner.nextLine();
		System.out.println("Enter your password: ");
		psw = scanner.nextLine();
		scanner.close();
		
	}
	
	

}
