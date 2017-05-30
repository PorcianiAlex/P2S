package it.polimi.ingsw.GC_21.view;


import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.controller.Controller;

public class ServerForSocket {

    private int port;
    private ServerSocket serverSocket;
    private static final int maxClientsCount = 4;
    private static final ArrayList<RemoteView> threads = new ArrayList<RemoteView>();
    private PrintStream out=null;
    private Scanner in = null;
    private Game game;
    private Controller controller;

    public  ServerForSocket(int port){
        this.port=port;
        this.game = new Game();
		this.controller = new Controller(game);
				
    }



    public void startServer() {

       ExecutorService executor =  Executors.newCachedThreadPool();
       ServerSocket serverSocket;
       try{
           serverSocket = new ServerSocket(port);
       } catch (IOException e){
           System.err.println(e.getMessage());
           return;
       }
        System.out.println("Server ready!");
     
    
           try{
        	 
        	         	   
        	   while (true) {
        		  Socket socket = serverSocket.accept();
        		  RemoteView remoteView = new RemoteView(socket, threads, out, in, game);
        		  threads.add(remoteView);
        		  remoteView.attach(controller);
        		  executor.submit(remoteView);
        		  if (threads.size() == 2) {
					break;
				}
  					
				
			} 
				
			   Thread.sleep(10000);      	               
                            
           } catch (IOException | InterruptedException e){
                System.out.println("error");
           }
       
      
           
        game.executeGame();
        
        
    }


}
