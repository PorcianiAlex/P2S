
package it.polimi.ingsw.GC_21.VIEW;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;


public class Main {
	
	public static void main(String[] args) throws RemoteException, UnknownHostException {		
		//Server start!
		System.out.println(InetAddress.getLocalHost().getHostAddress());
		Server server1 = new Server();
        server1.startServer();
        
        
        
	}
}
