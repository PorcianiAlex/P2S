package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

public class LoginMessage extends Message{
	private String username;
	private String password;
	private boolean insert;
	

	public LoginMessage(String username, String password, boolean insert) {
		this.username = username;
		this.password = password;
		this.insert = insert;
	}

	@Override
	public boolean convert()  {
		boolean ok = false;
		try {
			ok = controller.getControllerManager().Login(username, password, insert);
			if(ok == false && insert == true) {
				controller.getRemoteView().getAdapter().out("these username and password doesn't exist!");
			}
			if(ok == false && insert == false) {
				controller.getRemoteView().getAdapter().out("these username and password doesn't exist!");
			}
			if(!checkLoggedUsers(username) && insert==false) {
				controller.getRemoteView().chooseUsername();
			}
			if (ok == false) {
				controller.getRemoteView().chooseUsername();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
		
	}
		
	public boolean checkLoggedUsers(String name) { 
	    for (int i = 0; i < controller.getControllerManager().getRemoteViews().size(); i++) { 
	      if(name.equals(controller.getControllerManager().getRemoteViews().get(i).getUsername())){ 
	        controller.getRemoteView().getAdapter().out("Oh grullo! tu sei già loggato!"); 
	        return false; 
	      } 
	    } 
	    return true; 
	  }


	
	

}
