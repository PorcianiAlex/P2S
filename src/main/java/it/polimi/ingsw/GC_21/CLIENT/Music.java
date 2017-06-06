package it.polimi.ingsw.GC_21.CLIENT;

import sun.audio.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class Music {

	 public static void start() 
	    {       
	        AudioPlayer MGP = AudioPlayer.player;
	        AudioStream BGM;
	        AudioData MD;

	        ContinuousAudioDataStream loop = null;

	        try
	        {
	            InputStream test = new FileInputStream("Song1.wav");
	            BGM = new AudioStream(test);
	            AudioPlayer.player.start(BGM);
	            //MD = BGM.getData();
	            //loop = new ContinuousAudioDataStream(MD);

	        }
	        catch(FileNotFoundException e){
	            System.out.print(e.toString());
	        }
	        catch(IOException error)
	        {
	            System.out.print(error.toString());
	        }
	        MGP.start(loop);
	    }

	 public static void stop() {
		 AudioPlayer.player.stop();
	}
}

