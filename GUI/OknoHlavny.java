package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Classes.Clovek;
import Classes.FinancnyPoradca;
import Classes.Penazenka;
import Classes.Ucet;
import Classes.Zamestnanec;
import Zoznamy.Zoznam;
import Zoznamy.ZoznamLudi;
import Zoznamy.ZoznamTovarov;

public class OknoHlavny extends JFrame {

	private JLabel suma = new JLabel("Cena:");
	private JLabel tovar = new JLabel("Nazov tovaru:");
	private JLabel kusy = new JLabel("ks");
	private JLabel zames = new JLabel("Prihlasovacie meno, heslo, meno, adresa, cislo obcianskeho, plat, penazenka:");
	private JLabel vyplata = new JLabel("Vyska vyplaty:");

	private JTextField sumaW = new JTextField(5);
	private JTextField tovarW = new JTextField(13);
	private JTextField kusyW = new JTextField(2);
	private JTextField prihlMenoW = new JTextField(10);
	private JTextField hesloW = new JTextField(10);
	private JTextField menoW = new JTextField(10);
	private JTextField adresaW = new JTextField(10);
	private JTextField IDW = new JTextField(10);
	private JTextField vyplataW = new JTextField(40);
	private JTextField platW = new JTextField(5);
	private JTextField penazenkaW = new JTextField(5);
	private JComboBox<String> typZamestnanca = new JComboBox<>();

	private JButton[] button = { new JButton("Stav uctu"), new JButton("Naspat"), new JButton("Pridaj"),
			new JButton("Zoznam produktov"), new JButton("Zamestnaj"), new JButton("Zoznam zamestnancov"),
			new JButton("Skry"), new JButton("Vyplata"), new JButton("Prepusti"),
			new JButton("Historia vykonanych akcii") };

	private JTextArea vypis = new JTextArea(10, 37);
	private JScrollPane skrolVypis = new JScrollPane(vypis, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JTextArea vypis1 = new JTextArea(7, 37);

	public OknoHlavny(String nazov, Ucet ucet, Zoznam zoznam, ZoznamTovarov tovary) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new FlowLayout());
		
		typZamestnanca.addItem("Zamestnanec");
		typZamestnanca.addItem("Financnik");

		add(tovar);
		add(tovarW);
		add(suma);
		add(sumaW);
		add(kusyW);
		add(kusy);
		add(button[2]); // Pridaj
		add(zames);
		add(prihlMenoW);
		add(hesloW);
		add(menoW);
		add(adresaW);
		add(IDW);
		add(platW);
		add(penazenkaW);
		add(typZamestnanca);
		add(button[4]); // Zamestnaj
		add(button[0]); // Stav uctu
		add(button[3]); // Zoznam produktov
		add(button[5]); // Zoznam zamestnancov
		add(button[9]); // Historia vykonanych akcii

		add(skrolVypis, BorderLayout.CENTER);
		add(button[1], BorderLayout.CENTER); // Naspat

		setSize(480, 620);
		// pack();
		setTitle(nazov);
		setLocationRelativeTo(null);
		setVisible(true);

		button[0].addActionListener(new ActionListener() { // Stav uctu
			@Override
			public void actionPerformed(ActionEvent arg0) {
				vypis.append(String.format("\nNa ucte zostava: %s€\n", ucet.getCelkovaSuma()));
				vypis.append("_____________________________________");
			}
		});

