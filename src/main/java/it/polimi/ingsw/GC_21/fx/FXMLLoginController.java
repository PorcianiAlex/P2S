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

import org.omg.PortableInterceptor.USER_EXCEPTION;

import it.polimi.ingsw.GC_21.CLIENT.CheckLoginMessage;
import it.polimi.ingsw.GC_21.CLIENT.MessageToClient;
import it.polimi.ingsw.GC_21.CLIENT.RmiClient;
import it.polimi.ingsw.GC_21.CLIENT.SocketClient;
import it.polimi.ingsw.GC_21.view.LoginInput;
import it.polimi.ingsw.GC_21.view.ServerInterface;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class FXMLLoginController extends MetaController {

	
	@FXML private Text welcometext;
	@FXML private Text actiontarget;
	@FXML private javafx.scene.control.TextField user;
	@FXML private javafx.scene.control.TextField passwordField;
	@FXML private RadioButton RMI;
	@FXML private RadioButton SOCKET;
	@FXML private ToggleGroup connect;
    
	
    @FXML protected void handleSignInAction(ActionEvent event) throws Exception {
    	this.connect(false);
    }
    
    @FXML protected void handleSignUpAction(ActionEvent event) throws Exception {
    	this.connect(true);
    	
    }
    
    
    protected void connect(boolean insert) throws RemoteException, NotBoundException {
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
    	MessageToClient logmess = client2.getReceivedMessage();
    	LoginInput loginInput = new LoginInput(username, pass, insert);
    	client2.setInputToSend(loginInput);
    	CheckLoginMessage inputmessage = (CheckLoginMessage) client2.getReceivedMessage();
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
    	Stage stage = (Stage) welcometext.getScene().getWindow();
        FXMLLobby fxmlLobby = new FXMLLobby();
        try {
			fxmlLobby.start(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
    
    }
    }

}
