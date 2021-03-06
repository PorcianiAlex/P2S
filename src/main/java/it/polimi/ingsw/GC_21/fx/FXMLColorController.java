package it.polimi.ingsw.GC_21.fx;

import java.awt.Button;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.xml.ws.handler.MessageContext;

import it.polimi.ingsw.GC_21.CLIENT.CheckColorMessage;
import it.polimi.ingsw.GC_21.CLIENT.ChooseActionMessage;
import it.polimi.ingsw.GC_21.CLIENT.InitialTimerThread;
import it.polimi.ingsw.GC_21.CLIENT.MessageToClient;
import it.polimi.ingsw.GC_21.CONTROLLER.ControllerForm;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.VIEW.CreatePlayerInput;
import it.polimi.ingsw.GC_21.VIEW.InitGameInput;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.ColorInput;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXMLColorController extends MetaController {

	private Color colorplayer = null ;
	private boolean start;
	private ColorThread colorThread;
	private boolean host = false;
	InitialTimerThread initialTimerThread;
	
	@FXML private Text texttarget;
	@FXML private Text welcometext;
	@FXML private ToggleGroup place;
	@FXML private ToggleButton blue, black, red, yellow, green;
	@FXML private javafx.scene.control.Button ready;
	
	@FXML
    public void initialize() {
        System.out.println("inizializzazione schermata colore");
        green.setAccessibleText(Color.Green.toString());
        blue.setAccessibleText(Color.Blue.toString());
        yellow.setAccessibleText(Color.Yellow.toString());
        red.setAccessibleText(Color.Red.toString());
        black.setAccessibleText(Color.Black.toString());
        ready.setVisible(false);
        
        
    }
	

	 @FXML protected void Color(ActionEvent event) throws ClassNotFoundException, IOException{
		 ToggleButton button = (ToggleButton) place.getSelectedToggle();
		 colorplayer = Color.valueOf(button.getAccessibleText());
		 CreatePlayerInput createPlayerInput = new  CreatePlayerInput(colorplayer);
		 client.sendInput(createPlayerInput); 
		 CheckColorMessage checkColorMessage = null;
		 boolean go= false;
		 while (!go) {
			MessageToClient messageToClient = client.getReceivedMessage();
			if(messageToClient instanceof CheckColorMessage) {
				go=true;
				checkColorMessage = (CheckColorMessage) messageToClient;
			}
		 }
		 host = checkColorMessage.isHost();
		 if (!checkColorMessage.isResult()) {
			this.popup();
			return;
		} else {
		 	colorThread = new ColorThread(texttarget, client, this);
			colorThread.start();
			if(host) {
				texttarget.setText("waiting for other players...");
			}
	 }
	}
	 
	 @FXML public void Ready(ActionEvent event) throws ClassNotFoundException, IOException {
		 if(colorplayer!=null && host) {
			 initialTimerThread.interrupt();
			 InitGameInput initGameInput = new InitGameInput(true);
	        try {
				client.sendInput(initGameInput);
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		 } 
		 return;
	}
	 

	public synchronized void gameScene() {
		Stage stage = (Stage) welcometext.getScene().getWindow();
	        FXMLGame fxmlGame = new FXMLGame();
	        try {
				fxmlGame.start(stage);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	 
	public void popup() {
		
		
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("color already in use!");
			alert.setContentText(null);
			alert.showAndWait();
		
		
	}


	public void startTimer() {
		if (host) {
			System.out.println("timer partito");
			 initialTimerThread = new InitialTimerThread(client);
			 initialTimerThread.start();
			 ready.setVisible(true);
		}
		
	} 
	
	
}
