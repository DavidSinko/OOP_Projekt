package Classes;

import java.io.Serializable;

/** Trieda Penazenka uchovava v sebe hodnotu sumy v penazenke roznych ludi */
public class Penazenka implements Serializable {
	
	public static final double MIN_ZAKAZNIKA = 500.0;
	public static final double MAX_ZAKAZNIKA = 2000.0;
	
	protected double suma = 1000;
	protected double cena;
	
	public Penazenka(double suma) {
		this.suma = suma;
	}
	
	/** Vraciam sumu v penazenke
	 * @return suma		suma v penazenke
	 * */
	public double getSuma() {
		return suma;
	}
	
	/** Vraciam sumu po odratani z celkovej sumy
	 * @return suma		suma v penazenke po odratani
	 * @param cena		cena, ktoru odratavam od celkovej sumy v penazenke
	 * */
	public double ubytok(double cena) {
		return suma -= cena;
	}
	
	public double prirastok(double cena) {
		return suma += cena;
	}
	
	public void vypis() {
		System.out.printf("V penazenke ostava: %s.\n", suma);
	}
	
	public double konverziaEuroDolar() {
		return suma *= 1.14;
	}
	
	public double kovnerziaDolarEuro() {
		return suma *= 0.88;
	}

}
