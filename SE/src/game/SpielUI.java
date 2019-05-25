package game;

import java.util.Scanner;

public class SpielUI {

	public static void main(String[] arg) {
		System.out.println("Willkommen. " + "\n" + "Hauptmenü:");
		System.out.println("(1) Spiel starten");
		System.out.println("(2) Spiel beenden");

		String abfrage;
		Scanner sc = new Scanner(System.in);
		boolean nochmal = true;
		do {
			abfrage = sc.nextLine();
			switch (abfrage) {
			case "1":
				System.out.println("Das Spiel wird gestartet."+"\n");
				nochmal = false;
				break;
			case "2":
				nochmal = false;
				break;
			default:
				System.out.println("Fehlerhafte Eingabe, bitte 1 oder 2 eingeben.");
				nochmal = true;
				break;
			}
		} while (nochmal);

		// String was = "ja";
		if (abfrage.equals("1")) {
			boolean game = true;
			System.out.println("Name für Spieler 1 eingeben. Falls KI gewünscht, bitte KI eingeben.");
			String spieler1, spieler2;
			spieler1 = sc.nextLine();
			
			Spiel sp = new Spiel();

			if (spieler1.toLowerCase().equals("ki")) {
				System.out.println("Auswahl Schwierigkeitsgrad für " +spieler1 + ":" + "\n"
						+ "Bitte normal bzw. schwer eingeben.");
				String mode = sc.nextLine();
				boolean korrekt = false;
				do {
					if (mode.toLowerCase().equals("schwer")) {
						KI cpS = new KI("1#" + spieler1, 0, 0, true);
						Spiel.addSpieler(cpS);
						korrekt = true;
					} else if (mode.toLowerCase().equals("normal")) {
						KI cp = new KI("1#" + spieler1, 0, 0, false);
						Spiel.addSpieler(cp);
						korrekt = true;
					} else {
						System.out.println("falsche Eingabe, bitte normal oder schwer eingeben.");

					}
				} while (!korrekt);

			} else {
				Mensch mensch = new Mensch(spieler1, 0,0);
				Spiel.addSpieler(mensch);
			}
			
			System.out.println("Name für Spieler 2 eingeben. Falls KI gewünscht, bitte KI eingeben.");
			spieler2=sc.nextLine();
			
			if (spieler2.toLowerCase().equals("ki")) {
				System.out.println("Auswahl Schwierigkeitsgrad für " + spieler2 + ":" + "\n"
						+ "Bitte normal bzw. schwer eingeben.");
				String mode = sc.nextLine();
				boolean korrekt = false;
				do {
					if (mode.toLowerCase().equals("schwer")) {
						KI cpS = new KI(spieler2, 1, 0, true);
						Spiel.addSpieler(cpS);
						korrekt = true;
					} else if (mode.toLowerCase().equals("normal")) {
						KI cp = new KI(spieler2, 1, 0, false);
						Spiel.addSpieler(cp);
						korrekt = true;
					} else {
						System.out.println("falsche Eingabe, bitte erneut versuchen.");

					}
				} while (!korrekt);

			} else {
				Mensch mensch = new Mensch(spieler2, 0,1);
				Spiel.addSpieler(mensch);

			}
			sp.start();
			while (game) {
				System.out.println("Spielfeld");
				System.out.println(sp.sf.toString());
				if (sp.gibSpieler().getName().contains("ki")) {
					KI cp = (KI) sp.gibSpieler();
					if (cp.isSchwer()) {
						sp.zieheKISchwer(sp.aktSpieler);

					} else {
						int zufallszahl = (int) (Math.random() * 6);
						sp.ziehe(sp.gibSpieler(), zufallszahl);
					}
				} else {
					System.out.println(sp.aktSpieler.getName());
					System.out.println(sp.aktSpieler.getSteine());
					if (sp.aktSpieler.getSteine() >= 36) {
						System.out.println("Möchtest du aufgeben? J/N ");
						if (sc.nextLine().toLowerCase().equals("j")) {
							for (Spieler spieler : sp.spielerListe) {
								if (!(spieler.getName().equals(sp.aktSpieler.getName()))) {
									System.out.println("Spiel wird beendet. " + spieler + " hat gewonnen.");
									sc.close();
								}
							}

						}

					}else {
						System.out.println("Wählen Sie die gewünschte Spalte (1 bis 7) aus.");
						int spalte = sc.nextInt() - 1;
						while( (spalte < 0 || spalte > 6) || (sp.sf.fieldNotEmpty(sp.gibSpieler().getReihe() ,spalte)!=true) ) {
							if(spalte > 6 || spalte < 0) System.out.println("Bitte geben Sie eine Spalte von 1-7 an");
							else System.out.println(" Bitte waehlen Sie ein Feld aus, was nicht leer ist.");
							spalte = sc.nextInt() - 1;
						}
						sp.ziehe(sp.gibSpieler(), spalte);
						
					}
				}
				game = !(sp.hatGewonnen());
				if (game) {
					sp.getStatus();
					sp.changeSpieler();
				} else {
					System.out.println("Spiel wird beendet");
				}

			}

		} else {
			System.out.println("Spiel wird beendet");
		}

		sc.close();

	}
}
