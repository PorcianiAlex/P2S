package it.polimi.ingsw.GC_21.fx;

import java.awt.Button;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.xml.ws.handler.MessageContext;

import com.sun.glass.ui.Window;
import com.sun.media.jfxmedia.events.NewFrameEvent;

import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.CLIENT.ChooseActionMessage;
import it.polimi.ingsw.GC_21.CLIENT.MessageToClient;
import it.polimi.ingsw.GC_21.CLIENT.TurnMessage;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.VIEW.CouncilPlacementInput;
import it.polimi.ingsw.GC_21.VIEW.CraftInput;
import it.polimi.ingsw.GC_21.VIEW.CraftPlacementInput;
import it.polimi.ingsw.GC_21.VIEW.InputForm;
import it.polimi.ingsw.GC_21.VIEW.MarketPlacementInput;
import it.polimi.ingsw.GC_21.VIEW.PlacementInput;
import it.polimi.ingsw.GC_21.VIEW.TowerPlacementInput;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.ValueAxis;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FXMLGameController extends MetaController implements Initializable{

	private FamilyMemberColor familyMemberColor;
	private int servToConvert = 0;
	private PlacementInput inputForm;
	private Board classBoard;
	private ArrayList<Player> classPlayers;
	private MessThread messThread;
	
	@FXML private ToggleGroup place;
	@FXML private ToggleGroup family;

	
	 @FXML protected void Tower(ActionEvent event) {
			 
		 ToggleButton button = (ToggleButton) place.getSelectedToggle();
		 DevCardType devCardType =  DevCardType.valueOf(button.getUserData().toString());
		 int floor = Integer.parseInt(button.getText());
		 System.out.println(button.getId());
		 System.out.println( devCardType );
		 System.out.println( floor);

		 //settare la input con il giusto costruttore
		 inputForm = new TowerPlacementInput(devCardType, floor);
		 		 
	 }
	 
	 @FXML protected void Council(ActionEvent event) {
		 
		inputForm = new CouncilPlacementInput();
		 //TowerPlacementInput towerPlacementInput = new TowerPlacementInput();

	 }
	 
	 @FXML protected void Market(ActionEvent event) {
		 
		 ToggleButton button = (ToggleButton) place.getSelectedToggle();
		 int area = Integer.parseInt(button.getText());
		 System.out.println( area);
		 
		inputForm = new MarketPlacementInput(area);
		//TowerPlacementInput towerPlacementInput = new TowerPlacementInput();

		 }
	 
	 @FXML protected void Craft(ActionEvent event) {
		 
		 ToggleButton button = (ToggleButton) place.getSelectedToggle();
		 CraftType craftType =  CraftType.valueOf(button.getUserData().toString());
		 int area = Integer.parseInt(button.getText());
		
		 System.out.println( craftType );
		 System.out.println( area);

		 inputForm = new CraftPlacementInput(craftType, area);
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
		 
		 familyMemberColor=null;
			servToConvert=0;
			inputForm=null;
			
	 }
	 
	 @FXML protected void Confirm(ActionEvent event) {
		inputForm.setFamilyMemberColor(familyMemberColor);
		inputForm.setServantsToConvert(servToConvert);
		client2.setInputToSend(inputForm);
		System.out.println("action send");
		
		familyMemberColor=null;
		servToConvert=0;
		inputForm=null;
		
	 }


	public void refreshBoard(Board board, ArrayList<Player> players) {
		System.out.println("sono nella refreshboard");
		classBoard = board;
		classPlayers = players;
		//setto la board
		for (int i = 0; i < 3; i++) {
			int val = board.getDices()[i].getValue();
			System.out.println(val);
		}
		
	}
	
	public void ifChooseAction() {
		//gli mostro la conferma
		System.out.println("è il tuo turno, sono nella chooseaction");
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("default initialize!");
		messThread = new MessThread(client, this);
        messThread.start();
		
	}
	
	
}
