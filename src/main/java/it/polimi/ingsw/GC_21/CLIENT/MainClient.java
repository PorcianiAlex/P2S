package it.polimi.ingsw.GC_21.CLIENT;

import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.polimi.ingsw.GC_21.VIEW.ServerInterface;
import it.polimi.ingsw.GC_21.fx.ViewType;

public class MainClient {
    public static void main(String[] args) {

    System.out.println("Welcome to Lorenzo il Magnifico! \n Choose your connection to start the game! \n 1:RMI \n 2:Socket");
    Scanner scanner = new Scanner(System.in);
    String choice = scanner.next();
    switch (choice) {
	case  "1": try {
			factoryRmi();
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}

		break;
	case "2" : factorySocket();
	break;
	default: ; factorySocket();
		break;
	}
        
   }
    	
    public static void factorySocket() {
    	
    	try {
    		
    		FileReader file = new FileReader("Connection.json");
			JSONParser parser = new JSONParser(); //loading by file 
			JSONObject obj = (JSONObject) parser.parse(file);
			int port = Integer.parseInt(obj.get("SOCKETPORT").toString());
			String ip = (obj.get("IP").toString());
			SocketClient client1 = new SocketClient(ip, port, ViewType.CLI);
			 RunCli runCli = new RunCli(client1);
		     runCli.start();
		} catch (ClassNotFoundException | IOException | ParseException e) {
			e.printStackTrace();
		}      

     }
    
    public static void factoryRmi() throws RemoteException, NotBoundException {
        try {
        	FileReader file = new FileReader("Connection.json");
			JSONParser parser = new JSONParser(); //loading by file 
			JSONObject obj = (JSONObject) parser.parse(file);
			int port = Integer.parseInt(obj.get("RMIPORT").toString());
        	Registry reg = LocateRegistry.getRegistry(port);
            ServerInterface srv = (ServerInterface) reg.lookup("server"); 
        	RmiClient client2 = new RmiClient(ViewType.CLI);
            srv.join(client2);
            RunCli runCli = new RunCli(client2);
			runCli.start();
		} catch (ClassNotFoundException | IOException | ParseException e) {
			e.printStackTrace();
		}
	}
    
}
