package it.polimi.ingsw.GC_21.CLIENT;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;
import java.util.*;

public class Client {
    private String ip;
    private int port;
    private Thread inputThread;

    public Client(String ip, int port){
    this.ip=ip;
    this.port=port;
    }

    public void startClient() throws IOException {
        Socket socketclient = new Socket(ip,port);
        System.out.println("Sono dentro il gioco!");
        Scanner in = new Scanner(socketclient.getInputStream()); //arriva dal server
        PrintWriter out = new PrintWriter(socketclient.getOutputStream()); //invia al server
        Scanner tastiera = new Scanner(System.in);
        this.inputThread = new InputThread(out, in);
        inputThread.start();
 
    try {
        while (true){
     
            String stringa = tastiera.nextLine();
            out.println(stringa);                       // mando al socket
            out.flush();
            if (stringa == "sara mazzoleni ti amo") {
            	break;
            }
        }

        }
    catch (NoSuchElementException e3){
        System.out.println("L'altro ha chiuso la connesione");
        }
    finally {
        in.close();
        out.close();
        tastiera.close();
        socketclient.close();
        }

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
