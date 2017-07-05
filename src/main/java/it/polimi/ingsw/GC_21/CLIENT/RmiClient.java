package it.polimi.ingsw.GC_21.CLIENT;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import it.polimi.ingsw.GC_21.VIEW.InputForm;

import it.polimi.ingsw.GC_21.fx.ViewType;


public class RmiClient extends UnicastRemoteObject implements Serializable, RmiClientInterface{

	private ArrayList<String> messagesForServer;
	private Stack<String> stackForClient;
	private ViewType view;
	private Object LOCK = new Object(); // just something to lock on
	private Object LOCK2 = new Object(); // just something to lock on
	private Object LOCK3 = new Object(); // just something to lock on
	private Object LOCK4 = new Object(); // just something to lock on
	private InputForm inputToSend;
	private MessageToClient receivedMessage;

	
	public RmiClient(ViewType view) throws RemoteException {
		this.view=view;
		this.messagesForServer = new ArrayList<String>();
		this.stackForClient = new Stack<String>();
		this.receivedMessage = null;
		this.inputToSend = null;
	}
	
	@Override
	public  MessageToClient getReceivedMessage() throws IOException {
		synchronized (LOCK) {
		while (receivedMessage == null) {
			 try {
				LOCK.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		

		}
		}
		MessageToClient message = receivedMessage;
		receivedMessage = null;
		return message;
	}

	@Override
	public void receiveObject(MessageToClient message) {
		this.receivedMessage = message;
		synchronized (LOCK) {
		    LOCK.notifyAll();
		}
	}

	


	public void sendGUI(String mess) {
		messagesForServer.add(mess);
		synchronized (LOCK3) {
		    LOCK3.notifyAll();
		}
		
		
	}

	
	public void clientReceive(String string) {
		if ("music".equals(string)) {
			Music music = new Music();
			music.start();
		} 
		if(view.equals(ViewType.GUI)) {
			this.stackForClient.push(string);
			System.out.println(string);
			synchronized (LOCK4) {
		    LOCK4.notifyAll();
		}
		} else {
			System.out.println(string);
		}
		
	}
	
	



	@Override
	public InputForm sendObjectToServer() throws RemoteException {
		synchronized (LOCK2) {
			while (inputToSend == null) {	
				try { LOCK2.wait(); }
		        catch (InterruptedException e) {
		            break;
		        }
		}
		}
		InputForm inputFromView = inputToSend;
		inputToSend = null;
		return inputFromView;
	}



	public InputForm getInputToSend() {
		return inputToSend;
	}


	@Override
	public void sendInput(InputForm inputToSend) {
		this.inputToSend = inputToSend;
		synchronized (LOCK2) {
		    LOCK2.notifyAll();
		}
	}

	

	@Override
	public void close() throws IOException {
		
	}

	

	



	
}
