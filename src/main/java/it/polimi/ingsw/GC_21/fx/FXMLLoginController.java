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

import org.omg.PortableInterceptor.USER_EXCEPTION;

import it.polimi.ingsw.GC_21.CLIENT.RmiClient;
import it.polimi.ingsw.GC_21.CLIENT.SocketClient;
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
	
	
	@FXML private Text actiontarget;
	@FXML private javafx.scene.control.TextField user;
	@FXML private javafx.scene.control.TextField passwordField;
	@FXML private RadioButton RMI;
	@FXML private RadioButton SOCKET;
	@FXML private ToggleGroup connect;
    
	
    @FXML protected void handleSignInAction(ActionEvent event) throws Exception {
    	this.connect("2");
    }
    
    @FXML protected void handleSignUpAction(ActionEvent event) throws Exception {
    	this.connect("1");
    	
    }
    
    
    protected void connect(String inorup) throws RemoteException, NotBoundException {
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
    	
    	client.sendGUI(inorup);
    	client.sendGUI(username);
    	client.sendGUI(pass);
    	
    	while(true) {
    	String arrivingmess = client.getMessage();
    		if(arrivingmess.equals("these username and password doesn't exist!") ||
    				arrivingmess.equals("Oh grullo! tu sei già loggato!") ||
    				arrivingmess.equals("this username already exists!")) {
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Login Error");
    			alert.setHeaderText(arrivingmess);
    			alert.setContentText("Ooops, there was an error!");

    			alert.showAndWait();
    			return;
    		}
    		if(arrivingmess.equals("Hi "+username+", welcome to our Lobby!")) {
    			break;
    		}
    	}
    	
    	Stage stage = (Stage) user.getScene().getWindow();
        FXMLLobby fxmlLobby = new FXMLLobby();
        try {
			fxmlLobby.start(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    

}
