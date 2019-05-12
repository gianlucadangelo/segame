package game;

abstract class Spieler {
	
	private String name;
	private int steine;
	
	
	public Spieler(String name, int steine) {
		setName(name);
		setSteine(0);
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
