package ihm;

public class Melodie {

	private int temps;
	private String note;
	
	public Melodie(int temps, String note) {
		this.temps = temps;
		this.note = note;
	}
	
	public int getTemps() {
		return this.temps;
	}
	
	public String getNote() {
		return this.note;
	}
		
	
}
