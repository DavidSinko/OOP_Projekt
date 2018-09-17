package Classes;

import java.io.Serializable;

/** Abstraktna trieda od ktorej dedia ostatne typy uzivatelov */
abstract public class Clovek implements Interface, Serializable {
	protected String prihlMeno;
	protected String heslo;
	
	protected String meno;
	protected String adresa;
	protected String ID;
	
	protected Penazenka penazenka;		// agregacia
	protected Ucet ucet;
	
	public Clovek(String prihlMeno, String heslo, double suma, Ucet ucet, String meno, String adresa, String ID) {
		this.prihlMeno = prihlMeno;
		this.heslo = heslo;
		this.penazenka = new Penazenka(suma);
		this.ucet = ucet;
		this.meno = meno;
		this.adresa = adresa;
		this.ID = ID;
	}
	
	/** @return Metoda, ktora varcia meno zamestnanca - atribut typu string */
	public String getMeno() {
		return meno;
	}
	
	/** @return Metoda, ktora varcia adresu zamestnanca - atribut typu string */
	public String getAdresa() {
		return adresa;
	}
	
	/** @return Metoda, ktora varcia ID zamestnanca - atribut typu string */
	public String getID() {
		return ID;
	}
	
	/** @return Metoda, ktora varcia prihlasovacie meno - atribut typu string */
	public String getPrihlMeno() {
		return prihlMeno;
	}
	
	/** @return Metoda, ktora varcia heslo - atribut typu string */
	public String getHeslo() {
		return heslo;
	}

}
