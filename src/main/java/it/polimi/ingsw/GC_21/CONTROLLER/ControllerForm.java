package it.polimi.ingsw.GC_21.CONTROLLER;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import org.json.simple.parser.ParseException;

public abstract class ControllerForm {
	
	protected Controller controller; 
	
	public boolean executeController() {
		return true;
	}
	
	

	public void setController(Controller controller) {
		this.controller = controller;
	}


}