		/** Tlacidlo pre pridavanie (kupovanie )produktov do zoznamu */
		button[2].addActionListener(new ActionListener() { // Pridaj
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String pridaj = tovarW.getText();
					double cislo = Double.parseDouble(sumaW.getText());
					int ks = Integer.parseInt(kusyW.getText());
					for (int i = 0; i < ks; i++) {
						tovary.pridaj(pridaj, cislo);
					}
					if ((cislo * ks) <= 2000) {
						zoznam.pridajAkciu("Nakup " + ks + "x " + pridaj + " za " + (cislo * ks) + "€");
						vypis.append(String.format("\nPrave pridany produkt: %s, %sks.\n", pridaj, ks));
						vypis.append(String.format("\nZ uctu odislo: %s€\n", (cislo * ks)));
						ucet.ubytok(cislo * ks);
					} else if ((cislo * ks) > 2000) {
						zoznam.pridajAkciu(
								"Nakup " + ks + "x " + pridaj + " za " + (cislo * ks * (0.8)) + "€ s 20%-nou zlavou");
						vypis.append(String.format("\nPrave pridany produkt: %s, %sks.\n", pridaj, ks));
						vypis.append("S narokom na 20%/nu zlavu.");
						vypis.append(String.format("\nZ uctu odislo: %s€\n", (cislo * ks * (0.8))));
						ucet.ubytok(cislo * ks * (0.8));
					}
					vypis.append("_____________________________________");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(OknoHlavny.this, "Nevyplnil si vsetky udaje spravne!");
				}
			}
		});

		button[1].addActionListener(new ActionListener() { // Naspat
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new OknoPrihlasenie(ucet, zoznam, tovary);
			}
		});

		button[3].addActionListener(new ActionListener() { // Zoznam produktov
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new OknoZoznam(zoznam, tovary);
			}
		});

		/** Tlacidlo na zamestnanie novych zamestnancov */
		button[4].addActionListener(new ActionListener() { // Zamestnaj
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String prihlMeno = prihlMenoW.getText();
					String heslo = hesloW.getText();
					String meno = menoW.getText();
					String adresa = adresaW.getText();
					String ID = IDW.getText();
					double plat = 0;
					double penazenka = 0;
					try {
						plat = Double.parseDouble(platW.getText());
						penazenka = Double.parseDouble(penazenkaW.getText());
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Zadali ste cislo v nespravnom formate");
						return;
					}

					Clovek c;
					if (typZamestnanca.getSelectedIndex() == 0) {
						c = new Zamestnanec(prihlMeno, heslo, plat, penazenka, ucet, meno, adresa, ID);
					} else if (typZamestnanca.getSelectedIndex() == 1) {
						c = new FinancnyPoradca(prihlMeno, heslo, penazenka, ucet, plat, meno, adresa, ID);
					} else {
						JOptionPane.showMessageDialog(null, "Vybrali ste zly typ zamestnanca");
						return;
					}
					ZoznamLudi.registruj(c);
					ZoznamLudi.ulozZoznam();
					zoznam.pridajAkciu("Zamestnany " + meno);
					vypis.append(String.format("\nPrave ste zamestnali osobu:\n%s\n", c));
					vypis.append("_____________________________________");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(OknoHlavny.this, "Nevyplnil si udaje o zamestnancovi!");
				}
			}
		});

		button[5].addActionListener(new ActionListener() { // Zoznam zamestnancov
			@Override
			public void actionPerformed(ActionEvent arg0) {
				add(vypis1);
				vypis1.setVisible(true);
				vypis1.setText("");
				ZoznamLudi.vypisZoznam(vypis1);
				add(button[6]); // Skry
				button[6].setVisible(true);
				add(vyplataW);
				vyplataW.setVisible(true);
				add(button[7]); // Vyplata
				button[7].setVisible(true);
				add(button[8]); // Prepusti
				button[8].setVisible(true);
			}
		});

		button[6].addActionListener(e -> { // Skry Lambda vyraz
			vypis1.setText(null);
			vypis1.setVisible(false);
			button[6].setVisible(false);
			button[7].setVisible(false);
			button[8].setVisible(false);
			vyplataW.setVisible(false);
		});

		button[7].addActionListener(e -> { // Vyplata Lambda vyraz
			try {
				int vyplata = Integer.parseInt(vyplataW.getText());
				ucet.ubytok(vyplata * ZoznamLudi.ziskajPocetUzivatelov());
				zoznam.pridajAkciu("Vyplatenych " + ZoznamLudi.ziskajPocetUzivatelov() + " zamestnancov v celkovej hodnote "
						+ vyplata * ZoznamLudi.ziskajPocetUzivatelov() + "€");
				vypis.append(String.format("\nPrave ste vyplatili %s zamestnancov.\n", ZoznamLudi.ziskajPocetUzivatelov()));
				vypis.append(String.format("\nZ uctu odislo: %s€\n", (vyplata * ZoznamLudi.ziskajPocetUzivatelov())));
				vypis.append("_____________________________________");
			} catch (Exception a) {
				JOptionPane.showMessageDialog(OknoHlavny.this, "Zadaj vysku vyplaty!");
			}
		});

		button[8].addActionListener(new ActionListener() { // Prepusti
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String vz = vyplataW.getText();
					// if (!vz.isEmpty() && vz.equals()) {
					ZoznamLudi.vymazUzivatela(vz);
					zoznam.pridajAkciu("Prepusteny " + vz);
					vypis.append(String.format("\nPrave ste prepustili zamestnanca:\n%s", vz));
					vypis.append("\n_____________________________________");
				}
				// }
				catch (Exception e) {
					JOptionPane.showMessageDialog(OknoHlavny.this, "Zadaj meno zamestnanca!");
				}
			}
		});

		button[9].addActionListener(new ActionListener() { // Historia vykonanych akcii
			@Override
			public void actionPerformed(ActionEvent arg0) {
				vypis.append("\nZoznam doteraz vykonanych akcii:\n\n");
				if (zoznam.zistiPocet() == 0)
					vypis.append("Zatial neboli vykonane ziadne akcie.\n");
				else {
				zoznam.vypisZoznamAkcii(vypis);
				}
				vypis.append("_____________________________________");
			}
		});
	}
}
