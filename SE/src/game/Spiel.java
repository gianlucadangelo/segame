package game;
import java.util.Scanner;
import java.util.Random;
public class Spiel {
	public static Spieler[] spielerListe;
	public static int spielercounter = 0;
	Spieler aktSpieler;
	Spielfeld sf;

	Spiel() {
		spielerListe = new Spieler[2];

		start();
	}

	/**
	 * zieht die Steine vom angegebenem Feld und ruft sich so lange selbst wieder auf
	 * bis das Feld, welches nach dem Feld kommt in dem die letzte Kugel eingelegt wurde, leer ist
	 * Dann wird removeStones aufgerufen um die Steine aus dem übernaächsten Feld + unde dessen gegenseite 
	 * zu entfernen und dem aktuellen Spieler zu übergeben
	 * @param reihe Die Reihe des ausgewählten Feld für den Zugbeginn
	 * @param spalte Die Spalte des ausgewählten Feld für den Zugbeginn
	 * @param akt Der aktuelle Spieler, welcher den Zug macht
	 */
	public void ziehe( Spieler akt,int spalte, int reihe,Scanner sc) {
		int reiheKaru=akt.getReihe();
		int spalteKaru=0;
		String spielerKaru="";
		int[][] spielfeld = sf.getSpielfeld();
		int ziehSteine = spielfeld[reihe][spalte];
		spielfeld[reihe][spalte]=0;
		sf.setSpielfeld(spielfeld);
		int platzierung = sf.zugReihenfolgenPlatzierung(reihe,spalte);
		if(platzierung==13)platzierung=0;
		else platzierung++;
		String input;
		//Diese Schleife wiederholt sich so oft wieviele Steine platziert werden muessen und geht die Eintraege des Arrays
		//Zugreihenfolge durch und ließt daraus das nächste Feld aus
		for(int i = 0; i<ziehSteine ; i++) {
			if(platzierung==14)platzierung=0;
			System.out.println("Drücke ENTER zum fortfahren oder geben Sie KARU ein um ein Karu anzugeben.");
			input = sc.nextLine();
			
			
//			if(input.equals("")) {
//				String[] zwischenSpeicher = sf.zugReihenfolge[platzierung].split("#");
//				reihe = Integer.parseInt(zwischenSpeicher[0]);
//				spalte = Integer.parseInt(zwischenSpeicher[1]);
//				spielfeld[reihe][spalte]++;
//				if(platzierung==13)platzierung=0;
//				else platzierung++;
//				System.out.println(sf.toString());
			while(input.equalsIgnoreCase("karu")) {
				if(sf.karuCheck() !=true) System.out.println("Es gibt kein Karu zu beanspruchen.");
				else {
					boolean bedingung = true;
					if(!(akt.getAndererSpieler().getName().equalsIgnoreCase("ki")) ) {
						
					
						do {
								System.out.println("Geben Sie den Spieler an der das Karu beansprucht.");
								spielerKaru = sc.nextLine();
									if(spielerKaru.equals(akt.getName() ) ) {
										bedingung = false;
										reiheKaru=akt.getReihe();
									}
									else if(spielerKaru.equals(akt.getAndererSpieler().getName())) {
										bedingung = false;
										reiheKaru=akt.getAndererSpieler().getReihe();
									}
									else {
										System.out.println("Bitte geben Sie den Namen des Spielers korrekt ein.");
									}
								
							}while(bedingung);
					}	
					
					do {
						System.out.println("Geben Sie die Spalte vom Feld mit dem Karu an.");
						try {
							spalteKaru = sc.nextInt()-1;
							sc.nextLine();
							bedingung=true;
						}catch(Exception e) {
							System.out.println("Falsche Eingabe. Bitte geben Sie die Spalte mit den Zahlen 1-7 an");
							sc.next();
						}
					}while(!bedingung);
					
					
					
					if(sf.getKaruListe().contains(reiheKaru+"#"+spalteKaru)) {
						System.out.print("Eingabe korrekt\n");
						akt.getSpielerVonListe(reiheKaru).addSteine(spielfeld[reiheKaru][spalteKaru]);
						spielfeld[reiheKaru][spalteKaru]=0;
						sf.setSpielfeld(spielfeld);
						System.out.println(sf.toString());
						System.out.println("Drücke ENTER zum fortfahren oder geben Sie KARU ein um das nächste Karu anzugeben.");
						input = sc.nextLine();
					}
					else {
						System.out.println("Falsche Eingabe, in diesem Feld gibt es kein Karu. Der naechste Zug wird ausgefuehrt\n");
					}
					
				}
				
			}
			String[] zwischenSpeicher = sf.zugReihenfolge[platzierung].split("#");
			reihe = Integer.parseInt(zwischenSpeicher[0]);
			spalte = Integer.parseInt(zwischenSpeicher[1]);
			spielfeld[reihe][spalte]++;
			if(platzierung==13)platzierung=0;
			else platzierung++;
			System.out.println(sf.toString());
		
		}
		//falls das nächste Feld nicht leer ist, wird ziehen rekursiv wieder aufgerufen mit der Position des
		//nächsten Feldes
//		System.out.println("Drücke ENTER zum fortfahren oder geben Sie KARU ein um ein Karu anzugeben.");
//		input = sc.nextLine();
//		if(input.equalsIgnoreCase("karu")) {
//			if(sf.karuCheck() !=true) System.out.println("Es gibt kein Karu zu beanspruchen.");
//			else {
//				boolean bedingung = false;
//				do {
//					System.out.println("Geben Sie die Reihe vom Feld mit dem Karu an.");
//					try {
//						reiheEingabe = sc.nextInt()-1;
//						bedingung=true;
//					}catch(Exception e) {
//						System.out.println("Falsche Eingabe.");
//						sc.next();
//					}
//					
//				}while(!bedingung);
//				
//				
//				
//				do {
//					System.out.println("Geben Sie die Spalte vom Feld mit dem Karu an.");
//					try {
//						spalteEingabe = sc.nextInt()-1;
//						bedingung=false;
//					}catch(Exception e) {
//						System.out.println("Falsche Eingabe.");
//						sc.next();
//					}
//				}while(bedingung);
//				
//				if(sf.getKaruListe().contains(reiheEingabe+"#"+spalteEingabe)) {
//					System.out.print("Eingabe korrekt\n");
//					akt.getSpielerVonListe(reiheEingabe).addSteine(spielfeld[reiheEingabe][spalteEingabe]);
//					spielfeld[reiheEingabe][spalteEingabe]=0;
//				}
//				else {
//					System.out.println("Falsche Eingabe, in diesem Feld gibt es kein Karu. Der naechste Zug wird ausgefuehrt\n");
//				}
//			}
//		}
		//System.out.println(sf.toString());
		if(sf.nextNotZero(reihe,spalte)) {
			String[]zwischenSpeicher = sf.zugReihenfolge[platzierung].split("#");
			reihe = Integer.parseInt(zwischenSpeicher[0]);
			spalte = Integer.parseInt(zwischenSpeicher[1]);
			ziehe(akt,spalte,reihe,sc);
		}	
		//Falls das nächste Feld leer ist, wird removeStones mit der Position des übernächsten Feldes aufgerufen
		else {
			if(platzierung == 13)platzierung = 0;
			else platzierung++;
			String[]zwischenSpeicher = sf.zugReihenfolge[platzierung].split("#");
			reihe = Integer.parseInt(zwischenSpeicher[0]);
			spalte = Integer.parseInt(zwischenSpeicher[1]);
			sf.removeStones(spalte,akt);
			}	
		
		}
	


