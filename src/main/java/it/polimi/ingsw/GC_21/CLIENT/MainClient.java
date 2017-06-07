package it.polimi.ingsw.GC_21.CLIENT;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.util.Scanner;

import org.junit.experimental.theories.Theories;
import org.omg.CORBA.PUBLIC_MEMBER;

import it.polimi.ingsw.GC_21.view.ServerForSocket;
import it.polimi.ingsw.GC_21.view.ServerInterface;

public class MainClient {
    public static void main(String[] args) {

    System.out.println("Welcome to Lorenzo il Magnifico! \n Choose your connection to start the game! \n 1:RMI \n 2:Socket");
    Scanner scanner = new Scanner(System.in);
    String choice = scanner.nextLine();
    switch (choice) {
	case  "1": try {
			factoryRmi();
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}

		break;

	case "2" : factorySocket();
	
	default: ; factorySocket();
		break;
	}
    
   }
    	
    public static void factorySocket() {
    try {
			String ip = InetAddress.getLocalHost().getHostAddress();
			System.out.println(ip);
			SocketClient client1 = new SocketClient(ip, 6620);
			client1.startClient();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e2) {
            System.out.println("errore");
        }
    	        

     }
    
    public static void factoryRmi() throws RemoteException, NotBoundException {
    	Registry reg = LocateRegistry.getRegistry(8000);
        ServerInterface srv = (ServerInterface) reg.lookup("server");          
    	RmiClient client2 = new RmiClient();
        srv.join(client2);
	}
    
}