package it.polimi.ingsw.GC_21.fx;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import it.polimi.ingsw.GC_21.CLIENT.RmiClient;
import it.polimi.ingsw.GC_21.CLIENT.SocketClient;
import it.polimi.ingsw.GC_21.REMOTEVIEW.ServerInterface;
import javafx.application.Application;

public class MainGUI {

	public static void main(String[] args) {
	        
		Application.launch(FXMLLogin.class, args);

	}
	
}
