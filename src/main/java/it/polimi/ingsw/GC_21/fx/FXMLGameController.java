package it.polimi.ingsw.GC_21.fx;

import java.awt.Button;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
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
import it.polimi.ingsw.GC_21.VIEW.InitGameInput;
import it.polimi.ingsw.GC_21.VIEW.InputForm;
import it.polimi.ingsw.GC_21.VIEW.MarketPlacementInput;
import it.polimi.ingsw.GC_21.VIEW.PlacementInput;
import it.polimi.ingsw.GC_21.VIEW.TowerPlacementInput;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.ValueAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXMLGameController extends MetaController implements Initializable{

	private FamilyMemberColor familyMemberColor;
	private int servToConvert = 0;
	private PlacementInput inputForm;
	private Board classBoard;
	private ArrayList<Player> classPlayers;
	private MessThread messThread;
	private boolean canGo;
	
	@FXML private ToggleGroup place;
	@FXML private ToggleGroup family;
	@FXML private Text whitedice, blackdice, orangedice;
	@FXML private ToggleGroup cards;


	
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
		 System.out.println(area);
		 
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
	 
	 @FXML protected void Confirm(ActionEvent event) throws RemoteException, IOException {
		if(canGo) {
		inputForm.setFamilyMemberColor(familyMemberColor);
		inputForm.setServantsToConvert(servToConvert);
		client.sendInput(inputForm);
		System.out.println("action send");
		
		familyMemberColor=null;
		servToConvert=0;
		inputForm=null;
		canGo=false;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("It's not your turn!");
			alert.setContentText("wait your turn please");
			alert.showAndWait();
		}
		return;
	 }
	 
	 @FXML protected void Card(ActionEvent event) {
		 
		 ToggleButton button = (ToggleButton) cards.getSelectedToggle();
		 BackgroundImage imageView = button.getBackground().getImages().get(0);
		 Image image = imageView.getImage();
		 
		 Alert alert = new Alert(AlertType.ERROR);
		 alert.setTitle("Zoom on card");
		 alert.setHeaderText(null);
		 alert.setContentText(null);
		 alert.setGraphic(new ImageView(image));
		 alert.showAndWait();
	 }

	public void refreshBoard(Board board, ArrayList<Player> players) {
		System.out.println("sono nella refreshboard");
		classBoard = board;
		classPlayers = players;
		//setto la board
		//dadi:
		blackdice.setText(String.valueOf(board.getDices()[0].getValue()));
		whitedice.setText(String.valueOf(board.getDices()[1].getValue()));	
		orangedice.setText(String.valueOf(board.getDices()[2].getValue()));
		//carte
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				ToggleButton toggleButton =  (ToggleButton) cards.getToggles().get(j+4*i);
				
				if(!board.getTowers()[i].getFloors()[j].getSingleActionSpace().isBusy()) {
				String idcard = board.getTowers()[i].getFloors()[j].getDevCardPlace().getCard().getID();		
				toggleButton.setStyle
				("-fx-background-image: url('/devcards/devcards_f_en_c_"+idcard+".png');  -fx-background-size: 70px; -fx-background-repeat: no-repeat; -fx-background-position: 90%;");
				} else {
					toggleButton.setStyle
					("-fx-background-color: white;  -fx-background-size: 70px; -fx-background-repeat: no-repeat; -fx-background-position: 90%;");
					
				}
			}
			
		}
			
	}
	
	public void ifChooseAction() {
		//gli mostro la conferma
		System.out.println("è il tuo turno, sono nella chooseaction");
		canGo = true;
		return;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("default initialize!");
		messThread = new MessThread(client, this);
        messThread.start();
                
	}

	
}
