package it.polimi.ingsw.GC_21.fx;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLGame extends Application {
	 @Override
	    public void start(Stage stage) throws Exception {
	      
	    	Parent root = FXMLLoader.load(getClass().getResource("fxml_Game.fxml"));
	        stage.setTitle("Lorenzo il Magnifico");
	        stage.setScene(new Scene(root,java.awt.Toolkit.getDefaultToolkit().getScreenSize().width, java.awt.Toolkit.getDefaultToolkit().getScreenSize().height));
	        stage.centerOnScreen();
	        stage.show();
	    }
}
