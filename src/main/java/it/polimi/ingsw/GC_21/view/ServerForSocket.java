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
import it.polimi.ingsw.GC_21.controller.ControlloreManager;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerForSocket extends UnicastRemoteObject implements ServerInterface, Serializable {

    private int port;
    private ServerSocket serverSocket;
    private ExecutorService executor;
    private ControlloreManager controlloreManager;

    public  ServerForSocket(int port) throws RemoteException{
        this.port=port;
        
				
    }
    
    public  ServerForSocket() throws RemoteException{
        
				
    }
    
    public void startServer() {
    	
    	controlloreManager = new ControlloreManager();
    	
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
        	  while(true){       	         	   
        	           		   		          		   
        		  Socket socket = serverSocket.accept();
          		  RemoteView remoteView = new RemoteView(socket, controlloreManager);
        		  executor.submit(remoteView);        		  
        	   if(serverSocket.isClosed()) {break;}
        		 
			} 
				                            
           } catch (IOException e){
                System.out.println("error");
           }
       
              
        
    }
    
    
    public synchronized void join(RmiClientInterface rmiClient) throws RemoteException {
    	 
    	  RemoteView remoteView = new RemoteView(rmiClient, controlloreManager);
		  executor.submit(remoteView);
    }
    
    public synchronized String serverReceive(String string) throws RemoteException{
    	return string;
    }

  
  

}
