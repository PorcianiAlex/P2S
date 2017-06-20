package it.polimi.ingsw.GC_21.VIEW;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.CLIENT.MessageToClient;

public class SocketAdapter implements AdapterConnection{

	private Socket socket;
	private Scanner in;
	private PrintStream out;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;

        
	public SocketAdapter(Socket socket) throws IOException {
		this.socket = socket;
		in = new Scanner(socket.getInputStream());// Canale in ingresso della socket (out del client)
	    out = new PrintStream(socket.getOutputStream()); // Canale in uscita della socket (in del client)
		ois = new ObjectInputStream(socket.getInputStream());
		oos = new ObjectOutputStream(socket.getOutputStream());


	}
	

	@Override
	public String in() {
		System.out.println("Socket In");
		return in.next();			
	}

	@Override
	public void out(String string) {
		System.out.println("Socket out" + string);
		out.println(string);
		out.flush();
		
	}

	@Override
	public void sendObject(MessageToClient message) {
		try {
			oos.writeObject(message);
			oos.flush();
		} catch (IOException e) {
			out("IO Error");
			sendObject(message);//retry to send
		}
	}

	@Override
	public InputForm receiveObject() {
		try {
			InputForm inputForm = (InputForm) ois.readObject();
			return inputForm;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			out("Error, retry");
			return null;
		}
	}
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public Scanner getIn() {
		return in;
	}

	public void setIn(Scanner in) {
		this.in = in;
	}

	public PrintStream getOut() {
		return out;
	}

	public void setOut(PrintStream out) {
		this.out = out;
	}

}
