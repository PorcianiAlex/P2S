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
 
public class FXMLLoginController {
	 
	public static void factorySocket() {
		    try {
					String ip = InetAddress.getLocalHost().getHostAddress();
					System.out.println(ip);
					SocketClient client1 = new SocketClient(ip, 6620);
					client1.startClient();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e2) {
		            System.out.println("errore");
		        }
		    	        

		     }
		    
		    public static void factoryRmi() throws RemoteException, NotBoundException {
		    	Registry reg = LocateRegistry.getRegistry(8000);
		        ServerInterface srv = (ServerInterface) reg.lookup("server");          
		    	RmiClient client2 = new RmiClient();
		        srv.join(client2);
			}
	
	@FXML private Text actiontarget;
	@FXML private javafx.scene.control.TextField user;
	@FXML private javafx.scene.control.TextField passwordField;
    

	
    @FXML protected void handleSignInAction(ActionEvent event) throws Exception {
    	String username = user.getText();
    	actiontarget.setText(username);
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
    
    
    protected void connect(ActionEvent event) {
       //apre la connessione scelta
    	//testa il login o reg 
    	//se tutto va a buon fine apre la nuova scena
    }
    
    

}
