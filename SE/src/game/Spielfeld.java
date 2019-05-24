package game;

public class Spielfeld {

	private int[][] spielfeld;
	private int spalte;
	private int reihe;
	/**
	 *  Ein Array was die Zugreihenfolge angibt an der sich die ziehen Methode entlanghangelt
	 */
	private String[] zugReihenfolge;

	Spielfeld() {
		spielfeld = new int[2][7];
		fillFields();
		zugReihenfolge = new String[] {"0#6","0#5","0#4","0#3","0#2","0#1","0#0","1#0","1#1","1#2","1#3","1#4","1#5","1#6"};
	}

	private void fillFields() {
		for (int i = 0; i < spielfeld.length; i++) {
			for (int j = 0; j < spielfeld[i].length; j++) {
				spielfeld[i][j] = 5;
			}
		}
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
	public void ziehen(int reihe, int spalte, Spieler akt) {
		System.out.println(this.toString());
		int ziehSteine = spielfeld[reihe][spalte];
		spielfeld[reihe][spalte]=0;
		int platzierung = zugReihenfolgenPlatzierung(reihe,spalte)+1;
		//Diese Schleife wiederholt sich so oft wieviele Steine platziert werden muessen und geht die Eintraege des Arrays
		//Zugreihenfolge durch und ließt daraus das nächste Feld aus
		for(int i = 0; i<ziehSteine ; i++) {
			String[] zwischenSpeicher = zugReihenfolge[platzierung].split("#");
			reihe = Integer.parseInt(zwischenSpeicher[0]);
			spalte = Integer.parseInt(zwischenSpeicher[1]);
			spielfeld[reihe][spalte]++;
			platzierung++;
			if(platzierung>13)platzierung=0;
		}
		//falls das nächste Feld nicht leer ist, wird ziehen rekursiv wieder aufgerufen mit der Position des
		//nächsten Feldes
		if(nextNotZero(reihe,spalte)) {
			String[]zwischenSpeicher = zugReihenfolge[platzierung].split("#");
			reihe = Integer.parseInt(zwischenSpeicher[0]);
			spalte = Integer.parseInt(zwischenSpeicher[1]);
			ziehen(reihe,spalte,akt);
		}
		//Falls das nächste Feld leer ist, wird removeStones mit der Position des übernächsten Feldes aufgerufen
		else {
			String[]zwischenSpeicher = zugReihenfolge[platzierung+1].split("#");
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

//	public void ziehe(int reihe, int spalte, Spieler akt) {
//		System.out.println(this.toString());
//		int steine = spielfeld[reihe][spalte];
//		spielfeld[reihe][spalte] = 0;
//		// Schau ob wir oben oder unten sind
//		if (reihe == 0) {
//
//			oben(reihe, spalte - 1, steine, akt);
//
//		} else {
//			// Wir sind unten
//			unten(reihe, spalte + 1, steine, akt);
//
//		}
//
//	}

//	private void unten(int reihe, int spalte, int steine, Spieler akt) {
//		// 1 0 13 Steine [[6, 6, 6, 0, 5, 5, 5], [6, 5, 0, 5, 5, 5, 5]] -- [6, 0, 0, 5,
//		// 5, 5, 5]
//		// [[7, 7, 7, 1, 6, 0, 6], [6, 6, 0, 6, 6, 6, 6]]
//		int bis = spalte + steine;
//		int ubergabeSteine = steine;
//		// int ubergabeStein = steine - spalte-1;
//		// spalte 0 steine 8
//
//		if (bis > 6) {
//
//			for (int i = spalte; i <= 6; i++) {
//				spielfeld[reihe][i]++;
//				this.kanuCheck(reihe, i);
//				ubergabeSteine--;
//			}
//			oben(0, 6, ubergabeSteine, akt);
//		} else {
//			int aktS = 0; 
//			for (int i = spalte; i < spalte + steine; i++) {
//				spielfeld[reihe][i]++;
//				this.kanuCheck(reihe, i);
//
//				aktS = i;
//			}
//			if (nextNotZero(reihe, aktS)) {
//				if (aktS == 6) {
//					ziehe(0, 6, akt);
//				} else {
//					ziehe(reihe, aktS + 1, akt);
//
//				}
//			} else {
//				// nÃ¤chste ist 0
//				removeStones(aktS, reihe, akt);
//
//			}
//		}
//	}

//	private void oben(int reihe, int spalte, int steine, Spieler akt) {
//		// bsp spalte 2 15 steine [[6, 6, 6, 0, 5, 0, 6], [6, 6, 0, 6, 6, 6, 6]]
//		// 0 0 15 0 0 0 0
//		// zweiter durchlauf 7 steine
//		int bis = spalte - steine + 1;
//		int ubergabeSteine = steine;
//		if (bis < 0) {
//			for (int i = spalte; i >= 0; i--) {
//				spielfeld[reihe][i]++;
//				this.kanuCheck(reihe, i);
//
//				ubergabeSteine--;
//			}
//
//			unten(1, 0, ubergabeSteine, akt);
//		} else {
//			int aktS = 0;
//			int ende = spalte - steine;
//			for (int i = spalte; i > ende; i--) {
//				spielfeld[reihe][i]++;
//				this.kanuCheck(reihe, i);
//
//				aktS = i;
//			}
//			if (nextNotZero(reihe, aktS)) {
//				if (aktS == 0) {
//					ziehe(1, 0, akt);
//				} else {
//					ziehe(reihe, aktS - 1, akt);
//				}
//			} else {
//				// next is zero
//				removeStones(aktS, reihe, akt);
//			}
//
//		}
//
//	}
	
//	private void kanuCheck(int reihe, int spalte) {
//		
//		if(spielfeld[reihe][spalte]==4) {
//			int steine = 4;
//			spielfeld[reihe][spalte]=0;
//			if(reihe==0) {
//				Spieler sp1 = Spiel.spielerListe[0];
//				sp1.addSteine(steine);
//			}else {
//				Spieler sp2 = Spiel.spielerListe[0];
//				sp2.addSteine(4);
//
//			}
//		}
//	}

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
		String[] array = akt.getName().split("#");
		String feld = array[0];
		int check = Integer.valueOf(feld) - 1;
		
		if(this.spielfeld[check][0] ==0 && this.spielfeld[check][1] ==0 && this.spielfeld[check][2] ==0 && this.spielfeld[check][3] ==0 && this.spielfeld[check][4] ==0 && this.spielfeld[check][5] ==0 && this.spielfeld[check][6] ==0) {
			cafeFull = true;
			int gesSteineAufFeld=0;
			for (int[] reihe : spielfeld) {
				for (int steine : reihe) {	
					gesSteineAufFeld= gesSteineAufFeld+steine;
				}		
			}
			akt.addSteine(gesSteineAufFeld); // Falsch --> Steine werden aktuellen Spieler gutgeschrieben und nicht dem auf welcher Seite die Steine liegen
		}
		
		return cafeFull;
		
	}
}
