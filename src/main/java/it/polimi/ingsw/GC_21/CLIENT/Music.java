package it.polimi.ingsw.GC_21.CLIENT;

import sun.audio.*;
import java.io.*;

public class Music {

    private AudioStream BGM;
    private AudioData MD;
    
	
	private Boolean ongoing=false;
	
	 public void start() 
	    {   
		 	ongoing=true;
		 	
	        ContinuousAudioDataStream loop = null;

	        try
	        {
	            InputStream test = new FileInputStream("Song1.wav");
	            BGM = new AudioStream(test);
	            AudioPlayer.player.start(BGM);
	            MD = BGM.getData();
	            loop = new ContinuousAudioDataStream(MD);
	            AudioPlayer.player.start(loop);

	        }
	        catch(IOException error){
	          //IO exept on player
	        }
	       
	    }

	 public void stop() {
		 ongoing=false;
		 AudioPlayer.player.stop(BGM);
	}
	 
	 public boolean getOnGoing() {
		return ongoing;
	}
	 
}

