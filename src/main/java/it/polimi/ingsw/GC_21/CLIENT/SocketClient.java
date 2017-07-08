package it.polimi.ingsw.GC_21.CLIENT;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.*;
import java.rmi.RemoteException;

import it.polimi.ingsw.GC_21.VIEW.InputForm;
import it.polimi.ingsw.GC_21.fx.ViewType;

import java.util.*;


public class SocketClient implements Connections {
    protected String ip;
    protected int port;
    protected ObjectInputStream oisClient;
    protected ObjectOutputStream oosClient;
    protected Socket socketclient;
	protected Stack<String> stackforclient;
	protected Object LOCK = new Object();
	protected Object LOCKobj = new Object();
	protected MessageToClient receivedMessage;

	

    public SocketClient(String ip, int port, ViewType viewType) throws UnknownHostException, IOException{
    this.ip=ip;
    this.port=port;
    socketclient = new Socket(ip,port);
    stackforclient = new Stack<String>();
	this.oosClient = new ObjectOutputStream(socketclient.getOutputStream());
	this.oisClient = new ObjectInputStream(socketclient.getInputStream());

    }
@Override
    public void close() throws IOException{
    	oosClient.close();
    	oisClient.close();
    	socketclient.close();
    }


	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	
	public void setPort(int port) {
		this.port = port;
	}


	public void setMessForGui(String mess) {
		synchronized (LOCK) {
			stackforclient.push(mess);
		LOCK.notifyAll();
		}
		
	}
	
	

	@Override
	public MessageToClient getReceivedMessage() throws ClassNotFoundException, IOException {
			MessageToClient messageToClient = (MessageToClient) oisClient.readObject();
			if (messageToClient == null) {
				return getReceivedMessage();
			}
				return messageToClient;
		
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
		oosClient.reset();
	}

	

	


}
