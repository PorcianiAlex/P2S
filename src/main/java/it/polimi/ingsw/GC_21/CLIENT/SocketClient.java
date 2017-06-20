package it.polimi.ingsw.GC_21.CLIENT;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.ingsw.GC_21.VIEW.InputForm;
import it.polimi.ingsw.GC_21.fx.ViewType;

import java.util.*;

import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardDownRightHandler;

public class SocketClient implements Connections {
    protected String ip;
    protected Scanner keyboard;
    protected int port;
    protected PrintWriter out;
    protected Scanner in;
    protected ObjectInputStream oisClient;
    protected ObjectOutputStream oosClient;
    protected Socket socketclient;
    protected ViewType view;
	protected Stack<String> stackforclient;
	protected Object LOCK = new Object();
	protected Object LOCKobj = new Object();
	protected MessageToClient receivedMessage;

	

    public SocketClient(String ip, int port, ViewType viewType) throws UnknownHostException, IOException{
    this.ip=ip;
    this.port=port;
    socketclient = new Socket(ip,port);
    this.view=viewType;
    stackforclient = new Stack<String>();
	this.oosClient = new ObjectOutputStream(socketclient.getOutputStream());
	this.oisClient = new ObjectInputStream(socketclient.getInputStream());

    }

    public void startClient() throws IOException {
         //crealo con due viewtype diverse
        
        System.out.println("Sono dentro il gioco!");
        in = new Scanner(socketclient.getInputStream()); //arriva dal server
        out = new PrintWriter(socketclient.getOutputStream()); //invia al server
        
	    Thread inputThread = new InputThread(out, in, this);
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
	public MessageToClient getReceivedMessage() throws ClassNotFoundException, IOException {
			MessageToClient messageToClient = (MessageToClient) oisClient.readObject();
				return messageToClient;
		
	}

	@Override
	public Scanner getKeyboard() {
		return null;
	}

	public void setObjForGui(MessageToClient receivedMessage) {
		synchronized (LOCKobj) {
		this.receivedMessage = receivedMessage;
		LOCKobj.notifyAll();
		}		
	}

	@Override
	public void sendInput(InputForm inputForm) throws IOException {
		oosClient.writeObject(inputForm);
		oosClient.flush();		
	}

	@Override
	public void sendString() throws RemoteException {
		out.println(keyboard.nextLine());
	}

	@Override
	public void setKeyboard(Scanner keyboard) throws RemoteException {
		this.keyboard = keyboard;
	}





}
