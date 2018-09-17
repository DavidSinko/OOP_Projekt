package Classes;

/** Zamestnanec dedi od abstraktnej triedy Clovek */
public class Zamestnanec extends Clovek implements Interface {	
	
	protected double plat;
	
	public Zamestnanec(String prihlMeno, String heslo, double plat, double suma, Ucet ucet, String meno, String adresa, String ID) {
		super(prihlMeno, heslo, suma, ucet, meno, adresa, ID);
		this.plat = plat;
	}
	
	/** Metoda pre nakup tovaru pre firmu,
	 *  kde sa odrata suma z uctu firmy */
	@Override
	public void nakup(double cena) {
		ucet.ubytok(cena);
	}
	
	/** Vypis vsetkych udajov o zamestnancovi */
	@Override
	public String toString() {
		return meno + ", " + adresa + ", " + ID + " s platom " + plat;
	}
}
