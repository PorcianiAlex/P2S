package it.polimi.ingsw.GC_21.view;

import java.io.FileNotFoundException;
import java.io.IOException;

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
		try {
			super.execute(remoteView);
			if (CLI) {
				chooseUsername(remoteView);
			}
			LoginMessage loginMessage = new LoginMessage(username, psw, insert);
			remoteView.notifyMessage(loginMessage);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void chooseUsername(RemoteView remoteView) throws FileNotFoundException, IOException, ParseException {
		adapterConnection.out("Hi, do you want to Register (1) or Login (2) ?");
		insert = true;
	    String choice = adapterConnection.in(); 
	    switch (choice) { 
	    case "1": insert = true; 
	    break; 
	    case "2": insert =false;; 
	    break;
	    default : chooseUsername(remoteView);
	    break;
	    }
		adapterConnection.out("Enter your username: ");
		username = adapterConnection.in();
		adapterConnection.out("Enter your password: ");
		psw = adapterConnection.in();
		remoteView.setUsername(username);
		
	}
	
	

}
