package it.polimi.ingsw.GC_21.REMOTEVIEW;

import java.io.Serializable;
import java.util.Scanner;

public class InputForm implements Serializable {
	protected AdapterConnection adapterConnection;
	protected StringBuffer input = new StringBuffer();
	protected InputForm actionInput;
	
	public InputForm(InputForm actionInput, StringBuffer input) {
		this.input = input;
		this.actionInput = actionInput;
	}


	public InputForm() {
	}
	
	public void execute(RemoteView remoteView){
		this.setAdapter(remoteView);
	}
	
	
	
	public void inputFromCli() throws InterruptedException {
	}
	
	public void setAdapter(RemoteView remoteView) {
		this.adapterConnection = remoteView.getAdapter();
	}
	
	public String takeInput(Object object) throws InterruptedException  {
		synchronized (object) {
			while (input.length() == 0) {
					object.wait();
				} 
				
		}
		String string = String.valueOf(input);
		input.setLength(0);	
		return string;
	}



	public void fill(String choice) {
		synchronized (this) {
			this.input.append(choice);
			this.notifyAll();
		}
		
	}



	
	


	
}
