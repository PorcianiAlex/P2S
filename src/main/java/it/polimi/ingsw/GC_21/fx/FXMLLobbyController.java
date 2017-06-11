package it.polimi.ingsw.GC_21.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

public class FXMLLobbyController extends MetaController {
	
	@FXML private Text actiontarget;
	@FXML private javafx.scene.control.TextField numberofmatch;

	@FXML
    public void initialize() {
        actiontarget.setText("inizializzazione bla \n bla \n blaaa");
       
    }

	
	  @FXML protected void Create(ActionEvent event) throws Exception {
	    	client.sendGUI("C");
	    	
	    }
	  @FXML protected void Join(ActionEvent event) throws Exception {
	    	if("".equals(numberofmatch.getText())) {
	    		Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error");
    			alert.setHeaderText("you didn't choose the number of match you want to join!");
    			alert.setContentText(null);
    			alert.showAndWait();
	    		return;
	    	} else {
	    		String num = numberofmatch.getText();
	    		System.out.println(num);
	    		client.sendGUI(num);
	    	}
	    }


}
