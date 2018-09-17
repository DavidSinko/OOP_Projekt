package Zoznamy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

import Classes.Clovek;

/** Zoznam, ktory sluzi na ukladanie zamestnancov a ludi vo firme */
public class ZoznamLudi {
	private static List<Clovek> zoznam = new ArrayList<Clovek>();
	
	public static void nacitajZoznam() {
		ObjectInputStream stream;
		try {
			stream = new ObjectInputStream(new FileInputStream("zoznamludi"));
			zoznam = (List<Clovek>)stream.readObject();
			stream.close();
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static int ziskajPocetUzivatelov() {
		return zoznam.size();
	}
	
	public static void vymazUzivatela(String meno) {
		for (int i = 0; i < zoznam.size(); i++) {
			Clovek c = zoznam.get(i);
			if (c.getMeno().equals(meno)) {
				zoznam.remove(i);
				return;
			}
		}
	}
	
	public static void vypisZoznam(JTextArea vypis) {
        for (int i = 0; i < zoznam.size(); i++) {
        	Clovek c = zoznam.get(i);
            vypis.append(c.getMeno() + ", " + c.getAdresa() + ", " + c.getID() + "\n");
        }
    }
	
	public static void ulozZoznam() {
		ObjectOutputStream stream;
		try {
			stream = new ObjectOutputStream(new FileOutputStream("zoznamludi"));
			stream.writeObject(zoznam);
			stream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Clovek prihlas(String meno, String heslo) {
		for (int i = 0; i < zoznam.size(); i++) {
			Clovek c = zoznam.get(i);
			if (c.getPrihlMeno().equals(meno)) {
				if (c.getHeslo().equals(heslo)) {
					return c;
				}
				return null;
			}
		}
		return null;
	}
	
	public static void registruj(Clovek clovek) {
		zoznam.add(clovek);
	}
}
