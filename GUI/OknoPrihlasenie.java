package GUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Classes.Clovek;
import Classes.FinancnyPoradca;
import Classes.Hlavny;
import Classes.Penazenka;
import Classes.Ucet;
import Classes.Zamestnanec;
import Zoznamy.Zoznam;
import Zoznamy.ZoznamLudi;
import Zoznamy.ZoznamTovarov;

import java.util.*;

public class OknoPrihlasenie extends JFrame {
	
//	Clovek clovek = new Clovek("admin", "admin");
	
	private JLabel prihlasenie = new JLabel("Meno:");
	private JLabel heslo = new JLabel("Heslo:");
	
	private JTextField prihlasenieW = new JTextField(11);
	private JPasswordField hesloW = new JPasswordField(11);
	
	private JButton [] button = {new JButton("Prihlasit"), new JButton("Zakaznik")};
	
	public OknoPrihlasenie(Ucet ucet, Zoznam zoznam, ZoznamTovarov tovary) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		add(prihlasenie);
		add(prihlasenieW);
		
		add(heslo);
		add(hesloW);
		
		add(button[0]);	// Prihlasit
		add(button[1]);	// Zakaznik
		
		setSize(190, 160);
		setTitle("Prihlasenie");
		setLocationRelativeTo(null);
		setVisible(true);
		
		button[1].addActionListener(new ActionListener() {	// Spat
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new OknoZakaznik("Zakaznik", ucet, zoznam, tovary);
				dispose();
			}
		});
		
		/** Tlacidlo pre prihlasenie (overenie prihlasovacich udajov) */
		button[0].addActionListener(new ActionListener() {	// Prihlasit
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String login = prihlasenieW.getText();
				String password = hesloW.getText();
				
				Clovek prihlasenyClovek = ZoznamLudi.prihlas(login, password);
				if (prihlasenyClovek != null) {
					if (prihlasenyClovek instanceof Hlavny) {
						new OknoHlavny("Hlavny", ucet, zoznam, tovary);
					} else if (prihlasenyClovek instanceof FinancnyPoradca) {
						new OknoFinancnyPoradca("FinancnyPoradca", ucet, zoznam, tovary);
					} else if (prihlasenyClovek instanceof Zamestnanec) {
						new OknoZamestnanec(ucet, zoznam, tovary);
					}
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(OknoPrihlasenie.this, "Nespravne meno alebo heslo!", "Prihlasenie", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

}
