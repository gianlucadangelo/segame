package game;

abstract class Spieler {
	
	private String name;
	private int steine;
	private int reihe;
	static Spieler[] liste = new Spieler[2];
	
	public Spieler(String name, int steine,int reihe) {
		setName(name);
		setSteine(steine);
		setReihe(reihe);
		setSpielerVonListe();
	}
	
	public int getReihe() {
		return reihe;
	}

	
	public Spieler getAndererSpieler() {
		for(Spieler a : liste) {
			if(a!=this)return a;
			
		}
		return this;
	}
	
	public Spieler getSpielerVonListe(int a) {
		return liste[a];
	}
	
	public void setSpielerVonListe() {
		liste[reihe]=this;
	}
	
	public void setReihe(int reihe) {
		this.reihe = reihe;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSteine() {
		return steine;
	}
	public void setSteine(int steine) {
		this.steine = steine;
	}
	
	public void addSteine(int steine) {
		this.steine=this.steine+steine;
	}
	

}
