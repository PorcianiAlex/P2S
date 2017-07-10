package it.polimi.ingsw.GC_21.fx;

 
import java.awt.Button;
import java.awt.TextField;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.channels.MulticastChannel;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Optional;

import org.omg.PortableInterceptor.USER_EXCEPTION;

import it.polimi.ingsw.GC_21.CLIENT.CheckLoginMessage;
import it.polimi.ingsw.GC_21.CLIENT.MessageToClient;
import it.polimi.ingsw.GC_21.CLIENT.RmiClient;
import it.polimi.ingsw.GC_21.CLIENT.SocketClient;
import it.polimi.ingsw.GC_21.CLIENT.StartMessage;
import it.polimi.ingsw.GC_21.VIEW.ExcommInput;
import it.polimi.ingsw.GC_21.VIEW.LobbyInput;
import it.polimi.ingsw.GC_21.VIEW.LoginInput;
import it.polimi.ingsw.GC_21.VIEW.ServerInterface;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class FXMLLoginController extends MetaController {

	private static boolean go = true;
	
	@FXML private Text welcometext;
	@FXML private Text actiontarget;
	@FXML private javafx.scene.control.TextField user;
	@FXML private javafx.scene.control.TextField passwordField;
	@FXML private RadioButton RMI;
	@FXML private RadioButton SOCKET;
	@FXML private ToggleGroup connect;
	@FXML private GridPane grid;

    
	
    @FXML protected void handleSignInAction(ActionEvent event) throws Exception {
    	this.connect(false);
    }
    
    @FXML protected void handleSignUpAction(ActionEvent event) throws Exception {
    	this.connect(true);
    	
    }
    
    
    protected void connect(boolean insert) throws NotBoundException, IOException, ClassNotFoundException {
       //apre la connessione scelta
    	//testa il login / reg 
  
    	if(connect.getSelectedToggle()==(RMI)) {
    	factoryRmi();
    	System.out.println("rmi");
    	} else if(connect.getSelectedToggle()==(SOCKET)){
    		factorySocket();
    		System.out.println("sck");
		} else {
			actiontarget.setText("You must choose connection!");
			return;
		}
    	
    	String username = user.getText();
    	String pass = passwordField.getText();
       	
    	// crea e manda loginInput con username, pass, inorup
    	
    	StartMessage logmess = (StartMessage) client.getReceivedMessage();
    	LoginInput loginInput = new LoginInput(username, pass, insert);
    	client.sendInput(loginInput);
    	CheckLoginMessage inputmessage = (CheckLoginMessage) client.getReceivedMessage();
    	//for reconnecting users..
    	if(inputmessage.isPossibleReconnection()) {
    		this.ReconnectUser(inputmessage);
    		return;
    	} 
    	//for saved games..
    	else if(inputmessage.isSavedGames()) {
    		this.SaveReconnection(inputmessage);
    		return;
		}
		
    	newLobby(inputmessage);
    	
    	
    }
    
    
    private void newLobby(CheckLoginMessage inputmessage) {
    	games = inputmessage.getGames();
    	System.out.println("from login:" + inputmessage.getDescription());
    		 if(!inputmessage.isResult()) {
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Login Error");
    			alert.setHeaderText(inputmessage.getDescription());
    			alert.setContentText("Ooops, there was an error!");
    			alert.showAndWait();
    			return;
    		}
    		 else {
    	Stage stage = (Stage) grid.getScene().getWindow();
        FXMLLobby fxmlLobby = new FXMLLobby();
        try {
			fxmlLobby.start(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
    		 }
	}

    public  void reconnect() {	
				Stage stage = (Stage) grid.getScene().getWindow();
				
				FXMLGame fxmlGame = new FXMLGame();
				try {
					fxmlGame.start(stage);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
		
    }
    
    public void ReconnectUser(CheckLoginMessage inputmessage) {

		Platform.runLater(new Runnable(){ 
			@Override
			public void run() {
				
			   	Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("You have a standby match!");
				alert.setHeaderText("You can reconnect to previous match or start a new game");
				alert.setContentText("Do you want to reconnect?");

				ButtonType buttonTypeOne = new ButtonType("Yes");
				ButtonType buttonTypeTwo = new ButtonType("No");

				alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == buttonTypeOne){
				    LobbyInput lobbyInput = new LobbyInput(true, false);
				    try {
						client.sendInput(lobbyInput);
						reconnect();
						return;
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else{
					if(inputmessage.isSavedGames())
					{
						SaveReconnection(inputmessage);
						return;
					} else {
				    	newLobby(inputmessage);
					}
				}
			}

			});	
    	
    }

   public void SaveReconnection(CheckLoginMessage inputmessage){
    	Platform.runLater(new Runnable(){ 
			@Override
			public void run() {
				
			   	Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("You have a saved match!");
				alert.setHeaderText("You can reload your saved match or start a new game");
				alert.setContentText("Do you want to reload it?");

				ButtonType buttonTypeOne = new ButtonType("Yes");
				ButtonType buttonTypeTwo = new ButtonType("No");

				alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == buttonTypeOne){
				    LobbyInput lobbyInput = new LobbyInput(false, true);
				    try {
						client.sendInput(lobbyInput);
						reconnect();
						return;
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else{ 
			    	newLobby(inputmessage);
				}
			}

			});	
	
    }
	
}
