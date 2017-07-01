package it.polimi.ingsw.GC_21.fx;

import java.awt.Button;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.List;
import java.awt.ScrollPaneAdjustable;
import java.awt.Scrollbar;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Currency;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.jar.Attributes.Name;

import javax.naming.spi.DirStateFactory.Result;
import javax.sound.midi.Soundbank;
import javax.swing.ButtonGroup;
import javax.swing.Scrollable;
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
import it.polimi.ingsw.GC_21.CLIENT.TimerThread;
import it.polimi.ingsw.GC_21.CLIENT.TurnMessage;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.ExcommunicationCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.LeaderCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.OncePerTurnLeaderCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.VIEW.ConvertInput;
import it.polimi.ingsw.GC_21.VIEW.CouncilPlacementInput;
import it.polimi.ingsw.GC_21.VIEW.CraftInput;
import it.polimi.ingsw.GC_21.VIEW.CraftPlacementInput;
import it.polimi.ingsw.GC_21.VIEW.DiscardInput;
import it.polimi.ingsw.GC_21.VIEW.ExcommInput;
import it.polimi.ingsw.GC_21.VIEW.InitGameInput;
import it.polimi.ingsw.GC_21.VIEW.InputForm;
import it.polimi.ingsw.GC_21.VIEW.LeaderInput;
import it.polimi.ingsw.GC_21.VIEW.MarketPlacementInput;
import it.polimi.ingsw.GC_21.VIEW.PassInput;
import it.polimi.ingsw.GC_21.VIEW.PlacementInput;
import it.polimi.ingsw.GC_21.VIEW.PrivilegeInput;
import it.polimi.ingsw.GC_21.VIEW.SetFamilyMemberInput;
import it.polimi.ingsw.GC_21.VIEW.TakeCardInput;
import it.polimi.ingsw.GC_21.VIEW.TowerPlacementInput;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.ValueAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Toggle;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;


