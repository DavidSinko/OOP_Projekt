package Classes;

import java.io.Serializable;

/** Ucet - je to ucet celej firmy */
public class Ucet implements Serializable {
	
	protected double celkovaSuma = 10000;
	protected double cena;
	
	public Ucet(double celkovaSuma) {
		this.celkovaSuma = celkovaSuma;
	}
	
	public double getCelkovaSuma() {
		return celkovaSuma;
	}
	
	public double ubytok(double cena) {
		return celkovaSuma -= cena;
	}
	
	public double prirastok(double cena) {
		return celkovaSuma += cena;
	}
	
	public void vypis() {
		System.out.printf("Na ucte je momentalne: %s€.\n", celkovaSuma);
	}

}
