package it.polimi.ingsw.GC_21.view;

public abstract class InputFromView {
	protected Adapter adapter;
	
	public void execute(RemoteView remoteView){
		this.adapter = remoteView.getAdapter();
	}
	
	public int selectFloor(){ 
	    adapter.out("Select Floor [1-4]:"); 
	    String choicestring = adapter.in(); 
	    int choice = Integer.parseInt(choicestring); 
	    if (choice <=4 && choice >=1){ 
	      return choice; 
	    } 
	    else { 
	      adapter.out("Invalid floor choice, try again!"); 
	      return this.selectFloor(); 
	    } 
	  } 


	
}