	public void zieheKI(Spieler akt,int spalte, int reihe, Scanner sc,boolean schwer) {
		String input="";
		int reiheKaru = akt.getAndererSpieler().getReihe();
		int spalteKaru=0;
		boolean karuNehmen;
		Random zufall = new Random();
		
		int[][] spielfeld = sf.getSpielfeld();
		int ziehSteine = spielfeld[reihe][spalte];
		spielfeld[reihe][spalte]=0;
		sf.setSpielfeld(spielfeld);
		int platzierung = sf.zugReihenfolgenPlatzierung(reihe,spalte);
		if(platzierung==13)platzierung=0;
		else platzierung++;
		
		for(int i = 0; i<ziehSteine ; i++) {
			if(schwer) {
				 karuNehmen = true;
			}
			else {
				
				karuNehmen = zufall.nextBoolean();
				}
			
			if(platzierung==14)platzierung=0;
			
			System.out.println("Drücke ENTER zum fortfahren oder geben Sie KARU ein um ein Karu anzugeben.");
			input = sc.nextLine();
			
			
			if(input.equals("")) {
				String[] zwischenSpeicher = sf.zugReihenfolge[platzierung].split("#");
				reihe = Integer.parseInt(zwischenSpeicher[0]);
				spalte = Integer.parseInt(zwischenSpeicher[1]);
				spielfeld[reihe][spalte]++;
				if(platzierung==13)platzierung=0;
				else platzierung++;
				System.out.println(sf.toString());
			}
			else if(input.equalsIgnoreCase("karu")) {
				if(sf.karuCheck() !=true) System.out.println("Es gibt kein Karu zu beanspruchen.");
				else {
					boolean bedingung = false;
					
					do {
						System.out.println("Geben Sie die Spalte vom Feld mit dem Karu an.");
						try {
							spalteKaru = sc.nextInt()-1;
							bedingung=true;
						}catch(Exception e) {
							System.out.println("Falsche Eingabe. Bitte geben Sie die Spalte mit den Zahlen 1-7 an");
							sc.next();
						}
					}while(!bedingung);
					
					
					
					if(sf.getKaruListe().contains(reiheKaru+"#"+spalteKaru)) {
						System.out.print("Eingabe korrekt\n");
						akt.getSpielerVonListe(reiheKaru).addSteine(spielfeld[reiheKaru][spalteKaru]);
						spielfeld[reiheKaru][spalteKaru]=0;
					}
					else {
						System.out.println("Falsche Eingabe, in diesem Feld gibt es kein Karu. Der naechste Zug wird ausgefuehrt\n");
					}
					
					if(karuNehmen) {
						if(sf.karuCheck()) {
							for(int a = 0; a<7;a++) {
								if(sf.getKaruListe().contains(akt.getReihe()+"#"+a)) {
									System.out.println("KI nimmt Karu.");
									int[][]array = sf.getSpielfeld();
									akt.addSteine(spielfeld[akt.getReihe()][a]);
									spielfeld[akt.getReihe()][a]=0;
								}
							}
								
						}
					}
					
					String[] zwischenSpeicher = sf.zugReihenfolge[platzierung].split("#");
					reihe = Integer.parseInt(zwischenSpeicher[0]);
					spalte = Integer.parseInt(zwischenSpeicher[1]);
					spielfeld[reihe][spalte]++;
					if(platzierung==13)platzierung=0;
					else platzierung++;
					System.out.println(sf.toString());
				}
				
			}
		
		}
		if(sf.nextNotZero(reihe,spalte)) {
			String[]zwischenSpeicher = sf.zugReihenfolge[platzierung].split("#");
			reihe = Integer.parseInt(zwischenSpeicher[0]);
			spalte = Integer.parseInt(zwischenSpeicher[1]);
			ziehe(akt,spalte,reihe,sc);
		}	
		//Falls das nächste Feld leer ist, wird removeStones mit der Position des übernächsten Feldes aufgerufen
		else {
			if(platzierung == 13)platzierung = 0;
			else platzierung++;
			String[]zwischenSpeicher = sf.zugReihenfolge[platzierung].split("#");
			reihe = Integer.parseInt(zwischenSpeicher[0]);
			spalte = Integer.parseInt(zwischenSpeicher[1]);
			sf.removeStones(spalte,akt);
			}	
		
	}
	
