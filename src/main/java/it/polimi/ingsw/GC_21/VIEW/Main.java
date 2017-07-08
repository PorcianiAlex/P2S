
package it.polimi.ingsw.GC_21.VIEW;

import java.rmi.RemoteException;


public class Main {
	
	public static void main(String[] args) throws RemoteException {		
		//Server start!
		Server server1 = new Server();
        server1.startServer();
        
        
        
	}
}
