package it.polimi.ingsw.GC_21.fx;

 
import java.awt.TextField;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class FXMLLoginController extends MetaController {
	
	
	@FXML private Text actiontarget;
	@FXML private javafx.scene.control.TextField user;
	@FXML private javafx.scene.control.TextField passwordField;
    
	
    @FXML protected void handleSignInAction(ActionEvent event) throws Exception {
    	this.connect();
    	Stage stage = (Stage) actiontarget.getScene().getWindow();
        FXMLLobby fxmlLobby = new FXMLLobby();
        //fxmlLobby.start(stage);
    }
    
    @FXML protected void handleSignUpAction(ActionEvent event) throws Exception {
    	String username = user.getText();
    	actiontarget.setText(username);
    	Stage stage = (Stage) actiontarget.getScene().getWindow();
        FXMLLobby fxmlLobby = new FXMLLobby();
        fxmlLobby.start(stage);
    }
    
    
    protected void connect() throws RemoteException, NotBoundException {
       //apre la connessione scelta
    	//testa il login o reg 
    	//se tutto va a buon fine apre la nuova scena
    	factoryRmi();
    	
    	client.sendGUI("2");
    	
    	String username = user.getText();
    	String pass = passwordField.getText();
    	
    	client.sendGUI(username);
    	client.sendGUI(pass);
    }
    
    

}
