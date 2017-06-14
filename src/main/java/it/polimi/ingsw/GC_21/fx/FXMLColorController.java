package it.polimi.ingsw.GC_21.fx;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXMLColorController extends MetaController {

	@FXML private Text texttarget;
	@FXML private Text welcometext;
	
	@FXML
    public void initialize() {
        System.out.println("inizializzazione");
		//texttarget.setText(client.getMessage());
       
    }
	
	 @FXML protected void Blue(ActionEvent event)  {
		 //setta blu e manda
	 }
	 @FXML protected void Red(ActionEvent event)  {
		// client.sendGUI("2");	     
    }
	 @FXML protected void Yellow(ActionEvent event){
	    // client.sendGUI("3");	     
    }
	 @FXML protected void Green(ActionEvent event){
	    // client.sendGUI("4");	     
    }
	 
	 @FXML protected void Ready(ActionEvent event){
	   //  client.sendGUI("start"); //se sei l'host fa partire effettivamente la partita altrimenti ti fa andare sulla nuova schrmata senza eseguire il gioco	     
	     //nuova scena
	     Stage stage = (Stage) welcometext.getScene().getWindow();
	        FXMLGame fxmlGame = new FXMLGame();
	        try {
				fxmlGame.start(stage);
			} catch (Exception e) {
				e.printStackTrace();
			}
	 } 
	
	 
	public void popup() {
		String mess = client.getMessage();
		if(mess.equals("Oh grullo! This color is already in use, choose another one, please!")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(mess);
			alert.setContentText(null);
			alert.showAndWait();
		}
		
	} 
	
	
}
