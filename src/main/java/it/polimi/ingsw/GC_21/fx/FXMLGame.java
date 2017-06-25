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
	        stage.setTitle("Game");
	        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	        int width = gd.getDisplayMode().getWidth();
	        int height = gd.getDisplayMode().getHeight();
	        stage.setScene(new Scene(root, width, height));
	        stage.centerOnScreen();
	        stage.show();
	    }
}
