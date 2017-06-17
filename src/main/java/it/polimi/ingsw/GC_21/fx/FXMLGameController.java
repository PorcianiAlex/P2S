package it.polimi.ingsw.GC_21.fx;

import java.awt.Button;

import com.sun.media.jfxmedia.events.NewFrameEvent;

import it.polimi.ingsw.GC_21.view.TowerPlacementInput;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class FXMLGameController extends MetaController{

	@FXML private ToggleGroup place;
	@FXML private ToggleButton T1F1;
	@FXML private ToggleButton T1F2;
	@FXML private ToggleButton T1F3;
	@FXML private ToggleButton T1F4;


	
	 @FXML protected void Tower(ActionEvent event) {
		 
		 System.out.println(place.getSelectedToggle().getProperties());
		 //todo costruire la placement con le proprietà dei toggle!
		 TowerPlacementInput towerPlacementInput = new TowerPlacementInput();
		 client2.setInputToSend(towerPlacementInput);

	 }
	 
	
}
