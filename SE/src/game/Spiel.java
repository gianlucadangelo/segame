package game;

public class Spiel {
	public static Spieler[] spielerListe;
	public static int spielercounter = 0;
	Spieler aktSpieler;
	Spielfeld sf;

	Spiel() {
		spielerListe = new Spieler[2];

		start();
	}

	public void ziehe(Spieler aktSpieler, int spalte) {
		System.out.println("################################");
		System.out.println("\n Vorher \n");
		System.out.println(sf.toString());

		String[] array = aktSpieler.getName().split("#");
		String feld = array[0];
		int startfeld = Integer.valueOf(feld) - 1;
		sf.ziehe(startfeld, spalte, aktSpieler);

	}

	public void start() {
		sf = new Spielfeld();
		aktSpieler = spielerListe[0];
	}

	public static void addSpieler(Spieler name) {
		spielerListe[spielercounter++] = name;

	}

	public void getStatus() {
		System.out.println("\n Nachher \n");
		System.out.println(sf.toString());
		// System.out.println(aktSpieler.getName());
		System.out.println(aktSpieler.getSteine());
	
		System.out.println("\n");
		System.out.println("####################################");
	}

	public Spieler gibSpieler() {
		return this.aktSpieler;
	}

	public void changeSpieler() {
		for (Spieler spieler : spielerListe) {
			if (!(spieler.getName().equals(aktSpieler.getName()))) {
				aktSpieler = spieler;
				break;
			}
		}

	}

	public boolean hatGewonnen() {
		boolean gewonnen = false;
		if (sf.spielerLeisteLeer(aktSpieler)) {
			if (spielerListe[0].getSteine() < spielerListe[1].getSteine()) {
				System.out.println(spielerListe[1].getName() + "hat gewonnen");
				gewonnen = true;
			} else {
				System.out.println(spielerListe[0].getName() + "hat gewonnen");
				gewonnen = true;

			}
		}

		return gewonnen;
	}

	public void zieheComputerSchwer(Spieler aktSpieler1) {

	}

}
