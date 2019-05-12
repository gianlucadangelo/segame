import java.util.Scanner;

public class SpielUI {

	public static void main (String[]arg) {
		System.out.println("Spielen Ja oder nein? ");
		Scanner sc = new Scanner(System.in);

		//String was = sc.nextLine();
		String was = "ja";
		if(was.toLowerCase().equals("ja")) {
			boolean game = true;
			System.out.println("Spieler1");
			String spieler1,spieler2;
			//spieler1 = sc.nextLine();
			spieler1="test";
			System.out.println("Spieler2");
			//spieler2 = sc.nextLine();
			Spiel sp = new Spiel();
			spieler2 = "test2";
			
			if(spieler1.toLowerCase().equals("computer")) {
				Computer cp = new Computer("1#"+spieler1, 0, false);
				sp.addSpieler(cp);
			}else {
				Mensch mensch = new Mensch("1#"+spieler1,0);
				sp.addSpieler(mensch);
			}
			if(spieler2.toLowerCase().equals("computer")) {
				Computer cp = new Computer("2#"+spieler1, 0, false);
				sp.addSpieler(cp);

			}else {
				Mensch mensch = new Mensch("2#"+spieler1,0);
				sp.addSpieler(mensch);

			}
			sp.start();
			while(game) {
				System.out.println(sp.aktSpieler);
				System.out.println("Gib spalte an");
				int spalte = sc.nextInt();
				sp.ziehe(sp.gibSpieler(), spalte);
				sp.getStatus();
				sp.changeSpieler();
				}

		}else {
			System.out.println("Ciao du hund");
		}
		
		
		sc.close();
		
		}
}
