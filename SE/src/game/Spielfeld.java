 package game;
import java.util.Scanner;
import java.util.ArrayList;
public class Spielfeld {

	private int[][] spielfeld;
	private int spalte;
	private int reihe;
	private ArrayList<String>karuListe = new ArrayList<String>() ;
	
	/**
	 *  Ein Array was die Zugreihenfolge angibt an der sich die ziehen Methode entlanghangelt
	 */
	public String[] zugReihenfolge = new String[] {"0#6","0#5","0#4","0#3","0#2","0#1","0#0","1#0","1#1","1#2","1#3","1#4","1#5","1#6"};

	Spielfeld() {
		spielfeld = new int[2][7];
		fillFields();
	}
	
	Spielfeld(int[][] spielfeld){
		this.spielfeld=spielfeld;
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
	
	public ArrayList<String> getKaruListe(){
		return this.karuListe;
	}
	
	public void karuAusListeEntfernen(int reihe, int feld) {
		karuListe.remove(reihe+"#"+feld);
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
	
	public int[][] getSpielfeld(){
		return this.spielfeld;
	}
	
	public void setSpielfeld(int[][]sf) {
		this.spielfeld=sf;
	}
	
	
	
	/**
	 *  findet die Position des angegebenen Spielfeldes im Array
	 *  wieder und gibt diese zurück
	 * @param reihe Ist die Reihe des übergebenen  Spielfeldes
	 * @param spalte Ist die Spalte des übergebenen Spielfeldes
	 * @return ist die Position im Array
	 */
	public int zugReihenfolgenPlatzierung(int reihe, int spalte){
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
	public void removeStones(int spalte, Spieler akt) {
		int punkte;
		punkte = spielfeld[0][spalte];
		punkte += spielfeld[1][spalte];
		spielfeld[0][spalte]=0;
		spielfeld[1][spalte]=0;
		akt.addSteine(punkte);
	}

	public boolean nextNotZero(int reihe, int spalte) {
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
	
	public boolean karuCheck() {
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
	
	public int[][] getSpielfeldCopy(){
		int[][]copy = new int[2][7];
		int zaehlerReihe=0;
		int zaehlerSpalte=0;
		for(int[]reihe:spielfeld) {
			for(int spalte:reihe) {
				copy[zaehlerReihe][zaehlerSpalte]=spalte;
				zaehlerSpalte++;
			}
			zaehlerReihe++;
			zaehlerSpalte=0;
		}
		return copy;
	}
}
