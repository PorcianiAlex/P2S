package it.polimi.ingsw.GC_21.view;

public class AdapterCLI implements AdapterView{
	private AdapterConnection adapterConnection;

	public AdapterCLI(it.polimi.ingsw.GC_21.view.AdapterConnection adapterConnection) {
		super();
		this.adapterConnection = adapterConnection;
	}



	@Override
	public void send(String string) {
		adapterConnection.out(string);
	}

}
