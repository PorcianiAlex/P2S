package it.polimi.ingsw.GC_21.fx;

import java.awt.Button;

import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.CLIENT.CheckColorMessage;
import it.polimi.ingsw.GC_21.CONTROLLER.ControllerForm;
import it.polimi.ingsw.GC_21.VIEW.CreatePlayerInput;
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

	private Color colorplayer;
	
	@FXML private Text texttarget;
	@FXML private Text welcometext;
	@FXML private ToggleGroup place;
	@FXML private ToggleButton blue;
	@FXML private ToggleButton red;
	@FXML private ToggleButton yellow;
	@FXML private ToggleButton green;
	
	@FXML
    public void initialize() {
        System.out.println("inizializzazione schermata colore");
        green.setAccessibleText(Color.Green.toString());
        blue.setAccessibleText(Color.Blue.toString());
        yellow.setAccessibleText(Color.Yellow.toString());
        red.setAccessibleText(Color.Red.toString());

		//texttarget.setText(client.getMessage());
    }
	

	 @FXML protected void Ready(ActionEvent event){
	   //  client.sendGUI("start"); //se sei l'host fa partire effettivamente la partita altrimenti ti fa andare sulla nuova schrmata senza eseguire il gioco	     
		gameScene();
		/* button = (ToggleButton) place.getSelectedToggle();
		 colorplayer = Color.valueOf(button.getAccessibleText());
		 CreatePlayerInput createPlayerInput = new  CreatePlayerInput(colorplayer);
		 client2.setInputToSend(createPlayerInput);
		 CheckColorMessage checkColorMessage = (CheckColorMessage) client.getReceivedMessage();
		 if (!checkColorMessage.isResult()) {
			this.popup();
			return;
		} else if (checkColorMessage.isResult() && "attendo lo start".equals(checkColorMessage.getDescription())) {
			System.out.println(checkColorMessage.getDescription());
			return;
		} else {
	     this.gameScene();
		} */
	 }
	 
	 
	public void gameScene() {
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
