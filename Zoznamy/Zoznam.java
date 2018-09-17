package Zoznamy;
import java.util.ArrayList;

import javax.swing.JTextArea;

/** Zoznam sluziaci pre ukladanie vykonanych akcii - historia vykonanych akcii */
public class Zoznam {	// Zaznamy o akciach
	
	public ArrayList<String> zaznamy;
	
	public Zoznam() {
		zaznamy = new ArrayList<String>();
	}
	
	public void pridajAkciu(String akcia) {
	    zaznamy.add(akcia);
    }
	
	public void vypisZoznamAkcii(JTextArea vypis) {
        for (int i = 0; i < zaznamy.size(); i++)
            vypis.append(zaznamy.get(i) + "\n");
    }
	
	public int zistiPocet() {
		int a = 0;
		for (int i = 0; i < zaznamy.size(); i++) {
			a++;
		}
		return a;
	}

/*	
	public int vyhladajTovar(String nazov) {
		int i=0;
            for (i = 0; i < zaznamy.size(); i++) {
            	if (zaznamy.get(i) == nazov)
            		return i;
            	break;
            }
                return i;
    }
	
	public void vymazTovar(String nazov) {
		for (int i = 0; i < zaznamy.size(); i++)
		zaznamy.remove(nazov);
	}
*/
}
