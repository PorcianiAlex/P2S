package it.polimi.ingsw.GC_21.fx;

import java.awt.Button;

import com.sun.glass.ui.Window;
import com.sun.media.jfxmedia.events.NewFrameEvent;

import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.view.CouncilPlacementInput;
import it.polimi.ingsw.GC_21.view.CraftInput;
import it.polimi.ingsw.GC_21.view.InputForm;
import it.polimi.ingsw.GC_21.view.MarketPlacementInput;
import it.polimi.ingsw.GC_21.view.TowerPlacementInput;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FXMLGameController extends MetaController{

	private FamilyMemberColor familyMemberColor;
	private int servToConvert = 0;
	private InputForm inputForm;
	
	@FXML private ToggleGroup place;
	@FXML private ToggleGroup family;

	
	
	@FXML
    public void initialize() {
        System.out.println("inizializzazione schermata gioco");
   
    }
	
	
	 @FXML protected void Tower(ActionEvent event) {
			 
		 ToggleButton button = (ToggleButton) place.getSelectedToggle();
		 DevCardType devCardType =  DevCardType.valueOf(button.getUserData().toString());
		 int floor = Integer.parseInt(button.getText());
		 System.out.println(button.getId());
		 System.out.println( devCardType );
		 System.out.println( floor);

		 //settare la input con il giusto costruttore
		 inputForm = new TowerPlacementInput();
		 		 
	 }
	 
	 @FXML protected void Council(ActionEvent event) {
		 
		inputForm = new CouncilPlacementInput();
		 //TowerPlacementInput towerPlacementInput = new TowerPlacementInput();

	 }
	 
	 @FXML protected void Market(ActionEvent event) {
		 
		 ToggleButton button = (ToggleButton) place.getSelectedToggle();
		 int area = Integer.parseInt(button.getText());
		 System.out.println( area);
		 
		inputForm = new MarketPlacementInput();
		//TowerPlacementInput towerPlacementInput = new TowerPlacementInput();

		 }
	 
	 @FXML protected void Craft(ActionEvent event) {
		 
		 ToggleButton button = (ToggleButton) place.getSelectedToggle();
		 CraftType craftType =  CraftType.valueOf(button.getUserData().toString());
		 int area = Integer.parseInt(button.getText());
		
		 System.out.println( craftType );
		 System.out.println( area);

		 inputForm = new CraftInput(craftType, area);
		 }
	 
	 	@FXML protected void FamilyMember(ActionEvent event) {
		 
	 		// da mettere i tasti (4 TOGGLE DI UN NUOVO GROUP)
	 		
		 ToggleButton button = (ToggleButton) family.getSelectedToggle();
		 familyMemberColor = FamilyMemberColor.valueOf(button.getText());
		 System.out.println( familyMemberColor );
		 

		 }
	 
	 @FXML protected void Serv(ActionEvent event) {
		 this.servToConvert++;
		 System.out.println(servToConvert);
	 }
	 
	 @FXML protected void Reset(ActionEvent event) {
		 
		 	Node source = (Node) event.getSource();
		 	Stage theStage = (Stage) source.getScene().getWindow();
		 
	        FXMLGame fxmlGame = new FXMLGame();
	        try {
				fxmlGame.start(theStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
	 }
	 
	 @FXML protected void Confirm(ActionEvent event) {
		//client2.setInputToSend(inputForm);
	 }
}
