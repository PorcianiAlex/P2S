package it.polimi.ingsw.GC_21.VIEW;

import java.io.Serializable;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class InputForm implements Serializable {
	
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
