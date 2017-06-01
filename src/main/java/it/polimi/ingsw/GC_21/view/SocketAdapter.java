package it.polimi.ingsw.GC_21.view;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketAdapter implements Adapter{

	private Socket socket;
	private Scanner in;
	private PrintStream out;
        
	public SocketAdapter(Socket socket) throws IOException {
		super();
		this.socket = socket;
		in = new Scanner(socket.getInputStream());// Canale in ingresso della socket (out del client)
	    out = new PrintStream(socket.getOutputStream()); // Canale in uscita della socket (in del client)
		
}

	@Override
	public String in() {
		return in.next();			
	}

	@Override
	public void out(String string) {
		out.println(string);
		out.flush();
		
	}

}
