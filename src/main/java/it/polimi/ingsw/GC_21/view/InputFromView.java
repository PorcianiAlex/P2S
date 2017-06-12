package it.polimi.ingsw.GC_21.view;

public abstract class InputFromView {
	protected AdapterConnection adapter;
	
	public void execute(RemoteView remoteView){
		
	}
	
	public void setAdapter(RemoteView remoteView) {
		this.adapter = remoteView.getAdapter();
	}
	
	
	


	
}
