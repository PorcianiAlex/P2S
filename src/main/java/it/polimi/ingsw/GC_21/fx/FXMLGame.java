package it.polimi.ingsw.GC_21.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLGame extends Application {
	 @Override
	    public void start(Stage stage) throws Exception {
	      
	    	Parent root = FXMLLoader.load(getClass().getResource("fxml_Game.fxml"));
	        stage.setTitle("Game");
	        stage.setScene(new Scene(root));
	        stage.setFullScreen (true);
	        stage.show();
	    }
}