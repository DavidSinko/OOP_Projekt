package Classes;

/** Hlavny - nejaky sef, ktory spravuje celu firmu */
public class Hlavny extends FinancnyPoradca implements Interface {
//	protected String ucty[][] = {{"admin", "admin"}};
	
	public Hlavny(String prihlMeno, String heslo, double suma, Ucet ucet, double plat, String meno, String adresa, String ID) {
		super(prihlMeno, heslo, suma, ucet, plat, meno, adresa, ID);
	}
	
	@Override
	public void nakup(double cena) {
		if(cena > 2000) {
			ucet.ubytok(cena * (0.8));
		}
		else {
			ucet.ubytok(cena);
		}
	}
	
	/** Metoda na vyplatenie zamestnancov 
	 * @param cena	cena je vyska vyplaty pre zamestnanca
	 * @param ucet	spolocny firemny ucet
	 * @param penazenka	  penazenka zamestnanca
	 *  */
	public void vyplatZames(double cena, Ucet ucet, Penazenka penazenka) {
		ucet.ubytok(cena);
	}
	
	/** Pri prihlasovani overuje spravnost zadaneho mena a potom aj hesla
	 * @return vrati true ak overovanie prebehlo spravne, false ak nespravne
	 * @param prihlMeno		prihlasovacie meno sefa pre vstup do programu
	 * @param heslo		heslo sefa pre vstup do programu
	 * */
	public static boolean overenie(String prihlMeno, String heslo) {
		if ((prihlMeno.equals("admin")) && (heslo.equals("tuadmin"))) {
			System.out.println("Spravne");
			return true;
		}
		System.out.println("Nespravne");
		return false;
	}

}
