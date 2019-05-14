package game;

public class Spiel {
	Spieler [] spielerListe;
	int spielercounter=0;
	Spieler aktSpieler;
	Spielfeld sf;
	
	
	
	Spiel(){
		spielerListe = new Spieler[2];

		start();
	}
	
	public void ziehe(Spieler aktSpieler,int spalte) {
		System.out.println("################################");
		System.out.println("\n Vorher \n");
		System.out.println(sf.toString());

		String[] array = aktSpieler.getName().split("#");
		String feld = array[0];
		int startfeld = Integer.valueOf(feld)-1;
		sf.ziehe(startfeld, spalte,aktSpieler);

	}
	
	public void start() {
		sf = new Spielfeld();
		aktSpieler=spielerListe[0];
	}
	
	
	public void addSpieler(Spieler name) {
		spielerListe[spielercounter++]=name;
		
	}
	
	public void getStatus() {
		System.out.println("\n Nachher \n");
		System.out.println(sf.toString());
	//	System.out.println(aktSpieler.getName());
		System.out.println(aktSpieler.getSteine());
		System.out.println("\n");
		System.out.println("####################################");
	}
	
	public Spieler gibSpieler() {
		return this.aktSpieler;
	}
	public void changeSpieler() {
		for (Spieler spieler : spielerListe) {
			if(!(spieler.getName().equals(aktSpieler.getName()))) {
				aktSpieler=spieler;
				break;
			}
		}
		
	}
	
	public void hatGewonnen() {
		
	}

}
