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
    private static final int maxClientsCount = 1;
    private static final RemoteView[] threads = new RemoteView[maxClientsCount];
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
     
        while(true){
           try{
                Socket socket = serverSocket.accept(); //il server trova un client e crea la Socket
               Thread.sleep(10000);
                for (int i = 0; i < threads.length; i++) {
					threads[i] =new RemoteView(socket, threads, out, in, game);
					threads[i].attach(controller);
					executor.submit(threads[i]); //passo il socket al generatore di thread che gestiscono le socket dei client
				}
               
                // game.executeGame();
                
           } catch (IOException | InterruptedException e){
                break;
           }
       }
        executor.shutdown();
    }


}
