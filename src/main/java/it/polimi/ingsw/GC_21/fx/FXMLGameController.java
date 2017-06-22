package it.polimi.ingsw.GC_21.fx;

import java.awt.Button;
import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Currency;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.xml.ws.handler.MessageContext;

import com.sun.glass.ui.Window;
import com.sun.media.jfxmedia.events.NewFrameEvent;

import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.BOARD.OwnedCards;
import it.polimi.ingsw.GC_21.CLIENT.ChooseActionMessage;
import it.polimi.ingsw.GC_21.CLIENT.MessageToClient;
import it.polimi.ingsw.GC_21.CLIENT.Music;
import it.polimi.ingsw.GC_21.CLIENT.TurnMessage;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.LeaderCard;
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
import javafx.application.Platform;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Toggle;
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
    private ArrayList<ArrayList<ToggleGroup>> tabs = new ArrayList<ArrayList<ToggleGroup>>();

	
	@FXML private ToggleGroup cards, place, family, myterritory, mybuilding, myventure, myleader, mycharacheter, x3,x4,x5,x6, x7;
	@FXML private Text whitedice, blackdice, orangedice, state;
	@FXML private Text r1,r2,r3,r4,r5,r6,r7,r8;
	@FXML private Tab pl1, pl2;


	
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
		 ToggleButton button = (ToggleButton) event.getSource();
		 
		 BackgroundImage imageView = button.getBackground().getImages().get(0);
		 Image image = imageView.getImage();
		 
		 Alert alert = new Alert(AlertType.ERROR);
		 alert.setTitle("Zoom on card");
		 alert.setHeaderText(null);
		 alert.setContentText(null);
		 alert.setGraphic(new ImageView(image));
		 alert.showAndWait();
	 }

	public void refreshBoard(Board board, ArrayList<Player> players, String dString) {
		System.out.println("sono nella refreshboard");
		classBoard = board;
		classPlayers = players;
		//setto la board
		//gamemanagement
		state.setText(dString);
		//dadi:
		blackdice.setText(String.valueOf(board.getDices()[0].getValue()));
		whitedice.setText(String.valueOf(board.getDices()[1].getValue()));	
		orangedice.setText(String.valueOf(board.getDices()[2].getValue()));
		//carte
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				ToggleButton toggleButton =  (ToggleButton) cards.getToggles().get(j+4*i);
				
				if(board.getTowers()[i].getFloors()[j].getDevCardPlace().getCard()!=null) {
				String idcard = board.getTowers()[i].getFloors()[j].getDevCardPlace().getCard().getID();		
				toggleButton.setStyle
				("-fx-background-image: url('/devcards/devcards_f_en_c_"+idcard+".png');  -fx-background-size: 70px; -fx-background-repeat: no-repeat; -fx-background-position: 90%; -fx-opacity: 1;");
				} else {
					ToggleButton placebutton =  (ToggleButton) place.getToggles().get(j+4*i);
					toggleButton.setStyle
					("-fx-background-image: url('/devcards/whitecard.png');  -fx-background-size: 70px; -fx-background-repeat: no-repeat; -fx-background-position: 90%; -fx-opacity:0;");
					if(board.getTowers()[i].getFloors()[j].getSingleActionSpace().isBusy()) {
					String color = board.getTowers()[i].getFloors()[j].getSingleActionSpace().getFamilyMemberLocated().getOwnerPlayer().getPlayerColor().toString();
				    placebutton.setStyle("-fx-background-color:"+color+"; -fx-opacity:0.3;");
					} else {
					placebutton.setStyle("-fx-background-color: white; -fx-opacity:0.3; ");
					}
				}
			}
			
		}
		
		//setto la personalboard
		//pl1.setText(players.get(0).getName());
		//pl2.setText(players.get(1).getName());

		for (int i = 0; i < players.size(); i++) {
			Player currPlayer = players.get(i);
			ArrayList<ToggleGroup> currtab = tabs.get(i);
			for (int k = 0; k < currtab.size(); k++) {
				ToggleGroup currToggleGroup =  currtab.get(k);
				OwnedCards currcards = currPlayer.getMyPersonalBoard().getSpecificOwnedCards(DevCardType.geType(k));
					for (int j = 0; j < 6; j++) {
						ToggleButton toggleButton = (ToggleButton) currToggleGroup.getToggles().get(j);
							if(currcards.getMyOwnedCards()[j].getCard()!=null) {
							String idmycard = currcards.getMyOwnedCards()[j].getCard().getID();
							toggleButton.setStyle
							("-fx-background-image: url('/devcards/devcards_f_en_c_"+idmycard+".png');  -fx-background-size: 70px; -fx-background-repeat: no-repeat; -fx-background-position: 90%; -fx-opacity: 1;");
							} else {
								toggleButton.setStyle
								("-fx-background-image: url('/devcards/whitecard.png');  -fx-background-size: 70px; -fx-background-repeat: no-repeat; -fx-background-position: 90%; -fx-opacity:0;");
								
							}
			
					}
			}
		}

		//resources
		for (int i = 0; i < players.size(); i++) {
		

		}
		
	}
	
	public void ifChooseAction(boolean firstaction, String description) {
		//gli mostro la conferma
		if(firstaction) {
		System.out.println("è il tuo turno, sono nella chooseaction");
		state.setText("It's your turn!");	
		} else {
			Platform.runLater(new Runnable() {
			    @Override
			    public void run() {
			    	Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Invalid Action!");
					alert.setContentText(description);
					alert.showAndWait();
			    }
			});
		
		}	
		canGo = true;
		return;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("default initialize!");
		Music.start();
		messThread = new MessThread(client, this);
        messThread.start();
        // creazione arraylist di togglegroup per ogni tab del tabPane
        ArrayList<ToggleGroup> tab1 = new ArrayList<>();
    	ArrayList<ToggleGroup> tab2 = new ArrayList<>();
        tab1.addAll(Arrays.asList(myterritory,mycharacheter,mybuilding,myventure));
        tab2.addAll(Arrays.asList(x3,x7,x4,x6));
        tabs.add(tab1);
        tabs.add(tab2);
        ArrayList<Text> res1 = new ArrayList<>();
        ArrayList<Text> res2 = new ArrayList<>();
        res1.addAll(Arrays.asList(r1,r2,r3,r4,r5,r6,r7));
        
	}
	
	

	
}
