package it.polimi.ingsw.GC_21.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXMLLobbyController extends MetaController {
	
	@FXML private Text texttarget;
	@FXML private javafx.scene.control.TextField numberofmatch;

	@FXML
    public void initialize() {
        System.out.println("inizializzazione?!");
		//texttarget.setText(client.getMessage());
       
    }

	
	  @FXML protected void Create(ActionEvent event) throws Exception {
	    	System.out.println("crea premuto");
	    	// mcrea e manda Lobby con C e 0 join
	    	this.openColorScene();
	 
	    }
	  
	  @FXML protected void Join(ActionEvent event) throws Exception {
	    	/*if(join non nel campo dei possibili join oppure vuuoto) {
	    		Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error");
    			alert.setHeaderText("you didn't choose the number of match you want to join!");
    			alert.setContentText(null);
    			alert.showAndWait();
	    		return;
	    	} else {
	    		String num = numberofmatch.getText();
	    		crea e manda lobby senza C e con join = num
	    		this.openColorScene();

	    	}*/
	    	this.openColorScene();
	    }
	  
	  public void openColorScene() {
		  Stage stage = (Stage) numberofmatch.getScene().getWindow();
	        FXMLColor fxmlColor = new FXMLColor();
	        try {
				fxmlColor.start(stage);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}


}
