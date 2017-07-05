package it.polimi.ingsw.GC_21.CLIENT;

import sun.audio.*;
import java.io.*;

public class Music {

	private Boolean ongoing=false;
	
	 public void start() 
	    {   
		 	ongoing=true;
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

	 public void stop() {
		 ongoing=false;
		 AudioPlayer.player.stop();
	}
	 
	 public boolean getOnGoing() {
		return ongoing;
	}
	 
}

