package Classes;

/** Zakaznik dedi od abstraktnej triedy Clovek; moze nakupovat tovar */
public class Zakaznik extends Clovek {
	
	public Zakaznik(String prihlMeno, String heslo, double suma, Ucet ucet, String meno, String adresa, String ID) {
		super(prihlMeno, heslo, suma, ucet, meno, adresa, ID);
		penazenka.suma = 500;
	}
	
	/** Nakup tovaru od firmy */
	@Override
	public void nakup(double cena) {
		 penazenka.ubytok(cena);
		 ucet.prirastok(cena);
	}
}
