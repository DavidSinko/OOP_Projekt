package GUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Zoznamy.Zoznam;
import Zoznamy.ZoznamTovarov;

import java.util.*;

public class OknoZoznam extends JFrame {

/*	
	JList list;
	DefaultListModel model;
	int counter = 15;
*/
	
//	private JTextField pridajW = new JTextField(10);
//	private JTextField cenaW = new JTextField(7);
	
	private JButton button = new JButton("Zatvorit");
	private JButton kup = new JButton("Kupit");
//	private JButton addButton = new JButton("Add");
//	private JButton removeButton = new JButton("Remove");
	
	private JTextArea vypis = new JTextArea(15, 33);
	private JScrollPane skrolVypis = new JScrollPane(vypis, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	
	public OknoZoznam(Zoznam zoznam, ZoznamTovarov tovary) {
		
/*		
		setLayout(new BorderLayout());
	    model = new DefaultListModel();
	    list = new JList(model);
	    JScrollPane pane = new JScrollPane(list);
	    JButton addButton = new JButton("Add Element");
	    JButton removeButton = new JButton("Remove Element");
	    for (int i = 0; i < 15; i++)
	      model.addElement("Element " + i);

	    add(pane, BorderLayout.NORTH);
	    add(addButton, BorderLayout.WEST);
	    add(removeButton, BorderLayout.EAST);
	    
//	    setContentPane();
	  }
*/	  
		
//		JPanel listPane = new JPanel();
		
		JList list = new JList(tovary.getModel());
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
		setLayout(new FlowLayout());
		
		setTitle("Zoznam Produktov");
		
//		add(skrolVypis);
		add(new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		vypis.setEditable(false);
//		add(pridajW);
//		add(cenaW);
//		add(addButton);
//		add(removeButton);
		add(button);
		
//		Box box = Box.createVerticalBox();
//		button.setAlignmentX(Component.LEFT_ALIGNMENT);
//		box.add(button);
//		add(box, BorderLayout.WEST);
		
//		zoznam.vypisZoznamTovarov(vypis);
//		tovary.vypisTovary(vypis);
		
		setSize(400, 260);
//		pack();
		setVisible(true);
//		setDefaultLookAndFeelDecorated(true);
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

/*
		addButton.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  String dd = pridajW.getText();
		    	  Double cena = Double.parseDouble(cenaW.getText());
		    	  tovary.pridaj(dd, cena);
		      }
		    });
		    
		removeButton.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  tovary.remove(list.getSelectedIndex());
//		    	  System.out.println(list.getSelectedIndex());
		      }
		    });
*/
	
	}
}
