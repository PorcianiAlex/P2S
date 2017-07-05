package it.polimi.ingsw.GC_21.CLIENT;

import sun.audio.*;
import java.io.*;

import javax.sound.sampled.AudioInputStream;

public class Music {

	private AudioPlayer MGP = AudioPlayer.player;
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
		 AudioPlayer.player.stop(BGM);
	}
	 
	 public boolean getOnGoing() {
		return ongoing;
	}
	 
}

