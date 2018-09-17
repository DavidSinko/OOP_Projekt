package Classes;

/** Trieda financneho poradcu, ktory dedi od zamestnanca */
public class FinancnyPoradca extends Zamestnanec implements Interface {
	
	public double bonus;
	
	public FinancnyPoradca(String prihlMeno, String heslo, double suma, Ucet ucet, double plat, String meno, String adresa, String ID) {
		super(prihlMeno, heslo, plat, suma, ucet, meno, adresa, ID);
	}
	
	/** 
	 * Financny poradca touto metodou kontroluje sumu na ucte firmy 
	 * @param ucet metoda prijima typ ucet
	 * */
	public void skontrolujUcet(Ucet ucet) {
		ucet.vypis();
	}
	
	/** Nakup tovaru pre firmu */
	@Override
	public void nakup(double cena) {
		if(cena > 2000) {
			ucet.ubytok(cena * (0.9));
		}
		else {
			ucet.ubytok(cena);
		}
	}

}