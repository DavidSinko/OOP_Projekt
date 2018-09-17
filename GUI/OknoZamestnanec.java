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

public class OknoZamestnanec extends JFrame {
	
	private JLabel suma = new JLabel("Cena:");
	private JLabel tovar = new JLabel("Nazov tovaru:");
	private JLabel kusy = new JLabel("ks");
	
	private JTextField sumaW = new JTextField(5);
	private JTextField tovarW = new JTextField(13);
	private JTextField kusyW = new JTextField(2);
	
	private JButton [] button = {new JButton("Naspat"), new JButton("Pridaj"), new JButton("Zoznam produktov")};
	
	private JTextArea vypis = new JTextArea(15, 20);
	private JScrollPane skrolVypis = new JScrollPane(vypis, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	public OknoZamestnanec(Ucet ucet, Zoznam zoznam, ZoznamTovarov tovary) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		add(tovar);
		add(tovarW);
		add(suma);
		add(sumaW);
		add(kusyW);
		add(kusy);
		add(button[1]);	// Pridaj
		add(button[2], BorderLayout.CENTER);	// Zoznam produktov
				
		add(skrolVypis, BorderLayout.CENTER);
		add(button[0], BorderLayout.CENTER);	// Naspat
		
		setSize(260, 420);	//pevna velkost okna
//		pack();	//optimalna velkost okna vzhladom na obsah
		setTitle("Zamestnanec");
		setLocationRelativeTo(null);
		setVisible(true);
		
		button[0].addActionListener(new ActionListener() {	// Naspat
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new OknoPrihlasenie(ucet, zoznam, tovary);
				}
		});
		
		button[1].addActionListener(new ActionListener() {	// Pridaj
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
				String pridaj = tovarW.getText();
				double cislo = Double.parseDouble(sumaW.getText());
				int ks = Integer.parseInt(kusyW.getText());
				for (int i = 0; i < ks; i++) {
					tovary.pridaj(pridaj, cislo);
					}
				zoznam.pridajAkciu("Zamestnanec kupil " + ks + "x " + pridaj + " za " + (cislo*ks) + "€");
				vypis.append(String.format("\nPrave pridany produkt: %s, %sks.\n", pridaj, ks));
				vypis.append(String.format("Z uctu odislo: %s€\n", (cislo*ks)));
				ucet.ubytok(cislo*ks);
				vypis.append("_______________________________");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(OknoZamestnanec.this, "Nevyplnil si vsetky udaje spravne!");
				}
			}
		});
		
		button[2].addActionListener(new ActionListener() {	// Zoznam produktov
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new OknoZoznam(zoznam, tovary);
			}
		});
	}
}