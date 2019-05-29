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
			boolean name = false;
			do {
				spieler2=sc.nextLine();
				if (spieler2.toLowerCase().equals("ki"))
					name =true;
				if(spieler2.toLowerCase().equals(spieler1.toLowerCase())&&!(spieler2.toLowerCase().equals("ki")) )
				System.out.println("Bitte anderen Namen für Spieler 2 wählen!");
				
				 
				else {
					name=true;
				}
			}while(!name);
			
			
			
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
				boolean spielerwechsel = false;
				sp.getStatus();
				if (sp.gibSpieler().getName().contains("ki")) {
//					KI cp = (KI) sp.gibSpieler();
//					if (cp.isSchwer()) {
//						sp.zieheKISchwer(sp.aktSpieler);
//
//					} else {
//						int zufallszahl = (int) (Math.random() * 6);
//						sp.ziehe(sp.gibSpieler(), zufallszahl,sc);
//					}
				} else {
					if (sp.aktSpieler.getAndererSpieler().getSteine() >= 36) {
						System.out.println("Möchte "+sp.aktSpieler.getName()+" aufgeben? Bitte geben Sie JA oder NEIN ein.");
						if (sc.nextLine().toLowerCase().equals("ja")) {
							System.out.println("Herzlichen Glückwunsch. " + sp.aktSpieler.getAndererSpieler().getName() + " hat gewonnen.\n");
							System.out.println("Spiel wird beendet.");
							
							sc.close();
							break;
							}

						}

					
						System.out.println("Wählen Sie die gewünschte Spalte (1 bis 7) aus.");
						try {
							
							int spalte = Integer.parseInt(sc.nextLine())-1;
						
						
						while( (spalte < 0 || spalte > 6) || (sp.sf.fieldNotEmpty(sp.gibSpieler().getReihe() ,spalte)!=true) ) {
							if(spalte > 6 || spalte < 0) System.out.println("Bitte geben Sie eine Spalte von 1-7 an");
							else System.out.println(" Bitte waehlen Sie ein Feld aus, was nicht leer ist.");
							 spalte = Integer.parseInt(sc.nextLine())-1;
						}
						sp.ziehe(sp.gibSpieler(), spalte, sp.gibSpieler().getReihe(),sc);
						
						}catch(NumberFormatException ex) {
							System.out.println("Bitte geben Sie eine Spalte von 1-7 an");
							spielerwechsel = true;
							}
					
				}
				
				game = !(sp.hatGewonnen());
				if (game) {
					sp.getStatus();
					if(!spielerwechsel)
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
