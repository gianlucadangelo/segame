package game;

import java.util.Scanner;

public class SpielUI {

	public static void main(String[] arg) {
		System.out.println("Spielen Ja oder nein? ");
		Scanner sc = new Scanner(System.in);

		String was = sc.nextLine();
		// String was = "ja";
		if (was.toLowerCase().equals("ja")) {
			boolean game = true;
			System.out.println("Spieler1");
			String spieler1, spieler2;
			spieler1 = sc.nextLine();
			System.out.println("Spieler2");
			spieler2 = sc.nextLine();
			Spiel sp = new Spiel();

			if (spieler1.toLowerCase().contains("computer")) {
				System.out.println("Auswahl Schwierigkeitsgrad für" + spieler1 + ":" + "\n"
						+ "Bitte normal bzw. schwer eingeben.");
				String mode = sc.nextLine();
				boolean korrekt = false;
				do {
					if (mode.toLowerCase().equals("schwer")) {
						Computer cpS = new Computer("1#" + spieler1, 0, true);
						Spiel.addSpieler(cpS);
						korrekt = true;
					} else if (mode.toLowerCase().equals("normal")) {
						Computer cp = new Computer("1#" + spieler1, 0, false);
						Spiel.addSpieler(cp);
						korrekt = true;
					} else {
						System.out.println("falsche Eingabe, bitte erneut versuchen.");

					}
				} while (!korrekt);

			} else {
				Mensch mensch = new Mensch("1#" + spieler1, 0);
				Spiel.addSpieler(mensch);
			}
			if (spieler2.toLowerCase().contains("computer")) {
				System.out.println("Auswahl Schwierigkeitsgrad für" + spieler2 + ":" + "\n"
						+ "Bitte normal bzw. schwer eingeben.");
				String mode = sc.nextLine();
				boolean korrekt = false;
				do {
					if (mode.toLowerCase().equals("schwer")) {
						Computer cpS = new Computer("2#" + spieler2, 0, true);
						Spiel.addSpieler(cpS);
						korrekt = true;
					} else if (mode.toLowerCase().equals("normal")) {
						Computer cp = new Computer("2#" + spieler2, 0, false);
						Spiel.addSpieler(cp);
						korrekt = true;
					} else {
						System.out.println("falsche Eingabe, bitte erneut versuchen.");

					}
				} while (!korrekt);

			} else {
				Mensch mensch = new Mensch("2#" + spieler2, 0);
				Spiel.addSpieler(mensch);

			}
			sp.start();
			while (game) {
				System.out.println("Spielfeld");
				System.out.println(sp.sf.toString());
				if (sp.gibSpieler().getName().contains("computer")) {
					Computer cp = (Computer) sp.gibSpieler();
					if (cp.isSchwer()) {
						sp.zieheComputerSchwer(sp.aktSpieler);

					} else {
						int zufallszahl = (int) (Math.random() * 6);
						sp.ziehe(sp.gibSpieler(), zufallszahl);
					}
				} else {
					System.out.println(sp.aktSpieler.getName());
					System.out.println(sp.aktSpieler.getSteine());
					System.out.println("Gib spalte an");
					int spalte = sc.nextInt() - 1;
					sp.ziehe(sp.gibSpieler(), spalte);
				}
				game = !(sp.hatGewonnen());
				if (game = !false) {
					sp.getStatus();
					sp.changeSpieler();
				}

			}

		} else {
			System.out.println("Spiel beendet");
		}

		sc.close();

	}
}
