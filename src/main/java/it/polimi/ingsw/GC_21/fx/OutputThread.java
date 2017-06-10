package it.polimi.ingsw.GC_21.fx;

import java.io.PrintWriter;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.CLIENT.Music;

public class OutputThread extends Thread {

	private PrintWriter out;
	private Scanner in;
	
	
	public OutputThread(PrintWriter out, Scanner in) {
		this.out = out;
		this.in = in;
	}


	@Override
	public void run() {
		while(true) {
			out.println();
			out.flush();
		}
	}


}


