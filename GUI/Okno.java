package GUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Classes.Penazenka;
import Classes.Ucet;
import Zoznamy.Zoznam;
import Zoznamy.ZoznamLudi;
import Zoznamy.ZoznamTovarov;

public class Okno extends JFrame {

		private JButton [] button = {new JButton("Hlavny"), new JButton("Financny Poradca"), new JButton("Zamestnanec"), new JButton("Zakaznik")};

		public Okno(Ucet ucet, Zoznam zoznam, ZoznamTovarov tovary) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // inak sa okno len skryje - platí aj WindowConstants.EXIT_ON_CLOSE
			setLayout(new FlowLayout());

			add(button[0]); //talcido Hlavny
			add(button[1]); //tlacidlo Financny Poradca
			add(button[2]); //tlacidlo Zamestnanec
			add(button[3]); // tlacidlo Zakaznik

			pack(); // zabezpeci optimalnu velkost okna vzhladom na obsah

		    setLocationRelativeTo(null); // otvori okno v strede obrazovky
			setVisible(true); // dolezite aby sme videli okno
		
			button[0].addActionListener(new ActionListener() {	// Hlavny
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new OknoHlavny("Hlavny", ucet, zoznam, tovary);
//					new OknoPrihlasenie(penazenka, ucet, zoznam, zoznamlud, tovary);
				}
			});
			
			button[1].addActionListener(new ActionListener() {	// Financny Poradca
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new OknoFinancnyPoradca("FinancnyPoradca", ucet, zoznam, tovary);
					}
			});
			
			button[2].addActionListener(new ActionListener() {	// Zamestnanec
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new OknoZamestnanec(ucet, zoznam, tovary);
					}
			});

			button[3].addActionListener(new ActionListener() {	// Zakaznik
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new OknoZakaznik("Zakaznik", ucet, zoznam, tovary);
					}
			});
				
		}
}