package it.polimi.ingsw.GC_21.view;

import java.io.Serializable;

public abstract class InputFromView implements Serializable {
	protected AdapterConnection adapterConnection;
	protected boolean CLI = false;
	
	public void execute(RemoteView remoteView){
		this.setAdapter(remoteView);
	}
	
	public void setAdapter(RemoteView remoteView) {
		this.adapterConnection = remoteView.getAdapter();
	}
	
	
	


	
}
