package it.polimi.ingsw.GC_21.CONTROLLER;


public abstract class ControllerForm {
	
	protected Controller controller; 
	
	public boolean executeController() {
		return true;
	}
	
	

	public void setController(Controller controller) {
		this.controller = controller;
	}


}
