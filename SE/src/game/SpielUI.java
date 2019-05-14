package game;
import java.util.Scanner;

public class SpielUI {

	public static void main (String[]arg) {
		System.out.println("Spielen Ja oder nein? ");
		Scanner sc = new Scanner(System.in);

		String was = sc.nextLine();
		//String was = "ja";
		if(was.toLowerCase().equals("ja")) {
			boolean game = true;
			System.out.println("Spieler1");
			String spieler1,spieler2;
			spieler1 = sc.nextLine();
			System.out.println("Spieler2");
			spieler2 = sc.nextLine();
			Spiel sp = new Spiel();
			
			if(spieler1.toLowerCase().contains("computer")) {
				Computer cp = new Computer("1#"+spieler1, 0, false);
				sp.addSpieler(cp);
			}else {
				Mensch mensch = new Mensch("1#"+spieler1,0);
				sp.addSpieler(mensch);
			}
			if(spieler2.toLowerCase().contains("computer")) {
				Computer cp = new Computer("2#"+spieler2, 0, false);
				sp.addSpieler(cp);

			}else {
				Mensch mensch = new Mensch("2#"+spieler2,0);
				sp.addSpieler(mensch);

			}
			sp.start();
			while(game) {
				if(sp.gibSpieler().getName().contains("computer")) {
					Computer cp = (Computer) sp.gibSpieler();
					if(cp.isSchwer()) {
					
					}else {
					   int zufallszahl = (int)(Math.random() * 6); 
					    sp.ziehe(sp.gibSpieler(), zufallszahl);
					}
				}else {
					System.out.println(sp.aktSpieler.getName());
					System.out.println(sp.aktSpieler.getSteine());
					System.out.println("Gib spalte an");
					int spalte = sc.nextInt();
					sp.ziehe(sp.gibSpieler(), spalte);
				}

				sp.getStatus();
				sp.changeSpieler();
				}

		}else {
			System.out.println("Ciao du hund");
		}
		
		
		sc.close();
		
		}
}
