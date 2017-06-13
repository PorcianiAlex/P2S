package it.polimi.ingsw.GC_21.view;

public abstract class InputFromView {
	protected Adapter adapter;
	
	public void execute(RemoteView remoteView){
		this.setAdapter(remoteView);
	}
	
	public void setAdapter(RemoteView remoteView) {
		this.adapter = remoteView.getAdapter();
	}
	
	
	


	
}
