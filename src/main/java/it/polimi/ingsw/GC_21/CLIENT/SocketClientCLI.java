package it.polimi.ingsw.GC_21.CLIENT;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.fx.ViewType;

public class SocketClientCLI extends SocketClient{

	public SocketClientCLI(String ip, int port) throws UnknownHostException, IOException {
		super(ip, port, ViewType.CLI);
	}
	
	@Override
	public void startClient() throws IOException {
		super.startClient();
		 Scanner tastiera = new Scanner(System.in);
		 
		
        while (true){
        	
            String stringa = tastiera.nextLine();
            out.println(stringa);                       // mando al socket
            out.flush();
            if (stringa == "quit") {
            	break;
            }
            
        }  
  
        in.close();
        out.close();
        tastiera.close();
        socketclient.close();       

	}

	
}