import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXMLGameController extends MetaController implements Initializable{

	private FamilyMemberColor familyMemberColor;
	private int servToConvert = 0;
	private PlacementInput placementinputForm;
	private InputForm inputForm;
	private Board classBoard;
	private ArrayList<Player> classPlayers;
	private Player myPlayer;
	private MessThread messThread;
	private boolean canGo, secondcard = false;
	private Object LOCK = new Object(); // just something to lock on
    private ArrayList<ArrayList<ToggleGroup>> tabs = new ArrayList<ArrayList<ToggleGroup>>();
    private ArrayList<ToggleGroup> leaders = new ArrayList<ToggleGroup>();
    private ArrayList<ArrayList<Text>> ress = new ArrayList<ArrayList<Text>>();
    private ArrayList<Tab> playerNames = new ArrayList<Tab>();
    private TimerThread timerThread;

	//riferimento ad array di carte, dadi, risorse e player
	@FXML private ToggleGroup cards, place, excomm, family, myterritory, mybuilding, myventure, myleader, mycharacheter, x3,x4,x5,x6,x7,x12,x14,x15,x16,x13,x20,x23,x21,x22,x24;
	@FXML private Text whitedice, blackdice, orangedice, state, turntext;
	@FXML private Text servconverting, r1,r2,r3,r4,r5,r6,r7,r8, r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28;
	@FXML private Tab pl1,pl2,pl3,pl4;
	@FXML private javafx.scene.control.Button confirmbtn;
	@FXML private ToggleButton white, black, orange, neutral;
	@FXML private AnchorPane anchorPane; 
	
	 @FXML protected void Tower(ActionEvent event) {
			 
		 ToggleButton button = (ToggleButton) place.getSelectedToggle();
		 DevCardType devCardType =  DevCardType.valueOf(button.getUserData().toString());
		 int floor = Integer.parseInt(button.getText());
		 System.out.println(button.getId());
		 System.out.println( devCardType );
		 System.out.println( floor);

		 //settare la input con il giusto costruttore
		 placementinputForm = new TowerPlacementInput(devCardType, floor);
		 		 
	 }
	 
	 @FXML protected void Council(ActionEvent event) {
		 
		placementinputForm = new CouncilPlacementInput();

	 }
	 
	 @FXML protected void Market(ActionEvent event) {
		 
		 ToggleButton button = (ToggleButton) place.getSelectedToggle();
		 int area = Integer.parseInt(button.getText());
		 System.out.println(area);
		 
		placementinputForm = new MarketPlacementInput(area);

		 }
	 
	 @FXML protected void Craft(ActionEvent event) {
		 
		 ToggleButton button = (ToggleButton) place.getSelectedToggle();
		 CraftType craftType =  CraftType.valueOf(button.getUserData().toString());
		 int area = Integer.parseInt(button.getText());
		
		 System.out.println( craftType );
		 System.out.println( area);

		 placementinputForm = new CraftPlacementInput(craftType, area);
		 }
	 
	 	@FXML protected void FamilyMember(ActionEvent event) {
		 	 		
		 ToggleButton button = (ToggleButton) family.getSelectedToggle();
		 familyMemberColor = FamilyMemberColor.valueOf(button.getText());
		 System.out.println( familyMemberColor );
		 
		 }
	 
	 @FXML protected void Serv(ActionEvent event) {
		 javafx.scene.control.Button button = (javafx.scene.control.Button) event.getSource();
		 if(button.getText().equals("+") && servToConvert < myPlayer.getMyPersonalBoard().getMyPossession().getServants().getValue()) {
		 this.servToConvert++;
		 } else if(button.getText().equals("-") && servToConvert > 0){
		 this.servToConvert--;
		}
		 servconverting.setText(String.valueOf(servToConvert));
	 }
	 
	 @FXML protected void Reset(ActionEvent event) {
		
		 	familyMemberColor=null;
			servToConvert=0;
			servconverting.setText(String.valueOf(servToConvert));
			placementinputForm=null;
			for (int i = 0; i < family.getToggles().size(); i++) {
				family.getToggles().get(i).setSelected(false);
			}
			for (int i = 0; i < place.getToggles().size(); i++) {
				place.getToggles().get(i).setSelected(false);
			}
			
	 }
	 
	 @FXML protected void Pass(ActionEvent event) {
		 if(timerThread!=null){	
			 timerThread.interrupt();
			 }
		 PassInput passInput = new PassInput();
		 try {
			client.sendInput(passInput);
			turntext.setText(" ");

		} catch (IOException e) {
			e.printStackTrace();
		}
		 canGo = false;
	 }
	 
	 @FXML protected void Confirm(ActionEvent event) throws RemoteException, IOException {
		if(secondcard) {
			synchronized (LOCK) {
			    LOCK.notifyAll();
			}
			secondcard = false;
		}
		else if(canGo && familyMemberColor!=null && placementinputForm!=null) {
		
		placementinputForm.setFamilyMemberColor(familyMemberColor);
		placementinputForm.setServantsToConvert(servToConvert);
		client.sendInput(placementinputForm);
		System.out.println("action send");
		turntext.setText(" ");
		canGo=false;
		} else if(!canGo){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("It's not your turn!");
			alert.setContentText("wait your turn please");
			alert.showAndWait();
		}
		 if(timerThread!=null){	
			 timerThread.interrupt();
			 }
		this.setFamilyButton();
		return;
	 }
	 
	 @FXML protected void Card(ActionEvent event) {
		 ToggleButton button = (ToggleButton) event.getSource();
		 
		 if(button.getBackground()!=null) {
		 BackgroundImage imageView = button.getBackground().getImages().get(0);
		 Image image = imageView.getImage();
		 
		 Platform.runLater(new Runnable() {
			    @Override
			    public void run() {
			    	Alert alert = new Alert(AlertType.ERROR);
			    	alert.setTitle("Zoom on card");
			    	alert.setHeaderText(null);
			    	alert.setContentText(null);
			    	ImageView imageView = new ImageView(image);
			    	imageView.setFitHeight(400);
			    	imageView.setFitWidth(250);
			    	alert.setGraphic(imageView);
			    	alert.showAndWait();
			    }
			});
		 }
		 
	 }
	 
	 @FXML protected void discardLeader(ActionEvent event) {
		 	
		 	javafx.scene.control.Button button = (javafx.scene.control.Button) event.getSource();
			ArrayList<String> choices = new ArrayList<String>();
		
			if(button.getText().equals("Play Leader")) {
			for (int j = 0; j < myPlayer.getMyPersonalBoard().getLeaderCards().size(); j++) {
				if(myPlayer.getMyPersonalBoard().getLeaderCards().get(j).isPlayed() && myPlayer.getMyPersonalBoard().getLeaderCards().get(j) instanceof OncePerTurnLeaderCard) {
				choices.add(myPlayer.getMyPersonalBoard().getLeaderCards().get(j).getName());
				}
			}
			} else {
			for (int j = 0; j < myPlayer.getMyPersonalBoard().getLeaderCards().size(); j++) {
				if(!myPlayer.getMyPersonalBoard().getLeaderCards().get(j).isPlayed()) {
				choices.add(myPlayer.getMyPersonalBoard().getLeaderCards().get(j).getName());
				}
			}
			}
		
			if(choices.size()==0) {
				Platform.runLater(new Runnable() {
				    @Override
				    public void run() {
				    	Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error");
						alert.setHeaderText("Invalid Action!");
						alert.showAndWait();
				    }
				});
			}
			
			Platform.runLater(new Runnable() {
			    @Override
			    public void run() {
			    	ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
			    	dialog.setTitle("Leaders");
			    	dialog.setHeaderText(button.getText());
			    	dialog.setContentText("Choose which one: ");
			    	Optional<String> result = dialog.showAndWait();
			    	int j;
			    	if(result.get()==null) {
			    		return;
			    	}
			    	for ( j= 0; j < myPlayer.getMyPersonalBoard().getLeaderCards().size(); j++) {
						if(result.get().equals(myPlayer.getMyPersonalBoard().getLeaderCards().get(j).getName())) 
						{break;}
					}
			    	InputForm inputForm;
			    	switch (button.getText()) {
					case "Discard Leader":
				    	 inputForm = new DiscardInput(myPlayer, j);
						break;
					case "Activate Leader":
				    	 inputForm = new LeaderInput(myPlayer,String.valueOf(j+1),true);
						break;
					case "Play Leader":
				    	 inputForm = new LeaderInput(myPlayer,String.valueOf(j+1),false);
						break;
					default: System.out.println("nome del bottone non coincide!");
						return;
					}
			    	try {
			    		client.sendInput(inputForm);
			    	} catch (IOException e) {
			    		e.printStackTrace();
			    	}   
			    }
			});
			
		 
	 }
	 
	

	public void refreshBoard(Board board, ArrayList<Player> players, String dString) {
		//update my player
		for (int i = 0; i < players.size(); i++) {
			if(myPlayer!=null && myPlayer.getName().equals(players.get(i).getName())) {
				myPlayer=players.get(i);
			}
		}
		
		System.out.println("sono nella refreshboard");
		classBoard = board;
		classPlayers = players;
		//gamemanagement
		state.setText(dString);
		//dadi:
		blackdice.setText(String.valueOf(board.getDices()[0].getValue()));
		whitedice.setText(String.valueOf(board.getDices()[1].getValue()));	
		orangedice.setText(String.valueOf(board.getDices()[2].getValue()));
		//excomcards
		for (int i = 0; i < 3; i++) {
			String exid = board.getExcommunicationCards()[i].getID();
			ToggleButton toggleExcomm =  (ToggleButton) excomm.getToggles().get(i);
			toggleExcomm.setStyle
			("-fx-background-image: url('/components/excomm_"+exid+".png');  -fx-background-size: 40px; -fx-background-repeat: no-repeat; -fx-background-position: 90%; -fx-opacity: 1;");
		}
		//towers
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				ToggleButton toggleButton =  (ToggleButton) cards.getToggles().get(j+4*i);
				ToggleButton placebutton =  (ToggleButton) place.getToggles().get(j+4*i);
				if(board.getTowers()[i].getFloors()[j].getDevCardPlace().getCard()!=null) {
				String idcard = board.getTowers()[i].getFloors()[j].getDevCardPlace().getCard().getID();		
				toggleButton.setStyle
				("-fx-background-image: url('/devcards/devcards_f_en_c_"+idcard+".png');  -fx-background-size: 70px; -fx-background-repeat: no-repeat; -fx-background-position: 90%; -fx-opacity: 1;");
				} else {
					toggleButton.setStyle
					("-fx-background-image: url('/devcards/whitecard.png');  -fx-background-size: 70px; -fx-background-repeat: no-repeat; -fx-background-position: 90%; -fx-opacity:0;");
				}
				
				if(board.getTowers()[i].getFloors()[j].getSingleActionSpace().isBusy()) {
					String color = board.getTowers()[i].getFloors()[j].getSingleActionSpace().getFamilyMemberLocated().getOwnerPlayer().getPlayerColor().toString();
					String famcolor = board.getTowers()[i].getFloors()[j].getSingleActionSpace().getFamilyMemberLocated().getAssociatedDice().getdiceColor().toString();
					placebutton.setStyle(" -fx-background-image: url('/familymembers/"+color+famcolor+".png'); -fx-background-size: 35px; -fx-background-repeat: no-repeat; -fx-background-position: 100%; -fx-opacity:1; -fx-background-color: transparent;");
					} else {
					placebutton.setStyle(" -fx-background-color: transparent; -fx-opacity:1;");
					}
				}			
		}
		
		//refresh market
		for (int i = 0; i < 4; i++) {
			ToggleButton placebutton =  (ToggleButton) place.getToggles().get(20+i);
			if(board.getMarketArea().getSingleActionSpace()[i].isBusy()) {
			String color = board.getMarketArea().getSingleActionSpace()[i].getFamilyMemberLocated().getOwnerPlayer().getPlayerColor().toString();
			String famcolor = board.getMarketArea().getSingleActionSpace()[i].getFamilyMemberLocated().getAssociatedDice().getdiceColor().toString();
			placebutton.setStyle(" -fx-background-image: url('/familymembers/"+color+famcolor+".png'); -fx-background-size: 35px; -fx-background-repeat: no-repeat; -fx-background-position: 100%; -fx-opacity:1; -fx-background-color: transparent;");
			} else {
				placebutton.setStyle(" -fx-background-color: transparent; -fx-opacity:1;");
			}
		}
		//refresh craft area
				ToggleButton harvestbtn =  (ToggleButton) place.getToggles().get(19);
				if(board.getHarvestArea().getSingleActionSpace().isBusy()) {
					String color = board.getHarvestArea().getSingleActionSpace().getFamilyMemberLocated().getOwnerPlayer().getPlayerColor().toString();
					String famcolor = board.getHarvestArea().getSingleActionSpace().getFamilyMemberLocated().getAssociatedDice().getdiceColor().toString();
					harvestbtn.setStyle(" -fx-background-image: url('/familymembers/"+color+famcolor+".png'); -fx-background-size: 35px; -fx-background-repeat: no-repeat; -fx-background-position: 100%; -fx-opacity:1; -fx-background-color: transparent;");
				}else {
					harvestbtn.setStyle(" -fx-background-color: transparent; -fx-opacity:1;");
				}
				ToggleButton prodbtn =  (ToggleButton) place.getToggles().get(18);
				if(board.getProductionArea().getSingleActionSpace().isBusy()) {
					String color = board.getProductionArea().getSingleActionSpace().getFamilyMemberLocated().getOwnerPlayer().getPlayerColor().toString();
					String famcolor = board.getProductionArea().getSingleActionSpace().getFamilyMemberLocated().getAssociatedDice().getdiceColor().toString();
					prodbtn.setStyle(" -fx-background-image: url('/familymembers/"+color+famcolor+".png'); -fx-background-size: 35px; -fx-background-repeat: no-repeat; -fx-background-position: 100%; -fx-opacity:1; -fx-background-color: transparent;");
				}else {
					prodbtn.setStyle(" -fx-background-color: transparent; -fx-opacity:1;");
				}
				
			
				
		
		for (int i = 0; i < players.size(); i++) {
			String name = players.get(i).getName();
			Tab currtab = playerNames.get(i);
			Platform.runLater(new Runnable() {
			    @Override
			    public void run() {
			    	currtab.setText(name);
			    }
			   });
		}
				
		//personalboards refresh
		
		for (int i = 0; i < players.size(); i++) {
			Player currPlayer = players.get(i);
			ArrayList<Text> currress = ress.get(i);
			currress.get(0).setText(String.valueOf(currPlayer.getMyPersonalBoard().getMyPossession().getCoins().getValue()));
			System.out.println(String.valueOf(currPlayer.getMyPersonalBoard().getMyPossession().getCoins().getValue()));
			currress.get(1).setText(String.valueOf(currPlayer.getMyPersonalBoard().getMyPossession().getWoods().getValue()));
			currress.get(2).setText(String.valueOf(currPlayer.getMyPersonalBoard().getMyPossession().getStones().getValue()));
			currress.get(3).setText(String.valueOf(currPlayer.getMyPersonalBoard().getMyPossession().getServants().getValue()));
			currress.get(4).setText("VP: "+String.valueOf(currPlayer.getMyPersonalBoard().getMyPossession().getVictoryPoints().getValue()));
			currress.get(5).setText("MP: "+String.valueOf(currPlayer.getMyPersonalBoard().getMyPossession().getMilitaryPoints().getValue()));
			currress.get(6).setText("FP: "+String.valueOf(currPlayer.getMyPersonalBoard().getMyPossession().getFaithPoints().getValue()));		
			
			ToggleGroup currLeadersGroup = leaders.get(i);
			for (int j = 0; j < 4; j++) {
				ArrayList<LeaderCard> currcardleaders = currPlayer.getMyPersonalBoard().getLeaderCards();
				ToggleButton currleader = (ToggleButton) currLeadersGroup.getToggles().get(j);
				if(j < currPlayer.getMyPersonalBoard().getLeaderCards().size()) {
					String idleadercard = currPlayer.getMyPersonalBoard().getLeaderCards().get(j).getID();
					currleader.setStyle
					("-fx-background-image: url('/leadercards/leaders_f_c_"+idleadercard+".jpg');  -fx-background-size: 70px; -fx-background-repeat: no-repeat; -fx-background-position: 90%; -fx-opacity: 1;");
					} else {
						currleader.setStyle
						("-fx-background-image: url('/devcards/whitecard.png');  -fx-background-size: 70px; -fx-background-repeat: no-repeat; -fx-background-position: 90%; -fx-opacity:0;");
						
					}
			}
			
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
	
		
	}
	
	public void setFamilyButton() {
		//set family member placed
				for (int i = 0; i < 4; i++) {
					ToggleButton tButton = (ToggleButton) family.getToggles().get(i);
					if(myPlayer.getFamilyMembers()[i].isPlaced()) {
						tButton.setVisible(false);
						tButton.setOpacity(0.0);
					} else {
						tButton.setVisible(true);
						tButton.setOpacity(1);
					}
				}
	}
	
	  public void ifChooseAction(boolean firstaction, String description, Player player, TimerThread timerThread) { 
		myPlayer = player;
	    this.timerThread=timerThread; 

		//start timer animation
		//set family button
		ToggleButton black = (ToggleButton) family.getToggles().get(0);
        black.setStyle(" -fx-background-image: url('/familymembers/"+myPlayer.getPlayerColor()+"Black.png'); -fx-background-size: 35px; -fx-background-repeat: no-repeat; -fx-background-position: 100%; -fx-background-color: transparent;");
        ToggleButton white = (ToggleButton) family.getToggles().get(1);
        white.setStyle(" -fx-background-image: url('/familymembers/"+myPlayer.getPlayerColor()+"White.png'); -fx-background-size: 35px; -fx-background-repeat: no-repeat; -fx-background-position: 100%; -fx-background-color: transparent;");
        ToggleButton orange = (ToggleButton) family.getToggles().get(2);
        orange.setStyle(" -fx-background-image: url('/familymembers/"+myPlayer.getPlayerColor()+"Orange.png'); -fx-background-size: 35px; -fx-background-repeat: no-repeat; -fx-background-position: 100%; -fx-background-color: transparent;");
        ToggleButton neutral = (ToggleButton) family.getToggles().get(3);
        neutral.setStyle(" -fx-background-image: url('/familymembers/"+myPlayer.getPlayerColor()+"Neutral.png'); -fx-background-size: 35px; -fx-background-repeat: no-repeat; -fx-background-position: 100%; -fx-background-color: transparent;");
		
        this.setFamilyButton();
        
		//gli mostro la conferma
		if(firstaction) {
		System.out.println("è il tuo turno, sono nella chooseaction");
		turntext.setText(player.getName()+", it's your turn!");	
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
    	ArrayList<ToggleGroup> tab3 = new ArrayList<>();
    	ArrayList<ToggleGroup> tab4 = new ArrayList<>();
        tab1.addAll(Arrays.asList(myterritory,mycharacheter,mybuilding,myventure));
        tab2.addAll(Arrays.asList(x3,x7,x4,x6));
        tab3.addAll(Arrays.asList(x12,x15,x16,x13));
        tab4.addAll(Arrays.asList(x20,x23,x21,x24));
        tabs.add(tab1);
        tabs.add(tab2);
        tabs.add(tab3);
        tabs.add(tab4);
        ArrayList<Text> res1 = new ArrayList<>();
        ArrayList<Text> res2 = new ArrayList<>();
        ArrayList<Text> res3 = new ArrayList<>();
        ArrayList<Text> res4 = new ArrayList<>();
        res1.addAll(Arrays.asList(r1,r2,r3,r4,r5,r6,r7));
        res2.addAll(Arrays.asList(r8,r9,r10,r11,r12,r13,r14));
        res3.addAll(Arrays.asList(r15,r16,r17,r18,r19,r20,r21));
        res3.addAll(Arrays.asList(r22,r23,r24,r25,r26,r27,r28));
        ress.add(res1); 
        ress.add(res2);
        ress.add(res3);
        ress.add(res4);
        leaders.addAll(Arrays.asList(myleader,x5,x14,x22));
        playerNames.addAll(Arrays.asList(pl1,pl2,pl3,pl4));
        
	}

	//interactions
	
	public void Privilege(PrivilegeInput privilegeInput) {
		
		ArrayList<String> choices = new ArrayList<String>();
		choices.add("1 Wood and 1 stone");
		choices.add("2 Servants");
		choices.add("2 Coins");
		choices.add("2 Military points");
		choices.add("1 Faith point");

		
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
		    	dialog.setTitle("New Privilege!");
		    	dialog.setHeaderText("Oh ciccio! You have a new Council Privilege!");
		    	dialog.setContentText("Choose your reward:");
		    	Optional<String> result = dialog.showAndWait();
		    	result.ifPresent(letter -> privilegeInput.setChoice(result.get()));
		    	try {
		    		client.sendInput(privilegeInput);
		    	} catch (IOException e) {
		    		e.printStackTrace();
		    	}   
		    }
		});
	 }

	public void takeNewCard(DevCardType devCardType, int actionValueInfluencer, Possession discount) {
		secondcard =true;
		
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Take new card");
				alert.setHeaderText("You can take another card!");
				alert.setContentText("Hey sgangherato, you can take another Card!!!\n"
						+ " Check your Card, you may have some restriction on Card Type and a discount\nJust choose your space on tower and press confirm\n"
						+ "Your new action value is: " + actionValueInfluencer);
				alert.showAndWait();
		    }    
		});
		
		synchronized (LOCK) {
				 try {
					LOCK.wait(); // wait confirm
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
				}
		
			ToggleButton button = (ToggleButton) place.getSelectedToggle();
		    DevCardType dType =  DevCardType.valueOf(button.getUserData().toString());
		    int floor = Integer.parseInt(button.getText());
		    if(devCardType != null) {
		    	dType=devCardType;
		    }
		    inputForm = new TakeCardInput(dType, actionValueInfluencer, discount, floor);
		    try {
				client.sendInput(inputForm);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void excomm(String description) {
		
	Platform.runLater(new Runnable(){ 
	@Override
	public void run() {
		ExcommInput excommInput;
	   	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Excommunication time");
		alert.setHeaderText(description);
		alert.setContentText("Choose your destiny:");

		ButtonType buttonTypeOne = new ButtonType("Yes");
		ButtonType buttonTypeTwo = new ButtonType("No");

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne){
		    excommInput = new ExcommInput("Y");
		} else {
			excommInput = new ExcommInput("N");
			 } 
		try {
			client.sendInput(excommInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
			});		
			
	}

	public void convertMessage(Possession toPay1, Possession toTake1, Possession toPay2, Possession toTake2) {

		ArrayList<String> choices = new ArrayList<String>();
		choices.add("pay: "+toPay1.toString()+" and gain: "+toTake1.toString());
		choices.add("pay: "+toPay2.toString()+" and gain: "+toTake2.toString());

		
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
		    	dialog.setTitle("Convert your resources!");
		    	dialog.setHeaderText("Oh grullo! You can choose resources to convert");
		    	dialog.setContentText("Choose your convert combination:");
		    	Optional<String> result = dialog.showAndWait();
		    	int j;
		    	if(result.get()==null) {
		    		return;
		    	}
		    	for ( j= 0; j < 2; j++) {
					if(result.get().equals(choices.get(j))){
					break;
					}
				}
		    	try {
		    		if(j==0) {
		    			client.sendInput(new ConvertInput(toPay1, toTake1, toPay2, toTake2, toPay1, toTake1));
		    		} else {
		    			client.sendInput(new ConvertInput(toPay1, toTake1, toPay2, toTake2, toPay2, toTake2));
					}
		    		
		    	} catch (IOException e) {
		    		e.printStackTrace();
		    	}
		    }
		});
		
		}

	public void setFamilyMemberLeader(int newFamilyMemberValue, Player player) {
		ArrayList<String> choices = new ArrayList<String>();
		choices.add("Black");
		choices.add("White");
		choices.add("Orange");
		choices.add("Neutral");

		
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
		    	dialog.setTitle("Set family member value");
		    	dialog.setHeaderText("One of your family member can be upgraded to value "
						+ newFamilyMemberValue);
		    	dialog.setContentText("Choose which one:");
		    	Optional<String> result = dialog.showAndWait();
		    	
		    	try {
		    		client.sendInput(new SetFamilyMemberInput(newFamilyMemberValue, player, FamilyMemberColor.valueOf(result.get())));
		    	} catch (IOException e) {
		    		e.printStackTrace();
		    	}
		    }
		});
	}

	public void craftMessage(CraftType craftType, int actionValue) {
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	TextInputDialog dialog = new TextInputDialog();
		    	dialog.setTitle("you can add servants");
		    	dialog.setHeaderText("You can make a craft with value " + actionValue + ", how many servant do you want to convert?");
		    	Optional<String> result = dialog.showAndWait();
			    	
		    	try {
		    		if(result.isPresent()) {
		    			client.sendInput(new CraftInput(craftType, actionValue, Integer.valueOf(result.get())));
		    		} else {
		    			client.sendInput(new CraftInput(craftType, actionValue, Integer.valueOf(result.get())));
					}
		    	} catch (IOException e) {
		    		e.printStackTrace();
		    	}
		    }
		
		});
	}
	
	
	

	
}
