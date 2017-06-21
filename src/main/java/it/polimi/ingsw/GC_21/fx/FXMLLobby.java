package it.polimi.ingsw.GC_21.fx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLLobby extends Application {		
	
    @Override
    public void start(Stage stage)  {
      
		try {
	        Parent root = FXMLLoader.load(getClass().getResource("fxml_Lobby.fxml"));
			stage.setTitle("Lorenzo's Lobby");
			stage.setScene(new Scene(root, 530, 375));
	        stage.centerOnScreen();
			stage.show();
        } catch (IOException e) {
			e.printStackTrace();
		}
        
    }
}
