package it.polimi.ingsw.GC_21.CLIENT;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.*;

public class SocketClient implements Connections {
    protected String ip;
    protected int port;
    protected Thread inputThread;
    protected PrintWriter out;
    protected Scanner in;
    protected Socket socketclient;

    public SocketClient(String ip, int port) throws UnknownHostException, IOException{
    this.ip=ip;
    this.port=port;
    socketclient = new Socket(ip,port);
    }

    public void startClient() throws IOException {
         
        
        System.out.println("Sono dentro il gioco!");
        in = new Scanner(socketclient.getInputStream()); //arriva dal server
        out = new PrintWriter(socketclient.getOutputStream()); //invia al server
       
        this.inputThread = new InputThread(out, in);
      
        inputThread.start();

       
    }

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void sendGUI(String stringa) {
		out.println(stringa);
		out.flush();
	}




}
