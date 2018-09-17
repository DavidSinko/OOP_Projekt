package Classes;
import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import GUI.Okno;
import GUI.OknoPrihlasenie;
import GUI.OknoRegistracia;
import Zoznamy.Zoznam;
import Zoznamy.ZoznamLudi;
import Zoznamy.ZoznamTovarov;

public class Main {
	
	public static void main(String[] args) {
		ZoznamLudi.nacitajZoznam();
		
		Penazenka penazenka = new Penazenka(500);
		Ucet ucet = new Ucet(500);
		ZoznamTovarov tovary = new ZoznamTovarov();
		Zoznam zoznam = new Zoznam();
		
//		Hlavny sef = new Hlavny("admin", "tuadmin", penazenka, ucet, 800, "Karol", "Skalska 5", "EB486347");
		
		tovary.tovar.addElement("________________________________________________");
		tovary.tovar.addElement("Canon EOS 1200D");
		tovary.tovar.addElement("402.15");
		tovary.tovar.addElement("Nikon D5200");
		tovary.tovar.addElement("439.00");
		tovary.tovar.addElement("Nikon D5200");
		tovary.tovar.addElement("439.00");
		tovary.tovar.addElement("Canon EOS 70D");
		tovary.tovar.addElement("935.25");
		tovary.tovar.addElement("Canon 10-18mm f/4,5-5,6 IS STM");
		tovary.tovar.addElement("224.90");
		tovary.tovar.addElement("Nikon AF-S 18-105mm f/3,5-5,6G DX ED VR");
		tovary.tovar.addElement("262.24");
		
//		ZoznamLudi.registruj(sef);
		
//		new Okno(penazenka, ucet, zoznam, zoznamlud, tovary);
		if (ZoznamLudi.ziskajPocetUzivatelov() == 0) {
			new OknoRegistracia(ucet, zoznam, tovary);
		} else {
			new OknoPrihlasenie(ucet, zoznam, tovary);
		}

	}
}
