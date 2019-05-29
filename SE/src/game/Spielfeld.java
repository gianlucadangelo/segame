package game;
import java.util.Scanner;
import java.util.ArrayList;
public class Spielfeld {

	private int[][] spielfeld;
	private int spalte;
	private int reihe;
	ArrayList<String>karuListe = new ArrayList<String>() ;
	/**
	 *  Ein Array was die Zugreihenfolge angibt an der sich die ziehen Methode entlanghangelt
	 */
	private String[] zugReihenfolge = new String[] {"0#6","0#5","0#4","0#3","0#2","0#1","0#0","1#0","1#1","1#2","1#3","1#4","1#5","1#6"};

	Spielfeld() {
		spielfeld = new int[2][7];
		fillFields();
	}

	private void fillFields() {
		for (int i = 0; i < spielfeld.length; i++) {
			for (int j = 0; j < spielfeld[i].length; j++) {
				spielfeld[i][j] = 5;
			}
		}
	}

	public boolean fieldNotEmpty(int reihe, int spalte) {
		if(spielfeld[reihe][spalte]==0)return false;
		return true;
	}
	@Override
	public String toString() {
		return 	"-----------------------------------\n"
				+"Oben  " + "[" + spielfeld[0][0] + "] [" + spielfeld[0][1] + "] [" + spielfeld[0][2] + "] ["
				+ spielfeld[0][3] + "] [" + spielfeld[0][4] + "] [" + spielfeld[0][5] + "] [" + spielfeld[0][6] + "]"
				+ "\nUnten " + "[" + spielfeld[1][0] + "] [" + spielfeld[1][1] + "] [" + spielfeld[1][2] + "] ["
				+ spielfeld[1][3] + "] [" + spielfeld[1][4] + "] [" + spielfeld[1][5] + "] [" + spielfeld[1][6] + "]\n"
				+"-----------------------------------";
		
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
	public void ziehen(int reihe, int spalte, Spieler akt,Scanner sc) {
		int reiheEingabe=0;
		int spalteEingabe=0;
		int ziehSteine = spielfeld[reihe][spalte];
		spielfeld[reihe][spalte]=0;
		int platzierung = zugReihenfolgenPlatzierung(reihe,spalte);
		if(platzierung==13)platzierung=0;
		else platzierung++;
		String input;
		//Diese Schleife wiederholt sich so oft wieviele Steine platziert werden muessen und geht die Eintraege des Arrays
		//Zugreihenfolge durch und ließt daraus das nächste Feld aus
		for(int i = 0; i<ziehSteine ; i++) {
			if(platzierung==14)platzierung=0;
			System.out.println("Drücke ENTER zum fortfahren oder geben Sie KARU ein um ein Karu anzugeben.");
			input = sc.nextLine();
			
			
			if(input.equals("")) {
				String[] zwischenSpeicher = zugReihenfolge[platzierung].split("#");
				reihe = Integer.parseInt(zwischenSpeicher[0]);
				spalte = Integer.parseInt(zwischenSpeicher[1]);
				spielfeld[reihe][spalte]++;
				if(platzierung==13)platzierung=0;
				else platzierung++;
				System.out.println(this.toString());
			}
			else if(input.equalsIgnoreCase("karu")) {
				if(this.karu() !=true) System.out.println("Es gibt kein Karu zu beanspruchen.");
				else {
					boolean bedingung = false;
					do {
						System.out.println("Geben Sie die Reihe vom Feld mit dem Karu an.");
						try {
							reiheEingabe = sc.nextInt()-1;
							bedingung=true;
						}catch(Exception e) {
							System.out.println("Falsche Eingabe.");
							sc.next();
						}
						
					}while(!bedingung);
					
					
					
					do {
						System.out.println("Geben Sie die Spalte vom Feld mit dem Karu an.");
						try {
							spalteEingabe = sc.nextInt()-1;
							bedingung=false;
						}catch(Exception e) {
							System.out.println("Falsche Eingabe.");
							sc.next();
						}
					}while(bedingung);
					
					
					
					if(karuListe.contains(reiheEingabe+"#"+spalteEingabe)) {
						System.out.print("Eingabe korrekt\n");
						akt.getSpielerVonListe(reiheEingabe).addSteine(this.spielfeld[reiheEingabe][spalteEingabe]);
						this.spielfeld[reiheEingabe][spalteEingabe]=0;
					}
					else {
						System.out.println("Falsche Eingabe, in diesem Feld gibt es kein Karu. Der naechste Zug wird ausgefuehrt\n");
					}
					String[] zwischenSpeicher = zugReihenfolge[platzierung].split("#");
					reihe = Integer.parseInt(zwischenSpeicher[0]);
					spalte = Integer.parseInt(zwischenSpeicher[1]);
					spielfeld[reihe][spalte]++;
					if(platzierung==13)platzierung=0;
					else platzierung++;
					System.out.println(this.toString());
				}
				
			}
		
		}
		//falls das nächste Feld nicht leer ist, wird ziehen rekursiv wieder aufgerufen mit der Position des
		//nächsten Feldes
		System.out.println("Drücke ENTER zum fortfahren oder geben Sie KARU ein um ein Karu anzugeben.");
		input = sc.nextLine();
		if(input.equalsIgnoreCase("karu")) {
			if(this.karu() !=true) System.out.println("Es gibt kein Karu zu beanspruchen.");
			else {
				boolean bedingung = false;
				do {
					System.out.println("Geben Sie die Reihe vom Feld mit dem Karu an.");
					try {
						reiheEingabe = sc.nextInt()-1;
						bedingung=true;
					}catch(Exception e) {
						System.out.println("Falsche Eingabe.");
						sc.next();
					}
					
				}while(!bedingung);
				
				
				
				do {
					System.out.println("Geben Sie die Spalte vom Feld mit dem Karu an.");
					try {
						spalteEingabe = sc.nextInt()-1;
						bedingung=false;
					}catch(Exception e) {
						System.out.println("Falsche Eingabe.");
						sc.next();
					}
				}while(bedingung);
				
				if(karuListe.contains(reiheEingabe+"#"+spalteEingabe)) {
					System.out.print("Eingabe korrekt\n");
					akt.getSpielerVonListe(reiheEingabe).addSteine(this.spielfeld[reiheEingabe][spalteEingabe]);
					this.spielfeld[reiheEingabe][spalteEingabe]=0;
				}
				else {
					System.out.println("Falsche Eingabe, in diesem Feld gibt es kein Karu. Der naechste Zug wird ausgefuehrt\n");
				}
			}
		}
		System.out.println(this.toString());
		if(nextNotZero(reihe,spalte)) {
			String[]zwischenSpeicher = zugReihenfolge[platzierung].split("#");
			reihe = Integer.parseInt(zwischenSpeicher[0]);
			spalte = Integer.parseInt(zwischenSpeicher[1]);
			ziehen(reihe,spalte,akt,sc);
		}	
		//Falls das nächste Feld leer ist, wird removeStones mit der Position des übernächsten Feldes aufgerufen
		else {
			if(platzierung == 13)platzierung = 0;
			else platzierung++;
			String[]zwischenSpeicher = zugReihenfolge[platzierung].split("#");
			reihe = Integer.parseInt(zwischenSpeicher[0]);
			spalte = Integer.parseInt(zwischenSpeicher[1]);
			removeStones(spalte,akt);
			}	
		
		}
	
	
	/**
	 *  findet die Position des angegebenen Spielfeldes im Array
	 *  wieder und gibt diese zurück
	 * @param reihe Ist die Reihe des übergebenen  Spielfeldes
	 * @param spalte Ist die Spalte des übergebenen Spielfeldes
	 * @return ist die Position im Array
	 */
	private int zugReihenfolgenPlatzierung(int reihe, int spalte){
		int zaehler = 0;
		for(String speicher : zugReihenfolge) {
			String[] array = speicher.split("#");
			if(reihe == Integer.parseInt(array[0])){
				if(spalte== Integer.parseInt(array[1])) {
					return zaehler;
				}
			}
			zaehler++;
		}
		return -4;
	}


	/**
	 * Nimmt die Steine aus den Mulden und gibt Sie dem
	 * aktuellen Spieler
	 * @param spalte gibt die Spalte der betroffenen Mulden an
	 * @param akt ist der Spieler der aktuell am Zug ist
	 */
	private void removeStones(int spalte, Spieler akt) {
		int punkte;
		punkte = spielfeld[0][spalte];
		punkte += spielfeld[1][spalte];
		spielfeld[0][spalte]=0;
		spielfeld[1][spalte]=0;
		akt.addSteine(punkte);
	}

	private boolean nextNotZero(int reihe, int spalte) {
		boolean nextNotZero = false;
		if (reihe == 0) {
			if (spalte != 0) {
				if (spielfeld[reihe][spalte - 1] != 0) {
					nextNotZero = true;
				}
			} else {
				if (spielfeld[1][0] != 0) {
					nextNotZero = true;
				}
			}
		} else {
			if (spalte != 6) {
				if (spielfeld[reihe][spalte + 1] != 0) {
					nextNotZero = true;
				}
			} else {
				if (spielfeld[0][6] != 0) {
					nextNotZero = true;
				}
			}
		}
		return nextNotZero;
	}

	public int getSpalte() {
		return spalte;
	}

	public int getReihe() {
		return reihe;
	}
	
	public boolean spielerLeisteLeer(Spieler akt){
		boolean cafeFull = false;
		int check = akt.getReihe();
		
		if(this.spielfeld[check][0] ==0 && this.spielfeld[check][1] ==0 && this.spielfeld[check][2] ==0 && this.spielfeld[check][3] ==0 && this.spielfeld[check][4] ==0 && this.spielfeld[check][5] ==0 && this.spielfeld[check][6] ==0) {
			cafeFull = true;
			int gesSteineAufFeld=0;
			for (int[] reihe : spielfeld) {
				for (int steine : reihe) {	
					gesSteineAufFeld= gesSteineAufFeld+steine;
				}		
			}
			akt.getAndererSpieler().addSteine(gesSteineAufFeld); 
		}
		
		return cafeFull;
		
	}
	
	private boolean karu() {
		boolean karu = false;
		for(int i = 0; i<2;i++) {
			for(int j=0;j<7;j++) {
				
				if(this.spielfeld[i][j]==4) {
					karu=true;
					karuListe.add(i+"#"+j);
				}
			}
		}
		return karu;
	}
}
