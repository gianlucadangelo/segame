package game;

public class KI extends Spieler{

	private boolean schwer;
	public KI(String name, int steine,boolean mode) {
		super(name, steine);
		this.setSchwer(mode);
	}
	public boolean isSchwer() {
		return schwer;
	}
	public void setSchwer(boolean schwer) {
		this.schwer = schwer;
	}

	
	
	

}
