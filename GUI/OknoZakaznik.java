package GUI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Classes.Penazenka;
import Classes.Ucet;
import Zoznamy.Zoznam;
import Zoznamy.ZoznamTovarov;

public class OknoZakaznik extends JFrame {
	
	private JLabel suma = new JLabel("Cena:");
	private JLabel tovar = new JLabel("Nazov tovaru:");
	private JLabel kusy = new JLabel("ks");
	
	private JTextField sumaW = new JTextField(5);
	private JTextField tovarW = new JTextField(13);
	private JTextField kusyW = new JTextField(2);
	
	private JButton [] button = {new JButton("Kupit"), new JButton("Suma v penazenke"), new JButton("Naspat")};
	
	private JTextArea vypis = new JTextArea(14, 32);
	private JScrollPane skrolVypis = new JScrollPane(vypis, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	public OknoZakaznik(String nazov, Ucet ucet, Zoznam zoznam, ZoznamTovarov tovary) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		Penazenka penazenka = new Penazenka(Math.random() * (Penazenka.MAX_ZAKAZNIKA - Penazenka.MIN_ZAKAZNIKA) + Penazenka.MIN_ZAKAZNIKA);
				
		add(skrolVypis, BorderLayout.CENTER);
		
		add(button[1]);	// Suma v penazenke
		
		JList list = new JList(tovary.getModel());
		add(new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		
		add(button[0]);	// Kupit
		add(button[2], BorderLayout.CENTER);	// Naspat
		
		setSize(400, 520);	//pevna velkost okna
//		pack();	//optimalna velkost okna vzhladom na obsah
		setTitle(nazov);
		setLocationRelativeTo(null);
		setVisible(true);
		
		button[0].addActionListener(new ActionListener() {	// Kupit
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
				double cena = Double.parseDouble(tovary.getCena(list.getSelectedIndex()));
					if (penazenka.getSuma() - cena >= 0) {
				penazenka.ubytok(cena);
				ucet.prirastok(cena);
				zoznam.pridajAkciu("Predany produkt " + tovary.getProdukt(list.getSelectedIndex()));
				vypis.append(String.format("\nKupil si:\n%s", tovary.getProdukt(list.getSelectedIndex())));
				vypis.append(String.format(" za %s", cena + "€\n"));
				vypis.append(String.format("\nZ penazenky odislo: %s€\n", cena));
				tovary.remove(list.getSelectedIndex());
				vypis.append("_______________________________");
					}
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(OknoZakaznik.this, "Nemas dostatok penazi!");
				}
			}
		});
		
		button[1].addActionListener(new ActionListener() {	// Suma v penazenke
			@Override
			public void actionPerformed(ActionEvent arg0) {
				vypis.append(String.format("\nV penazenke ti zostava %s€\n", penazenka.getSuma()));
				vypis.append("_______________________________");
			}
		});
		
		button[2].addActionListener(new ActionListener() { // Naspat
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new OknoPrihlasenie(ucet, zoznam, tovary);
				}
		});
	}
	

}