	private int ziehenSimulation(Spieler akt,int reihe, int spalte) {
		int ergebnis = 0;
		int[][] spielfeld = sf.getSpielfeld();
		int ziehSteine = spielfeld[reihe][spalte];
		spielfeld[reihe][spalte]=0;
		int platzierung = sf.zugReihenfolgenPlatzierung(reihe,spalte);
		if(platzierung==13)platzierung=0;
		else platzierung++;
		for(int i = 0;i<ziehSteine;i++) {
			
			if(sf.karuCheck()) {
				for(int a = 0; a<7;a++) {
					if(sf.getKaruListe().contains(akt.getReihe()+"#"+a)) {
						int[][]array = sf.getSpielfeld();
						ergebnis+=(spielfeld[akt.getReihe()][a]);
						spielfeld[akt.getReihe()][a]=0;
					}
				}
			}

			String[] zwischenSpeicher = sf.zugReihenfolge[platzierung].split("#");
			reihe = Integer.parseInt(zwischenSpeicher[0]);
			spalte = Integer.parseInt(zwischenSpeicher[1]);
			spielfeld[reihe][spalte]++;
			if(platzierung==13)platzierung=0;
			else platzierung++;
			
		}
		if(sf.nextNotZero(reihe,spalte)) {
			String[]zwischenSpeicher = sf.zugReihenfolge[platzierung].split("#");
			reihe = Integer.parseInt(zwischenSpeicher[0]);
			spalte = Integer.parseInt(zwischenSpeicher[1]);
			ziehenSimulation(akt,reihe,spalte);
		}	
		//Falls das nächste Feld leer ist, wird removeStones mit der Position des übernächsten Feldes aufgerufen
		else {
			if(platzierung == 13)platzierung = 0;
			else platzierung++;
			String[]zwischenSpeicher = sf.zugReihenfolge[platzierung].split("#");
			reihe = Integer.parseInt(zwischenSpeicher[0]);
			spalte = Integer.parseInt(zwischenSpeicher[1]);
			ergebnis+= spielfeld[0][spalte];
			ergebnis+= spielfeld[1][spalte];
			}	
		return  ergebnis;
	}
	
	public int zugBerechnen(KI ki) {
		int zug=0;
		int[]zuege= new int[7];
		if(!ki.getSchwer()) {
			Random zufall = new Random();
			zug = zufall.nextInt(7);
		}
		else{
			for(int i = 0;i<7;i++) {
				Spiel clone = this;
				zuege[i]=clone.ziehenSimulation(ki,ki.getReihe(),i);
			}
			for(int punkte : zuege) {
				if(punkte>zug) {
					zug=punkte;
				}
			}
		}
		return zug;
	}
	
	public void start() {
		sf = new Spielfeld();
		int zufall = (int)((Math.random()) * 2 );
		aktSpieler = spielerListe[zufall];
	}

	public static void addSpieler(Spieler name) {
		spielerListe[spielercounter++] = name;

	}

	public void getStatus() {
	//	System.out.println("\n Nachher \n");
		System.out.println(sf.toString());
		System.out.println("Am Zug: "+aktSpieler.getName()+" hat "+aktSpieler.getSteine()+" Steine.");
		System.out.println("        "+aktSpieler.getAndererSpieler().getName()+" hat "+aktSpieler.getAndererSpieler().getSteine()+" Steine.");
		System.out.println("\n");
		
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

	public void zieheKISchwer(Spieler aktSpieler1) {

	}

}
