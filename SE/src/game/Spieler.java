package game;

abstract class Spieler {
	
	private String name;
	private int steine;
	private int reihe;
	
	public Spieler(String name, int steine,int reihe) {
		setName(name);
		setSteine(steine);
		setReihe(reihe);
	}
	
	public int getReihe() {
		return reihe;
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
