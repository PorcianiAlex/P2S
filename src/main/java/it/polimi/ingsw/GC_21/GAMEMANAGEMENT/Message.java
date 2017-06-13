package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import org.json.simple.parser.ParseException;

import it.polimi.ingsw.GC_21.controller.Controller;

public abstract class Message implements Serializable{
	
	protected Controller controller; 
	
	public boolean convert() {
		return true;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

}
