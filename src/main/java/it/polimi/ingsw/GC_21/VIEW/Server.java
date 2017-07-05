package it.polimi.ingsw.GC_21.VIEW;


import java.io.IOException;
import java.io.Serializable;
import java.net.*;
import java.net.UnknownHostException;
import java.util.concurrent.*;


import it.polimi.ingsw.GC_21.CLIENT.RmiClientInterface;
import it.polimi.ingsw.GC_21.CONTROLLER.ControllerManager;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements ServerInterface, Serializable {

    private int port;
    private ServerSocket serverSocket;
    private ExecutorService executor;
    private ControllerManager controllerManager;

    public  Server(int port) throws RemoteException{
        this.port=port;
        
        String ip;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
			System.out.println(ip); 
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
				
    }
    
    public  Server() throws RemoteException{
        
				
    }
    
    public void startServer() {
    	
    	controllerManager = new ControllerManager();
    	
       executor =  Executors.newCachedThreadPool();
       
       ServerSocket serverSocket;
       try{
           serverSocket = new ServerSocket(port);
           System.out.println("Server Socket ready...");
       
           LocateRegistry.createRegistry(8000);
           Registry reg = LocateRegistry.getRegistry(8000);
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
        		  SocketAdapter socketAdapter = new SocketAdapter(socket);
          		  RemoteView remoteView = new RemoteView(socketAdapter, controllerManager);
          		  socketAdapter.setRemoteView(remoteView);
        		  executor.submit(remoteView);        		  
        	   if(serverSocket.isClosed()) {break;}
        		 
			} 
				                            
           } catch (IOException e){
                System.out.println("error");
           }
       
              
        
    }
    
    
    public synchronized void join(RmiClientInterface rmiClient) {
    	RmiAdapter rmiAdapter = new RmiAdapter(rmiClient);
    	RemoteView remoteView;
		try {
			remoteView = new RemoteView(rmiAdapter, controllerManager);
			rmiAdapter.setRemoteView(remoteView);
			executor.submit(remoteView);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }
    
    public synchronized String serverReceive(String string) throws RemoteException{
    	return string;
    }

  
  

}
