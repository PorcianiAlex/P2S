package it.polimi.ingsw.GC_21.VIEW;

public class AdapterCLI implements AdapterView{
	private AdapterConnection adapterConnection;

	public AdapterCLI(it.polimi.ingsw.GC_21.VIEW.AdapterConnection adapterConnection) {
		super();
		this.adapterConnection = adapterConnection;
	}



	@Override
	public void send(String string) {
		System.out.println(string);
		adapterConnection.out(string);
	}

}
