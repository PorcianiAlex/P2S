package it.polimi.ingsw.GC_21.CLIENT;
import java.io.PrintWriter;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.fx.ViewType;


public class InputThread extends Thread {
	private PrintWriter out;
	private Scanner in;
	private SocketClient socketClient;
	
	public InputThread(PrintWriter out, Scanner in, SocketClient socketClient) {
		this.out = out;
		this.in = in;
		this.socketClient = socketClient;
	}


	@Override
	public void run() {
		while(true) {
			String messaggioricevuto = in.nextLine(); //arriva dal socket server
			if (messaggioricevuto.equals("music")) {
				Music.start();
			}
            if (messaggioricevuto.equals("addio")){
                out.println("sei uscito");
                break;
            }
			if(socketClient.getView() == ViewType.GUI){
				System.out.println("thread: "+messaggioricevuto);
				socketClient.setMessForGui(messaggioricevuto);
			} else {
            System.out.println(messaggioricevuto);
            
		}
	
		}
	}
}