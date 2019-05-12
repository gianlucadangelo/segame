
public class Spielfeld {

	private int[][] spielfeld;
	private int spalte;
	private int reihe;

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

	@Override
	public String toString() {
		return "Oben \n " + "|"+spielfeld[0][0] +"|"+ spielfeld[0][1] +"|"+ spielfeld[0][2] +"|"+ spielfeld[0][3] + "|"+spielfeld[0][4]
				+ "|"+spielfeld[0][5] + "|"+spielfeld[0][6] + "\n Unten \n" +"|"+ spielfeld[1][0] +"|"+ spielfeld[1][1]
				+"|"+ spielfeld[1][2] + "|"+spielfeld[1][3] + "|"+spielfeld[1][4] + "|"+spielfeld[1][5] +"|"+ spielfeld[1][6];

	}

	public void ziehe(int reihe, int spalte) {
		System.out.println(this.toString());
		int steine = spielfeld[reihe][spalte];
		spielfeld[reihe][spalte] = 0;
		// Schau ob wir oben oder unten sind
		if (reihe == 0) {

				oben(reihe, spalte - 1, steine);
		
		} else {
			// Wir sind unten
				unten(reihe, spalte + 1, steine);
			
		}


	}



	private void unten(int reihe, int spalte, int steine) {
		// 1 0 13 Steine [[6, 6, 6, 0, 5, 5, 5], [6, 5, 0, 5, 5, 5, 5]] -- [6, 0, 0, 5, 5, 5, 5]
		// [[7, 7, 7, 1, 6, 0, 6], [6, 6, 0, 6, 6, 6, 6]]
		int bis = spalte + steine;
		int ubergabeSteine = steine;
		// int ubergabeStein = steine - spalte-1;
		// spalte 0 steine 8

		if (bis > 6) {
			
			for (int i = spalte; i <= 6; i++) {
				spielfeld[reihe][i]++;
				ubergabeSteine--;
			}
			oben(0, 6, ubergabeSteine);
		} else {
			int aktS = 0;
			for (int i = spalte; i < spalte+steine; i++) {
				spielfeld[reihe][i]++;
				aktS = i;
			}
			if (nextNotZero(aktS, reihe)) {
				if(aktS==6) {
					ziehe(0,6);
				}else {
					ziehe(reihe, aktS + 1);

				}
			} else {
				// nächste ist 0 
				removeStones(aktS, reihe);

				
				
			}
		}
	}

	private void oben(int reihe, int spalte, int steine) {
		// bsp spalte 2 15 steine [[6, 6, 6, 0, 5, 0, 6], [6, 6, 0, 6, 6, 6, 6]]
		// 0 0 15 0 0 0 0
		// zweiter durchlauf 7 steine
		int bis = spalte - steine + 1;
		int ubergabeSteine = steine;
		if (bis < 0) {
			for (int i = spalte; i >= 0; i--) {
				spielfeld[reihe][i]++;
				ubergabeSteine--;
			}

			unten(1, 0, ubergabeSteine);
		} else {
			int aktS = 0;
			int ende = spalte-steine;
			for (int i = spalte; i > ende; i--) {
				spielfeld[reihe][i]++;
				aktS = i;
			}
			if (nextNotZero(aktS, reihe)) {
				if(aktS==0) {
					ziehe(1, 0);
				}else {
					ziehe(reihe, aktS  -1);
				}
			}else {
				//next is zero
				removeStones(aktS, reihe);
			}

		}

	}

	private void removeStones(int spalte, int reihe) {
		if(reihe==0) {
			spielfeld[1][spalte]=0;
			if(spalte!=0) {
				spielfeld[0][spalte-1]=0;
			}
		}else {
			spielfeld[0][spalte]=0;
			if(spalte!=6) {
				spielfeld[1][spalte+1]=0;
			}
		}
	}
	private boolean nextNotZero(int spalte, int reihe) {
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

}
