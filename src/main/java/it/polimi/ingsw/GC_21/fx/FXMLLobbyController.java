package it.polimi.ingsw.GC_21.fx;

import java.io.IOException;

import it.polimi.ingsw.GC_21.CLIENT.CheckLobbyMessage;
import it.polimi.ingsw.GC_21.CLIENT.CheckLoginMessage;
import it.polimi.ingsw.GC_21.CLIENT.MessageToClient;
import it.polimi.ingsw.GC_21.VIEW.LobbyInput;
import it.polimi.ingsw.GC_21.VIEW.LoginInput;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class FXMLLobbyController extends MetaController {
	

	
	@FXML private TextFlow texttarget;
	@FXML private javafx.scene.control.TextField numberofmatch;

	@FXML
    public void initialize() {
		System.out.println("iniz lobby");
		
		for (int i = 0; i < games.size(); i++) {
		 Text text1 = new Text(games.get(i)+"\n");
		 texttarget.getChildren().add(text1);
		 }
       
    }

	
	  @FXML protected void Create(ActionEvent event) throws IOException  {
	    	System.out.println("crea premuto");
	    	// mcrea e manda Lobby con C e 0 join
	    	this.openColorScene(true, 0);
	 
	    }
	  
	  @FXML protected void Join(ActionEvent event) throws IOException  {
	    	
	    
		  	int num = Integer.parseInt(numberofmatch.getText());
	    		
	    	this.openColorScene(false, num);
	    }
	  
	  public void openColorScene(boolean create, int joined) throws IOException {
		  	
		    LobbyInput lobbyInput = new LobbyInput(create, joined);
	    	client2.sendInput(lobbyInput);
	    	CheckLobbyMessage inputmessage = (CheckLobbyMessage) client2.getReceivedMessage();
		  	System.out.println(inputmessage.getDescription());
		  	
		  	if(!inputmessage.isResult()) {
	    		Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error");
    			alert.setHeaderText(inputmessage.getDescription());
    			alert.setContentText(null);
    			alert.showAndWait();
	    		return;
		  	} 
	    	
		  	Stage stage = (Stage) numberofmatch.getScene().getWindow();
	        FXMLColor fxmlColor = new FXMLColor();
	        try {
				fxmlColor.start(stage);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}


}
