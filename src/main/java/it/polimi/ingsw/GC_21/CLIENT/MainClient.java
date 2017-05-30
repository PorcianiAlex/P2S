package it.polimi.ingsw.GC_21.CLIENT;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainClient {
    public static void main(String[] args) {

    	try {
			String ip = InetAddress.getLocalHost().getHostAddress();
			System.out.println(ip);
			Client client1 =new Client(ip, 6620);
			client1.startClient();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e2) {
            System.out.println("errore");
        }
    	        

     }
}
