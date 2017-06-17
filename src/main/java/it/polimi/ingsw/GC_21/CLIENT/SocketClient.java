package it.polimi.ingsw.GC_21.CLIENT;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.ingsw.GC_21.fx.ViewType;

import java.util.*;

public class SocketClient implements Connections {
    protected String ip;
    protected int port;
    protected Thread inputThread;
    protected PrintWriter out;
    protected Scanner in;
    protected Socket socketclient;
    protected ViewType view;
	protected Stack<String> stackforclient;
	protected Object LOCK = new Object();

    public SocketClient(String ip, int port, ViewType viewType) throws UnknownHostException, IOException{
    this.ip=ip;
    this.port=port;
    socketclient = new Socket(ip,port);
    this.view=viewType;
    stackforclient = new Stack<String>();
    }

    public void startClient() throws IOException {
         //crealo con due viewtype diverse
        
        System.out.println("Sono dentro il gioco!");
        in = new Scanner(socketclient.getInputStream()); //arriva dal server
        out = new PrintWriter(socketclient.getOutputStream()); //invia al server
       
        this.inputThread = new InputThread(out, in, this);
      
        inputThread.start();

       
    }

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public ViewType getView() {
		return view;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void sendGUI(String stringa) {
		out.println(stringa);
		System.out.println(stringa);
		out.flush();
	}

	public void setMessForGui(String mess) {
		synchronized (LOCK) {
			stackforclient.push(mess);
		LOCK.notifyAll();
		}
		
	}
	
	@Override
	public String getMessage() {
		while(stackforclient.isEmpty()) {
		try { synchronized (LOCK) {
			LOCK.wait();
		}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
		
			
		return stackforclient.pop();
	}

	@Override
	public MessageToClient getReceivedMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Scanner getKeyboard() {
		// TODO Auto-generated method stub
		return null;
	}




}
