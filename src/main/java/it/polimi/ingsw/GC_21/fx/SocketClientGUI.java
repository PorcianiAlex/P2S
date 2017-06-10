package it.polimi.ingsw.GC_21.fx;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.CLIENT.InputThread;

import java.util.*;

public class SocketClientGUI {
    private String ip;
    private int port;
    private Thread inputThread;
	private OutputThread outputThread;
	private PrintWriter out;

    public SocketClientGUI(String ip, int port) throws RemoteException{
    this.ip=ip;
    this.port=port;
    }

    public void startClient() throws IOException {
         
        Socket socketclient = new Socket(ip,port);
        System.out.println("Sono dentro il gioco!");
        Scanner in = new Scanner(socketclient.getInputStream()); //arriva dal server
        out = new PrintWriter(socketclient.getOutputStream()); //invia al server
        

    }
    
    public void send(String stringtosend) {
    	out.println(stringtosend);
    	out.flush();
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





}
