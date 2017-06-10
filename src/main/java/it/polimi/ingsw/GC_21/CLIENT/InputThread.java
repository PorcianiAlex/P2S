package it.polimi.ingsw.GC_21.CLIENT;
import java.io.PrintWriter;
import java.util.Scanner;


public class InputThread extends Thread {
	private PrintWriter out;
	private Scanner in;
	
	
	public InputThread(PrintWriter out, Scanner in) {
		this.out = out;
		this.in = in;
	}


	@Override
	public void run() {
		while(true) {
			String messaggioricevuto = in.nextLine(); //arriva dal socket server
            System.out.println(messaggioricevuto);
            if (messaggioricevuto.equals("music")) {
				Music.start();
			}
            if (messaggioricevuto.equals("addio")){
                out.println("sei uscito");
                break;
            }
		}
	}

}
