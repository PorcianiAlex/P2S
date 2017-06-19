package it.polimi.ingsw.GC_21.CLIENT;

import java.awt.image.TileObserver;
import java.io.InputStream;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import it.polimi.ingsw.GC_21.VIEW.InputForm;
import it.polimi.ingsw.GC_21.VIEW.Server;
import it.polimi.ingsw.GC_21.VIEW.ServerInterface;
import it.polimi.ingsw.GC_21.fx.ViewType;


public class RmiClient extends UnicastRemoteObject implements Serializable, RmiClientInterface, Connections{

	private ArrayList<String> messagesforserver;
	private Stack<String> stackforclient;
	private ViewType view;
	private Object LOCK = new Object(); // just something to lock on
	private Object LOCK2 = new Object(); // just something to lock on
	private Object LOCK3 = new Object(); // just something to lock on
	private Object LOCK4 = new Object(); // just something to lock on
	private InputForm inputToSend = null;
	private MessageToClient receivedMessage;
	private Scanner keyboard;

	
	public RmiClient(ViewType view) throws RemoteException {
		super();
		this.view=view;
		this.messagesforserver = new ArrayList<String>();
		this.stackforclient = new Stack<String>();
		this.keyboard = new Scanner(System.in);
		this.receivedMessage = null;
	
		
		
	}
	
	public  MessageToClient getReceivedMessage() {
		synchronized (LOCK) {
		while (receivedMessage == null) {
			try { LOCK.wait(); 
			}
	        catch (InterruptedException e) {
	            // treat interrupt as exit request
	            break;
	        }
		}
		}
		MessageToClient message = receivedMessage;
		receivedMessage = null;
		if(view.equals(ViewType.CLI)) {
			 InputForm inputForm = message.executeCLI(keyboard);
			 setInputToSend(inputForm);
		}
		return message;
	}

	@Override
	public void receiveObject(MessageToClient message) {
		this.receivedMessage = message;
		synchronized (LOCK) {
		    LOCK.notifyAll();
		}
	}

	
	public String sendToServer() throws RemoteException {
		if(view.equals(ViewType.GUI)) {
			synchronized (LOCK3) {
			    while (messagesforserver.isEmpty()) {
			        try { LOCK3.wait(); }
			        catch (InterruptedException e) {
			            // treat interrupt as exit request
			            break;
			        }
			    }
			}
		String toserver = new String(messagesforserver.get(0));
		System.out.println(toserver);
		messagesforserver.remove(0);
		System.out.println(toserver);
		return toserver;
		} else {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
		}
	}


	public void sendGUI(String mess) {
		messagesforserver.add(mess);
		synchronized (LOCK3) {
		    LOCK3.notifyAll();
		}
		
		
	}

	
	public void clientReceive(String string) {
		if ("music".equals(string)) {
			Music.start();
		} 
		if(view.equals(ViewType.GUI)) {
			this.stackforclient.push(string);
			System.out.println(string);
			synchronized (LOCK4) {
		    LOCK4.notifyAll();
		}
		} else {
			System.out.println(string);
		}
		
	}
	
	@Override
	public String getMessage() {
		synchronized (LOCK4) {
			while (stackforclient.isEmpty()) {	
				try { LOCK4.wait(); }
		        catch (InterruptedException e) {
		            // treat interrupt as exit request
		            break;
		        }
		}
		}
		return stackforclient.pop();
	}



	@Override
	public InputForm sendObjectToServer() throws RemoteException {
		synchronized (LOCK2) {
			while (inputToSend == null) {	
				try { LOCK2.wait(); }
		        catch (InterruptedException e) {
		            // treat interrupt as exit request
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



	public void setInputToSend(InputForm inputToSend) {
		this.inputToSend = inputToSend;
		synchronized (LOCK2) {
		    LOCK2.notifyAll();
		}
	}

	@Override
	public Scanner getKeyboard() {
		return keyboard;
	}

	public void setKeyboard(Scanner keyboard) {
		this.keyboard = keyboard;
	}
	

	
}
