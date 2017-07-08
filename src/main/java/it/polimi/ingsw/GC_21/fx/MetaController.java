package it.polimi.ingsw.GC_21.fx;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.polimi.ingsw.GC_21.CLIENT.Connections;
import it.polimi.ingsw.GC_21.CLIENT.RmiClient;
import it.polimi.ingsw.GC_21.CLIENT.SocketClient;
import it.polimi.ingsw.GC_21.VIEW.ServerInterface;

public class MetaController {
	
	protected static Connections client;
	protected static ArrayList<String> games = new ArrayList<String>();
	
	
	public static void factorySocket() {
	    try {
	    	
				FileReader file = new FileReader("Connection.json");
				JSONParser parser = new JSONParser(); //loading by file 
				JSONObject obj = (JSONObject) parser.parse(file);
				int port = Integer.parseInt(obj.get("SOCKETPORT").toString());
				String ip = (obj.get("RMIPORT").toString());
			
				ip = InetAddress.getLocalHost().getHostAddress();
				SocketClient client1 = new SocketClient(ip, port, ViewType.GUI);
				client = client1;
	    } catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	    	        

	     }
	    
	    public static void factoryRmi() throws RemoteException, NotBoundException {
	    	
	    	FileReader file;
	    	
			try {
				file = new FileReader("Connection.json");
				JSONParser parser = new JSONParser(); //loading by file 
				JSONObject obj = (JSONObject) parser.parse(file);
				int port = Integer.parseInt(obj.get("RMIPORT").toString());
		    	Registry reg = LocateRegistry.getRegistry(port);
		        ServerInterface srv = (ServerInterface) reg.lookup("server");
		    	RmiClient client2 = new RmiClient(ViewType.GUI);
		        srv.join(client2);
		        client = client2;
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
			
		}


}
