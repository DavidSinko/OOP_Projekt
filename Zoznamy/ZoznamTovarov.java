package Zoznamy;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JTextArea;
import javax.swing.ListModel;

/** Zoznam uklada tovar ponukany firmou */
public class ZoznamTovarov {
	
	public DefaultListModel<String> tovar;
	
	public ZoznamTovarov() {
		tovar = new DefaultListModel<>();
	}
	
	/** Prida novy tovar do kolekcie
	 * @param nazovTovaru	nazov tvaru, ktory pridavam
	 * @param cena		cena pridavaneho tovaru
	 * */
	public void pridaj(String nazovTovaru, double cena) {
        tovar.addElement(nazovTovaru);
        tovar.addElement(String.valueOf(cena));
    }
	
	/** Vrati datovy model pre Swing komponenty
	 * @return tovar	vraciam tovar zo zoznamu
	 * */
	public ListModel getModel() {
	        return tovar;
	    }
	 
	/** Vrati zoznam tovarov
	 * @return Collections.list(tovar.elements())	vraciam kolekciu, zoznam
	 * */
	public List<String> getTovar() {
	        return Collections.list(tovar.elements());
	    }
	
	/** Vypise tovary v kolekcii
	 * @param vypis		pre vypis textu v okne
	 * */
	public void vypisTovary(JTextArea vypis) {
		for (int i = 0; i < tovar.size(); i++)
            vypis.append(tovar.getElementAt(i)+"\n");		
	}
	
	/** Odoberie tovar
	 * @param index		index prijimany z kliknutej polozky
	 * */
	public void remove(int index) {
		tovar.removeElementAt(index);
		tovar.removeElementAt(index);
		
	}
	
	/** Berie cenu ktora prislucha kliknutemu tovaru
	 * @param index		index z kliknutej polozky
	 * @return tovar.getElementAt(index + 1)	vraciam cenu tovaru pod kliknutym produktom
	 * */
	public String getCena(int index) {
		return tovar.getElementAt(index + 1);
	}
	
	/** Berie kliknuty produkt
	 * @param index		index z kliknutej polozky
	 * @return tovar.getElementAt(index)	beriem tovar na ktory kliknem
	 * */
	public String getProdukt(int index) {
		return tovar.getElementAt(index);
	}
}
