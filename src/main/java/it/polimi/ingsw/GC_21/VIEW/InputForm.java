package it.polimi.ingsw.GC_21.VIEW;

import java.io.Serializable;
import java.util.Scanner;

public abstract class InputForm implements Serializable {
	
	protected AdapterConnection adapterConnection;
	
	public void execute(RemoteView remoteView){
		this.setAdapter(remoteView);
	}
	
	
	
	public void inputFromCli(Scanner keyboard) {
		
	}
	
	public void setAdapter(RemoteView remoteView) {
		this.adapterConnection = remoteView.getAdapter();
	}
	
	
	


	
}
