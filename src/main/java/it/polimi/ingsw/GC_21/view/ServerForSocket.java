package it.polimi.ingsw.GC_21.view;


import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

import javax.print.attribute.standard.JobImpressions;

import it.polimi.ingsw.GC_21.CLIENT.RmiClient;
import it.polimi.ingsw.GC_21.CLIENT.RmiClientInterface;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.controller.Controller;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerForSocket extends UnicastRemoteObject implements ServerInterface, Serializable {

    private int port;
    private ServerSocket serverSocket;
    private static final int maxClientsCount = 4;
    private static final ArrayList<RemoteView> threads = new ArrayList<RemoteView>();
    private Game game;
    private Controller controller;
    private ExecutorService executor;

    public  ServerForSocket(int port) throws RemoteException{
        this.port=port;
        
				
    }
    
    public  ServerForSocket() throws RemoteException{
        
				
    }
    



    public void startServer() {

    	this.game = new Game();
		this.controller = new Controller(game);
    	
       executor =  Executors.newCachedThreadPool();
           
       
       ServerSocket serverSocket;
       try{
           serverSocket = new ServerSocket(port);
           System.out.println("Server Socket ready...");
       
           LocateRegistry.createRegistry(8080);
           Registry reg = LocateRegistry.getRegistry(8080);
           reg.rebind("server", this);
           System.out.println("Server RMI up and running...");
       } catch (IOException e){
           System.err.println(e.getMessage());
           return;
       }
        System.out.println("Server ready!");
     
    
           try{
        	 
        	   //da sistemare!!
        	  while(threads.size()<2){       	         	   
        	           		   		          		   
        		  Socket socket = serverSocket.accept();
          		  RemoteView remoteView = new RemoteView(socket, threads, game);
        		  threads.add(remoteView);
        		  remoteView.attach(controller);
        		  executor.submit(remoteView);
        		  
        	   
        		 
			} 
				
			   Thread.sleep(5000);      	               
                            
           } catch (IOException | InterruptedException e){
                System.out.println("error");
           }
       
      
           
        game.executeGame();
        
        
    }
    
    
    public synchronized void join(RmiClientInterface rmiClient) throws RemoteException {
    	 
    	  RemoteView remoteView = new RemoteView(threads, game, rmiClient);
		  threads.add(remoteView);
		  remoteView.attach(controller);
		  executor.submit(remoteView);
    }
    
    public synchronized String serverReceive(String string) throws RemoteException{
    	return string;
    }

  
  

}
