package GUI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Classes.Penazenka;
import Classes.Ucet;
import Zoznamy.Zoznam;
import Zoznamy.ZoznamTovarov;

public class OknoFinancnyPoradca extends JFrame {
	
	private JLabel suma = new JLabel("Cena:");
	private JLabel tovar = new JLabel("Nazov tovaru:");
	private JLabel kusy = new JLabel("ks");
	
	private JTextField sumaW = new JTextField(5);
	private JTextField tovarW = new JTextField(13);
	private JTextField kusyW = new JTextField(2);
	
	private JButton [] button = {new JButton("Stav uctu"), new JButton("Naspat"), new JButton("Pridaj"), new JButton("Zoznam produktov")};
	
	private JTextArea vypis = new JTextArea(15, 20);
	private JScrollPane skrolVypis = new JScrollPane(vypis, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	public OknoFinancnyPoradca(String nazov, Ucet ucet, Zoznam zoznam, ZoznamTovarov tovary) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		add(tovar);
		add(tovarW);
		add(suma);
		add(sumaW);
		add(kusyW);
		add(kusy);
		add(button[2]);	// Pridaj
		add(button[0]);	// Stav uctu
		add(button[3]);	// Zoznam produktov
				
		add(skrolVypis, BorderLayout.CENTER);
		add(button[1], BorderLayout.CENTER);	// Naspat
		
		setSize(255, 420);	//pevna velkost okna
//		pack();	//optimalna velkost okna vzhladom na obsah
		setTitle(nazov);
		setLocationRelativeTo(null);
		setVisible(true);
		
		button[0].addActionListener(new ActionListener() {	// Stav uctu
			@Override
			public void actionPerformed(ActionEvent arg0) {
				vypis.append(String.format("\nNa ucte zostava: %s€\n", ucet.getCelkovaSuma()));
				vypis.append("_______________________________");
			}
		});
		
		button[1].addActionListener(new ActionListener() {	// Naspat
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new OknoPrihlasenie(ucet, zoznam, tovary);
				}
		});
		
		button[2].addActionListener(new ActionListener() {	// Pridaj
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
				String pridaj = tovarW.getText();
				double cislo = Double.parseDouble(sumaW.getText());
				int ks = Integer.parseInt(kusyW.getText());
				for (int i = 0; i < ks; i++) {
					tovary.pridaj(pridaj, cislo);
					}
				if ((cislo*ks) <= 2000) {
					zoznam.pridajAkciu("Financny poradca kupil " + ks + "x " + pridaj + " za " + (cislo*ks) + "€");
					vypis.append(String.format("\nPrave pridany produkt: %s, %sks.\n", pridaj, ks));
					vypis.append(String.format("\nZ uctu odislo: %s€\n", cislo*ks));
					ucet.ubytok(cislo*ks);
				}
				else if ((cislo*ks) > 2000) {
					double a = cislo*ks*(0.9);
					zoznam.pridajAkciu("Financny poradca kupil " + ks + "x " + pridaj + " za " + (cislo*ks*(0.9)) + "€ s 10%-nou zlavou");
					vypis.append(String.format("\nPrave pridany produkt: %s, %sks.\n", pridaj, ks));
					vypis.append("S narokom na 10%-nu zlavu.");
					vypis.append(String.format("\nZ uctu odislo: %.7s€\n", a));
					ucet.ubytok(cislo*ks*(0.9));
				}
//				FinancnyPoradca fin = 
				vypis.append("_______________________________");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(OknoFinancnyPoradca.this, "Nevyplnil si vsetky udaje spravne!");
				}
			}
		});
		
		button[3].addActionListener(new ActionListener() {	// Zoznam produktov
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new OknoZoznam(zoznam, tovary);
				}
		});
	}
	
}
