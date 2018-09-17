package GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Classes.Hlavny;
import Classes.Penazenka;
import Classes.Ucet;
import Zoznamy.Zoznam;
import Zoznamy.ZoznamLudi;
import Zoznamy.ZoznamTovarov;

public class OknoRegistracia extends JFrame {
	
	private JLabel prihlasenie = new JLabel("Meno:");
	private JLabel heslo = new JLabel("Heslo:");
	private JLabel plat = new JLabel("Plat:");
	private JLabel penazenka = new JLabel("Penazenka:");
	private JLabel meno = new JLabel("Meno:");
	private JLabel adresa = new JLabel("Adresa:");
	private JLabel ID = new JLabel("ID:");
	
	private JTextField prihlasenieW = new JTextField(11);
	private JTextField hesloW = new JTextField(11);
	private JTextField platW = new JTextField(11);
	private JTextField penazenkaW = new JTextField(11);
	private JTextField menoW = new JTextField(11);
	private JTextField adresaW = new JTextField(11);
	private JTextField IDW = new JTextField(11);
	
	private JButton registracia = new JButton("Registrovat");
	
	public OknoRegistracia(Ucet ucet, Zoznam zoznam, ZoznamTovarov tovary) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		add(prihlasenie);
		add(prihlasenieW);
		
		add(heslo);
		add(hesloW);
//		hesloW.secureTextEntry = true;
		
		add(plat);
		add(platW);
		
		add(penazenka);
		add(penazenkaW);
		
		add(meno);
		add(menoW);
		
		add(adresa);
		add(adresaW);
		
		add(ID);
		add(IDW);
		
		add(registracia);
		
		pack();
		setTitle("Registracia");
		setLocationRelativeTo(null);
		setVisible(true);
		
		registracia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String prihlMeno = prihlasenieW.getText();
				String heslo = hesloW.getText();
				String meno = menoW.getText();
				String adresa = adresaW.getText();
				String ID = IDW.getText();
				double suma = 0;
				double plat = 0;
				try {
					plat = Double.parseDouble(platW.getText());
					suma = Double.parseDouble(penazenkaW.getText());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Zly format cisla");
					return;
				}
				
				Hlavny hlavny = new Hlavny(prihlMeno, heslo, suma, ucet, plat, meno, adresa, ID);
				ZoznamLudi.registruj(hlavny);
				ZoznamLudi.ulozZoznam();
				
				dispose();
				new OknoHlavny("Hlavny", ucet, zoznam, tovary);
			}
		});
	}
}
